package at.fabianachammer.pdusend.dsl

import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.pdu.RawDataUnit
import at.fabianachammer.pdusend.util.BitOperator

/**
 * Class that resolves arguments of an pdu object.
 * @author fabian
 *
 */
class ArgumentResolver {

	private Objects objects
	
	/**
	 * 
	 * @param objects
	 */
	ArgumentResolver(Objects objects){
		this.objects = objects
	}
	
	/**
	 * represents the possible number types in the pdusend dsl.
	 */
	private final def allowedNumberTypes = [
		Byte.class,
		Short.class,
		Integer.class,
		Long.class,
		BigInteger.class
	]

	/**
	 * resolves a method returning a pdu without any arguments or a closure.
	 */
	final Closure resolveNoArguments = {Object key ->
		objects.binding[key]()
	}

	/**
	 * resolves the given map arguments of an method that returns a pdu.
	 */
	final Closure resolveMapArguments = {Object key, Map map ->
		def pdu = resolveNoArguments(key)

		map.keySet().each{
			def input = map[it]
			def fieldClass = pdu."$it".class

			if(input.class == fieldClass){
				pdu."$it" = input
			}

			else if(input.class == byte[].class){
				if(allowedNumberTypes.any{it.class == fieldClass}){
					pdu."$it" = BitOperator.merge input
				}
				else if(pdu."$it" instanceof DataUnit){
					pdu."$it" = pdu."$it".decoder.decode input
				}
			}

			else if(input instanceof DataUnit){
				if(fieldClass == byte[].class){
					pdu."$it" = input.encode
					pdu
				}

				else{
					throw new IllegalArgumentException(input + " cannot be converted to "+ fieldClass)
				}
			}

			else if(allowedNumberTypes.any{n -> n == input.class}){
				if(fieldClass == byte[].class){
					pdu."$it" = BitOperator.split(input as BigInteger, pdu."$it".length)
				}

				else if(pdu."$it" instanceof DataUnit){
					byte[] b = BitOperator.split(input as BigInteger)
					pdu."$it" = pdu."$it".decoder.decode b
				}
			}

			else{
				throw new IllegalArgumentException(input + " cannot be converted to " + fieldClass)
			}
		}

		pdu
	}

	/**
	 * resolves a closure for a method returning a pdu. The closure represents an embedded data unit.
	 */
	final Closure resolveClosure = {Object key, Closure c ->
		def pdu = resolveNoArguments(key)

		def dataResult = c()
		
		if(dataResult instanceof DataUnit){
			pdu.embeddedData = dataResult
		}

		else if(dataResult instanceof byte[]){
			pdu.embeddedData = new RawDataUnit(dataResult)
		}

		else if(allowedNumberTypes.any{it == dataResult.class}){
			pdu.embeddedData = new RawDataUnit(BitOperator.split(dataResult))
		}

		else{
			throw new IllegalArgumentException(dataResult + " cannot be embedded in " + pdu)
		}

		pdu
	}

	/**
	 * resolves the given map arguments and closure for a method returning a pdu.
	 * The closure represents an embedded data unit
	 */
	final Closure resolveClosureWithArguments = {Object key, Map map, Closure c ->
		def pdu = resolveMapArguments key, map
		def pduWithEmbeddedData = resolveClosure key, c

		pdu.embeddedData = pduWithEmbeddedData.embeddedData

		pdu
	}
	
}

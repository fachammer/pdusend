package at.fabianachammer.pdusend.dsl

import java.net.NetworkInterface;

import at.fabianachammer.pdusend.Sender
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.pdu.ArpPacket
import at.fabianachammer.pdusend.type.pdu.EmbeddingProtocolDataUnit
import at.fabianachammer.pdusend.type.pdu.EthernetFrame
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit
import at.fabianachammer.pdusend.type.pdu.RawDataUnit
import at.fabianachammer.pdusend.type.pdu.VlanTag
import at.fabianachammer.pdusend.util.BitOperator

/**
 * contains the methods that can be used by users of the pdusend dsl.
 * @author fabian
 *
 */
class Vocabulary {
	
	final def allowedNumberTypes = [
		Byte.class,
		Short.class,
		Integer.class,
		Long.class,
		BigInteger.class
	]

	final def resolveMapArguments = {Object key, Map map ->
		def pdu = pduObjects[key]()

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
					throw new IllegalArgumentException(input + " cannot be converted to "+ fieldClass.toString())
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
				throw new IllegalArgumentException(input + " cannot be converted to "+ fieldClass.toString())
			}
		}

		pdu
	}

	final def resolveClosure = {Object key, Map map, Closure c ->
		def pdu = resolveMapArguments key, map

		def dataResult = c()

		if(dataResult instanceof DataUnit){
			pdu.embeddedData = dataResult
		}

		else if(dataResult instanceof byte[]){
			pdu.embeddedData = new RawDataUnit(dataResult)
		}

		else if(allowedNumberTypes.any{it.class == dataResult.class}){
			pdu.embeddedData = new RawDataUnit(BitOperator.split(dataResult))
		}

		pdu
	}

	def networkInterfaces = [:]

	def pduObjects = [:]

	def pduMethods = [:]

	private Map getNetworkInterfaceBinding(){
		def netIfs = [:]
		NetworkInterface.networkInterfaces.each{ netIfs."$it.name" = it }

		netIfs
	}

	private Map getPduBinding(){
		[
			ethernetObj: { new EthernetFrame() },
			arpObj: { new ArpPacket() },
			vlanTagObj: { new VlanTag() }
		]
	}
	
	class PduMethods{
		
	}

	private Map getMethodBinding(){
		def methods = [:]
		pduObjects.keySet().each{ key ->
			def methodName = "$key"-"Obj"
			PduMethods.metaClass."$methodName" << resolveMapArguments.curry(key)

			if(pduObjects[key]() instanceof EmbeddingProtocolDataUnit){
				PduMethods.metaClass."$methodName" << resolveClosure.curry(key)
			}
			methods."$methodName" = PduMethods.&"$methodName"
		}

		methods
	}

	Vocabulary(){
		networkInterfaces = getNetworkInterfaceBinding()
		pduObjects = getPduBinding()
		pduMethods = getMethodBinding()
	}

	Sender sender = new Sender()
	DataUnit dataUnitToSend = null

	def binding(){
		networkInterfaces +
				pduObjects +
				pduMethods
	}

	def send(BigInteger bi){
		send(bi.toByteArray())
	}

	def send(byte[] data){
		send(new RawDataUnit(data))
	}

	def send(DataUnit du){
		this.dataUnitToSend = du
		this
	}

	def on(NetworkInterface ni){
		if(dataUnitToSend != null){
			sender.send ni, dataUnitToSend
			dataUnitToSend = null
			return this
		}

		throw new NullPointerException("the data unit couldn't be sent because it was null")
	}

	def on(String s){
		on NetworkInterface.getByName(s)
	}

	def on(int i){
		on NetworkInterface.getByIndex(i)
	}
}

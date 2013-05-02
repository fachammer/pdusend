package at.fabianachammer.pdusend.dsl

import at.fabianachammer.pdusend.util.BitOperator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.pdu.EmbeddingProtocolDataUnit
import at.fabianachammer.pdusend.type.pdu.RawDataUnit
import java.util.Map;

/**
 * Class that holds the methods for creating the pdu objects in the pdusend dsl. 
 * @author fabian
 *
 */
class Methods {

	private Objects objects

	/**
	 * binding of the pdusend pdu methods.
	 */
	def binding = [:]

	/**
	 * creates a new Methods object from the given pdu objects of the pdusend dsl
	 * @param objects Objects object that contains the pdu objects of the pdusend dsl
	 */
	Methods(Objects objects){
		this.objects = objects
		objects.binding.keySet().each{ key ->
			def methodName = "$key"-"Obj"
			Methods.metaClass."$methodName" << ArgumentResolver.resolveNoArguments.curry(key)
			Methods.metaClass."$methodName" << ArgumentResolver.resolveMapArguments.curry(key)

			if(objects.binding[key]() instanceof EmbeddingProtocolDataUnit){
				Methods.metaClass."$methodName" << ArgumentResolver.resolveClosure.curry(key)
				Methods.metaClass."$methodName" << ArgumentResolver.resolveClosureWithArguments.curry(key)
			}
			binding."$methodName" = Methods.&"$methodName"
		}
	}
}

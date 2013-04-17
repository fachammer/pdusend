package at.fabianachammer.pdusend.dsl;

import groovy.lang.Script;

import java.net.NetworkInterface;

import at.fabianachammer.pdusend.PduSender
import at.fabianachammer.pdusend.pdu.ArpSegment
import at.fabianachammer.pdusend.pdu.EthernetFrame
import at.fabianachammer.pdusend.pdu.MacAddress
import at.fabianachammer.pdusend.pdu.ProtocolDataUnit
import at.fabianachammer.pdusend.pdu.NetworkType
import at.fabianachammer.pdusend.pdu.RawDataUnit
import at.fabianachammer.pdusend.util.BitOperator
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer;

def networkInterfaces = [:]

NetworkInterface.networkInterfaces.each{
	networkInterfaces."$it.name" = it.name
}

class PduMethods{

}

def pduObjects = [
	ethernetObj: { new EthernetFrame() },
	arpObj: { new ArpSegment() }
]

def pduMethods = [:]

def resolveMapArguments = {Object key, Map map ->
	def pdu = pduObjects[key]()

	map.keySet().each{
		if(pdu."$it" instanceof NetworkType) {
			if([
				Integer.class,
				Long.class
			].any{ n ->
				n == map[it].class
			}){
				def bytes = BitOperator.split(map[it],pdu."$it".size)
				def bytesToDecode = bytes[(bytes.length-pdu."$it".size)..(bytes.length-1)] as byte[]
				pdu."$it".decode(bytesToDecode)
			}

			else if(map[it].class == byte[].class){
				pdu."$it".decode(map[it])
			}

			else{
				throw new IllegalArgumentException("property $it cannot have a value of $map[$it]")
			}
		}
		else if(pdu."$it".class == map[it].class){
			pdu."$it" = map[it]
		}
		else{
			throw new IllegalArgumentException("property $it cannot have a value of $map[$it]")
		}
	}

	pdu
}

def resolveClosure = {Object key, Map map, Closure c ->
	def pdu = resolveMapArguments key, map

	def dataResult = c()

	if(dataResult instanceof ProtocolDataUnit){
		pdu?.data = dataResult
	}
	
	else if(dataResult instanceof byte[]){
		pdu?.data = new RawDataUnit(map[it])
	}

	else if([
		Integer.class,
		Long.class,
		BigInteger.class
	].any{ n ->
		n == dataResult?.class
	}){
		pdu?.data = new RawDataUnit(BitOperator.split(dataResult))
	}

	pdu
}

pduObjects.keySet().each{ key ->
	def methodName = "$key"-"Obj"
	PduMethods.metaClass."$methodName" << resolveMapArguments.curry(key)
	PduMethods.metaClass."$methodName" << resolveClosure.curry(key)
	pduMethods."$methodName" = PduMethods.&"$methodName"
}

def binding = new Binding(pduObjects + pduMethods + networkInterfaces)

def configuration = new CompilerConfiguration()

configuration.scriptBaseClass = SenderBaseScript.class.name

def shell = new GroovyShell(binding, configuration)
shell.evaluate(new File("src/main/groovy/ExampleScript.groovy"))

abstract class SenderBaseScript extends Script{
	PduSender sender = new PduSender()
	ProtocolDataUnit pdu = null

	def send(BigInteger bi){
		send(bi.toByteArray())
	}

	def send(byte[] data){
		send(new RawDataUnit(data))
	}

	def send(ProtocolDataUnit pdu){
		this.pdu = pdu
		this
	}

	def on(NetworkInterface ni){
		sender.send ni, pdu
	}

	def on(String s){
		on NetworkInterface.getByName(s)
	}

	def on(int i){
		on NetworkInterface.getByIndex(i)
	}
}
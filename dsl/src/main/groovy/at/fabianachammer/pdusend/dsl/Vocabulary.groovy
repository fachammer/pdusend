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

	private def networkInterfaces = [:]
	private Objects objects
	private Methods methods
	def sender
	private DataUnit dataUnitToSend

	Vocabulary(){
		networkInterfaces = getNetworkInterfaceBinding()
		objects = new Objects()
		methods = new Methods(objects)
		sender = new Sender()
	}

	private Map getNetworkInterfaceBinding(){
		def netIfs = [:]
		NetworkInterface.networkInterfaces.each{ netIfs."$it.name" = it }

		netIfs
	}

	Map binding(){
		networkInterfaces +
				objects.binding +
				methods.binding + [on: this.&on, send: this.&send]
	}

	def send(BigInteger bi){
		if(bi.signum() < 0){
			throw new IllegalArgumentException("negative numbers are not allowed to send")
		}
		send(BitOperator.split(bi))
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
			return
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

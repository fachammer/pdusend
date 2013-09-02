package at.fabianachammer.pdusend.dsl;

import at.fabianachammer.pdusend.sender.NetworkSender
import at.fabianachammer.pdusend.type.DataUnit


/**
 * Domain Specific Langauge that provides methods for easily sending packets over a network interface.
 * 
 * @author fabian
 *
 */
class DSL {

	private NetworkSender sender

	DataUnit dataUnit
	NetworkInterface networkInterface

	DSL(NetworkSender sender){
		this.sender = sender
	}

	DSL send(DataUnit dataUnit){
		this.dataUnit = dataUnit
		return this
	}

	DSL on(NetworkInterface networkInterface){
		this.networkInterface = networkInterface
		sender.send(networkInterface, dataUnit)
		return this
	}
}

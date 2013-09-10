package at.fabianachammer.pdusend.dsl

import at.fabianachammer.pdusend.sender.NetworkSender
import at.fabianachammer.pdusend.type.DataUnit


/**
 * Domain Specific Langauge that provides methods for sending packets over a network interface.
 * 
 * TODO: think about state/immutability of DSL "chains"
 * 
 * @author fabian
 *
 */
class DSL {

	private NetworkSender sender

	DataUnit dataUnit
	NetworkInterface networkInterface

	/**
	 * Instantiates a new DSL with a given NetworkSender
	 * @param sender sender with which to send the data units
	 */
	DSL(NetworkSender sender){
		this.sender = sender
	}

	/**
	 * Puts a given data unit in the DSL object which gets sent when on is called.
	 * @param dataUnit data unit to be sent over the network later
	 * @return DSL object that contains the given data unit
	 */
	DSL send(DataUnit dataUnit){
		this.dataUnit = dataUnit
		return this
	}

	/**
	 * Puts a given network interface in the DSL object and sends the former set data unit on the given network interface.
	 * @param networkInterface network interface to send the former set data unit over
	 * @return DSL object that contains the former set data unit and the given network interface
	 */
	DSL on(NetworkInterface networkInterface){
		this.networkInterface = networkInterface
		sender.send(dataUnit, networkInterface)
		return this
	}
}

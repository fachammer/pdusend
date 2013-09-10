package at.fabianachammer.pdusend.sender

import at.fabianachammer.pdusend.type.DataUnit

/**
 * interface for classes that send data units over the network.
 * @author fabian
 *
 */
interface NetworkSender {

	/**
	 * Sends a data unit over a specified network interface.
	 * @param dataUnit data unit to be sent over the network
	 * @param networkInterface interface to send the data unit on
	 */
	void send(DataUnit dataUnit, NetworkInterface networkInterface)
}

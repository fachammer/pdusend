package at.fabianachammer.pdusend.sender

import at.fabianachammer.pdusend.type.DataUnit

/**
 * interface for classes that can send data units over the network.
 * @author fabian
 *
 */
interface NetworkSender {
	void send(NetworkInterface networkInterface, DataUnit dataUnit)
}

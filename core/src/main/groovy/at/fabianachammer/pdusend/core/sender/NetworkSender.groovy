package at.fabianachammer.pdusend.core.sender

import at.fabianachammer.pdusend.core.type.DataUnit

/**
 * interface for classes that can send data units over the network.
 * @author fabian
 *
 */
interface NetworkSender {
	void send(NetworkInterface networkInterface, DataUnit dataUnit)
}

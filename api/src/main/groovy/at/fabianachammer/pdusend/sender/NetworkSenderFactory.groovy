package at.fabianachammer.pdusend.sender

/**
 * interface for classes that create NetworkSenders.
 * @author fabian
 *
 */
interface NetworkSenderFactory {
	
	/**
	 * @return implementation of a NetworkSender
	 */
	NetworkSender createNetworkSender()
}

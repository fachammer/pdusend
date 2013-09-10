package at.fabianachammer.pdusend.sender

import org.gcontracts.annotations.Ensures;

/**
 * Implements the NetworkSenderFactory for the NetworkSenderImpl.
 * @author fabian
 *
 */
class NetworkSenderFactoryImpl implements NetworkSenderFactory {

	@Ensures({ result != null })
	@Override
	public NetworkSender createNetworkSender() {
		return new NetworkSenderImpl()
	}

}

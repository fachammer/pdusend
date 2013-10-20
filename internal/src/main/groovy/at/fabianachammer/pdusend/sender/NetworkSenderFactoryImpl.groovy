package at.fabianachammer.pdusend.sender

/**
 * Implements the NetworkSenderFactory for the NetworkSenderImpl.
 * @author fabian
 *
 */
class NetworkSenderFactoryImpl implements NetworkSenderFactory {

	@Override
	public NetworkSender createNetworkSender() {
		return new NetworkSenderImpl()
	}

}

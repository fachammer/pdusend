package at.fabianachammer.pdusend.sender

class NetworkSenderFactoryImpl implements NetworkSenderFactory {

	@Override
	public NetworkSender createNetworkSender() {
		return new NetworkSenderImpl()
	}

}

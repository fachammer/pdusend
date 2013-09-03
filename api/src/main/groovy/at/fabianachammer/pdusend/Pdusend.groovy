package at.fabianachammer.pdusend

import at.fabianachammer.pdusend.sender.NetworkSenderFactory


final class Pdusend {
	
	private static final String NETWORK_SENDER_FACTORY_IMPLEMENTATION = 'at.fabianachammer.pdusend.sender.NetworkSenderFactoryImpl'
	
	static NetworkSenderFactory createNetworkSenderFactory(){
		Pdusend.classLoader.loadClass(NETWORK_SENDER_FACTORY_IMPLEMENTATION).newInstance()
	}
}

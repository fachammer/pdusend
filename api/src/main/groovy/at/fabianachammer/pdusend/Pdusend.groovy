package at.fabianachammer.pdusend

import at.fabianachammer.pdusend.sender.NetworkSenderFactory


/**
 * Entry class for the pdusend API. Used for creating implementations of pdusend classes.
 * @author fabian
 *
 */
final class Pdusend {

	// TODO: DataUnitGenerator map
	// TODO: traverse directories for data unit description files
	// TODO: get NetworkSenderFactory implementation from configuration file
	
	private static final String NETWORK_SENDER_FACTORY_IMPLEMENTATION = 'at.fabianachammer.pdusend.sender.NetworkSenderFactoryImpl'

	/**
	 * Creates a new NetworkSenderFactory. It does so via a static String and reflection currently, but should be done with configuration files in the future.
	 * @return implementation of a NetworkSenderFactory
	 */
	static NetworkSenderFactory createNetworkSenderFactory(){
		Pdusend.classLoader.loadClass(NETWORK_SENDER_FACTORY_IMPLEMENTATION).newInstance()
	}
}

package at.fabianachammer.pdusend.sender;

import java.net.NetworkInterface;

import at.fabianachammer.pdusend.common.validation.Validator;
import at.fabianachammer.pdusend.type.DataUnit;

/**
 * Implementation of the NetworkSender that sends data on a native raw socket
 * via the JNI.
 * 
 * @author fabian
 * 
 */
public class NetworkSenderImpl implements NetworkSender {

	// TODO: decide whether to use validator from common or use validation
	// framework like OVal

	private static final String LIB_PATH = "native";

	/**
	 * Loads the native library.
	 */
	static {
		System.loadLibrary(LIB_PATH);
	}

	@Override
	public void send(final DataUnit data,
			final NetworkInterface networkInterface) {
		Validator dataValidator = new Validator(data, "data");
		dataValidator.validateNotNull();
		Validator networkInterfaceValidator = new Validator(networkInterface, "network interface");
		networkInterfaceValidator.validateNotNull();
		send(networkInterface.getIndex(), data.encode());
	}

	/**
	 * Sends a data unit from a specific network interface.
	 * 
	 * @param interfaceIndex
	 *            id of the network interface that should send the data
	 * @param data
	 *            data that should be sent
	 */
	private native void send(final int interfaceIndex, final byte[] data);
}

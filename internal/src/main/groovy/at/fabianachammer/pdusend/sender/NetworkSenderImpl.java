package at.fabianachammer.pdusend.sender;

import java.net.NetworkInterface;

import at.fabianachammer.pdusend.type.DataUnit;

/**
 * Implementation of the NetworkSender that sends data on a native raw socket
 * via the JNI.
 * 
 * @author fabian
 * 
 */
public class NetworkSenderImpl implements NetworkSender {

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
		validateSendArguments(data, networkInterface);

		send(networkInterface.getIndex(), data.encode());
	}

	private void validateSendArguments(final DataUnit data,
			final NetworkInterface networkInterface) {
		validateDataUnit(data);
		validateNetworkInterface(networkInterface);
	}

	private void validateDataUnit(final DataUnit data) {
		if (data == null)
			throw new NullPointerException("data must not be null.");
	}

	private void validateNetworkInterface(
			final NetworkInterface networkInterface) {
		if (networkInterface == null)
			throw new NullPointerException(
					"network interface must not be null.");
	}

	/**
	 * Sends a data unit from a specific network interface. This call needs
	 * appropriate permissions to send on a network interface.
	 * 
	 * @param interfaceIndex
	 *            id of the network interface that should send the data
	 * @param data
	 *            data that should be sent
	 */
	private native void send(final int interfaceIndex, final byte[] data);
}

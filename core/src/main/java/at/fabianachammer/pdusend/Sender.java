/**
 * 
 */
package at.fabianachammer.pdusend;

import java.net.NetworkInterface;

import at.fabianachammer.pdusend.type.DataUnit;

/**
 * Class for sending arbitrary data.
 * 
 * @author fabian
 * 
 */
public class Sender {

	/**
	 * Specifies the path to the native library.
	 */
	private static final String LIB_PATH = "native";

	static {
		System.loadLibrary(LIB_PATH);
	}

	/**
	 * Sends a data unit from a specified network interface.
	 * 
	 * @param networkInterface
	 *            network interface to send the PDU from
	 * @param data
	 *            data unit to be sent
	 */
	public void send(final NetworkInterface networkInterface,
			final DataUnit data) {
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
	private native void send(final int interfaceIndex,
			final byte[] data);
}

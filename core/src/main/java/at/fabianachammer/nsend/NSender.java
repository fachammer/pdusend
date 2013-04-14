/**
 * 
 */
package at.fabianachammer.nsend;

import java.net.NetworkInterface;

import at.fabianachammer.nsend.pdu.ProtocolDataUnit;

/**
 * Class for sending arbitrary data wrapped in an Ethernet frame.
 * 
 * @author fabian
 * 
 */
public class NSender {

	/**
	 * Specifies the path to the nsender native library.
	 */
	private static final String NSENDER_LIB_PATH = "native";

	static {
		System.loadLibrary(NSENDER_LIB_PATH);
	}

	/**
	 * Sends a protocol data unit from a specified network interface.
	 * 
	 * @param networkInterface
	 *            network interface to send the PDU from
	 * @param data
	 *            PDU to be sent
	 */
	public final void send(final NetworkInterface networkInterface,
			final ProtocolDataUnit data) {
		send(networkInterface.getIndex(), data.toBytes());
	}

	/**
	 * Sends an arbitrary array of data wrapped in an Ethernet frame from a
	 * specific network interface.
	 * 
	 * @param interfaceIndex
	 *            id of the network interface that should send the data
	 * @param data
	 *            data that should be sent
	 */
	private native void send(final int interfaceIndex,
			final byte[] data);
}

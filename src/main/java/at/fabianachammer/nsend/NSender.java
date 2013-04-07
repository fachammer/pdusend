/**
 * 
 */
package at.fabianachammer.nsend;

import java.net.NetworkInterface;

import at.fabianachammer.nsend.pdu.EthernetHeader;
import at.fabianachammer.nsend.pdu.ProtocolDataUnit;
import at.fabianachammer.nsend.util.ByteConverter;

/**
 * Class for sending arbitrary data wrapped in an Ethernet frame.
 * 
 * @author fabian
 * 
 */
public class NSender {

	static {
		System.loadLibrary("libnsender");
	}

	/**
	 * Sends a protocol data unit from a specified network interface.
	 * 
	 * @param networkInterface
	 *            network interface to send the PDU from
	 * @param ethernetHeader
	 *            Ethernet header that the data should have
	 * @param data
	 *            PDU to be sent
	 */
	public final void send(final NetworkInterface networkInterface,
			final EthernetHeader ethernetHeader, final ProtocolDataUnit data) {

		byte[] sourceMac =
				ByteConverter.toByteArray(ethernetHeader.getSourceMacAddress());
		byte[] destinationMac =
				ByteConverter.toByteArray(ethernetHeader
						.getDestinationMacAddress());
		byte[] byteData = ByteConverter.toByteArray(data.toBytes());

		send(
				networkInterface.getIndex(), sourceMac, destinationMac,
				ethernetHeader.getEtherType().getId(), byteData);
	}

	/**
	 * Sends an arbitrary array of data wrapped in an Ethernet frame from a
	 * specific network interface.
	 * 
	 * @param interfaceIndex
	 *            id of the network interface that should send the data
	 * @param sourceMacAddress
	 *            source MAC address the packet that is being sent should have
	 * @param destinationMacAddress
	 *            destination MAC address that packet that is being sent should
	 *            have
	 * @param etherType
	 *            Ether type that the packet that is being sent should have
	 * @param data
	 *            data that should be sent
	 */
	private native void send(final int interfaceIndex,
			final byte[] sourceMacAddress, final byte[] destinationMacAddress,
			final short etherType, final byte[] data);
}

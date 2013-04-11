package at.fabianachammer.nsend.pdu;

import java.util.zip.CRC32;

import at.fabianachammer.nsend.util.BitOperator;
import at.fabianachammer.nsend.util.ByteConverter;
import at.fabianachammer.nsend.util.PrimitiveArrayList;

/**
 * Represents an Ethernet frame specified in IEEE 802.3.
 * 
 * @author fabian
 * 
 */
public class EthernetFrame implements ProtocolDataUnit {

	/**
	 * Specifies the broadcast MAC address.
	 */
	public static final Byte[] BROADCAST_MAC = new Byte[] {
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
			(byte) 0xff };

	/**
	 * Specifies the minimum size of an Ethernet frame in bytes.
	 */
	private static final int MIN_ETHERNET_FRAME_SIZE = 64;

	/**
	 * Specifies the size of the cyclic redundancy check in bytes.
	 */
	private static final int CRC_SIZE = 4;

	/**
	 * Destination MAC address. Describes the MAC address of the network
	 * interface to receive a packet.
	 */
	private Byte[] destinationMacAddress;

	/**
	 * Source MAC address. Describes the MAC address of the network interface
	 * which sends a packet.
	 */
	private Byte[] sourceMacAddress;

	/**
	 * VLAN-Tag. Describes the VLAN Tag (if any).
	 */
	private VlanTag vlanTag = null;

	/**
	 * EtherType. Describes the protocol for which this Ethernet frame is the
	 * carrier.
	 */
	private EtherType etherType;

	/**
	 * Describes the protocol data unit that encapsulates the data which the
	 * Ethernet frame carries.
	 */
	private ProtocolDataUnit data = null;

	@Override
	public final Byte[] toBytes() {
		Byte[] vlanTagBytes = new Byte[0];
		Byte[] pduBytes = new Byte[0];

		if (vlanTag != null) {
			vlanTagBytes = vlanTag.toBytes();
		}

		if (data != null) {
			pduBytes = data.toBytes();
		}

		PrimitiveArrayList<Byte> bytes = new PrimitiveArrayList<Byte>();
		bytes.addArray(destinationMacAddress);
		bytes.addArray(sourceMacAddress);
		bytes.addArray(vlanTagBytes);
		bytes.addArray(BitOperator.split(etherType.getId()));
		bytes.addArray(pduBytes);

		for (int i = bytes.size(); i < MIN_ETHERNET_FRAME_SIZE
				- CRC_SIZE; i++) {
			bytes.add((byte) 0);
		}

		bytes.addArray(calculateCheckSum(bytes));

		return bytes.toArray(new Byte[0]);
	}

	/**
	 * @return the destinationMacAddress
	 */
	public final Byte[] getDestinationMacAddress() {
		return destinationMacAddress;
	}

	/**
	 * @param destinationMacAddress
	 *            the destinationMacAddress to set
	 */
	public final void setDestinationMacAddress(
			final Byte[] destinationMacAddress) {
		this.destinationMacAddress = destinationMacAddress;
	}

	/**
	 * @return the sourceMacAddress
	 */
	public final Byte[] getSourceMacAddress() {
		return sourceMacAddress;
	}

	/**
	 * @param sourceMacAddress
	 *            the sourceMacAddress to set
	 */
	public final void setSourceMacAddress(final Byte[] sourceMacAddress) {
		this.sourceMacAddress = sourceMacAddress;
	}

	/**
	 * @return the vlanTag
	 */
	public final VlanTag getVlanTag() {
		return vlanTag;
	}

	/**
	 * @param vlanTag
	 *            the vlanTag to set
	 */
	public final void setVlanTag(final VlanTag vlanTag) {
		this.vlanTag = vlanTag;
	}

	/**
	 * @return the etherType
	 */
	public final EtherType getEtherType() {
		return etherType;
	}

	/**
	 * @param etherType
	 *            the etherType to set
	 */
	public final void setEtherType(final EtherType etherType) {
		this.etherType = etherType;
	}

	/**
	 * @return the data
	 */
	public final ProtocolDataUnit getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public final void setData(final ProtocolDataUnit data) {
		this.data = data;
	}

	/**
	 * Calculates a CRC-32 checksum for a given array of bytes.
	 * 
	 * @param data
	 *            PrimitiveArrayList<Byte> that holds the data
	 * @return a byte array with four bytes representing the checksum for the
	 *         given data
	 */
	private Byte[] calculateCheckSum(final PrimitiveArrayList<Byte> data) {
		CRC32 crc = new CRC32();
		crc.update(ByteConverter.toByteArray(data.toArray(new Byte[0])));

		Byte[] reverseCheckSum = BitOperator.split((int) crc.getValue());
		Byte[] checkSum = new Byte[reverseCheckSum.length];

		for (int i = 0; i < checkSum.length; i++) {
			checkSum[i] = reverseCheckSum[reverseCheckSum.length
					- i - 1];
		}

		return checkSum;
	}
}
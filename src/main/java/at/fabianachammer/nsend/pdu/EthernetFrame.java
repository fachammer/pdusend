package at.fabianachammer.nsend.pdu;

import java.util.zip.CRC32;

import at.fabianachammer.nsend.pdu.util.BitOperator;
import at.fabianachammer.nsend.pdu.util.ByteConverter;
import at.fabianachammer.nsend.pdu.util.BytePrimitiveConverter;
import at.fabianachammer.nsend.pdu.util.PrimitiveArrayList;

/**
 * Represents an Ethernet frame specified in IEEE 802.3.
 * 
 * @author fabian
 * 
 */
public class EthernetFrame implements ProtocolDataUnit {

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
	private ProtocolDataUnit pdu;

	@Override
	public final Byte[] toBytes() {

		Byte[] vlanTagBytes = new Byte[0];

		if (vlanTag != null) {
			vlanTagBytes = vlanTag.toBytes();
		}

		PrimitiveArrayList<Byte> bytes = new PrimitiveArrayList<Byte>();
		bytes.addArray(destinationMacAddress);
		bytes.addArray(sourceMacAddress);
		bytes.addArray(vlanTagBytes);
		bytes.addArray(BitOperator.split(etherType.getId()));
		bytes.addArray(pdu.toBytes());

		for (int i = bytes.size(); i < MIN_ETHERNET_FRAME_SIZE
				- CRC_SIZE; i++) {
			bytes.add((byte) 0);
		}

		String hexBytes =
				ByteConverter.toHexString(BytePrimitiveConverter
						.convertByteArray(bytes.toArray(new Byte[0])));

		CRC32 crc = new CRC32();
		crc.update(BytePrimitiveConverter.convertByteArray(bytes
				.toArray(new Byte[0])));

		Byte[] checkSum = BitOperator.split((int) crc.getValue());

		bytes.addArray(checkSum);

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
	 * @return the pdu
	 */
	public final ProtocolDataUnit getPdu() {
		return pdu;
	}

	/**
	 * @param pdu
	 *            the pdu to set
	 */
	public final void setPdu(final ProtocolDataUnit pdu) {
		this.pdu = pdu;
	}

}

package at.fabianachammer.pdusend.type.pdu;

import java.util.zip.CRC32;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.EthernetFrameDecoder;
import at.fabianachammer.pdusend.util.BitOperator;
import at.fabianachammer.pdusend.util.ByteArrayBuilder;

/**
 * Represents an Ethernet frame specified in IEEE 802.3.
 * 
 * @author fabian
 * 
 */
@Guarded
public class EthernetFrame implements EmbeddingProtocolDataUnit {

	/**
	 * Specifies the hardware address type of the Ethernet protocol.
	 */
	public static final short HARDWARE_ADDRESS_TYPE = 1;

	/**
	 * Specifies the minimum size of an Ethernet frame in bytes.
	 */
	private static final int MIN_ETHERNET_FRAME_SIZE = 64;

	/**
	 * Specifies the size of the cyclic redundancy check in bytes.
	 */
	private static final int CRC_SIZE = 4;

	/**
	 * Specifies the default Ether type for the Ethernet frame.
	 */
	private static final EtherType DEFAULT_ETHER_TYPE =
			EtherType.IPv4;

	/**
	 * the decoder used for decoding data units of this type.
	 */
	private static final DataUnitDecoder<EthernetFrame> DECODER =
			new EthernetFrameDecoder();

	/**
	 * Destination MAC address. Describes the MAC address of the network
	 * interface to receive a packet.
	 */
	@NotNull
	private MacAddress destinationMacAddress = new MacAddress();

	/**
	 * Source MAC address. Describes the MAC address of the network interface
	 * which sends a packet.
	 */
	@NotNull
	private MacAddress sourceMacAddress = new MacAddress();

	/**
	 * VLAN-Tag. Describes the VLAN Tag (if any).
	 */
	private VlanTag vlanTag = null;

	/**
	 * EtherType. Describes the protocol for which this Ethernet frame is the
	 * carrier.
	 */
	@NotNull
	private EtherType etherType = DEFAULT_ETHER_TYPE;

	/**
	 * Describes the protocol data unit that encapsulates the data which the
	 * Ethernet frame carries.
	 */
	private DataUnit data = null;

	/**
	 * Creates a new Ethernet frame without any defined attributes.
	 */
	public EthernetFrame() {

	}

	@Override
	public final DataUnitDecoder<EthernetFrame> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		byte[] vlanTagBytes = new byte[0];
		byte[] pduBytes = new byte[0];

		if (vlanTag != null) {
			vlanTagBytes = vlanTag.encode();
		}

		if (data != null) {
			pduBytes = data.encode();
		}

		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(destinationMacAddress.encode());
		bab.append(sourceMacAddress.encode());
		bab.append(vlanTagBytes);
		bab.append(etherType.encode());
		bab.append(pduBytes);

		for (int i = bab.size(); i < MIN_ETHERNET_FRAME_SIZE
				- CRC_SIZE; i++) {
			bab.append((byte) 0);
		}

		bab.append(calculateCheckSum(bab.toArray()));

		return bab.toArray();
	}
	
	@Override
	public final DataUnit getEmbeddedData() {
		return data;
	}

	/**
	 * @return the destinationMacAddress
	 */
	public final MacAddress getDestinationMacAddress() {
		return destinationMacAddress;
	}

	/**
	 * @param destinationMacAddress
	 *            the destinationMacAddress to set
	 */
	public final void setDestinationMacAddress(
			@AssertFieldConstraints final MacAddress destinationMacAddress) {
		this.destinationMacAddress = destinationMacAddress;
	}

	/**
	 * @return the sourceMacAddress
	 */
	public final MacAddress getSourceMacAddress() {
		return sourceMacAddress;
	}

	/**
	 * @param sourceMacAddress
	 *            the sourceMacAddress to set
	 */
	public final void setSourceMacAddress(
			@AssertFieldConstraints final MacAddress sourceMacAddress) {
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
	public final void setEtherType(
			@AssertFieldConstraints final EtherType etherType) {
		this.etherType = etherType;
	}

	/**
	 * @return the data
	 */
	public final DataUnit getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public final void setData(final DataUnit data) {
		this.data = data;
	}

	/**
	 * Calculates a CRC-32 checksum for a given array of bytes.
	 * 
	 * @param data
	 *            array that holds the data
	 * @return a byte array with four bytes representing the checksum for the
	 *         given data
	 */
	private byte[] calculateCheckSum(final byte[] data) {
		CRC32 crc = new CRC32();
		crc.update(data);

		byte[] reverseCheckSum =
				BitOperator.split((int) crc.getValue());
		byte[] checkSum = new byte[reverseCheckSum.length];

		for (int i = 0; i < checkSum.length; i++) {
			checkSum[i] = reverseCheckSum[reverseCheckSum.length
					- i - 1];
		}

		return checkSum;
	}
}

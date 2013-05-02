package at.fabianachammer.pdusend.type.pdu;

import java.util.zip.CRC32;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
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
public class EthernetFrame extends EmbeddingProtocolDataUnit
		implements DataLinkProtocol {

	/**
	 * hardware address type of the Ethernet protocol.
	 */
	public static final short HARDWARE_ADDRESS_TYPE = 1;

	/**
	 * minimum size of an Ethernet frame in bytes.
	 */
	public static final int MIN_SIZE = 64;

	/**
	 * maximum size of an Ethernet frame in bytes.
	 */
	public static final int MAX_SIZE = 1522;

	/**
	 * size of the cyclic redundancy check in bytes.
	 */
	public static final int CRC_SIZE = 4;

	/**
	 * Specifies the default Ether type for the Ethernet frame.
	 */
	private static final EtherType DEFAULT_ETHER_TYPE =
			EtherType.UNKNOWN;

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
	private EtherType etherType = null;

	/**
	 * Describes the protocol data unit that encapsulates the data which the
	 * Ethernet frame carries.
	 */
	private DataUnit data = null;

	/**
	 * padding bytes of the Ethernet frame in case the frame is too small.
	 */
	private byte[] padding = null;

	/**
	 * checksum of the Ethernet frame.
	 */
	@Size(min = CRC_SIZE, max = CRC_SIZE)
	private byte[] checksum = null;

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
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(destinationMacAddress.encode());
		bab.append(sourceMacAddress.encode());

		if (vlanTag != null) {
			bab.append(vlanTag.encode());
		}

		if (data != null
				&& data instanceof NetworkProtocol) {
			etherType = ((NetworkProtocol) data).getEtherType();
		} else if (etherType == null) {
			etherType = EtherType.UNKNOWN;
		}

		// TODO: ether type from embedded data unit

		bab.append(etherType.encode());

		if (data != null) {
			bab.append(data.encode());
		}

		if (padding == null) {
			padding = getPaddingBytes();
		}

		bab.append(padding);

		if (checksum == null) {
			checksum = calculateCheckSum(bab.toArray());
		}

		bab.append(checksum);

		return bab.toArray();
	}

	@Override
	public final int size() {
		return encode().length;
	}

	@Override
	public final DataUnit getEmbeddedData() {
		return data;
	}

	@Override
	public final void setEmbeddedData(final DataUnit dataUnit) {
		this.data = dataUnit;
	}

	@Override
	public final HardwareAddressType getHardwareAddressType() {
		return HardwareAddressType.ETHERNET;
	}

	@Override
	protected final <T extends DataUnit> boolean isEquals(final T obj) {
		EthernetFrame rhs = (EthernetFrame) obj;
		return new EqualsBuilder()
				.append(getDestinationMacAddress(),
						rhs.getDestinationMacAddress())
				.append(getSourceMacAddress(),
						rhs.getSourceMacAddress())
				.append(getEtherType(), rhs.getEtherType())
				.append(getVlanTag(), rhs.getVlanTag())
				.append(getEmbeddedData(), rhs.getEmbeddedData())
				.append(getPadding(), rhs.getPadding())
				.append(getChecksum(), rhs.getChecksum()).isEquals();
	}

	@Override
	public final int hashCode() {
		final int initial = 197;
		final int multiplier = 209;
		return new HashCodeBuilder(initial, multiplier)
				.append(getDestinationMacAddress())
				.append(getSourceMacAddress()).append(getEtherType())
				.append(getVlanTag()).append(getEmbeddedData())
				.append(getPadding()).append(getChecksum())
				.hashCode();
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
	 * @return the padding
	 */
	public final byte[] getPadding() {
		return padding;
	}

	/**
	 * @param padding
	 *            the padding to set
	 */
	public final void setPadding(final byte[] padding) {
		this.padding = padding;
	}

	/**
	 * @return the checksum
	 */
	public final byte[] getChecksum() {
		return checksum;
	}

	/**
	 * @param checksum
	 *            the checksum to set
	 */
	public final void setChecksum(
			@AssertFieldConstraints final byte[] checksum) {
		this.checksum = checksum;
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
		byte[] checksum = BitOperator.split((int) crc.getValue());
		byte[] reversedChecksum = new byte[checksum.length];

		for (int i = 0; i < checksum.length; i++) {
			reversedChecksum[i] = checksum[checksum.length
					- 1 - i];
		}

		return reversedChecksum;
	}

	/**
	 * Calculates the required padding to match the minimum size of thi Ethernet
	 * frame.
	 * 
	 * @return required padding in bytes
	 */
	private byte[] getPaddingBytes() {
		int currentFrameSize = 2
				* MacAddress.SIZE + EtherType.SIZE;

		if (vlanTag != null) {
			currentFrameSize += VlanTag.SIZE;
		}

		if (data != null) {
			currentFrameSize += data.encode().length;
		}

		ByteArrayBuilder bab = new ByteArrayBuilder();
		for (int i = currentFrameSize; i < MIN_SIZE
				- CRC_SIZE; i++) {
			bab.append((byte) 0);
		}
		return bab.toArray();
	}
}

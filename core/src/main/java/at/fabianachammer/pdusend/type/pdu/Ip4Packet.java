/**
 * 
 */
package at.fabianachammer.pdusend.type.pdu;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.ServiceType;
import at.fabianachammer.pdusend.type.ServiceTypeRFC791;
import at.fabianachammer.pdusend.type.TransportProtocolIdentifier;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.Ip4PacketDecoder;
import at.fabianachammer.pdusend.util.BitOperator;
import at.fabianachammer.pdusend.util.ByteArrayBuilder;

/**
 * Represents an IPv4 packet. Currently doesn't support options and padding.
 * 
 * @author fabian
 * 
 */
@Guarded
public class Ip4Packet extends EmbeddingProtocolDataUnit implements
		NetworkProtocol {

	/**
	 * minimum size of an IPv4 packet in bytes.
	 */
	public static final int MIN_SIZE = 20;

	/**
	 * maximum size of an Ipv4 packet in bytes.
	 */
	public static final int MAX_SIZE = EthernetFrame.MAX_SIZE
			- VlanTag.SIZE - 14 - EthernetFrame.CRC_SIZE;

	/**
	 * maximum ip version.
	 */
	private static final byte MAX_VERSION = 15;

	/**
	 * minimum internet header length.
	 */
	private static final byte MIN_INTERNET_HEADER_LENGTH = 5;

	/**
	 * maximum internet header length.
	 */
	private static final byte MAX_INTERNET_HEADER_LENGTH = 16;

	/**
	 * maximum fragment offset (13 bits).
	 */
	private static final int MAX_FRAGMENT_OFFSET = 8191;

	/**
	 * number of bytes the checksum must have.
	 */
	private static final int CHECKSUM_SIZE = 2;

	/**
	 * decoder for Ip4Packets.
	 */
	private static final DataUnitDecoder<Ip4Packet> DECODER =
			new Ip4PacketDecoder();

	/**
	 * version of the IPv4 packet.
	 */
	@Max(MAX_VERSION)
	private byte version = 4;

	/**
	 * internet header length.
	 */
	@Max(MAX_INTERNET_HEADER_LENGTH)
	private byte internetHeaderLength = MIN_INTERNET_HEADER_LENGTH;

	/**
	 * type of service.
	 */
	@NotNull
	private ServiceType serviceType = new ServiceTypeRFC791();

	/**
	 * total length of the packet in bytes.
	 */
	private short totalLength = MIN_SIZE;

	/**
	 * identifaction.
	 */
	private short identification = 0;

	/**
	 * indicates whether this packet is fragmented.
	 */
	private boolean fragmented = true;

	/**
	 * indicates whether this is the last fragment.
	 */
	private boolean lastFragment = true;

	/**
	 * offset of the fragment.
	 */
	@Max(MAX_FRAGMENT_OFFSET)
	private short fragmentOffset = 0;

	/**
	 * time to live (TTL).
	 */
	private byte timeToLive = 0;

	/**
	 * indicates the next layer (transport layer) protocol.
	 */
	@NotNull
	private TransportProtocolIdentifier protocol =
			TransportProtocolIdentifier.UNKNOWN;

	/**
	 * checksum of the header.
	 */
	@Size(min = CHECKSUM_SIZE, max = CHECKSUM_SIZE)
	private byte[] headerChecksum = null;

	/**
	 * source IP address.
	 */
	@NotNull
	private Ip4Address sourceAddress = new Ip4Address();

	/**
	 * destination IP address.
	 */
	@NotNull
	private Ip4Address destinationAddress = new Ip4Address();

	/**
	 * embedded data.
	 */
	private DataUnit embeddedData = null;

	/**
	 * @return the embedded data.
	 */
	@Override
	public DataUnit getEmbeddedData() {
		return embeddedData;
	}

	/**
	 * @param dataUnit
	 *            embedded data to set
	 */
	@Override
	public void setEmbeddedData(final DataUnit dataUnit) {
		this.embeddedData = dataUnit;
	}

	@Override
	public DataUnitDecoder<Ip4Packet> getDecoder() {
		return DECODER;
	}

	@Override
	public byte[] encode() {
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(BitOperator.mergeByte(getInternetHeaderLength(),
				getVersion()));
		bab.append(serviceType.encode());
		bab.append(BitOperator.split(getTotalLength()));
		bab.append(BitOperator.split(getIdentification()));
		bab.append(calculateFlagsAndFragmentOffsetBytes());
		bab.append(getTimeToLive());
		bab.append(protocol.encode());

		if (headerChecksum == null) {
			headerChecksum = calculateHeaderChecksum();
		}

		bab.append(headerChecksum);
		bab.append(sourceAddress.encode());
		bab.append(destinationAddress.encode());

		return bab.toArray();
	}

	@Override
	public int size() {
		int embeddedDataSize = 0;
		if (getEmbeddedData() != null) {
			embeddedDataSize = getEmbeddedData().size();
		}

		return MIN_SIZE
				+ embeddedDataSize;
	}

	@Override
	public EtherType getEtherType() {
		return EtherType.IPV4;
	}

	/**
	 * @return the version
	 */
	public byte getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(@AssertFieldConstraints final byte version) {
		this.version = version;
	}

	/**
	 * @return the internetHeaderLength
	 */
	public byte getInternetHeaderLength() {
		return internetHeaderLength;
	}

	/**
	 * @param internetHeaderLength
	 *            the internetHeaderLength to set
	 */
	public void setInternetHeaderLength(
			@AssertFieldConstraints final byte internetHeaderLength) {
		this.internetHeaderLength = internetHeaderLength;
	}

	/**
	 * @return the serviceType
	 */
	public ServiceType getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType
	 *            the serviceType to set
	 */
	public void setServiceType(
			@AssertFieldConstraints final ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the totalLength
	 */
	public short getTotalLength() {
		return totalLength;
	}

	/**
	 * @param totalLength
	 *            the totalLength to set
	 */
	public void setTotalLength(final short totalLength) {
		this.totalLength = totalLength;
	}

	/**
	 * @return the identification
	 */
	public short getIdentification() {
		return identification;
	}

	/**
	 * @param identification
	 *            the identification to set
	 */
	public void setIdentification(final short identification) {
		this.identification = identification;
	}

	/**
	 * @return the fragmented
	 */
	public boolean isFragmented() {
		return fragmented;
	}

	/**
	 * @param fragmented
	 *            the fragmented to set
	 */
	public void setFragmented(final boolean fragmented) {
		this.fragmented = fragmented;
	}

	/**
	 * @return the lastFragment
	 */
	public boolean isLastFragment() {
		return lastFragment;
	}

	/**
	 * @param lastFragment
	 *            the lastFragment to set
	 */
	public void setLastFragment(final boolean lastFragment) {
		this.lastFragment = lastFragment;
	}

	/**
	 * @return the fragmentOffset
	 */
	public short getFragmentOffset() {
		return fragmentOffset;
	}

	/**
	 * @param fragmentOffset
	 *            the fragmentOffset to set
	 */
	public void setFragmentOffset(
			@AssertFieldConstraints final short fragmentOffset) {
		this.fragmentOffset = fragmentOffset;
	}

	/**
	 * @return the timeToLive
	 */
	public byte getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive
	 *            the timeToLive to set
	 */
	public void setTimeToLive(final byte timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the protocol
	 */
	public TransportProtocolIdentifier getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 *            the protocol to set
	 */
	public void setProtocol(
			@AssertFieldConstraints final TransportProtocolIdentifier protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the headerChecksum
	 */
	public byte[] getHeaderChecksum() {
		return headerChecksum;
	}

	/**
	 * @param headerChecksum
	 *            the headerChecksum to set
	 */
	public void setHeaderChecksum(
			@AssertFieldConstraints final byte[] headerChecksum) {
		this.headerChecksum = headerChecksum;
	}

	/**
	 * @return the sourceAddress
	 */
	public Ip4Address getSourceAddress() {
		return sourceAddress;
	}

	/**
	 * @param sourceAddress
	 *            the sourceAddress to set
	 */
	public void setSourceAddress(
			@AssertFieldConstraints final Ip4Address sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	/**
	 * @return the destinationAddress
	 */
	public Ip4Address getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * @param destinationAddress
	 *            the destinationAddress to set
	 */
	public void setDestinationAddress(
			@AssertFieldConstraints final Ip4Address destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * calculates the two bytes that contain the flags and the fragment offset.
	 * 
	 * @return the two bytes that contain the flags and the fragment offset
	 */
	private byte[] calculateFlagsAndFragmentOffsetBytes() {
		byte[] flagsOffset = BitOperator.split(fragmentOffset);

		byte fragmentedBit = 1;
		if (fragmented) {
			fragmentedBit = 0;
		}

		byte lastFragmentBit = 1;
		if (lastFragment) {
			lastFragmentBit = 0;
		}

		flagsOffset[1] |= (fragmentedBit << 7);
		flagsOffset[1] |= (lastFragmentBit << 5);

		return flagsOffset;
	}

	/**
	 * calculates the checksum of the Ip4Packet's header.
	 * 
	 * @return the checksum of the Ip4Packet's header
	 */
	private byte[] calculateHeaderChecksum() {
		int checksum = 0;

		checksum +=
				BitOperator.merge(getServiceType().encode()[0],
						BitOperator.mergeByte(
								getInternetHeaderLength(),
								getVersion()));
		checksum += getTotalLength();
		checksum += getIdentification();
		byte[] flagsOffset = calculateFlagsAndFragmentOffsetBytes();
		checksum += BitOperator.merge(flagsOffset[0], flagsOffset[1]);
		checksum +=
				BitOperator.merge(getProtocol().encode()[0],
						getTimeToLive());
		short[] sourceAddressShorts =
				BitOperator.splitToShort(sourceAddress.getValue());
		checksum += sourceAddressShorts[0]
				+ sourceAddressShorts[1];
		short[] destinationAddressShorts =
				BitOperator.splitToShort(destinationAddress
						.getValue());
		checksum += destinationAddressShorts[0]
				+ destinationAddressShorts[1];

		short[] checksumAndCarry = BitOperator.splitToShort(checksum);

		short calculatedChecksum =
				(short) (checksumAndCarry[0] + checksumAndCarry[1]);

		calculatedChecksum = BitOperator.invert(calculatedChecksum);

		return BitOperator.split(calculatedChecksum);
	}
}

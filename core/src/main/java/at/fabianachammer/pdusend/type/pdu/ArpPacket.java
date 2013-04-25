/**
 * 
 */
package at.fabianachammer.pdusend.type.pdu;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.ArpOperation;
import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.ArpPacketDecoder;
import at.fabianachammer.pdusend.util.ByteArrayBuilder;

/**
 * Represents an ARP (Address Resolution Protocol) segment. Currently supports
 * MAC as hardware address and IPv4 as network address.
 * 
 * @author fabian
 * 
 */
@Guarded
public class ArpPacket extends ProtocolDataUnit {

	/**
	 * size of an ARP segment.
	 */
	public static final int SIZE = 28;

	/**
	 * Sepcifies the decoder used for decoding data units of this type.
	 */
	private static final DataUnitDecoder<ArpPacket> DECODER =
			new ArpPacketDecoder();

	/**
	 * Specifies the hardware address type of the ARP segment.
	 */
	@NotNull
	private HardwareAddressType hardwareType =
			HardwareAddressType.UNKOWN;

	/**
	 * Specifies the protocol address type (specified by EtherType) of the ARP
	 * segment.
	 */
	@NotNull
	private EtherType protocolType = EtherType.UNKNOWN;

	/**
	 * length of the hardware address.
	 */
	private byte hardwareAddressLength = MacAddress.SIZE;

	/**
	 * length of the protocol address.
	 */
	private byte protocolAddressLength = Ip4Address.SIZE;

	/**
	 * Specifies the ARP operation of the ARP segment.
	 */
	@NotNull
	private ArpOperation operation = ArpOperation.UNKNOWN;

	/**
	 * Specifies the hardware address of the sender of the ARP segment.
	 */
	@NotNull
	private MacAddress senderHardwareAddress = new MacAddress();

	/**
	 * Specifies the protocol address of the sender of the ARP segment.
	 */
	@NotNull
	private Ip4Address senderProtocolAddress = new Ip4Address();

	/**
	 * Specifies the hardware address of the target of the ARP segment.
	 */
	@NotNull
	private MacAddress targetHardwareAddress = new MacAddress();

	/**
	 * Specifies the protocol address of the target of the ARP segment.
	 */
	@NotNull
	private Ip4Address targetProtocolAddress = new Ip4Address();

	@Override
	public final DataUnitDecoder<ArpPacket> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(getHardwareType().encode());
		bab.append(getProtocolType().encode());
		bab.append(getHardwareAddressLength());
		bab.append(getProtocolAddressLength());
		bab.append(getOperation().encode());
		bab.append(getSenderHardwareAddress().encode());
		bab.append(getSenderProtocolAddress().encode());
		bab.append(getTargetHardwareAddress().encode());
		bab.append(getTargetProtocolAddress().encode());

		return bab.toArray();
	}

	@Override
	public final int size() {
		return hardwareType.size()
				+ protocolType.size()
				+ operation.size()
				+ 2 // lengths of the addresses
				+ senderHardwareAddress.size()
				+ senderProtocolAddress.size()
				+ targetHardwareAddress.size()
				+ targetProtocolAddress.size();
	}
	
	@Override
	protected final <T extends DataUnit> boolean isEquals(final T obj) {
		ArpPacket rhs = (ArpPacket) obj;
		return new EqualsBuilder()
				.append(getHardwareType(), rhs.getHardwareType())
				.append(getProtocolType(), rhs.getProtocolType())
				.append(getOperation(), rhs.getOperation())
				.append(getSenderHardwareAddress(),
						rhs.getSenderHardwareAddress())
				.append(getSenderProtocolAddress(),
						rhs.getSenderProtocolAddress())
				.append(getTargetHardwareAddress(),
						rhs.getTargetHardwareAddress())
				.append(getTargetProtocolAddress(),
						rhs.getTargetProtocolAddress())
				.isEquals();
	}

	@Override
	public final int hashCode() {
		final int initial = 111;
		final int multiplier = 19;
		return new HashCodeBuilder(initial, multiplier)
				.append(getHardwareType()).append(getProtocolType())
				.append(getOperation())
				.append(getSenderHardwareAddress())
				.append(getSenderProtocolAddress())
				.append(getTargetHardwareAddress())
				.append(getTargetProtocolAddress()).hashCode();
	}

	/**
	 * @return the hardwareType
	 */
	public final HardwareAddressType getHardwareType() {
		return hardwareType;
	}

	/**
	 * @param hardwareType
	 *            the hardwareType to set
	 */
	public final void setHardwareType(
			@AssertFieldConstraints final HardwareAddressType hardwareType) {
		this.hardwareType = hardwareType;
	}

	/**
	 * @return the protocolType
	 */
	public final EtherType getProtocolType() {
		return protocolType;
	}

	/**
	 * @param protocolType
	 *            the protocolType to set
	 */
	public final void setProtocolType(
			@AssertFieldConstraints final EtherType protocolType) {
		this.protocolType = protocolType;
	}

	/**
	 * @return the hardwareAddressLength
	 */
	public final byte getHardwareAddressLength() {
		return hardwareAddressLength;
	}

	/**
	 * @param hardwareAddressLength
	 *            the hardwareAddressLength to set
	 */
	public final void setHardwareAddressLength(
			final byte hardwareAddressLength) {
		this.hardwareAddressLength = hardwareAddressLength;
	}

	/**
	 * @return the protocolAddressLength
	 */
	public final byte getProtocolAddressLength() {
		return protocolAddressLength;
	}

	/**
	 * @param protocolAddressLength
	 *            the protocolAddressLength to set
	 */
	public final void setProtocolAddressLength(
			final byte protocolAddressLength) {
		this.protocolAddressLength = protocolAddressLength;
	}

	/**
	 * @return the operation
	 */
	public final ArpOperation getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public final void setOperation(
			@AssertFieldConstraints final ArpOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the senderHardwareAddress
	 */
	public final MacAddress getSenderHardwareAddress() {
		return senderHardwareAddress;
	}

	/**
	 * @param senderHardwareAddress
	 *            the senderHardwareAddress to set
	 */
	public final void setSenderHardwareAddress(
			@AssertFieldConstraints final MacAddress senderHardwareAddress) {
		this.senderHardwareAddress = senderHardwareAddress;
	}

	/**
	 * @return the senderProtocolAddress
	 */
	public final Ip4Address getSenderProtocolAddress() {
		return senderProtocolAddress;
	}

	/**
	 * @param senderProtocolAddress
	 *            the senderProtocolAddress to set
	 */
	public final void setSenderProtocolAddress(
			@AssertFieldConstraints final Ip4Address senderProtocolAddress) {
		this.senderProtocolAddress = senderProtocolAddress;
	}

	/**
	 * @return the targetHardwareAddress
	 */
	public final MacAddress getTargetHardwareAddress() {
		return targetHardwareAddress;
	}

	/**
	 * @param targetHardwareAddress
	 *            the targetHardwareAddress to set
	 */
	public final void setTargetHardwareAddress(
			@AssertFieldConstraints final MacAddress targetHardwareAddress) {
		this.targetHardwareAddress = targetHardwareAddress;
	}

	/**
	 * @return the targetProtocolAddress
	 */
	public final Ip4Address getTargetProtocolAddress() {
		return targetProtocolAddress;
	}

	/**
	 * @param targetProtocolAddress
	 *            the targetProtocolAddress to set
	 */
	public final void setTargetProtocolAddress(
			@AssertFieldConstraints final Ip4Address targetProtocolAddress) {
		this.targetProtocolAddress = targetProtocolAddress;
	}
}

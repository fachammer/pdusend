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
import at.fabianachammer.pdusend.type.EtherType;
import at.fabianachammer.pdusend.type.HardwareAddressType;
import at.fabianachammer.pdusend.type.Ip4Address;
import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.ArpSegmentDecoder;
import at.fabianachammer.pdusend.util.ByteArrayBuilder;

/**
 * Represents an ARP (Address Resolution Protocol) segment. Currently supports
 * MAC as hardware address and IPv4 as network address.
 * 
 * @author fabian
 * 
 */
@Guarded
public class ArpSegment implements ProtocolDataUnit {

	/**
	 * size of an ARP segment.
	 */
	public static final int SIZE = 28;

	/**
	 * Specifies the hardware address length.
	 */
	private static final byte HARDWARE_ADDRESS_LENGTH =
			MacAddress.SIZE;

	/**
	 * Specifies the protocol address length.
	 */
	private static final byte PROTOCOL_ADDRESS_LENGTH =
			Ip4Address.SIZE;

	/**
	 * Sepcifies the decoder used for decoding data units of this type.
	 */
	private static final DataUnitDecoder<ArpSegment> DECODER =
			new ArpSegmentDecoder();

	/**
	 * Specifies the hardware address type of the ARP segment.
	 */
	@NotNull
	private HardwareAddressType hardwareType =
			HardwareAddressType.Ethernet;

	/**
	 * Specifies the protocol address type (specified by EtherType) of the ARP
	 * segment.
	 */
	@NotNull
	private EtherType protocolType = EtherType.Unknown;

	/**
	 * Specifies the ARP operation of the ARP segment.
	 */
	@NotNull
	private ArpOperation operation = ArpOperation.Request;

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
	public final DataUnitDecoder<ArpSegment> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(hardwareType.encode());
		bab.append(protocolType.encode());
		bab.append(HARDWARE_ADDRESS_LENGTH);
		bab.append(PROTOCOL_ADDRESS_LENGTH);
		bab.append(operation.encode());
		bab.append(senderHardwareAddress.encode());
		bab.append(senderProtocolAddress.encode());
		bab.append(targetHardwareAddress.encode());
		bab.append(targetProtocolAddress.encode());

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
	public final boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof ArpSegment) {
			ArpSegment rhs = (ArpSegment) obj;
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
		return false;
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

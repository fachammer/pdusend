/**
 * 
 */
package at.fabianachammer.nsend.pdu;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.nsend.util.BitOperator;
import at.fabianachammer.nsend.util.ByteArrayBuilder;

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
	 * Specifies the hardware address type of the ARP segment.
	 */
	@NotNull
	private HardwareAddressType hardwareType;

	/**
	 * Specifies the protocol address type (specified by EtherType) of the ARP
	 * segment.
	 */
	@NotNull
	private EtherType protocolType;

	/**
	 * Specifies the ARP operation of the ARP segment.
	 */
	@NotNull
	private ArpOperation operation;

	/**
	 * Specifies the hardware address of the sender of the ARP segment.
	 */
	@NotNull
	private MacAddress senderHardwareAddress;

	/**
	 * Specifies the protocol address of the sender of the ARP segment.
	 */
	@NotNull
	private Ip4Address senderProtocolAddress;

	/**
	 * Specifies the hardware address of the target of the ARP segment.
	 */
	@NotNull
	private MacAddress targetHardwareAddress;

	/**
	 * Specifies the protocol address of the target of the ARP segment.
	 */
	@NotNull
	private Ip4Address targetProtocolAddress;

	@Override
	public final byte[] toBytes() {
		ByteArrayBuilder bab = new ByteArrayBuilder();

		bab.append(BitOperator.split(hardwareType.getId()));
		bab.append(BitOperator.split(protocolType.getId()));
		bab.append(HARDWARE_ADDRESS_LENGTH);
		bab.append(PROTOCOL_ADDRESS_LENGTH);
		bab.append(BitOperator.split(operation.getId()));
		bab.append(senderHardwareAddress.getBytes());
		bab.append(senderProtocolAddress.getBytes());
		bab.append(targetHardwareAddress.getBytes());
		bab.append(targetProtocolAddress.getBytes());

		return bab.toArray();
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

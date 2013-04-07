package at.fabianachammer.nsend.pdu;

/**
 * Represents an Ethernet frame specified in IEEE 802.3.
 * 
 * @author fabian
 * 
 */
public class EthernetHeader {

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
}

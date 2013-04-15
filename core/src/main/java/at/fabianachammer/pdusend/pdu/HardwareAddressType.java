/**
 * 
 */
package at.fabianachammer.pdusend.pdu;

/**
 * Represents a hardware address type that is used for protocol identification
 * in ARP on the data link layer.
 * 
 * @author fabian
 * 
 */
public enum HardwareAddressType {
	/**
	 * Ethernet protocol.
	 */
	Ethernet((short) 1);

	/**
	 * Specifies the id of the hardware address type.
	 */
	private short id;

	/**
	 * Creates a new hardware address type with the specified id.
	 * 
	 * @param id
	 *            id of the hardware address type
	 */
	private HardwareAddressType(final short id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}
}

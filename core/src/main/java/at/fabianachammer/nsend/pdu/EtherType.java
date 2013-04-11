package at.fabianachammer.nsend.pdu;

/**
 * Enumeration of possible EtherTypes.
 * 
 * @author fabian
 * 
 */
public enum EtherType {
	/**
	 * Internet Protocol version 4.
	 */
	IPv4((short) 0x0800),

	/**
	 * Address Resolution Protocol.
	 */
	ARP((short) 0x0806),

	/**
	 * Wake on LAN.
	 */
	WoL((short) 0x0842),

	/**
	 * Reverse Address Resolution Protocol.
	 */
	RARP((short) 0x8035),

	/**
	 * Internet Protocol version 6.
	 */
	IPv6((short) 0x86DD);

	/**
	 * ID for the protocol specified by IEEE 802.3.
	 */
	private short id;

	/**
	 * Creates a new EtherType with the specified ID.
	 * 
	 * @param id
	 *            ID of the EtherType
	 */
	private EtherType(final short id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}
}

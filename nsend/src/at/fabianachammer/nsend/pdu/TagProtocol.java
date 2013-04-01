package at.fabianachammer.nsend.pdu;

/**
 * Enumeration of possible TagProtocols.
 * 
 * @author fabian
 * 
 */
public enum TagProtocol {

	/**
	 * IEEE 802.1Q Tagging Protocol (0x8100 in HEX).
	 */
	IEEE_802_1Q((short) 0x8100);

	/**
	 * Identifier of the protocol by bytes.
	 */
	private short identifier;

	/**
	 * Creates a new TagProtocol with the given identifier.
	 * 
	 * @param identifier
	 *            identifier the protocol should have
	 */
	private TagProtocol(final short identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the identifier
	 */
	public short getIdentifier() {
		return identifier;
	}
}

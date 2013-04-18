package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.TagProtocolDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Enumeration of possible tag protocols for VLAN tagging purposes.
 * 
 * @author fabian
 * 
 */
public enum TagProtocol implements DataUnit {

	/**
	 * Unknown tag protocol.
	 */
	Unknown((short) 0),
	/**
	 * IEEE 802.1Q Tagging Protocol (0x8100 in HEX).
	 */
	IEEE_802_1Q((short) 0x8100);

	/**
	 * decoder for tag protocols.
	 */
	private static final DataUnitDecoder<TagProtocol> DECODER =
			new TagProtocolDecoder();

	/**
	 * Identifier of the protocol.
	 */
	private short id;

	/**
	 * Creates a new TagProtocol with the given identifier.
	 * 
	 * @param id
	 *            id the protocol should have
	 */
	private TagProtocol(final short id) {
		setId(id);
	}

	@Override
	public DataUnitDecoder<TagProtocol> getDecoder() {
		return DECODER;
	}

	@Override
	public byte[] encode() {
		return BitOperator.split(getId());
	}

	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final short id) {
		this.id = id;
	}
}

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
public class TagProtocol extends DataUnit {

	/**
	 * Unknown tag protocol.
	 */
	public static final TagProtocol UNKNOWN = new TagProtocol(
			(short) 0);

	/**
	 * IEEE 802.1Q Tagging Protocol (0x8100 in HEX).
	 */
	public static final TagProtocol IEEE_802_1Q = new TagProtocol(
			(short) 0x8100);

	/**
	 * array with predefined tag protocols.
	 */
	public static final TagProtocol[] VALUES =
			{ UNKNOWN, IEEE_802_1Q };

	/**
	 * size of tag protocol identifiers in bytes.
	 */
	public static final int SIZE = 2;

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
	public TagProtocol(final short id) {
		setId(id);
	}

	@Override
	public final DataUnitDecoder<TagProtocol> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return BitOperator.split(getId());
	}

	@Override
	public final int size() {
		return SIZE;
	}
	
	/**
	 * @return the id
	 */
	public final short getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	private void setId(final short id) {
		this.id = id;
	}
}

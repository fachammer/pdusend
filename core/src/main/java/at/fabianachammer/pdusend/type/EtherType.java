package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Enumeration of possible EtherTypes.
 * 
 * @author fabian
 * 
 */
public enum EtherType implements DataUnit {

	/**
	 * Unknown protocol.
	 */
	Unknown((short) 0),
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
	 * decoder for EtherTypes.
	 */
	private static final DataUnitDecoder<EtherType> ENCODING =
			new EtherTypeDecoder();

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
		setId(id);
	}

	@Override
	public DataUnitDecoder<EtherType> getDecoder() {
		return ENCODING;
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

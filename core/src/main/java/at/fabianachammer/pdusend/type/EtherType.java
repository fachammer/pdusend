package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.ArpSegmentDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Enumeration of possible EtherTypes.
 * 
 * @author fabian
 * 
 */
public enum EtherType implements ProtocolIdentifier {

	/**
	 * Unknown protocol.
	 */
	Unknown((short) 0, new RawDataUnitDecoder()),

	/**
	 * Address Resolution Protocol.
	 */
	ARP((short) 0x0806, new ArpSegmentDecoder());

	/**
	 * size of EtherTypes in bytes.
	 */
	public static final int SIZE = 2;

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
	 * decoder that is used for decoding data units of the protocol specified by
	 * the EtherType.
	 */
	private DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder;

	/**
	 * Creates a new EtherType with the specified ID.
	 * 
	 * @param id
	 *            ID of the EtherType
	 * @param protocolDecoder
	 *            decoder that is used for decoding the protocol specified by
	 *            the EtherType
	 */
	private EtherType(
			final short id,
			final DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder) {
		setId(id);
		setProtocolDecoder(protocolDecoder);
	}

	@Override
	public DataUnitDecoder<EtherType> getDecoder() {
		return ENCODING;
	}

	@Override
	public byte[] encode() {
		return BitOperator.split(getId());
	}

	@Override
	public int size() {
		return SIZE;
	}

	@Override
	public DataUnitDecoder<? extends ProtocolDataUnit> getProtocolDecoder() {
		return protocolDecoder;
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

	/**
	 * @param protocolDecoder
	 *            the protocolDecoder to set
	 */
	public void setProtocolDecoder(
			final DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder) {
		this.protocolDecoder = protocolDecoder;
	}
}

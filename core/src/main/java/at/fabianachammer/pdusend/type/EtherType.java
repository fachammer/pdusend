package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.ArpPacketDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.Ip4PacketDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Class that represents EtherTypes.
 * 
 * @author fabian
 * 
 */
public class EtherType extends DataUnit implements ProtocolIdentifier {

	/**
	 * Unknown protocol.
	 */
	public static final EtherType UNKNOWN = new EtherType((short) 0,
			new RawDataUnitDecoder());

	/**
	 * Address Resolution Protocol.
	 */
	public static final EtherType ARP = new EtherType((short) 0x0806,
			new ArpPacketDecoder());

	/**
	 * Internet Protocol version 4.
	 */
	public static final EtherType IPV4 = new EtherType((short) 0x800,
			new Ip4PacketDecoder());

	/**
	 * array with ossible EtherTypes.
	 */
	public static final EtherType[] VALUES = { UNKNOWN, ARP, IPV4 };

	/**
	 * size of EtherTypes in bytes.
	 */
	public static final int SIZE = 2;

	/**
	 * decoder for EtherTypes.
	 */
	private static final DataUnitDecoder<EtherType> DECODER =
			new EtherTypeDecoder();

	/**
	 * ID for the protocol specified by IEEE 802.3.
	 */
	private short id = 0;

	/**
	 * decoder that is used for decoding data units of the protocol specified by
	 * the EtherType.
	 */
	private DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder =
			new RawDataUnitDecoder();

	/**
	 * creates anew EtherType with the specified ID and a raw data unit decoder.
	 * 
	 * @param id
	 *            ID of the EtherType
	 */
	public EtherType(final short id) {
		for (EtherType e : VALUES) {
			if (id == e.getId()) {
				setId(id);
				setProtocolDecoder(e.getProtocolDecoder());
				return;
			}
		}
		setId(id);
	}

	/**
	 * Creates a new EtherType with the specified ID and protocol decoder.
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
	public final DataUnitDecoder<EtherType> getDecoder() {
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

	@Override
	public final DataUnitDecoder<? extends ProtocolDataUnit> getProtocolDecoder() {
		return protocolDecoder;
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

	/**
	 * @param protocolDecoder
	 *            the protocolDecoder to set
	 */
	private void setProtocolDecoder(
			final DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder) {
		this.protocolDecoder = protocolDecoder;
	}
}

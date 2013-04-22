package at.fabianachammer.pdusend.type;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.EtherTypeDecoder;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.ArpPacketDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Class that represents EtherTypes.
 * 
 * @author fabian
 * 
 */
public class EtherType implements ProtocolIdentifier {

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
	 * array with ossible EtherTypes.
	 */
	public static final EtherType[] VALUES = { UNKNOWN, ARP };

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

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof EtherType) {
			EtherType rhs = (EtherType) obj;
			return new EqualsBuilder().append(getId(), rhs.getId())
					.isEquals();
		}

		return false;
	}

	@Override
	public final int hashCode() {
		final int initial = 147;
		final int multiplier = 23;
		return new HashCodeBuilder(initial, multiplier).append(
				getId()).hashCode();
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

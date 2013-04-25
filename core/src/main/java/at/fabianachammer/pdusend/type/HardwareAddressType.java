/**
 * 
 */
package at.fabianachammer.pdusend.type;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.HardwareAddressTypeDecoder;
import at.fabianachammer.pdusend.type.pdu.ProtocolDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.EthernetFrameDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Represents a hardware address type that is used for protocol identification
 * in ARP on the data link layer.
 * 
 * @author fabian
 * 
 */
public class HardwareAddressType extends DataUnit implements
		ProtocolIdentifier {

	/**
	 * size of a hardware address type in bytes.
	 */
	public static final int SIZE = 2;

	/**
	 * Unknown hardware address type.
	 */
	public static final HardwareAddressType UNKOWN =
			new HardwareAddressType((short) 0,
					new RawDataUnitDecoder());

	/**
	 * Ethernet protocol.
	 */
	public static final HardwareAddressType ETHERNET =
			new HardwareAddressType((short) 1,
					new EthernetFrameDecoder());

	/**
	 * array of predefined hardware address types.
	 */
	public static final HardwareAddressType[] VALUES = {
			UNKOWN, ETHERNET };

	/**
	 * decoder for hardware address types.
	 */
	private static final DataUnitDecoder<HardwareAddressType> DECODER =
			new HardwareAddressTypeDecoder();

	/**
	 * id of the hardware address type.
	 */
	private short id = 0;

	/**
	 * decoder that is used for decoding data units of the protocol specified by
	 * the hardware address type.
	 */
	private DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder =
			new RawDataUnitDecoder();

	/**
	 * creates a new hardware address type with the specified id and a raw data
	 * unit as protocol decoder.
	 * 
	 * @param id
	 *            ID of the hardware address type
	 */
	public HardwareAddressType(final short id) {
		for (HardwareAddressType hat : VALUES) {
			if (id == hat.getId()) {
				setId(id);
				setProtocolDecoder(hat.getProtocolDecoder());
				return;
			}
		}

		setId(id);
	}

	/**
	 * Creates a new hardware address type with the specified id and protocol
	 * decoder.
	 * 
	 * @param id
	 *            id of the hardware address type
	 * @param protocolDecoder
	 *            decoder that is used for decoding data units of the protocol
	 *            specified by the hardware address type
	 */
	private HardwareAddressType(
			final short id,
			final DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder) {
		setId(id);
	}

	@Override
	public final DataUnitDecoder<HardwareAddressType> getDecoder() {
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
	protected final <T extends DataUnit> boolean isEquals(final T obj) {
		HardwareAddressType rhs = (HardwareAddressType) obj;
		return new EqualsBuilder().append(getId(), rhs.getId())
				.isEquals();
	}

	@Override
	public final int hashCode() {
		final int initial = 43;
		final int multiplier = 7;
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

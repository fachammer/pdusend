/**
 * 
 */
package at.fabianachammer.pdusend.type;

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
public enum HardwareAddressType implements ProtocolIdentifier {

	/**
	 * Unknown hardware address type.
	 */
	Unknown((short) 0, new RawDataUnitDecoder()),
	/**
	 * Ethernet protocol.
	 */
	Ethernet((short) 1, new EthernetFrameDecoder());

	/**
	 * size of a hardware address type in bytes.
	 */
	public static final int SIZE = 2;
	
	/**
	 * decoder for hardware address types.
	 */
	private static final DataUnitDecoder<HardwareAddressType> DECODER =
			new HardwareAddressTypeDecoder();

	/**
	 * id of the hardware address type.
	 */
	private short id;

	/**
	 * decoder that is used for decoding data units of the protocol specified by
	 * the hardware address type.
	 */
	private DataUnitDecoder<? extends ProtocolDataUnit> protocolDecoder;

	/**
	 * Creates a new hardware address type with the specified id.
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
	public DataUnitDecoder<HardwareAddressType> getDecoder() {
		return DECODER;
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

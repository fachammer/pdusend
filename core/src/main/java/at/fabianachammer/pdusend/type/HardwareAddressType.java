/**
 * 
 */
package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.HardwareAddressTypeDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Represents a hardware address type that is used for protocol identification
 * in ARP on the data link layer.
 * 
 * @author fabian
 * 
 */
public enum HardwareAddressType implements DataUnit {

	/**
	 * Unknown hardware address type.
	 */
	Unknown((short) 0),
	/**
	 * Ethernet protocol.
	 */
	Ethernet((short) 1);

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
	 * Creates a new hardware address type with the specified id.
	 * 
	 * @param id
	 *            id of the hardware address type
	 */
	private HardwareAddressType(final short id) {
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

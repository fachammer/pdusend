/**
 * 
 */
package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.MacAddressDecoder;
import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

/**
 * Represents a Media Access Control (MAC) address.
 * 
 * @author fabian
 * 
 */
@Guarded
public class MacAddress implements DataUnit {

	/**
	 * size of a MAC address in bytes.
	 */
	public static final int SIZE = 6;

	/**
	 * decoder for MAC addresses.
	 */
	private static final MacAddressDecoder DECODER =
			new MacAddressDecoder();

	/**
	 * The bytes that make up the MAC address.
	 */
	@Size(min = SIZE, max = SIZE)
	@NotNull
	private byte[] bytes = new byte[SIZE];

	/**
	 * Creates a new MAC address with all bits set to zero.
	 */
	public MacAddress() {

	}

	/**
	 * Creates a new MAC address with the specified bytes.
	 * 
	 * @param bytes
	 *            byte array that contains the data of a MAC address
	 */
	public MacAddress(final byte[] bytes) {
		setBytes(bytes);
	}

	@Override
	public final DataUnitDecoder<MacAddress> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return getBytes();
	}

	/**
	 * @return the address
	 */
	public final byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the address to set
	 */
	public final void setBytes(
			@AssertFieldConstraints final byte[] bytes) {
		this.bytes = bytes;
	}
}

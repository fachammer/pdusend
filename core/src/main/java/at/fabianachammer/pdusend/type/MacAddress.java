/**
 * 
 */
package at.fabianachammer.pdusend.type;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.MacAddressDecoder;
import at.fabianachammer.pdusend.util.ByteConverter;

/**
 * Represents a Media Access Control (MAC) address.
 * 
 * @author fabian
 * 
 */
@Guarded
public class MacAddress extends DataUnit {

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
	private byte[] value = new byte[SIZE];

	/**
	 * Creates a new MAC address with all bits set to zero.
	 */
	public MacAddress() {

	}

	/**
	 * Creates a new MAC address with the specified bytes.
	 * 
	 * @param value
	 *            byte array that contains the data of a MAC address
	 */
	public MacAddress(final byte[] value) {
		setValue(value);
	}

	@Override
	public final DataUnitDecoder<MacAddress> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return getValue();
	}

	@Override
	public final int size() {
		return SIZE;
	}

	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < value.length; i++) {
			sb.append(ByteConverter.toHexString(value[i]));
			sb.append(":");
		}

		return sb.substring(0, sb.length() - 1).toString();
	}

	/**
	 * @return the value
	 */
	public final byte[] getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(
			@AssertFieldConstraints final byte[] value) {
		this.value = value;
	}
}

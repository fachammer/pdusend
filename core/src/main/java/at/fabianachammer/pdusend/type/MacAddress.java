/**
 * 
 */
package at.fabianachammer.pdusend.type;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.MacAddressDecoder;

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
	protected final <T extends DataUnit> boolean isEquals(final T obj) {
		MacAddress rhs = (MacAddress) obj;
		return new EqualsBuilder().append(getValue(), rhs.getValue())
				.isEquals();
	}

	@Override
	public final int hashCode() {
		final int intial = 239;
		final int multiplier = 63;
		return new HashCodeBuilder(intial, multiplier).append(
				getValue()).hashCode();
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

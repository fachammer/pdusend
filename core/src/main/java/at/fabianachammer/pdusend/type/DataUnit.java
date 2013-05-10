/**
 * 
 */
package at.fabianachammer.pdusend.type;

import java.util.zip.CRC32;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.util.ArrayOperator;

/**
 * Defines an interface for classes that can be sent as a whole from a network
 * interface. Directly implementing classes usually define a byte array that has
 * certain constraints.
 * 
 * @author fabian
 * 
 */
public abstract class DataUnit {

	/**
	 * Returns the decoder of the data unit.
	 * 
	 * @return decoder of the data unit
	 */
	public abstract DataUnitDecoder<? extends DataUnit> getDecoder();

	/**
	 * Encodes the data unit into a byte array.
	 * 
	 * @return the encoded data unit
	 */
	public abstract byte[] encode();

	/**
	 * Returns the size of the data unit in bytes.
	 * 
	 * @return size of the data unit in bytes
	 */
	public int size() {
		return encode().length;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() == this.getClass()) {
			return ArrayOperator.arrayEquals(encode(), this
					.getClass().cast(obj).encode());
		}

		return false;
	}

	@Override
	public int hashCode() {
		CRC32 crc = new CRC32();
		crc.update(this.getClass().getName().getBytes());
		int initial = nextOddNumber((int) crc.getValue());
		crc.update(this.getClass().getCanonicalName().getBytes());
		int multiplier = nextOddNumber((int) crc.getValue());
		return new HashCodeBuilder(initial, multiplier).append(
				encode()).hashCode();
	}

	/**
	 * Returns the next odd number of the given number, if the number is even.
	 * Otherwise it returns the given number.
	 * 
	 * @param n
	 *            number to be checked for oddity
	 * @return n, if it is odd, n + 1 otherwise
	 */
	private int nextOddNumber(final int n) {
		if (n % 2 == 0) {
			return n + 1;
		}

		return n;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}

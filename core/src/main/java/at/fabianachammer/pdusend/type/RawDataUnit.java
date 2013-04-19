/**
 * 
 */
package at.fabianachammer.pdusend.type;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.RawDataUnitDecoder;

/**
 * This class serves as a data unit that encapsulates raw data.
 * 
 * @author fabian
 * 
 */
@Guarded
public class RawDataUnit implements DataUnit {

	/**
	 * decoder for raw data units.
	 */
	private static final DataUnitDecoder<RawDataUnit> DECODER =
			new RawDataUnitDecoder();

	/**
	 * data this raw data unit contains.
	 */
	@NotNull
	private byte[] data = new byte[0];

	/**
	 * empty constructor.
	 */
	public RawDataUnit() {

	}

	/**
	 * Creates a new raw data unit with the specified data.
	 * 
	 * @param data
	 *            raw byte data this data unit should have
	 */
	public RawDataUnit(final byte... data) {
		setData(data);
	}

	@Override
	public final DataUnitDecoder<RawDataUnit> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return getData();
	}

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof RawDataUnit) {
			RawDataUnit rhs = (RawDataUnit) obj;
			return new EqualsBuilder().append(getData(),
					rhs.getData()).isEquals();
		}

		return false;
	}

	@Override
	public final int hashCode() {
		final int initial = 121;
		final int multiplier = 135;
		return new HashCodeBuilder(initial, multiplier).append(
				getData()).hashCode();
	}

	/**
	 * @return the data
	 */
	public final byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public final void setData(
			@AssertFieldConstraints final byte... data) {
		this.data = data;
	}
}

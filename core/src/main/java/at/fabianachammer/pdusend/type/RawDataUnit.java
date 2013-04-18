/**
 * 
 */
package at.fabianachammer.pdusend.type;

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
	public RawDataUnit(final byte[] data) {
		setData(data);
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
	public final void setData(@AssertFieldConstraints final byte[] data) {
		this.data = data;
	}

	@Override
	public final DataUnitDecoder<RawDataUnit> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return getData();
	}

}

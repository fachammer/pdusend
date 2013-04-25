/**
 * 
 */
package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;

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
	public abstract int size();

	/**
	 * determines whether this object is equal to a given data unit.
	 * 
	 * @param <T>
	 *            type of the object that gets compared
	 * @param obj
	 *            data unit to which equality is checked
	 * @return true, if the data unit is equal, false otherwise
	 */
	protected abstract <T extends DataUnit> boolean isEquals(T obj);

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() == this.getClass()) {
			return isEquals(this.getClass().cast(obj));
		}

		return false;
	}

	@Override
	public abstract int hashCode();
}

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
public interface DataUnit {

	/**
	 * Returns the decoder of the data unit.
	 * 
	 * @return decoder of the data unit
	 */
	DataUnitDecoder<? extends DataUnit> getDecoder();

	/**
	 * Encodes the data unit into a byte array.
	 * 
	 * @return the encoded data unit
	 */
	byte[] encode();

	/**
	 * Returns the size of the data unit in bytes.
	 * 
	 * @return size of the data unit in bytes
	 */
	int size();
}

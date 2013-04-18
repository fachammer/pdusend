/**
 * 
 */
package at.fabianachammer.pdusend.type.decoder;

import at.fabianachammer.pdusend.type.DataUnit;

/**
 * Interface that needs implementing classes to provide a method for decoding a
 * byte array into an instance of <T>.
 * 
 * @param <T>
 *            type that this decoder decodes
 * @author fabian
 * 
 */
public interface DataUnitDecoder<T extends DataUnit> {

	/**
	 * Decodes a byte array into an instance of <T>.
	 * 
	 * @param data
	 *            data to be decoded
	 * @return instance of <T> decoded from the given data
	 */
	T decode(byte... data);
}

/**
 * 
 */
package at.fabianachammer.pdusend.pdu;

/**
 * Interface that provides support for parsing bytes to a certain network type.
 * 
 * @author fabian
 * 
 */
public interface NetworkType {

	int getSize();
	
	/**
	 * Decodes an array of bytes into an object of type <T>.
	 * 
	 * @param data
	 *            byte array that gets decoded
	 */
	void decode(byte[] data);

	/**
	 * Encodes an object of type <T> into an array of bytes.
	 * 
	 * @return byte array that represents the encoded object
	 */
	byte[] encode();
}

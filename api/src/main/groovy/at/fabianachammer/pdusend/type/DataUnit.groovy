package at.fabianachammer.pdusend.type


/**
 * Unit of data that can be sent over a network.
 * 
 * @author fabian
 *
 */
interface DataUnit {

	/**
	 * @return representation of the data unit in bytes to ensure it can be sent over the network
	 */
	byte[] encode()
	
	/**
	 * @return size of the data unit in bits
	 */
	int sizeInBits()
}

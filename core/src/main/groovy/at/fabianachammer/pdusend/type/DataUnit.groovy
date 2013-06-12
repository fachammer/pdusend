package at.fabianachammer.pdusend.type

/**
 * unit of data that can be sent over the network
 * 
 * @author fabian
 *
 */
interface DataUnit {

	/**
	 * @return representation of the data unit in bytes to ensure it can be sent over the network
	 */
	byte[] encode()
}

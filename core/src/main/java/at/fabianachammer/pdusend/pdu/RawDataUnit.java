/**
 * 
 */
package at.fabianachammer.pdusend.pdu;

/**
 * This class serves as a protocol data unit that encapsulates raw data.
 * 
 * @author fabian
 * 
 */
public class RawDataUnit implements ProtocolDataUnit {

	/**
	 * The data this PDU contains.
	 */
	private byte[] data;

	/**
	 * Creates a new RawDataUnit with the specified data.
	 * @param data raw byte data this data unit should have
	 */
	public RawDataUnit(final byte[] data) {
		this.data = data;
	}

	@Override
	public final byte[] toBytes() {
		return data;
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
	public final void setData(final byte[] data) {
		this.data = data;
	}

}

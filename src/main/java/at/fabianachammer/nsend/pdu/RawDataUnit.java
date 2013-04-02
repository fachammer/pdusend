/**
 * 
 */
package at.fabianachammer.nsend.pdu;

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
	private Byte[] data;

	/**
	 * Creates a new RawDataUnit with the specified data.
	 * @param data raw byte data this data unit should have
	 */
	public RawDataUnit(final Byte[] data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.fabianachammer.nsend.pdu.ProtocolDataUnit#toBytes()
	 */
	@Override
	public final Byte[] toBytes() {
		return data;
	}

	/**
	 * @return the data
	 */
	public final Byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public final void setData(final Byte[] data) {
		this.data = data;
	}

}

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

	private byte[] data;

	public RawDataUnit(byte[] data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.fabianachammer.nsend.pdu.ProtocolDataUnit#toBytes()
	 */
	@Override
	public byte[] toBytes() {
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
	public final void setData(byte[] data) {
		this.data = data;
	}

}

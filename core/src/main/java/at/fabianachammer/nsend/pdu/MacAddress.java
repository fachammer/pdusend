/**
 * 
 */
package at.fabianachammer.nsend.pdu;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

/**
 * Represents a Media Access Control (MAC) address.
 * 
 * @author fabian
 * 
 */
@Guarded
public class MacAddress {

	/**
	 * Specifies the size of a MAC address in bytes.
	 */
	public static final int SIZE = 6;

	/**
	 * The bytes that make up the MAC address.
	 */
	@Size(min = SIZE, max = SIZE)
	@NotNull
	private byte[] bytes = new byte[SIZE];

	/**
	 * Creates a new MAC address with all bits set to zero.
	 */
	public MacAddress() {

	}

	/**
	 * Creates a new MAC address with the specified bytes.
	 * 
	 * @param bytes
	 *            byte array that contains the data of a MAC address
	 */
	public MacAddress(final byte[] bytes) {
		setBytes(bytes);
	}

	/**
	 * @return the address
	 */
	public final byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the address to set
	 */
	public final void setBytes(
			@AssertFieldConstraints final byte[] bytes) {
		this.bytes = bytes;
	}
}

/**
 * 
 */
package at.fabianachammer.pdusend.pdu;

import net.sf.oval.constraint.AssertFieldConstraints;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

/**
 * Represents an IPv4 (Internet Protocol version 4) address.
 * 
 * @author fabian
 * 
 */
@Guarded
public class Ip4Address implements NetworkType {

	/**
	 * Specifies the number of bytes that an IPv4 address has.
	 */
	public static final int SIZE = 4;

	/**
	 * Specifies the bytes that make up the IPv4 address.
	 */
	@Size(min = SIZE, max = SIZE)
	@NotNull
	private byte[] bytes = new byte[SIZE];

	/**
	 * Creates an IPv4 address with all bits set to zero.
	 */
	public Ip4Address() {

	}

	/**
	 * Creates an IPv4 address with the specified bytes.
	 * 
	 * @param bytes
	 *            bytes that make up the IPv4 address
	 */
	public Ip4Address(final byte[] bytes) {
		setBytes(bytes);
	}

	/**
	 * @return the bytes
	 */
	public final byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public final void setBytes(
			@AssertFieldConstraints final byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public final int getSize() {
		return 4;
	}

	@Override
	public final void decode(final byte[] data) {
		setBytes(data);
	}

	@Override
	public final byte[] encode() {
		return getBytes();
	}

}

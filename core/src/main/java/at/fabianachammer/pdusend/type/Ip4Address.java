/**
 * 
 */
package at.fabianachammer.pdusend.type;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.Ip4AddressDecoder;
import at.fabianachammer.pdusend.util.BitOperator;

/**
 * Represents an IPv4 (Internet Protocol version 4) address.
 * 
 * @author fabian
 * 
 */
public class Ip4Address extends DataUnit {

	/**
	 * size of an IPv4 address in bytes.
	 */
	public static final byte SIZE = 4;

	/**
	 * decoder for IPv4 addresses.
	 */
	private static final DataUnitDecoder<Ip4Address> DECODER =
			new Ip4AddressDecoder();

	/**
	 * Specifies the bytes that make up the IPv4 address.
	 */
	private int value;

	/**
	 * Creates an IPv4 address with all bits set to zero.
	 */
	public Ip4Address() {

	}

	@Override
	public final DataUnitDecoder<Ip4Address> getDecoder() {
		return DECODER;
	}

	@Override
	public final byte[] encode() {
		return BitOperator.split(value);
	}

	@Override
	public final int size() {
		return SIZE;
	}

	@Override
	public final String toString() {
		byte[] bytes = BitOperator.split(value);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]);
			sb.append(".");
		}

		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * Creates an IPv4 address with the specified bytes.
	 * 
	 * @param value
	 *            value that makes up the IPv4 address
	 */
	public Ip4Address(final int value) {
		setValue(value);
	}

	/**
	 * @return the value
	 */
	public final int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(final int value) {
		this.value = value;
	}
}

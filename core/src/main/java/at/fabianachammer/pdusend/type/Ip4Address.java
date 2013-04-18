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
public class Ip4Address implements DataUnit {

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
	public boolean equals(Object obj) {
		if (obj instanceof Ip4Address) {
			return ((Ip4Address) obj).getValue() == getValue();
		}

		return false;
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

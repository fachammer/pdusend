/**
 * 
 */
package at.fabianachammer.pdusend.aspect;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.pdu.Ip4Address;

/**
 * @author fabian
 * 
 */
public class Ip4AddressValidationTest {

	@Test
	public final void testConstructIp4AddressWithValidByteArrayWorks() {
		byte[] validByteArray = new byte[4];

		new Ip4Address(validByteArray);
	}

	@Test
	public final void testSetValidByteArrayOnIp4AddressWorks() {
		byte[] validByteArray = new byte[4];
		Ip4Address ip4Address = new Ip4Address();

		ip4Address.setBytes(validByteArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testConstructIp4AddressWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		byte[] illegalSizeByteArray = new byte[5];

		new Ip4Address(illegalSizeByteArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSizeByteArrayOnIp4AddressThrowsIllegalArgumentException() {
		byte[] illegalSizeByteArray = new byte[5];
		Ip4Address ip4Address = new Ip4Address();

		ip4Address.setBytes(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testConstructIp4AddressWithNullByteArrayThrowsNullPointerException() {
		new Ip4Address(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullByteArrayOnIp4AddressThrowsNullPointerException() {
		Ip4Address ip4Address = new Ip4Address();

		ip4Address.setBytes(null);
	}
}

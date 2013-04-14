/**
 * 
 */
package at.fabianachammer.nsend.aspect;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.nsend.pdu.MacAddress;

/**
 * @author fabian
 * 
 */
public class MacAddressValidationTest {

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSizeByteArrayOnMacAddressThrowsIllegalArgumentException() {
		MacAddress macAddress = new MacAddress();
		byte[] illegalSizeByteArray = new byte[7];

		macAddress.setBytes(illegalSizeByteArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testConstructMacAddressWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		byte[] illegalSizeByteArray = new byte[7];

		new MacAddress(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullByteArrayOnMacAddressThrowsNullPointerException() {
		MacAddress macAddress = new MacAddress();

		macAddress.setBytes(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testConstructMacAddressWithNullByteArrayThrowsNullPointerException() {
		new MacAddress(null);
	}

	@Test
	public final void testSetValidByteArrayOnMacAddressWorks() {
		MacAddress macAddress = new MacAddress();
		byte[] validMacByteArray = new byte[6];

		macAddress.setBytes(validMacByteArray);
	}

	@Test
	public final void testConstructMacAddressWithValidByteArrayWorks() {
		byte[] validMacByteArray = new byte[6];

		new MacAddress(validMacByteArray);
	}

}

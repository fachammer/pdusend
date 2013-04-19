package at.fabianachammer.pdusend.type;

/**
 * 
 */

import org.junit.Test;
import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.MacAddress;
import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.MacAddressDecoder;

/**
 * @author fabian
 * 
 */
public class MacAddressTest {

	@Test(expected = IllegalArgumentException.class)
	public final void testSetIllegalSizeByteArrayOnMacAddressThrowsIllegalArgumentException() {
		MacAddress macAddress = new MacAddress();
		byte[] illegalSizeByteArray = new byte[7];

		macAddress.setValue(illegalSizeByteArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testConstructMacAddressWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		byte[] illegalSizeByteArray = new byte[7];

		new MacAddress(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullByteArrayOnMacAddressThrowsNullPointerException() {
		MacAddress macAddress = new MacAddress();

		macAddress.setValue(null);
	}

	@Test(expected = NullPointerException.class)
	public final void testConstructMacAddressWithNullByteArrayThrowsNullPointerException() {
		new MacAddress(null);
	}

	@Test
	public final void testSetValidByteArrayOnMacAddressWorks() {
		MacAddress macAddress = new MacAddress();
		byte[] validMacByteArray = new byte[6];

		macAddress.setValue(validMacByteArray);
	}

	@Test
	public final void testConstructMacAddressWithValidByteArrayWorks() {
		byte[] validMacByteArray = new byte[6];

		new MacAddress(validMacByteArray);
	}

	@Test
	public final void testGetDecoderOnMacAddressReturnsInstanceOfMacAddressDecoder() {
		MacAddress anyMacAddress = new MacAddress();

		DataUnitDecoder<MacAddress> decoder =
				anyMacAddress.getDecoder();

		assertTrue(decoder instanceof MacAddressDecoder);
	}

	@Test
	public final void testEqualsWithEqualMacAddressesOnMacAddressReturnsTrue() {
		assertTrue(new MacAddress().equals(new MacAddress()));
	}

	@Test
	public final void testEqualsWithDifferentMacAddressesOnMacAddressReturnsFalse() {
		assertFalse(new MacAddress().equals(new MacAddress(new byte[] {
				0, 0, 0, 0, 0, 1 })));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnMacAddressReturnsFalse() {
		assertFalse(new MacAddress().equals(new Object()));
	}

	@Test
	public final void testEqualsWithNullOnMacAddressReturnsFalse() {
		assertFalse(new MacAddress().equals(null));
	}

	@Test
	public final void testHashCodeWithEqualMacAddressesOnMacAddressReturnsEqualHashCodes() {
		assertEquals(
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 }).hashCode(),
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 }).hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentMacAddressesOnMacAddressReturnsDifferentHashCodes() {
		assertNotEquals(
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 }).hashCode(),
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 2 }).hashCode());
	}
}

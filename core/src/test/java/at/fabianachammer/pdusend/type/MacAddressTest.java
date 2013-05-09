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
	public final void setIllegalSizeByteArrayThrowsIllegalArgumentException() {
		MacAddress macAddress = new MacAddress();
		byte[] illegalSizeByteArray = new byte[7];

		macAddress.setValue(illegalSizeByteArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void constructMacAddressWithIllegalSizeByteArrayThrowsIllegalArgumentException() {
		// TODO: decoupling of creation and logic
		byte[] illegalSizeByteArray = new byte[7];

		new MacAddress(illegalSizeByteArray);
	}

	@Test(expected = NullPointerException.class)
	public final void setNullByteArrayThrowsNullPointerException() {
		MacAddress macAddress = new MacAddress();

		macAddress.setValue(null);
	}

	@Test(expected = NullPointerException.class)
	public final void constructMacAddressWithNullByteArrayThrowsNullPointerException() {
		// TODO: decoupling of creation and logic
		new MacAddress(null);
	}

	@Test
	public final void setValidByteArrayPasses() {
		MacAddress macAddress = new MacAddress();
		byte[] validMacByteArray = new byte[6];

		macAddress.setValue(validMacByteArray);
	}

	@Test
	public final void constructMacAddressWithValidByteArrayPasses() {
		// TODO: decoupling of creation and logic
		byte[] validMacByteArray = new byte[6];

		new MacAddress(validMacByteArray);
	}

	@Test
	public final void getDecoderReturnsInstanceOfMacAddressDecoder() {
		MacAddress anyMacAddress = new MacAddress();

		DataUnitDecoder<MacAddress> decoder =
				anyMacAddress.getDecoder();

		assertTrue(decoder instanceof MacAddressDecoder);
	}

	@Test
	public final void equalsWithEqualMacAddressesReturnsTrue() {
		assertTrue(new MacAddress().equals(new MacAddress()));
	}

	@Test
	public final void equalsWithDifferentMacAddressesReturnsFalse() {
		assertFalse(new MacAddress().equals(new MacAddress(
				new byte[] { 0, 0, 0, 0, 0, 1 })));
	}

	@Test
	public final void equalsWithDifferentTypesReturnsFalse() {
		assertFalse(new MacAddress().equals(new Object()));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(new MacAddress().equals(null));
	}

	@Test
	public final void hashCodeWithEqualMacAddressesReturnsEqualHashCodes() {
		assertEquals(
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 })
						.hashCode(),
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 1 })
						.hashCode());
	}

	@Test
	public final void hashCodeWithDifferentMacAddressesReturnsDifferentHashCodes() {
		assertNotEquals(new MacAddress(
				new byte[] { 0, 0, 0, 0, 0, 1 }).hashCode(),
				new MacAddress(new byte[] { 0, 0, 0, 0, 0, 2 })
						.hashCode());
	}

	@Test
	public final void toStringWithZeroValueReturnsHexadecimalColonRepresentation() {
		MacAddress address = new MacAddress();
		String expected = "00:00:00:00:00:00";

		assertEquals(expected, address.toString());
	}
	
	@Test
	public final void sizeOfMacAddressReturnsSix(){
		assertEquals(6, new MacAddress().size());
	}
}

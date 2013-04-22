package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.HardwareAddressTypeDecoder;

/**
 * @author fabian
 * 
 */
public class HardwareAddressTypeTest {

	@Test
	public final void testGetDecoderOnHardwareAddressTypeReturnsInstanceOfHardwareAddressTypeDecoder() {
		HardwareAddressType anyHardwareAddressType =
				HardwareAddressType.UNKOWN;

		DataUnitDecoder<HardwareAddressType> decoder =
				anyHardwareAddressType.getDecoder();

		assertTrue(decoder instanceof HardwareAddressTypeDecoder);
	}

	@Test
	public final void testEncodeOnHardwareAddressTypeWorks() {
		HardwareAddressType anyHardwareAddressType =
				HardwareAddressType.UNKOWN;
		byte[] expected = { 0, 0 };

		byte[] actual = anyHardwareAddressType.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEqualsWithEqualIdHardwareAddressTypesOnHardwareAddressTypeReturnsTrue() {
		HardwareAddressType hat = new HardwareAddressType((short) 0);
		HardwareAddressType equalHat =
				new HardwareAddressType((short) 0);

		assertTrue(hat.equals(equalHat));
	}

	@Test
	public final void testEqualsWithDifferentIdHardwareAddressTypesOnHardwareAddressTypeReturnsFalse() {
		HardwareAddressType hat = new HardwareAddressType((short) 0);
		HardwareAddressType differentHat =
				new HardwareAddressType((short) 1);

		assertFalse(hat.equals(differentHat));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnHardwareAddressTypeReturnsFalse() {
		assertFalse(new HardwareAddressType((short) 0)
				.equals(new Object()));
	}

	@Test
	public final void testEqualsWithNullOnHardwareAddressTypeReturnsFalse() {
		assertFalse(new HardwareAddressType((short) 0).equals(null));
	}

	@Test
	public final void testHashCodeWithEqualIdHardwareAddressTypesOnHardwareAddressTypeReturnsEqualHashCodes() {
		assertEquals(new HardwareAddressType((short) 0).hashCode(),
				new HardwareAddressType((short) 0).hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentIdHardwareAddressTypesOnHardwareAddressTypeReturnsDifferentHashCodes() {
		assertNotEquals(
				new HardwareAddressType((short) 0).hashCode(),
				new HardwareAddressType((short) 1).hashCode());
	}
}

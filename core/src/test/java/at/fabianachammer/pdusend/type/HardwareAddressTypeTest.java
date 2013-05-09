package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.HardwareAddressTypeDecoder;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;

/**
 * @author fabian
 * 
 */
public class HardwareAddressTypeTest {

	@Test
	public final void getDecoderReturnsInstanceOfHardwareAddressTypeDecoder() {
		HardwareAddressType anyHardwareAddressType =
				HardwareAddressType.UNKOWN;

		DataUnitDecoder<HardwareAddressType> decoder =
				anyHardwareAddressType.getDecoder();

		assertTrue(decoder instanceof HardwareAddressTypeDecoder);
	}

	@Test
	public final void createHardwareAddressTypeWithNonPredefinedIdReturnsHardwareAddressTypeWithSpecifiedIdAndRawDataUnitDecoderAsProtocolDecoder() {
		// TODO: decoupling of creation and logic
		short nonPredefinedId = 2;
		HardwareAddressType notDefinedHardwareAddressType =
				new HardwareAddressType(nonPredefinedId);

		assertEquals(nonPredefinedId,
				notDefinedHardwareAddressType.getId());
		assertTrue(notDefinedHardwareAddressType.getProtocolDecoder() instanceof RawDataUnitDecoder);
	}

	@Test
	public final void encodeWithUnknownHardwareAddressTypeReturnsAllZeroByteArray() {
		HardwareAddressType anyHardwareAddressType =
				HardwareAddressType.UNKOWN;
		byte[] expected = { 0, 0 };

		byte[] actual = anyHardwareAddressType.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void equalsWithEqualIdHardwareAddressTypesReturnsTrue() {
		HardwareAddressType hat = new HardwareAddressType((short) 0);
		HardwareAddressType equalHat =
				new HardwareAddressType((short) 0);

		assertTrue(hat.equals(equalHat));
	}

	@Test
	public final void equalsWithDifferentIdHardwareAddressTypesReturnsFalse() {
		HardwareAddressType hat = new HardwareAddressType((short) 0);
		HardwareAddressType differentHat =
				new HardwareAddressType((short) 1);

		assertFalse(hat.equals(differentHat));
	}

	@Test
	public final void equalsWithDifferentTypesOnHardwareAddressTypeReturnsFalse() {
		assertFalse(new HardwareAddressType((short) 0)
				.equals(new Object()));
	}

	@Test
	public final void equalsWithNullOnHardwareAddressTypeReturnsFalse() {
		assertFalse(new HardwareAddressType((short) 0).equals(null));
	}

	@Test
	public final void hashCodeWithEqualIdHardwareAddressTypesReturnsEqualHashCodes() {
		assertEquals(new HardwareAddressType((short) 0).hashCode(),
				new HardwareAddressType((short) 0).hashCode());
	}

	@Test
	public final void hashCodeWithDifferentIdHardwareAddressTypesReturnsDifferentHashCodes() {
		assertNotEquals(
				new HardwareAddressType((short) 0).hashCode(),
				new HardwareAddressType((short) 1).hashCode());
	}

	@Test
	public final void sizeOfUnknownHardwareAddressTypeReturnsTwo() {
		assertEquals(2, HardwareAddressType.UNKOWN.size());
	}
}

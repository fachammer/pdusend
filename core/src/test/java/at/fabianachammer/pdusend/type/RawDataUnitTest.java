package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.pdu.RawDataUnit;
import at.fabianachammer.pdusend.type.pdu.decoder.RawDataUnitDecoder;

/**
 * @author fabian
 * 
 */
public class RawDataUnitTest {

	@Test
	public final void testGetDecoderOnRawDataUnitReturnsInstanceOfRawDataUnitDecoder() {
		RawDataUnit anyRawDataUnit = new RawDataUnit();

		DataUnitDecoder<RawDataUnit> decoder =
				anyRawDataUnit.getDecoder();

		assertTrue(decoder instanceof RawDataUnitDecoder);
	}

	@Test
	public final void testEncodeOnRawDataUnitWorks() {
		RawDataUnit anyRawDataUnit =
				new RawDataUnit(new byte[] { 0 });
		byte[] expected = { 0 };

		byte[] actual = anyRawDataUnit.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testEncodeWithNullDataOnRawDataUnitReturnsEmptyByteArray() {
		RawDataUnit anyRawDataUnit = new RawDataUnit(null);
		byte[] expected = new byte[0];

		byte[] actual = anyRawDataUnit.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void testSetNonNullDataOnRawDataUnitWorks() {
		RawDataUnit anyRawDataUnit = new RawDataUnit();
		byte[] nonNullData = new byte[] { 0 };
		anyRawDataUnit.setData(nonNullData);
	}

	@Test
	public final void testEqualsWithEqualRawDataUnitsOnRawDataUnitReturnsTrue() {
		assertTrue(new RawDataUnit((byte) 1).equals(new RawDataUnit(
				(byte) 1)));
	}

	@Test
	public final void testEqualsWithDifferentRawDataUnitsOnRawDataUnitReturnsFalse() {
		assertFalse(new RawDataUnit((byte) 1).equals(new RawDataUnit(
				(byte) 2)));
	}

	@Test
	public final void testEqualsWithDifferentTypesOnRawDataUnitReturnsFalse() {
		assertFalse(new RawDataUnit().equals(new Object()));
	}

	@Test
	public final void testEqualsWithNullOnRawDataUnitReturnsFalse() {
		assertFalse(new RawDataUnit().equals(null));
	}

	@Test
	public final void testHashCodeWithEqualRawDataUnitsOnRawDataUnitReturnsEqualHashCodes() {
		assertEquals(new RawDataUnit((byte) 1).hashCode(),
				new RawDataUnit((byte) 1).hashCode());
	}

	@Test
	public final void testHashCodeWithDifferentRawDataUnitsOnRawDataUnitReturnsDifferentHashCodes() {
		assertNotEquals(new RawDataUnit((byte) 1).hashCode(),
				new RawDataUnit((byte) 2).hashCode());
	}
}

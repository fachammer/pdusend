package at.fabianachammer.pdusend.type.pdu;

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
	public final void getDecoderReturnsInstanceOfRawDataUnitDecoder() {
		RawDataUnit anyRawDataUnit = new RawDataUnit();

		DataUnitDecoder<RawDataUnit> decoder =
				anyRawDataUnit.getDecoder();

		assertTrue(decoder instanceof RawDataUnitDecoder);
	}

	@Test
	public final void encodeWithOneZeroByteArrayReturnsByteArrayWithOneZeroByte() {
		RawDataUnit anyRawDataUnit =
				new RawDataUnit(new byte[] { 0 });
		byte[] expected = { 0 };

		byte[] actual = anyRawDataUnit.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void encodeWithNullReturnsEmptyByteArray() {
		RawDataUnit anyRawDataUnit = new RawDataUnit(null);
		byte[] expected = new byte[0];

		byte[] actual = anyRawDataUnit.encode();

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void setNonNullDataPasses() {
		RawDataUnit anyRawDataUnit = new RawDataUnit();
		byte[] nonNullData = new byte[] { 0 };
		anyRawDataUnit.setData(nonNullData);
	}

	@Test
	public final void equalsWithEqualOneZeroByteRawDataUnitsReturnsTrue() {
		assertTrue(new RawDataUnit((byte) 0).equals(new RawDataUnit(
				(byte) 0)));
	}

	@Test
	public final void equalsWithDifferentRawDataUnitsReturnsFalse() {
		assertFalse(new RawDataUnit((byte) 0).equals(new RawDataUnit(
				(byte) 1)));
	}

	@Test
	public final void equalsWithDifferentTypesReturnsFalse() {
		assertFalse(new RawDataUnit().equals(new Object()));
	}

	@Test
	public final void equalsWithNullReturnsFalse() {
		assertFalse(new RawDataUnit().equals(null));
	}

	@Test
	public final void hashCodeWithEqualOneZeroByteRawDataUnitsReturnsEqualHashCodes() {
		assertEquals(new RawDataUnit((byte) 0).hashCode(),
				new RawDataUnit((byte) 0).hashCode());
	}

	@Test
	public final void hashCodeWithDifferentRawDataUnitsReturnsDifferentHashCodes() {
		assertNotEquals(new RawDataUnit((byte) 0).hashCode(),
				new RawDataUnit((byte) 1).hashCode());
	}

	@Test
	public final void sizeOfOneByteRawDataUnitReturnsOne() {
		assertEquals(1, new RawDataUnit((byte) 0).size());
	}
}

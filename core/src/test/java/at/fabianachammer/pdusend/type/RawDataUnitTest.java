package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.decoder.DataUnitDecoder;
import at.fabianachammer.pdusend.type.decoder.RawDataUnitDecoder;

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
	public final void testSetNonNullDataOnRawDataUnitWorks() {
		RawDataUnit anyRawDataUnit = new RawDataUnit();
		byte[] nonNullData = new byte[] { 0 };
		anyRawDataUnit.setData(nonNullData);
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNullDataOnRawDataUnitThrowsNullPointerException() {
		RawDataUnit anyRawDataUnit = new RawDataUnit();
		anyRawDataUnit.setData(null);
	}
}

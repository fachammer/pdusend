package at.fabianachammer.pdusend.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import at.fabianachammer.pdusend.util.ByteConverter;

/**
 * This class tests the ByteConverter class.
 * 
 * @author fabian
 * 
 */
public class ByteConverterTest {

	@Test
	public final void toHexStringWithAnyDataWorks() {
		final byte[] anyData =
				{ (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33 };
		final String expected = "00112233";

		String actual = ByteConverter.toHexString(anyData);

		assertEquals(expected, actual);
	}

	@Test
	public final void toPrimitiveByteArrayWithAnyDataWorks() {
		Byte[] anyInput = { (byte) 0, (byte) 0, (byte) 0 };
		byte[] expected = { (byte) 0, (byte) 0, (byte) 0 };

		byte[] actual = ByteConverter.toByteArray(anyInput);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void toPrimitiveByteArrayWithNullInputReturnsNull() {
		Byte[] input = null;

		assertNull(ByteConverter.toByteArray(input));
	}

	@Test
	public final void toTwosComplementWithAllZeroInputReturnsAllZeroOutput() {
		byte[] input = { 0, 0 };
		byte[] expected = { 0, 0 };

		byte[] actual = ByteConverter.toTwosComplement(input);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void toTwosComplementWithAllBitsSetInputReturnsAllZeroBitsExceptTheLast() {
		byte[] input = { (byte) 0b11111111, (byte) 0b11111111 };
		byte[] expected = { 0, 1 };

		byte[] actual = ByteConverter.toTwosComplement(input);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void toTwosComplementWithAllZeroBitsExceptTheLastReturnsAllBitsSet() {
		byte[] input = { 0, 1 };
		byte[] expected = { (byte) 0b11111111, (byte) 0b11111111 };

		byte[] actual = ByteConverter.toTwosComplement(input);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void toTwosComplementWithFirstBitOfEveryByteSetReturnsFirstBitNotSetAndLastSevenBitsNotSet() {
		byte[] input = { (byte) 0b10000000, (byte) 0b10000000 };
		byte[] expected = { (byte) 0b01111111, (byte) 0b10000000 };

		byte[] actual = ByteConverter.toTwosComplement(input);

		assertArrayEquals(expected, actual);
	}
}

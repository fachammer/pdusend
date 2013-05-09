package at.fabianachammer.pdusend.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.math.BigInteger;

import org.junit.Test;

import at.fabianachammer.pdusend.util.BitOperator;

/**
 * This class tests the BitOpterator class.
 * 
 * @author fabian
 * 
 */
public class BitOperatorTest {

	@Test
	public final void mergeToShortWithLowAndHighByteOneReturnsShortWithEighthAndSixteenthBitSet() {
		final byte lowByte = 0x01;
		final byte highByte = (byte) 0x01;
		final short expected = (short) 0x0101;

		short actual = BitOperator.merge(lowByte, highByte);

		assertEquals(expected, actual);
	}

	@Test
	public final void mergeToShortWithMaximumBytesReturnsShortWithAllBitsSet() {
		final byte lowByte = (byte) 0xff;
		final byte highByte = (byte) 0xff;
		final short expected = (short) 0xffff;

		short actual = BitOperator.merge(lowByte, highByte);

		assertEquals(expected, actual);
	}

	@Test
	public final void mergeToIntWithAllBytesOneReturnsIntWithEachEigthBitSet() {
		byte lowlowByte = 0x01;
		byte lowHighByte = 0x01;
		byte highLowByte = 0x01;
		byte highHighByte = 0x01;
		int expected = 0x01010101;

		int actual =
				BitOperator.merge(lowlowByte, lowHighByte,
						highLowByte, highHighByte);

		assertEquals(expected, actual);
	}

	@Test
	public final void shortSplitWithEachEigthBitSetReturnsByteArrayWithBothElementsSetToOne() {
		final short value = (short) 0x0101;
		final byte[] expected = { (byte) 0x01, (byte) 0x01 };

		byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void byteSplitWithOneReturnsByteArrayWithFirstElementEqualsZeroAndSecondElementEqualsOne() {
		final byte value = (byte) 0x01;
		final byte[] expected = { 0x0, 0x1 };

		byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void intToByteSplitWithEachEighthBitSetReturnsByteArrayWithAllFourElementsSetToOne() {
		final int value = 0x01010101;

		final byte[] expected =
				{ (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01 };

		byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void isSetWithOneAndZeroIndexReturnsTrue() {
		assertEquals(true, BitOperator.isSet(1, 0));
	}

	@Test
	public final void isSetWithZeroAndZeroIndexReturnsFalse() {
		assertEquals(false, BitOperator.isSet(0, 0));
	}

	@Test
	public final void bigIntegerSplitWithArraySizeWithMinusOneBigIntegerAndTwoArraySizeReturnsByteArrayWithAllBitsOfSecondByteSet() {
		BigInteger minusOne =
				new BigInteger(1, new byte[] { (byte) 0b11111111 });

		byte[] expected = { 0, (byte) 0b11111111 };

		byte[] actual = BitOperator.split(minusOne, 2);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void bigIntegerSplitWithArraySizeWithNineByteAllBitsSetExceptSignBitBigIntegerAndTenArraySizeReturnsByteArrayWithLastSeventyTwoBitsSet() {
		BigInteger nineByteAllBitsSet =
				new BigInteger(1, new byte[] {
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111 });

		byte[] expected =
				{
						0, (byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111, (byte) 0b11111111,
						(byte) 0b11111111 };

		byte[] actual = BitOperator.split(nineByteAllBitsSet, 10);

		assertArrayEquals(expected, actual);
	}

	@Test
	public final void bigIntegerSplitWithArraySizeWithArraySizeSmallerThanArrayLengthIgnoresGivenArraySize() {
		BigInteger someTwoByteBigInteger =
				new BigInteger(1, new byte[] { 1, 0, 0 });

		byte[] expected = { 1, 0, 0 };

		byte[] actual = BitOperator.split(someTwoByteBigInteger, 1);

		assertArrayEquals(expected, actual);
	}
}

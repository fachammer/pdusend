/**
 * 
 */
package at.fabianachammer.nsend.pdu.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * This class tests the BitOpterator class.
 * 
 * @author fabian
 * 
 */
public class BitOperatorTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.util.BitOperator#merge(byte, byte)}.
	 */
	@Test
	public final void testMerge() {
		final byte lowByte = 0b00110011;
		final byte highByte = (byte) 0b11110000;
		final short expected = (short) 0b1111000000110011;
		short actual = BitOperator.merge(lowByte, highByte);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.util.BitOperator#split(short)}.
	 */
	@Test
	public final void testShortSplit() {
		final short value = (short) 0b1111000011001100;
		final Byte[] expected = { (byte) 0b11110000, (byte) 0b11001100 };

		Byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.util.BitOperator#split(byte)}.
	 */
	@Test
	public final void testByteSplit() {
		final byte value = (byte) 0b11001111;
		final Byte[] expected = { 0b1100, 0b1111 };

		Byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link t.fabianachammer.nsend.pdu.util.BitOperator#split(int)}.
	 */
	@Test
	public final void testIntToByteSplit() {
		final int value = 0b11110000110011000001100011110000;

		final Byte[] expected =
				{ (byte) 0b11110000, (byte) 0b11001100, (byte) 0b00011000,
						(byte) 0b11110000 };

		Byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.pdu.util.BitOperator#isSet(int, int)}.
	 */
	@Test
	public final void testIsSet() {
		final int value = 0b1001;
		final int n = 3;

		final boolean expected = true;

		boolean actual = BitOperator.isSet(value, n);

		assertEquals(expected, actual);
	}

}

package at.fabianachammer.nsend.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import at.fabianachammer.nsend.util.BitOperator;

/**
 * This class tests the BitOpterator class.
 * 
 * @author fabian
 * 
 */
public class BitOperatorTest {

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.util.BitOperator#merge(byte, byte)}.
	 */
	@Test
	public final void testMergeWorks() {
		final byte lowByte = 0x01;
		final byte highByte = (byte) 0x01;
		final short expected = (short) 0x0101;
		short actual = BitOperator.merge(lowByte, highByte);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.util.BitOperator#split(short)}.
	 */
	@Test
	public final void testShortSplitWorks() {
		final short value = (short) 0x0101;
		final Byte[] expected = { (byte) 0x01, (byte) 0x01 };

		Byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.util.BitOperator#split(byte)}.
	 */
	@Test
	public final void testByteSplitWorks() {
		final byte value = (byte) 0x01;
		final Byte[] expected = { 0x0, 0x1 };

		Byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link t.fabianachammer.nsend.pdu.util.BitOperator#split(int)}.
	 */
	@Test
	public final void testIntToByteSplitWorks() {
		final int value = 0x01010101;

		final Byte[] expected =
				{ (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01 };

		Byte[] actual = BitOperator.split(value);

		assertArrayEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.util.BitOperator#isSet(int, int)}.
	 */
	@Test
	public final void testIsSetWorks() {
		assertEquals(true, BitOperator.isSet(1, 0));
	}

	/**
	 * Test method for
	 * {@link at.fabianachammer.nsend.util.BitOperator#isSet(int, int)}.
	 */
	@Test
	public final void testIsNotSetWorks() {
		assertEquals(false, BitOperator.isSet(0, 0));
	}
}
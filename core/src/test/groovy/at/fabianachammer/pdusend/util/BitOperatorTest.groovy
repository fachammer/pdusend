package at.fabianachammer.pdusend.util

import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertArrayEquals

import org.junit.Test


import at.fabianachammer.pdusend.util.BitOperator

class BitOperatorTest {

	@Test
	void isBitSeOnIndextWithOneAsValueAndOneAsIndexReturnsTrue(){
		assertTrue(BitOperator.isBitSetOnIndex(1, 0))
	}
	
	@Test
	void isBitSetOnIndexWithZeroAsValueAndZeroAsIndexReturnsFalse(){
		assertFalse(BitOperator.isBitSetOnIndex(0, 0))
	}
	
	@Test
	void isSetWithZeroAsValueAndThirtyOneAsIndexReturnsFalse(){
		assertFalse(BitOperator.isBitSetOnIndex(0, 31))
	}

	@Test
	void setBitWithZeroAsValueAndZeroAsIndexReturnsOne(){
		assertEquals(1, BitOperator.setBitOnIndex(0, 0))
	}

	@Test
	void setBitWithOneAsValueAndZeroAsIndexReturnsOne(){
		assertEquals(1, BitOperator.setBitOnIndex(1, 0))
	}

	@Test
	void setBitWithZeroAsValueAndOneAsIndexReturnsTwo(){
		assertEquals(2, BitOperator.setBitOnIndex(0, 1))
	}

	@Test
	void setBitWithZeroAsValueAndThrityOneAsIndexReturnsIntegerMinimumValue(){
		assertEquals(Integer.MIN_VALUE, BitOperator.setBitOnIndex(0, 31))
	}

	@Test
	void clearBitWithZeroAsValueAndZeroAsIndexReturnsZero(){
		assertEquals(0, BitOperator.clearBitOnIndex(0, 0))
	}

	@Test
	void clearBitWithOneAsValueAndZeroAsIndexReturnsZero(){
		assertEquals(0, BitOperator.clearBitOnIndex(1, 0))
	}

	@Test
	void clearBitWithOneAsValueAndOneAsIndexReturnsOne(){
		assertEquals(1, BitOperator.clearBitOnIndex(1, 1))
	}

	@Test
	void clearBitWithMinusOneAsValueAndThrityOneAsIndexReturnsIntegerMaximumValue(){
		assertEquals(Integer.MAX_VALUE, BitOperator.clearBitOnIndex(-1, 31))
	}
}

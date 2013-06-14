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
	void setBitOnIndexWithZeroAsValueAndZeroAsIndexReturnsOne(){
		assertEquals(1, BitOperator.setBitOnIndex(0, 0))
	}

	@Test
	void setBitOnIndexWithOneAsValueAndZeroAsIndexReturnsOne(){
		assertEquals(1, BitOperator.setBitOnIndex(1, 0))
	}

	@Test
	void setBitOnIndexWithZeroAsValueAndOneAsIndexReturnsTwo(){
		assertEquals(2, BitOperator.setBitOnIndex(0, 1))
	}

	@Test
	void setBitOnIndexWithZeroAsValueAndThrityOneAsIndexReturnsIntegerMinimumValue(){
		assertEquals(Integer.MIN_VALUE, BitOperator.setBitOnIndex(0, 31))
	}

	@Test
	void clearBitOnIndexWithZeroAsValueAndZeroAsIndexReturnsZero(){
		assertEquals(0, BitOperator.clearBitOnIndex(0, 0))
	}

	@Test
	void clearBitOnIndexWithOneAsValueAndZeroAsIndexReturnsZero(){
		assertEquals(0, BitOperator.clearBitOnIndex(1, 0))
	}

	@Test
	void clearBitOnIndexWithOneAsValueAndOneAsIndexReturnsOne(){
		assertEquals(1, BitOperator.clearBitOnIndex(1, 1))
	}

	@Test
	void clearBitOnIndexWithMinusOneAsValueAndThrityOneAsIndexReturnsIntegerMaximumValue(){
		assertEquals(Integer.MAX_VALUE, BitOperator.clearBitOnIndex(-1, 31))
	}
	
	@Test
	void bitCountFromNumberWithZeroReturnsOne(){
		assertEquals(1, BitOperator.bitCountFromNumber(0))
	}
	
	@Test
	void bitCountFromNumberWithOneReturnsOne(){
		assertEquals(1, BitOperator.bitCountFromNumber(1))
	}
	
	@Test
	void bitCountFromNumberWithTenReturnsFour(){
		assertEquals(4, BitOperator.bitCountFromNumber(10))
	}
}

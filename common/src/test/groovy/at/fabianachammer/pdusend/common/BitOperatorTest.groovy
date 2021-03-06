package at.fabianachammer.pdusend.common

import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertArrayEquals

import org.junit.Test

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
	void isBitSetOnIndexWithZeroAsValueAndThirtyOneAsIndexReturnsFalse(){
		assertFalse(BitOperator.isBitSetOnIndex(0, 31))
	}

	@Test
	void setBitOnIndexWithZeroAsValueAndZeroAsIndexReturnsOne(){
		assertEquals(1, BitOperator.newNumberWithBitSetOnIndex(0, 0))
	}

	@Test
	void setBitOnIndexWithOneAsValueAndZeroAsIndexReturnsOne(){
		assertEquals(1, BitOperator.newNumberWithBitSetOnIndex(1, 0))
	}

	@Test
	void setBitOnIndexWithZeroAsValueAndOneAsIndexReturnsTwo(){
		assertEquals(2, BitOperator.newNumberWithBitSetOnIndex(0, 1))
	}

	@Test
	void setBitOnIndexWithZeroAsValueAndThrityOneAsIndexReturnsIntegerMinimumValue(){
		assertEquals(Integer.MIN_VALUE, BitOperator.newNumberWithBitSetOnIndex(0, 31))
	}

	@Test
	void clearBitOnIndexWithZeroAsValueAndZeroAsIndexReturnsZero(){
		assertEquals(0, BitOperator.newNumberWithBitClearedOnIndex(0, 0))
	}

	@Test
	void clearBitOnIndexWithOneAsValueAndZeroAsIndexReturnsZero(){
		assertEquals(0, BitOperator.newNumberWithBitClearedOnIndex(1, 0))
	}

	@Test
	void clearBitOnIndexWithOneAsValueAndOneAsIndexReturnsOne(){
		assertEquals(1, BitOperator.newNumberWithBitClearedOnIndex(1, 1))
	}

	@Test
	void clearBitOnIndexWithMinusOneAsValueAndThrityOneAsIndexReturnsIntegerMaximumValue(){
		assertEquals(Integer.MAX_VALUE, BitOperator.newNumberWithBitClearedOnIndex(-1, 31))
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

	@Test
	void bitCountWithMinusOneAsByteReturnsByteSize(){
		assertEquals(Byte.SIZE, BitOperator.bitCountFromNumber(-1 as byte))
	}

	@Test
	void bitCountWithMinusOneAsShortReturnsShortSize(){
		assertEquals(Short.SIZE, BitOperator.bitCountFromNumber(-1 as short))
	}

	@Test
	void bitCountWithMinusOneAsIntReturnsIntSize(){
		assertEquals(Integer.SIZE, BitOperator.bitCountFromNumber(-1 as int))
	}

	@Test
	void bitCountWithMinusOneAsLongReturnsLongSize(){
		assertEquals(Long.SIZE, BitOperator.bitCountFromNumber(-1 as long))
	}

	@Test(expected = UnsupportedOperationException.class)
	void bitCountWithMinusOneAsFloatThrowsUnsupportedOperationException(){
		BitOperator.bitCountFromNumber(-1.0)
	}

	@Test
	void toByteArrayWithByteReturnsThatByteInArray(){
		assertArrayEquals([1] as byte[], BitOperator.toByteArray(1 as byte, 8))
	}

	@Test
	void toByteArrayWithShortReturnsByteArrayWithTwoBytesRepresentingTheGivenShort(){
		assertArrayEquals([0, 1] as byte[], BitOperator.toByteArray(1 as short, 16))
	}

	@Test
	void toByteArrayWithIntReturnsByteArrayWithFourBytesRepresentingTheGivenInt(){
		assertArrayEquals([0, 0, 0, 1] as byte[], BitOperator.toByteArray(1 as int, 32))
	}

	@Test
	void toByteArrayWithBigIntegerReturnsByteArrayWithNeededSizeAndCorrectElements(){
		assertArrayEquals([0, 0, 0, 0, 0, 0, 0, 0, 1] as byte[], BitOperator.toByteArray(1 as BigInteger, 72))
	}

	@Test
	void toByteArrayWithTenAsValueAndSixteenAsSizeInBitsReturnsTwoByteByteArrayWithProperRepresentation(){
		assertArrayEquals([0, 10] as byte[], BitOperator.toByteArray(10 as BigInteger, 16))
	}

	@Test
	void toByteArrayWithRealBigIntegerAsValueAndSeventyTwoAsSizeInBitsReturnsNineByteByteArrayWithProperRepresentation(){
		assertArrayEquals([
			0xff,
			0xee,
			0xdd,
			0xcc,
			0xbb,
			0xaa,
			0x99,
			0x88,
			0x77] as byte[], BitOperator.toByteArray(0xffeeddccbbaa998877, 72))
	}

	@Test
	void toByteArrayWithSizeInBitsLowerThanSizeOfBigIntegerInBytesReturnsBigIntegerInBytesWithoutLeadingZeros(){
		assertArrayEquals([0xff, 0xee] as byte[], BitOperator.toByteArray(0xffee, 8))
	}
	
	@Test
	void toByteArrayWithoutSizeInBitsReturnsValueOf0x1111InByteArrayRepresentationWithoutLeadingZeros(){
		assertArrayEquals([0x11, 0x11] as byte[], BitOperator.toByteArray(0x1111))
	}
	
	@Test
	void toByteArrayWithoutSizeInBitsReturnsValueOf0xffffInByteArrayRepresentationWithoutLeadingZeros(){
		assertArrayEquals([0xff, 0xff] as byte[], BitOperator.toByteArray(0xffff))
	}
}

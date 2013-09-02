package at.fabianachammer.pdusend.common

import static org.junit.Assert.*

import org.junit.Test;

import at.fabianachammer.pdusend.common.validation.ValueNullException
import at.fabianachammer.pdusend.common.validation.ValueTooGreatException;
import at.fabianachammer.pdusend.common.validation.ValueTooLowException

/**
 * @author fabian
 *
 */
class PerBitByteArrayBuilderTest {
	
	@Test
	void oneCallToAddBitsWithOneByteArrayAndOneAsSizeInBitsChangesPopulatedDataToReturnTheAddedData() {
		PerBitByteArrayBuilder perBitByteArrayBuilder = new PerBitByteArrayBuilder()
		byte[] expected = [1]
		
		perBitByteArrayBuilder.addBits([1] as byte[], 1)
		
		assertArrayEquals(expected, perBitByteArrayBuilder.data as byte[])
	}

	@Test
	void twoCallsToAddBitsWithOneByteArrayAndFourAsSizeInBitsReturnsByteArrayWithOneElementWithSeventeenAsValue(){
		PerBitByteArrayBuilder perBitByteArrayBuilder = new PerBitByteArrayBuilder()
		byte[] expected = [0b00010001]
		byte[] inputData = [1]
		int sizeInBits = 4
		
		perBitByteArrayBuilder.addBits(inputData, sizeInBits)
		perBitByteArrayBuilder.addBits(inputData, sizeInBits)
		
		assertArrayEquals(expected, perBitByteArrayBuilder.data as byte[])
	}
	
	@Test
	void threeCallsToAddBitsWithOneByteArrayAndFiveAsSizeInBitsReturnsByteArrayWithTwoElementsHavingValuesThirtyThreeAndTwo(){
		PerBitByteArrayBuilder perBitByteArrayBuilder = new PerBitByteArrayBuilder()
		byte[] expected = [0b00100001, 0b00000100]
		byte[] inputData = [1]
		int sizeInBits = 5
		
		perBitByteArrayBuilder.addBits(inputData, sizeInBits)
		perBitByteArrayBuilder.addBits(inputData, sizeInBits)
		perBitByteArrayBuilder.addBits(inputData, sizeInBits)
		
		assertArrayEquals(expected, perBitByteArrayBuilder.data as byte[])
	}
	
	@Test
	void toByteArrayWithoutElementsAddedReturnsEmptyArray(){
		PerBitByteArrayBuilder perBitByteArrayBuilder = new PerBitByteArrayBuilder()
		byte[] expected = []
		
		byte[] actual = perBitByteArrayBuilder.toByteArray()
		
		assertArrayEquals(expected, actual)
	}
	
	@Test(expected = ValueNullException.class)
	void addBitsWithNullByteArrayThrowsValueNullException(){
		PerBitByteArrayBuilder perBitByteArrayBuilder = new PerBitByteArrayBuilder()
		
		perBitByteArrayBuilder.addBits(null, 1)
	}
	
	@Test(expected = ValueTooLowException.class)
	void addBitsWithZeroAsSizeInBitsThrowsValueTooLowException(){
		new PerBitByteArrayBuilder().addBits([0] as byte[], 0)
	}
	
	@Test(expected = ValueTooGreatException.class)
	void addBitsWithSizeInBitsGreaterThanByteArrayLengthTimesEightThrowsValueTooGreatException(){
		new PerBitByteArrayBuilder().addBits([0] as byte[], 9)
	}
	
	@Test
	void addBitsWithSizeInBitsEqualToByteArrayLengthTimesEightPasses(){
		new PerBitByteArrayBuilder().addBits([0] as byte[], 8)
	}
}

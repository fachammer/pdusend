package at.fabianachammer.pdusend.common

import static org.junit.Assert.*

import org.junit.Test;

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
	
	@Test
	void addBitsWithSizeInBitsEqualToByteArrayLengthTimesEightPasses(){
		new PerBitByteArrayBuilder().addBits([0] as byte[], 8)
	}
}

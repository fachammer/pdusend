package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.type.DataUnit;
import at.fabianachammer.pdusend.type.FunctionDataUnit;

/**
 * @author fabian
 *
 */
class FunctionDataUnitTest {

	@Test
	void encodeWithStaticClosureReturnsByteArrayWithStaticValueOfClosure() {
		def staticClosure = { 1 }
		DataUnit functionDataUnit = new FunctionDataUnit(staticClosure)
		byte[] expected = [1]

		byte[] actual = functionDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWithPaddingClosureFilling10BytesReturnsByteArrayWith10ZeroBytes(){
		def paddingClosure = {int currentSizeInBytes, int minSizeInBytes ->
			(1..Math.max(0, minSizeInBytes-currentSizeInBytes)).collect  { 0 }
		}
		DataUnit paddingFunctionDataUnit = new FunctionDataUnit(paddingClosure.curry(0, 10))
		byte[] expected = new byte[10]

		byte[] actual = paddingFunctionDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWithEmptyClosureReturnsEmptyByteArray(){
		DataUnit functionDataUnit = new FunctionDataUnit({})

		byte[] actual = functionDataUnit.encode()

		assertArrayEquals([] as byte[], actual)
	}

	@Test
	void encodeReturnsSameResultWhenCalledTwice() {
		byte[] encodedData = new byte[1]
		DataUnit functionDataUnit = new FunctionDataUnit({ return encodedData })

		byte[] actual1 = functionDataUnit.encode()
		byte[] actual2 = functionDataUnit.encode()
		
		assertArrayEquals(encodedData, actual1)
		assertArrayEquals(encodedData, actual2)
	}

	@Test
	void sizeInBitsWithOneAsSizeInBitsReturnsOne(){
		FunctionDataUnit functionDataUnit = new FunctionDataUnit({}, 1)

		assertEquals(1, functionDataUnit.sizeInBits())
	}

	@Test
	void sizeInBitsWithoutDefinedSizeInBitsReturnsEncodedDataLengthTimesEight(){
		int encodedDataLength = 1
		FunctionDataUnit functionDataUnit = new FunctionDataUnit({ return new byte[encodedDataLength]})
		int expectedLength = encodedDataLength * 8

		assertEquals(expectedLength, functionDataUnit.sizeInBits())
	}
}
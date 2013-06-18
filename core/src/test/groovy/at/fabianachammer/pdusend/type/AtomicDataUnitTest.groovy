package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class AtomicDataUnitTest {

	@Test
	void encodeWithOneByteAsDataReturnsArrayWithOneByte() {
		byte oneByte = 1
		DataUnit dataUnit = new AtomicDataUnit(oneByte as byte[])
		byte[] expected = [oneByte]

		byte[] actual = dataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWith2OneBytesAsDataReturnsArrayWith2OneBytes(){
		byte[] input = [1, 1]
		DataUnit dataUnit = new AtomicDataUnit(input)
		byte[] expected = input

		byte[] actual = dataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void sizeInBitsWithOneAsSizeInBitsReturnsOne(){
		int sizeInBits = 1
		DataUnit atomicDataUnit = new AtomicDataUnit(sizeInBits)
		int expectedSize = sizeInBits

		int actualSize = atomicDataUnit.sizeInBits()

		assertEquals(expectedSize, actualSize)
	}

	@Test
	void encodeWithOneAsSizeInBitsReturnsByteArrayWithLengthOne(){
		int sizeInBits = 1
		DataUnit atomicDataUnit = new AtomicDataUnit(sizeInBits)
		int expectedLength = 1

		int actualLength = atomicDataUnit.encode().length

		assertEquals(expectedLength, actualLength)
	}

	@Test
	void encodeWithEightAsSizeInBitsReturnsByteArrayWithLengthOne(){
		int sizeInBits = 8
		DataUnit atomicDataUnit = new AtomicDataUnit(sizeInBits)
		int expectedLength = 1

		int actualLength = atomicDataUnit.encode().length

		assertEquals(expectedLength, actualLength)
	}

	@Test
	void encodeWithNineAsSizeInBitsReturnsByteArrayWithLengthTwo(){
		int sizeInBits = 9
		DataUnit atomicDataUnit = new AtomicDataUnit(sizeInBits)
		int expectedLength = 2

		int actualLength = atomicDataUnit.encode().length

		assertEquals(expectedLength, actualLength)
	}

	@Test
	void sizeInBitsWith1ZeroByteArrayAsDataReturnsEight(){
		byte[] data = [0]
		DataUnit atomicDataUnit = new AtomicDataUnit(data)
		int expectedSize = 8

		int actualSize = atomicDataUnit.sizeInBits()

		assertEquals(expectedSize, actualSize)
	}
	
	@Test
	void sizeInBitsWith1ZeroByteArrayAsDataAndTwoAsSizeInBitsReturnsTwo(){
		byte[] data = [0]
		int sizeInBits = 2
		DataUnit atomicDataUnit = new AtomicDataUnit(sizeInBits, data)
		int expectedSize = sizeInBits
	
		int actualSize = atomicDataUnit.sizeInBits()
		
		assertEquals(expectedSize, actualSize)
	}
	
	@Test
	void encodeWith1ZeroByteArrayAsDataAndTwoAsSizeInBitsReturnsZeroByteArray(){
		byte[] data = [0]
		int sizeInBits = 2
		DataUnit atomicDataUnit = new AtomicDataUnit(sizeInBits, data)
		byte[] expectedData = data
		
		byte[] actualData = atomicDataUnit.encode()
		
		assertArrayEquals(expectedData, actualData)
	}
}
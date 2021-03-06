package at.fabianachammer.pdusend.type

import static org.junit.Assert.*

import org.gcontracts.PreconditionViolation;
import org.junit.Test

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit

/**
 * @author fabian
 *
 */
class AtomicDataUnitTest {

	@Test(expected = PreconditionViolation)
	void createWithSizeInBitsSmallerThanZeroThrowsPreconditionViolation(){
		new AtomicDataUnit(-1)
	}
	
	@Test(expected = NullPointerException)
	void createWithDataEqualsNullThrowsPreconditionViolation(){
		new AtomicDataUnit(null)
	}
	
	@Test(expected = PreconditionViolation)
	void createWithSizeInBitsSmallerThanZeroAndDataValidThrowsPreconditionViolation(){
		new AtomicDataUnit(-1, [1] as byte[])
	}
	
	@Test(expected = PreconditionViolation)
	void createWithSizeInBitsValidAndDataEqualsNullThrowsPreconditionViolation(){
		new AtomicDataUnit(0, null)
	}
	
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
	
	@Test
	void equalsWithAtomicDataUnitWithSameSizeInBitsAndSameDataReturnsTrue(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		AtomicDataUnit equalAtomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		
		assertTrue(atomicDataUnit.equals(equalAtomicDataUnit))
	}
	
	@Test
	void equalsWithAtomicDataUnitWithDifferentSizeInBitsAndSameDataReturnsFalse(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		AtomicDataUnit differentSizeInBitsAtomicDataUnit = new AtomicDataUnit(2, [0] as byte[])
		
		assertFalse(atomicDataUnit.equals(differentSizeInBitsAtomicDataUnit))
	}
	
	@Test
	void equalsWithAtomicDataUnitWithDifferentDataAndSameSizeInBitsReturnsFalse(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		AtomicDataUnit differentDataAtomicDataUnit = new AtomicDataUnit(1, [1] as byte[])
		
		assertFalse(atomicDataUnit.equals(differentDataAtomicDataUnit))
	}
	
	@Test
	void equalsWithAtomicDataUnitWithDifferentDataAndDifferentSizeInBitsReturnsFalse(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		AtomicDataUnit differentAtomicDataUnit = new AtomicDataUnit(2, [1] as byte[])
		
		assertFalse(atomicDataUnit.equals(differentAtomicDataUnit))
	}
	
	@Test
	void equalsWithSameAtomicDataUnitReturnsTrue(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit([0] as byte[])
		
		assertTrue(atomicDataUnit.equals(atomicDataUnit))
	}
	
	@Test
	void equalsWithDifferentTypeObjectReturnsFalse(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit([0] as byte[])
		
		assertFalse(atomicDataUnit.equals(new Object()))
	}
	
	@Test
	void hashCodeWithEqualAtomicDataUnitsReturnsSameHashCode(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		AtomicDataUnit equalAtomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		
		assertEquals(atomicDataUnit.hashCode(), equalAtomicDataUnit.hashCode())
	}
	
	@Test
	void hashCodeWithDifferentAtomicDataUnitsReturnsDifferentHashCodes(){
		AtomicDataUnit atomicDataUnit = new AtomicDataUnit(1, [0] as byte[])
		AtomicDataUnit differentAtomicDataUnit = new AtomicDataUnit(2, [0] as byte[])
		
		assertNotEquals(atomicDataUnit.hashCode(), differentAtomicDataUnit.hashCode())
	}
}

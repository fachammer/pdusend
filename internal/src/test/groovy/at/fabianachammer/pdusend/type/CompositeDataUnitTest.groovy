package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.gcontracts.PreconditionViolation;
import org.junit.Test;

import at.fabianachammer.pdusend.type.AtomicDataUnit;
import at.fabianachammer.pdusend.type.CompositeDataUnit;
import at.fabianachammer.pdusend.type.DataUnit;

/**
 * @author fabian
 *
 */
class CompositeDataUnitTest {
	
	@Test(expected = PreconditionViolation)
	void createWithNullChildDataUnitsThrowsNullPointerException(){
		DataUnit du = new CompositeDataUnit((DataUnit[])null)
		println du
	}

	@Test
	void encodeWithOneAtomicDataUnitAsInitializedDataReturnsEncodingOfAtomicDataUnit() {
		byte[] atomicInput = [1, 1]
		DataUnit atomicDataUnit = new AtomicDataUnit(atomicInput)
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnit)
		byte[] expected = atomicDataUnit.encode()

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWithAtomicDataUnitArrayAsInitializedDataReturnsConcatenatedEncodingtOfAtomicDataUnits(){
		DataUnit[] initialDataUnits = [
			new AtomicDataUnit(1 as byte[]),
			new AtomicDataUnit(2 as byte[])
		]
		DataUnit compositeDataUnit = new CompositeDataUnit(initialDataUnits)
		byte[] expected = [1, 2]

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWithArrayOfAtomicAndCompositeDataUnitsAsInitializedDataReturnsConcatenatedEncodingOfAllAtomicDataUnits(){
		CompositeDataUnit innerCompositeDataUnit = new CompositeDataUnit(
				new AtomicDataUnit(1 as byte[]),
				new AtomicDataUnit(2 as byte[]))
		DataUnit[] initialDataUnits = [
			innerCompositeDataUnit,
			new AtomicDataUnit(3 as byte[])
		]
		DataUnit compositeDataUnit = new CompositeDataUnit(initialDataUnits)
		byte[] expected = [1, 2, 3]

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWithoutDataUnitsAsInitializedDataReturnsEmptyArray(){
		DataUnit compositeDataUnit = new CompositeDataUnit()
		byte[] expected = []

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void sizeInBitsWithTwoAtomicDataUnitsWithOneAsSizeInBitsReturnsTwo(){
		DataUnit[] atomicDataUnits = [
			new AtomicDataUnit(1),
			new AtomicDataUnit(1)
		]
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnits)
		int expectedSize = 2

		int actualSize = compositeDataUnit.sizeInBits()

		assertEquals(expectedSize, actualSize)
	}
	
	@Test
	void sizeInBitsWithoutAnyDataUnitsReturnsZero(){
		DataUnit compositeDataUnit = new CompositeDataUnit()
		int expectedSizeInBits = 0
		
		int actualSizeInBits = compositeDataUnit.sizeInBits()
		
		assertEquals(expectedSizeInBits, actualSizeInBits)
	}

	@Test
	void encodeWithTwoAtomicDataUnitsWithOneAsSizeInBitsReturnsZeroByteArrayWithLengthOne(){
		DataUnit[] atomicDataUnits = [
			new AtomicDataUnit(1),
			new AtomicDataUnit(1)
		]
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnits)
		byte[] expected = [0]

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void encodeWithTwoAtomicDataUnitsWithFiveAsSizeInBitsAndAllFiveBitsSetReturnsByteArrayWithLengthTwoAndAllLastBitsSet(){
		int sizeInBits = 5
		byte fiveBitsSet = 31
		DataUnit[] atomicDataUnits = [
			new AtomicDataUnit(sizeInBits, fiveBitsSet),
			new AtomicDataUnit(sizeInBits, fiveBitsSet)
		]
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnits)
		byte[] expected = [-1, 3]
		
		byte[] actual = compositeDataUnit.encode()
		
		assertArrayEquals(expected, actual)
	}
	
	@Test
	void equalsWithEqualChildElementCompositeDataUnitsReturnsTrue(){
		CompositeDataUnit compositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]))
		CompositeDataUnit equalCompositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]))
		
		assertTrue(compositeDataUnit.equals(equalCompositeDataUnit))
	}
	
	@Test
	void equalsWithSameCompositeDataUnitReturnsTrue(){
		CompositeDataUnit compositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]))
		
		assertTrue(compositeDataUnit.equals(compositeDataUnit))
	}
	
	@Test
	void equalsWithDifferentTypeReturnsFalse(){
		CompositeDataUnit compositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]))
		
		assertFalse(compositeDataUnit.equals(new Object()))
	}
	
	@Test
	void equalsWithDifferentChildElementsCompositeDataUnitsReturnsFalse(){
		CompositeDataUnit compositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]))
		CompositeDataUnit differentCompositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]), new AtomicDataUnit([2] as byte[]))
		
		assertFalse(compositeDataUnit.equals(differentCompositeDataUnit))
	}
	
	@Test
	void hashCodeWithEqualCompositeDataUnitsReturnsEqualHashCodes(){
		CompositeDataUnit compositeDataUnit = new CompositeDataUnit()
		CompositeDataUnit equalCompositeDataUnit = new CompositeDataUnit()
		
		assertTrue(compositeDataUnit.hashCode() == equalCompositeDataUnit.hashCode())
	}	
	
	@Test
	void hashCodeWithDifferentCompositeDataUnitsReturnsDifferentHashCodes(){
		CompositeDataUnit compositeDataUnit = new CompositeDataUnit()
		CompositeDataUnit differentCompositeDataUnit = new CompositeDataUnit(new AtomicDataUnit([1] as byte[]))
		
		assertFalse(compositeDataUnit.hashCode() == differentCompositeDataUnit.hashCode())
	}
	// TODO: hash code and equals implementation in CompositeDataUnit
}

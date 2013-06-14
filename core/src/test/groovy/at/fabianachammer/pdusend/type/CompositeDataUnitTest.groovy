package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class CompositeDataUnitTest {

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
	void addingOneDataUnitOnEmptyCompositeDataUnitMakesEncodeReturnByteRepresentationOfThatDataUnit(){
		DataUnit compositeDataUnit = new CompositeDataUnit()
		byte[] atomicInput = [1]
		byte[] expected = atomicInput
		compositeDataUnit.addDataUnit(new AtomicDataUnit(atomicInput))

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	void removingOneDataUnitFromCompositeDataUnitWithOneDataUnitMakesEncodeReturnAnEmptyArray(){
		DataUnit atomicDataUnit = new AtomicDataUnit(1 as byte)
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnit)
		compositeDataUnit.removeDataUnit(atomicDataUnit)
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
}

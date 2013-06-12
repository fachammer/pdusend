package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class CompositeDataUnitTest {

	@Test
	public void encodeWithOneAtomicDataUnitAsInitializedDataReturnsEncodingOfAtomicDataUnit() {
		byte[] atomicInput = [1, 1]
		DataUnit atomicDataUnit = new AtomicDataUnit(atomicInput)
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnit)
		byte[] expected = atomicDataUnit.encode()

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	public void encodeWithAtomicDataUnitArrayAsInitializedDataReturnsConcatenatedEncodingtOfAtomicDataUnits(){
		byte atomicInput1 = 1
		byte atomicInput2 = 2
		DataUnit atomicDataUnit1 = new AtomicDataUnit(atomicInput1)
		DataUnit atomicDataUnit2 = new AtomicDataUnit(atomicInput2)
		DataUnit[] atomicDataUnits = [
			atomicDataUnit1,
			atomicDataUnit2
		]
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnits)
		byte[] expected = ((atomicDataUnit1.encode() as List) << (atomicDataUnit2.encode() as List)).flatten()

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}

	@Test
	public void encodeWithArrayOfAtomicAndCompositeDataUnitsAsInitializedDataReturnsConcatenatedEncodingOfAllAtomicDataUnits(){
		byte[] atomicInput = 1..3
		AtomicDataUnit[] atomicDataUnits = atomicInput.collect{ new AtomicDataUnit(it) }
		DataUnit compositeDataUnit1 = new CompositeDataUnit(atomicDataUnits[0..1] as DataUnit[])
		DataUnit compositeDataUnit = new CompositeDataUnit(compositeDataUnit1, atomicDataUnits[2])
		byte[] expected = atomicInput

		byte[] actual = compositeDataUnit.encode()

		assertArrayEquals(expected, actual)
	}
	
	@Test
	public void encodeWithoutDataUnitsAsInitializedDataReturnsEmptyArray(){
		DataUnit compositeDataUnit = new CompositeDataUnit()
		byte[] expected = []
		
		byte[] actual = compositeDataUnit.encode()
		
		assertArrayEquals(expected, actual)
	}
	
	@Test
	public void addingOneDataUnitOnEmptyCompositeDataUnitMakesEncodeReturnByteRepresentationOfThatDataUnit(){
		DataUnit compositeDataUnit = new CompositeDataUnit()
		byte[] atomicInput = [1]
		byte[] expected = atomicInput
		compositeDataUnit.addDataUnit(new AtomicDataUnit(atomicInput))
		
		byte[] actual = compositeDataUnit.encode()
		
		assertArrayEquals(expected, actual)
	}
	
	@Test
	public void removingOneDataUnitFromCompositeDataUnitWithOneDataUnitMakesEncodeReturnAnEmptyArray(){
		DataUnit atomicDataUnit = new AtomicDataUnit(1 as byte)
		DataUnit compositeDataUnit = new CompositeDataUnit(atomicDataUnit)
		compositeDataUnit.removeDataUnit(atomicDataUnit)
		byte[] expected = []
		
		byte[] actual = compositeDataUnit.encode()
		
		assertArrayEquals(expected, actual)
	}
}

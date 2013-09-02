package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.AtomicDataUnitGenerator;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator;
import at.fabianachammer.pdusend.common.validation.*

import org.junit.Test;

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryTest {

	@Test
	void makeFromDataUnitSizeInBitsReturnsInstanceOfAtomicDataUnitFactory(){
		int dataUnitSizeInBits = 1

		DataUnitGenerator dataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(dataUnitSizeInBits)

		assertTrue(dataUnitFactory instanceof AtomicDataUnitGenerator)
	}

	@Test(expected = ValueTooLowException.class)
	void makeWithZeroAsDataUnitSizeInBitsThrowsValueTooLowException(){
		AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(0)
	}

	@Test(expected = ValueTooLowException.class)
	void makeWithMinusOneAsDataUnitSizeInBitsThrowsValueTooLowException(){
		AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(-1)
	}

	@Test
	void createDataUnitReturnsInstanceOfAtomicDataUnit(){
		int dataUnitSizeInBits = 1
		DataUnitGenerator atomicDataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(dataUnitSizeInBits)

		DataUnit dataUnit = atomicDataUnitFactory.generateDataUnit()

		assertTrue(dataUnit instanceof AtomicDataUnit)
	}

	@Test
	void makeWithDataUnitSizeInBitsReturnsAtomicDataUnitFactoryThatCreatesDataUnitsWithSizeOfGivenDataUnitSizeInBits(){
		int dataUnitSizeInBits = 1
		DataUnitGenerator atomicDataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		DataUnit atomicDataUnit = atomicDataUnitFactory.generateDataUnit()
		int expectedSize = dataUnitSizeInBits

		int actualSize = atomicDataUnit.sizeInBits()

		assertEquals(expectedSize, actualSize)
	}

	@Test
	void addPredefinedValueByKeyMakesPropertyAccessesOnTheFactoryWithTheGivenStringInAddPredefinedValueByKeyReturnTheSpecifiedValue(){
		int dataUnitSizeInBits = 1
		AtomicDataUnitGenerator atomicDataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		atomicDataUnitFactory.addPredefinedValueByKey("one", 1 as byte)
		DataUnit one = atomicDataUnitFactory.one
		byte[] expected = [1]

		byte[] actual = one.encode()

		assertArrayEquals(expected, actual)
	}

	@Test(expected = ValueTooGreatException.class)
	void addPredefinedValueByKeyWithIllegalSizeDataThrowsValueTooGreatException(){
		int dataUnitSizeInBits = 1
		AtomicDataUnitGenerator atomicDataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		atomicDataUnitFactory.addPredefinedValueByKey("on", 2 as byte)
	}

	@Test(expected = MissingPropertyException.class)
	void accessingNonExistenPropertyOnAtomicDataUnitFactoryThrowsMissingPropertyException(){
		AtomicDataUnitGenerator atomicDataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		atomicDataUnitFactory.nonExistentProperty
	}
	
	@Test
	void equalsWithAtomicDataUnitFactoryWithEqualDataUnitSizeInBitsAndEqualPredefinedValuesReturnsTrue(){
		AtomicDataUnitGenerator factory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		factory.addPredefinedValueByKey("key", [0] as byte[])
		
		AtomicDataUnitGenerator factoryToCompare = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		factoryToCompare.addPredefinedValueByKey("key", [0] as byte[])
		
		assertTrue(factory.equals(factoryToCompare))
	}
	
	@Test
	void equalsWithAtomicDataUnitFactoryWithEqualDataUnitSizeInBitsAndDifferentPredefinedValuesReturnsFalse(){
		AtomicDataUnitGenerator factory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		factory.addPredefinedValueByKey("key", [0] as byte[])
		AtomicDataUnitGenerator factoryToCompare = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		factoryToCompare.addPredefinedValueByKey("key", [1] as byte[])
		
		assertFalse(factory.equals(factoryToCompare))
	}
	
	@Test
	void equalsWithAtomicDataUnitFactoryWithEqualPredefinedValuesAndDifferentDataUnitSizeInBitsReturnsFalse(){
		AtomicDataUnitGenerator factory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		AtomicDataUnitGenerator differentDataUnitSizeInBitsFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(2)
		
		assertFalse(factory.equals(differentDataUnitSizeInBitsFactory))
	}
	
	@Test
	void equalsAsObjectWithEqualAtomicDataUnitFactoriesReturnsTrue(){
		Object factory1 = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		Object factory2 = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		
		assertTrue(factory1.equals(factory2))
	}
	
	@Test
	void equalsWithDifferentTypesReturnsFalse(){
		AtomicDataUnitGenerator factory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		Object otherObject = new Object()
		
		assertFalse(factory.equals(otherObject))
	}
	
	@Test
	void equalsWithNullReturnsFalse(){
		assertFalse(AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1).equals(null))
	}
	
	@Test
	void assertEqualsWithTwoEqualAtomicDataUnitFactoriesPasses(){
		assertEquals(AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1), AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1))
	}
	
	// TODO: equals / hashCode in atomic data unit factory
}

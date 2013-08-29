package at.fabianachammer.pdusend.core.type.factory;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.core.type.factory.AtomicDataUnitFactory;
import at.fabianachammer.pdusend.core.type.factory.DataUnitFactory;
import at.fabianachammer.pdusend.core.type.AtomicDataUnit
import at.fabianachammer.pdusend.core.type.DataUnit
import at.fabianachammer.pdusend.core.util.validation.*

import org.junit.Test;

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryTest {

	@Test
	void makeFromDataUnitSizeInBitsReturnsInstanceOfAtomicDataUnitFactory(){
		int dataUnitSizeInBits = 1

		DataUnitFactory dataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)

		assertTrue(dataUnitFactory instanceof AtomicDataUnitFactory)
	}

	@Test(expected = ValueTooLowException.class)
	void makeWithZeroAsDataUnitSizeInBitsThrowsValueTooLowException(){
		AtomicDataUnitFactory.makeFromDataUnitSizeInBits(0)
	}

	@Test(expected = ValueTooLowException.class)
	void makeWithMinusOneAsDataUnitSizeInBitsThrowsValueTooLowException(){
		AtomicDataUnitFactory.makeFromDataUnitSizeInBits(-1)
	}

	@Test
	void createDataUnitReturnsInstanceOfAtomicDataUnit(){
		int dataUnitSizeInBits = 1
		DataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)

		DataUnit dataUnit = atomicDataUnitFactory.createDataUnit()

		assertTrue(dataUnit instanceof AtomicDataUnit)
	}

	@Test
	void makeWithDataUnitSizeInBitsReturnsAtomicDataUnitFactoryThatCreatesDataUnitsWithSizeOfGivenDataUnitSizeInBits(){
		int dataUnitSizeInBits = 1
		DataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		DataUnit atomicDataUnit = atomicDataUnitFactory.createDataUnit()
		int expectedSize = dataUnitSizeInBits

		int actualSize = atomicDataUnit.sizeInBits()

		assertEquals(expectedSize, actualSize)
	}

	@Test
	void addPredefinedValueByKeyMakesPropertyAccessesOnTheFactoryWithTheGivenStringInAddPredefinedValueByKeyReturnTheSpecifiedValue(){
		int dataUnitSizeInBits = 1
		AtomicDataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		atomicDataUnitFactory.addPredefinedValueByKey("one", 1 as byte)
		DataUnit one = atomicDataUnitFactory.one
		byte[] expected = [1]

		byte[] actual = one.encode()

		assertArrayEquals(expected, actual)
	}

	@Test(expected = ValueTooGreatException.class)
	void addPredefinedValueByKeyWithIllegalSizeDataThrowsValueTooGreatException(){
		int dataUnitSizeInBits = 1
		AtomicDataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		atomicDataUnitFactory.addPredefinedValueByKey("on", 2 as byte)
	}

	@Test(expected = MissingPropertyException.class)
	void accessingNonExistenPropertyOnAtomicDataUnitFactoryThrowsMissingPropertyException(){
		AtomicDataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		atomicDataUnitFactory.nonExistentProperty
	}
	
	@Test
	void equalsWithAtomicDataUnitFactoryWithEqualDataUnitSizeInBitsAndEqualPredefinedValuesReturnsTrue(){
		AtomicDataUnitFactory factory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		factory.addPredefinedValueByKey("key", [0] as byte[])
		
		AtomicDataUnitFactory factoryToCompare = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		factoryToCompare.addPredefinedValueByKey("key", [0] as byte[])
		
		assertTrue(factory.equals(factoryToCompare))
	}
	
	@Test
	void equalsWithAtomicDataUnitFactoryWithEqualDataUnitSizeInBitsAndDifferentPredefinedValuesReturnsFalse(){
		AtomicDataUnitFactory factory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		factory.addPredefinedValueByKey("key", [0] as byte[])
		AtomicDataUnitFactory factoryToCompare = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		factoryToCompare.addPredefinedValueByKey("key", [1] as byte[])
		
		assertFalse(factory.equals(factoryToCompare))
	}
	
	@Test
	void equalsWithAtomicDataUnitFactoryWithEqualPredefinedValuesAndDifferentDataUnitSizeInBitsReturnsFalse(){
		AtomicDataUnitFactory factory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		AtomicDataUnitFactory differentDataUnitSizeInBitsFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(2)
		
		assertFalse(factory.equals(differentDataUnitSizeInBitsFactory))
	}
	
	@Test
	void equalsAsObjectWithEqualAtomicDataUnitFactoriesReturnsTrue(){
		Object factory1 = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		Object factory2 = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		
		assertTrue(factory1.equals(factory2))
	}
	
	@Test
	void equalsWithDifferentTypesReturnsFalse(){
		AtomicDataUnitFactory factory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		Object otherObject = new Object()
		
		assertFalse(factory.equals(otherObject))
	}
	
	@Test
	void equalsWithNullReturnsFalse(){
		assertFalse(AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1).equals(null))
	}
	
	@Test
	void assertEqualsWithTwoEqualAtomicDataUnitFactoriesPasses(){
		assertEquals(AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1), AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1))
	}
	
	// TODO: equals / hashCode in atomic data unit factory
}

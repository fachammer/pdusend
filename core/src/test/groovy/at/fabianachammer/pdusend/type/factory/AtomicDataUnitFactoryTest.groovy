package at.fabianachammer.pdusend.type.factory;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.factory.AtomicDataUnitFactory
import at.fabianachammer.pdusend.type.factory.DataUnitFactory
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryTest {

	@Test
	void makeWithDataUnitSizeInBitsReturnsInstanceOfAtomicDataUnitFactory(){
		int dataUnitSizeInBits = 1

		DataUnitFactory dataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)

		assertTrue(dataUnitFactory instanceof AtomicDataUnitFactory)
	}

	@Test(expected = IllegalArgumentException.class)
	void makeWithZeroAsDataUnitSizeInBitsThrowsIllegalArgumentException(){
		AtomicDataUnitFactory.makeFromDataUnitSizeInBits(0)
	}

	@Test(expected = IllegalArgumentException.class)
	void makeWithMinusOneAsDataUnitSizeInBitsThrowsIllegalArgumentExcepetion(){
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
		atomicDataUnitFactory.addPredefinedValueByKey("on", 1 as byte)
		DataUnit on = atomicDataUnitFactory.on
		byte[] expected = [1]

		byte[] actual = on.encode()

		assertArrayEquals(expected, actual)
	}

	@Test(expected = IllegalArgumentException.class)
	void addPredefinedValueByKeyWithIllegalSizeDataThrowsIllegalArgumentException(){
		int dataUnitSizeInBits = 1
		AtomicDataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(dataUnitSizeInBits)
		atomicDataUnitFactory.addPredefinedValueByKey("on", 2 as byte)
	}

	@Test(expected = MissingPropertyException.class)
	void accessingNonExistenPropertyOnAtomicDataUnitFactoryThrowsMissingPropertyException(){
		AtomicDataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		atomicDataUnitFactory.nonExistentProperty
	}
}

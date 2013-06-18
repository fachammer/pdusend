package at.fabianachammer.pdusend.type.factory;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.factory.AtomicDataUnitFactory
import at.fabianachammer.pdusend.type.factory.DataUnitFactory
import at.fabianachammer.pdusend.util.validation.*
import org.junit.Test;

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
}

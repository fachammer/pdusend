package at.fabianachammer.pdusend.type.factory;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.FunctionDataUnit
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author fabian
 *
 */
class FunctionDataUnitFactoryTest {

	@Test
	void makeFromClosureReturnsInstanceOfFunctionDataUnitFactory() {
		DataUnitFactory dataUnitFactory = FunctionDataUnitFactory.makeFromClosure({})

		assertTrue(dataUnitFactory instanceof FunctionDataUnitFactory)
	}
	
	@Test(expected = NullPointerException.class)
	void makeFromClosureWithNullThrowsNullPointerException(){
		FunctionDataUnitFactory.makeFromClosure(null)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsInstanceOfFunctionDataUnitFactory(){
		DataUnitFactory dataUnitFactory = FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits({}, 1)

		assertTrue(dataUnitFactory instanceof FunctionDataUnitFactory)
	}
	
	@Test(expected = NullPointerException.class)
	void makeFromClosureAndDataUnitSizeInBitsWithNullClosureThrowsNullPointerException(){
		FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits(null, 1)
	}

	@Test
	void createDataUnitReturnsInstanceOfFunctionDataUnit(){
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosure({})

		DataUnit dataUnit = functionDataUnitFactory.createDataUnit()

		assertTrue(dataUnit instanceof FunctionDataUnit)
	}

	@Test
	void createDataUnitWithStaticReturnClosureMakesEncodeOnCreatedFunctionDataUnitReturnTheValueOfTheStaticReturnClosure() {
		byte[] staticReturnValue = [0]
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosure({return staticReturnValue})
		byte[] expected = staticReturnValue
		DataUnit dataUnit = functionDataUnitFactory.createDataUnit()

		assertArrayEquals(staticReturnValue, dataUnit.encode())
	}

	@Test
	void makeFromClosureAndSizeInBitsReturnsFunctionDataUnitFactoryThatCreatesDataUnitsWithGivenSizeInBits(){
		int sizeInBits = 1
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits({}, sizeInBits)
		DataUnit dataUnit = functionDataUnitFactory.createDataUnit()
		int expected = sizeInBits
		
		assertEquals(expected, dataUnit.sizeInBits())
	}
}

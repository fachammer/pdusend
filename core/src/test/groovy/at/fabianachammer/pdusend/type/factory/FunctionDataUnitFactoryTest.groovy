package at.fabianachammer.pdusend.type.factory;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.FunctionDataUnit
import at.fabianachammer.pdusend.util.validation.ValueNullException;
import at.fabianachammer.pdusend.util.validation.ValueTooLowException;

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
	
	@Test(expected = ValueNullException.class)
	void makeFromClosureWithNullThrowsValueNullException(){
		FunctionDataUnitFactory.makeFromClosure(null)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsInstanceOfFunctionDataUnitFactory(){
		DataUnitFactory dataUnitFactory = FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits({}, 1)

		assertTrue(dataUnitFactory instanceof FunctionDataUnitFactory)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsFunctionDataUnitFactoryThatCreatesDataUnitsWithGivenSizeInBits(){
		int sizeInBits = 1
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits({}, sizeInBits)
		DataUnit dataUnit = functionDataUnitFactory.createDataUnit()
		int expected = sizeInBits
		
		assertEquals(expected, dataUnit.sizeInBits())
	}
	
	@Test(expected = ValueNullException.class)
	void makeFromClosureAndDataUnitSizeInBitsWithNullClosureThrowsValueNullException(){
		FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits(null, 1)
	}
	
	@Test(expected = ValueTooLowException.class)
	void makeFromClosureAndDataUnitSizeInBitsWithIllegalSizeInBitsThrowsValueTooLowException(){
		FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits({ }, 0)
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
	void createDataUnitWithSetClosureButUnsetDataUnitSizeInBitsReturnsFunctionDataUnitWithSizeInBitsOfEightTimesTheFunctionsValueArrayLength(){
		Closure<byte[]> function = { return new byte[1] }
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosure(function)
		FunctionDataUnit functionDataUnit = functionDataUnitFactory.createDataUnit()
		int expectedSizeInBits = function().length * 8
		
		int actualSizeInBits = functionDataUnit.sizeInBits()
		
		assertEquals(expectedSizeInBits, actualSizeInBits)
	}
}

package at.fabianachammer.pdusend.type.factory;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.FunctionDataUnit
import at.fabianachammer.pdusend.util.validation.ValueNullException;
import at.fabianachammer.pdusend.util.validation.ValueTooLowException;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author fabian
 *
 */
class FunctionDataUnitFactoryTest {
	
	private final def emptyClosure = {}
	
	private def makeStaticReturnClosure(value){
		return { value }
	}

	@Test
	void makeFromClosureReturnsInstanceOfFunctionDataUnitFactory() {
		DataUnitFactory dataUnitFactory = FunctionDataUnitFactory.makeFromClosure(emptyClosure)

		assertTrue(dataUnitFactory instanceof FunctionDataUnitFactory)
	}
	
	@Test(expected = ValueNullException.class)
	void makeFromClosureWithNullThrowsValueNullException(){
		FunctionDataUnitFactory.makeFromClosure(null)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsInstanceOfFunctionDataUnitFactory(){
		DataUnitFactory dataUnitFactory = FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits(emptyClosure, 1)

		assertTrue(dataUnitFactory instanceof FunctionDataUnitFactory)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsFunctionDataUnitFactoryThatCreatesDataUnitsWithGivenSizeInBits(){
		int sizeInBits = 1
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits(emptyClosure, sizeInBits)
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
		FunctionDataUnitFactory.makeFromClosureAndDataUnitSizeInBits(emptyClosure, 0)
	}

	@Test
	void createDataUnitReturnsInstanceOfFunctionDataUnit(){
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosure(emptyClosure)

		DataUnit dataUnit = functionDataUnitFactory.createDataUnit()

		assertTrue(dataUnit instanceof FunctionDataUnit)
	}

	@Test
	void createDataUnitWithStaticReturnClosureMakesEncodeOnCreatedFunctionDataUnitReturnTheValueOfTheStaticReturnClosure() {
		byte[] staticReturnValue = [0]
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosure(makeStaticReturnClosure(staticReturnValue))
		byte[] expected = staticReturnValue
		DataUnit dataUnit = functionDataUnitFactory.createDataUnit()

		assertArrayEquals(staticReturnValue, dataUnit.encode())
	}
	
	@Test
	void createDataUnitWithClosureReturnsFunctionDataUnitWithSizeInBitsOfEightTimesTheFunctionsValueArrayLength(){
		Closure<byte[]> function = makeStaticReturnClosure(new byte[1])
		FunctionDataUnitFactory functionDataUnitFactory = FunctionDataUnitFactory.makeFromClosure(function)
		FunctionDataUnit functionDataUnit = functionDataUnitFactory.createDataUnit()
		int expectedSizeInBits = function().length * 8
		
		int actualSizeInBits = functionDataUnit.sizeInBits()
		
		assertEquals(expectedSizeInBits, actualSizeInBits)
	}
}
package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator;
import at.fabianachammer.pdusend.type.FunctionDataUnit
import at.fabianachammer.pdusend.type.FunctionDataUnitGenerator;
import at.fabianachammer.pdusend.common.validation.ValueNullException;
import at.fabianachammer.pdusend.common.validation.ValueTooLowException;

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
		DataUnitGenerator dataUnitFactory = FunctionDataUnitGenerator.makeFromClosure(emptyClosure)

		assertTrue(dataUnitFactory instanceof FunctionDataUnitGenerator)
	}
	
	@Test(expected = ValueNullException.class)
	void makeFromClosureWithNullThrowsValueNullException(){
		FunctionDataUnitGenerator.makeFromClosure(null)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsInstanceOfFunctionDataUnitFactory(){
		DataUnitGenerator dataUnitFactory = FunctionDataUnitGenerator.makeFromClosureAndDataUnitSizeInBits(emptyClosure, 1)

		assertTrue(dataUnitFactory instanceof FunctionDataUnitGenerator)
	}
	
	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsFunctionDataUnitFactoryThatCreatesDataUnitsWithGivenSizeInBits(){
		int sizeInBits = 1
		FunctionDataUnitGenerator functionDataUnitFactory = FunctionDataUnitGenerator.makeFromClosureAndDataUnitSizeInBits(emptyClosure, sizeInBits)
		DataUnit dataUnit = functionDataUnitFactory.generateDataUnit()
		int expected = sizeInBits
		
		assertEquals(expected, dataUnit.sizeInBits())
	}
	
	@Test(expected = ValueNullException.class)
	void makeFromClosureAndDataUnitSizeInBitsWithNullClosureThrowsValueNullException(){
		FunctionDataUnitGenerator.makeFromClosureAndDataUnitSizeInBits(null, 1)
	}
	
	@Test(expected = ValueTooLowException.class)
	void makeFromClosureAndDataUnitSizeInBitsWithIllegalSizeInBitsThrowsValueTooLowException(){
		FunctionDataUnitGenerator.makeFromClosureAndDataUnitSizeInBits(emptyClosure, 0)
	}

	@Test
	void createDataUnitReturnsInstanceOfFunctionDataUnit(){
		FunctionDataUnitGenerator functionDataUnitFactory = FunctionDataUnitGenerator.makeFromClosure(emptyClosure)

		DataUnit dataUnit = functionDataUnitFactory.generateDataUnit()

		assertTrue(dataUnit instanceof FunctionDataUnit)
	}

	@Test
	void createDataUnitWithStaticReturnClosureMakesEncodeOnCreatedFunctionDataUnitReturnTheValueOfTheStaticReturnClosure() {
		byte[] staticReturnValue = [0]
		FunctionDataUnitGenerator functionDataUnitFactory = FunctionDataUnitGenerator.makeFromClosure(makeStaticReturnClosure(staticReturnValue))
		byte[] expected = staticReturnValue
		DataUnit dataUnit = functionDataUnitFactory.generateDataUnit()

		assertArrayEquals(staticReturnValue, dataUnit.encode())
	}
	
	@Test
	void createDataUnitWithClosureReturnsFunctionDataUnitWithSizeInBitsOfEightTimesTheFunctionsValueArrayLength(){
		Closure<byte[]> function = makeStaticReturnClosure(new byte[1])
		FunctionDataUnitGenerator functionDataUnitFactory = FunctionDataUnitGenerator.makeFromClosure(function)
		FunctionDataUnit functionDataUnit = functionDataUnitFactory.generateDataUnit()
		int expectedSizeInBits = function().length * 8
		
		int actualSizeInBits = functionDataUnit.sizeInBits()
		
		assertEquals(expectedSizeInBits, actualSizeInBits)
	}
}
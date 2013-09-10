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
class FunctionDataUnitGeneratorTest {
	
	private final def emptyClosure = {}
	
	private def makeStaticReturnClosure(value){
		return { value }
	}

	@Test
	void makeFromClosureAndDataUnitSizeInBitsReturnsFunctionDataUnitFactoryThatCreatesDataUnitsWithGivenSizeInBits(){
		int sizeInBits = 1
		FunctionDataUnitGenerator functionDataUnitFactory = new FunctionDataUnitGenerator('someId', emptyClosure, sizeInBits)
		DataUnit dataUnit = functionDataUnitFactory.generateDataUnit()
		int expected = sizeInBits
		
		assertEquals(expected, dataUnit.sizeInBits())
	}
	
	@Test
	void createDataUnitWithStaticReturnClosureMakesEncodeOnCreatedFunctionDataUnitReturnTheValueOfTheStaticReturnClosure() {
		byte[] staticReturnValue = [0]
		FunctionDataUnitGenerator functionDataUnitFactory = new FunctionDataUnitGenerator('someId', makeStaticReturnClosure(staticReturnValue))
		byte[] expected = staticReturnValue
		DataUnit dataUnit = functionDataUnitFactory.generateDataUnit()

		assertArrayEquals(staticReturnValue, dataUnit.encode())
	}
	
	@Test
	void createDataUnitWithClosureReturnsFunctionDataUnitWithSizeInBitsOfEightTimesTheFunctionsValueArrayLength(){
		Closure<byte[]> function = makeStaticReturnClosure(new byte[1])
		FunctionDataUnitGenerator functionDataUnitFactory = new FunctionDataUnitGenerator('someId', function)
		FunctionDataUnit functionDataUnit = functionDataUnitFactory.generateDataUnit()
		int expectedSizeInBits = function().length * 8
		
		int actualSizeInBits = functionDataUnit.sizeInBits()
		
		assertEquals(expectedSizeInBits, actualSizeInBits)
	}
}
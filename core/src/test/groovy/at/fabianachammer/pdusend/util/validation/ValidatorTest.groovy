package at.fabianachammer.pdusend.util.validation

import static org.junit.Assert.*

import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.Rule

import at.fabianachammer.pdusend.util.validation.ValueNotNullException
import at.fabianachammer.pdusend.util.validation.Validator
import at.fabianachammer.pdusend.util.validation.ValueTooLowException

/**
 * @author fabian
 *
 */
class ValidatorTest {

	private void assertExceptionWithGivenMessageIsThrownByClosure(Class<? extends Exception> exceptionClass, String expectedMessage, Closure codeToCheck){
		try{
			checkCodeAndFailIfNoExceptionIsThrown(exceptionClass, codeToCheck)
		}
		catch(Exception e){
			handleExcpectedException(e, exceptionClass, expectedMessage)
		}
	}

	private void checkCodeAndFailIfNoExceptionIsThrown(Class<? extends Exception> exceptionClass, Closure codeToCheck){
		codeToCheck()
		fail("didn't throw $exceptionClass.name")
	}

	private void handleExcpectedException(Exception e, Class<? extends Exception> exceptionClass, String expectedMessage){
		if(e.getClass() == exceptionClass){
			assertEquals(expectedMessage, e.getMessage())
		}
		else{
			fail("expected $exceptionClass.name, but was ${e.class.name}")
		}
	}

	@Test
	void validateNullWithNullValuePasses() {
		Validator v = new Validator(null, "value")
		v.validateNull()
	}

	@Test
	void validateNullWithNonNullValueThrowsNotNullException(){
		def value = new Object()
		Validator v = new Validator(value, "value")
		String expectedExceptionMessage = "value must be null, but was $value"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueNotNullException.class, expectedExceptionMessage) { v.validateNull() }
	}

	@Test
	void validateNotNullWithNullValueThrowsValueNullException(){
		Validator v = new Validator(null, "value")
		String expectedExceptionMessage = "value must have a value, but was null"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueNullException.class, expectedExceptionMessage) { v.validateNotNull() }
	}

	@Test
	void validateNotNullWithNonNullValuePasses(){
		Validator v = new Validator(new Object(), "value")
		v.validateNotNull()
	}

	@Test
	void validateGreaterThanWithValueGreaterThanSpecifiedPasses(){
		Validator v = new Validator(1, "value")
		v.validateGreaterThan(0)
	}

	@Test
	void validateGreaterThanWithValueLowerThanSpecifiedThrowsValueTooLowException(){
		Validator v = new Validator(0, "value")
		String expectedExceptionMessage = "value must be greater than 1, but was 0"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueTooLowException.class, expectedExceptionMessage) { v.validateGreaterThan(1) }
	}

	@Test
	void validateGreaterThanWithValueEqualToSpecifiedThrowsValueTooLowException(){
		Validator v = new Validator(0, "value")
		String expectedExceptionMessage = "value must be greater than 0, but was 0"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueTooLowException.class, expectedExceptionMessage) { v.validateGreaterThan(0) }
	}

	@Test
	void validateLowerThanWithValueLowerThanSpecifiedPasses(){
		Validator v = new Validator(0, "value")
		v.validateLowerThan(1)
	}

	@Test
	void validateLowerThanWithValueGreaterThanSpecifiedThrowsValueTooGreatException(){
		Validator v = new Validator(1, "value")
		String expectedExceptionMessage = "value must be lower than 0, but was 1"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueTooGreatException.class, expectedExceptionMessage) { v.validateLowerThan(0) }
	}

	@Test
	void validateLowerThanWithValueEqualToSpecifiedThrowsValueTooGreatException(){
		Validator v = new Validator(0, "value")
		String expectedExceptionMessage = "value must be lower than 0, but was 0"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueTooGreatException.class, expectedExceptionMessage) { v.validateLowerThan(0) }
	}

	@Test
	void validateBetweenInclusiveWithValueInBetweenSpecifiedRangePasses(){
		Validator v = new Validator(1, "value")
		v.validateBetweenInclusive(0..2)
	}

	@Test
	void validateBetweenInclusiveWithValueOutsideSpecifiedRangeThrowsValueOutOfRangeException(){
		Validator v = new Validator(0, "value")
		String expectedExceptionMessage = "value must be between 1 and 2, but was 0"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueOutOfRangeException.class, expectedExceptionMessage) {
			v.validateBetweenInclusive(1..2)
		}
	}

	@Test
	void validateBetweenInclusiveWithValueEqualToMinimumOfRangePasses(){
		Validator v = new Validator(0, "value")
		v.validateBetweenInclusive(0..1)
	}

	@Test
	void validateBetweenInclusiveWithValueEqualToMaximumOfRangePasses(){
		Validator v = new Validator(1, "value")
		v.validateBetweenInclusive(0..1)
	}

	@Test
	void validateLowerThanOrEqualsWithValueLowerThanSpecifiedPasses(){
		Validator v = new Validator(0, "value")
		v.validateLowerThanOrEquals(1)
	}

	@Test
	void validateLowerThanOrEqualsWithValueGreaterThanSpecifiedThrowsValueTooGreateExceptionWithProperExceptionMessage(){
		Validator v = new  Validator(1, "value")
		String expectedExceptionMessage = "value must be lower than or equal to 0, but was 1"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueTooGreatException.class, expectedExceptionMessage) { v.validateLowerThanOrEquals(0) }
	}

	@Test
	void validateLowerThanOrEqualsWithValueEqualToSpecifiedPasses(){
		Validator v = new Validator(0, "value")
		v.validateLowerThanOrEquals(0)
	}

	@Test
	void validateGreaterThanOrEqualsWithValueGreaterThanSpecifiedPasses(){
		Validator v = new Validator(1, "value")
		v.validateGreaterThanOrEquals(0)
	}

	@Test
	void validateGreaterThanOrEqualsWithValueLowerThanSpecifiedThrowsValueTooLowExceptionWithProperExceptionMessage(){
		Validator v = new Validator(0, "value")
		String expectedExceptionMessage = "value must be greater than or equal to 1, but was 0"

		assertExceptionWithGivenMessageIsThrownByClosure(ValueTooLowException.class, expectedExceptionMessage) { v.validateGreaterThanOrEquals(1) }
	}

	@Test
	void validateGreaterThanorEqualsWithValueEqualToSpecifiedPasses(){
		Validator v = new Validator(0, "value")
		v.validateGreaterThanOrEquals(0)
	}
}
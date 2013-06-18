package at.fabianachammer.pdusend.util.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.util.validation.ValueNotNullException;
import at.fabianachammer.pdusend.util.validation.Validator;
import at.fabianachammer.pdusend.util.validation.ValueTooLowException;

/**
 * @author fabian
 *
 */
class ValidatorTest {

	@Test
	void validateNullWithNullValuePasses() {
		Validator v = new Validator(null, "value")
		v.validateNull()
	}

	@Test(expected = ValueNotNullException.class)
	void validateNullWithNonNullValueThrowsNotNullException(){
		Validator v = new Validator(new Object(), "value")
		v.validateNull()
	}
	
	@Test(expected = ValueNullException.class)
	void validateNotNullWithNullValueThrowsValueNullException(){
		Validator v = new Validator(null, "value")
		v.validateNotNull()
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
	
	@Test(expected = ValueTooLowException.class)
	void validateGreaterThanWithValueLowerThanSpecifiedThrowsValueTooLowException(){
		Validator v = new Validator(0, "value")
		v.validateGreaterThan(1)
	}
	
	@Test(expected = ValueTooLowException.class)
	void validateGreaterThanWithValueEqualToSpecifiedThrowsValueTooLowException(){
		Validator v = new Validator(0, "value")
		v.validateGreaterThan(0)
	}
	
	@Test
	void validateLowerThanWithValueLowerThanSpecifiedPasses(){
		Validator v = new Validator(0, "value")
		v.validateLowerThan(1)
	}
	
	@Test(expected = ValueTooGreatException.class)
	void validateLowerThanWithValueGreaterThanSpecifiedThrowsValueTooGreatException(){
		Validator v = new Validator(1, "value")
		v.validateLowerThan(0)
	}
	
	@Test(expected = ValueTooGreatException.class)
	void validateLowerThanWithValueEqualToSpecifiedThrowsValueTooGreatException(){
		Validator v = new Validator(0, "value")
		v.validateLowerThan(0)
	}
	
	@Test
	void validateBetweenInclusiveWithValueInBetweenSpecifiedRangePasses(){
		Validator v = new Validator(1, "value")
		v.validateBetweenInclusive(0..2)
	}
	
	@Test(expected = ValueOutOfRangeException.class)
	void validateBetweenInclusiveWithValueOutsideSpecifiedRangeThrowsValueOutOfRangeException(){
		Validator v = new Validator(0, "value")
		v.validateBetweenInclusive(1..2)
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
	
	@Test(expected = ValueTooGreatException.class)
	void validateLowerThanOrEqualsWithValueGreaterThanSpecifiedThrowsValueTooGreateException(){
		Validator v = new  Validator(1, "value")
		v.validateLowerThanOrEquals(0)
	}
	
	@Test
	void validateLowerThanOrEqualsWithValueEqualsToSpecifiedPasses(){
		Validator v = new Validator(0, "value")
		v.validateLowerThanOrEquals(0)
	}
	
	@Test
	void validateGreaterThanOrEqualsWithValueGreaterThanSpecifiedPasses(){
		Validator v = new Validator(1, "value")
		v.validateGreaterThanOrEquals(0)
	}
	
	@Test(expected = ValueTooLowException.class)
	void validateGreaterThanOrEqualsWithValueLowerThanSpecifiedThrowsValueTooLowException(){
		Validator v = new Validator(0, "value")
		v.validateGreaterThanOrEquals(1)
	}
}

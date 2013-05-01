package at.fabianachammer.pdusend.ui.controller.validation;

import static org.junit.Assert.*;
import org.junit.Rule

import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author fabian
 *
 */
class ValidatorTest {

	@Test
	void validateGreaterThanWithGreaterThanSpecifiedMinimumInputDoesNothing() {
		int min = 0
		int input = 1
		String argumentName = "someArgument"

		Validator.validateGreaterThan(min, input, argumentName)
	}

	@Test(expected = IllegalArgumentException.class)
	void validateGreaterThanWithEqualToSpecifiedMinimumInputThrowsIllegalArgumentException(){
		int min = 0
		int input = 0
		String argumentName = "someArgument"

		Validator.validateGreaterThan(min, input, argumentName)
	}

	@Test(expected = IllegalArgumentException.class)
	void validateGreaterThanWithLowerThanSpecifiedMinimumInputThrowsIllegalArgumentException(){
		int min = 0
		int input = -1
		String argumentName = "someArgument"

		Validator.validateGreaterThan(min, input, argumentName)
	}

	@Test
	void validateGreaterThanEqualsWithGreaterThanSpecifiedMinimumInputDoesNothing(){
		int min = 0
		int input = 1
		String argumentName = "someArgument"

		Validator.validateGreaterThanEquals(min, input, argumentName)
	}

	@Test
	void validateGreaterThanEqualsWithEqualToSpecifiedMinimumInputDoesNothing(){
		int min = 0
		int input = 0
		String argumentName = "someArgument"

		Validator.validateGreaterThanEquals(min, input, argumentName)
	}

	@Test(expected = IllegalArgumentException.class)
	void validateGreaterThanEqualsWithLowerThanSpecifiedMinimumInputThrowsIllegalArgumentException(){
		int min = 0
		int input = -1
		String argumentName = "someArgument"

		Validator.validateGreaterThanEquals(min, input, argumentName)
	}

	@Test
	void validateLowerThanWithLowerThanSpecifiedMaximumInputDoesNothing(){
		int max = 0
		int input = -1
		String argumentName = "someArgument"

		Validator.validateLowerThan(max, input, argumentName)
	}

	@Test(expected = IllegalArgumentException.class)
	void validateLowerThanWithEqualToSpecifiedMaximumInputThrowsIllegalArgumentException(){
		int max = 0
		int input = 0
		String argumentName = "someArgument"

		Validator.validateLowerThan(max, input, argumentName)
	}

	@Test(expected = IllegalArgumentException.class)
	void validateLowerThanWithGreaterThanSpecifiedMaximumInputThrowsIllegalArgumentException(){
		int max = 0
		int input = 1
		String argumentName = "someArgument"

		Validator.validateLowerThan(max, input, argumentName)
	}

	@Test
	void validateLowerThanEqualsWithLowerThanSepcifiedMaximumInputDoesNothing(){
		int max = 0
		int input = -1
		String argumentName = "someArgument"

		Validator.validateLowerThanEquals(max, input, argumentName)
	}

	@Test
	void validateLowerThanEqualsWithEqualToSpecifiedMaximumInputDoesNothing(){
		int max = 0
		int input = 0
		String argumentName = "someArgument"

		Validator.validateLowerThanEquals(max, input, argumentName)
	}

	@Test(expected = IllegalArgumentException.class)
	void validateLowerThanEqualsWithGreaterThanSpecifiedMaximumInputThrowsIllegalArgumentException(){
		int max = 0
		int input = 1
		String argumentName = "someArgument"

		Validator.validateLowerThanEquals(max, input, argumentName)
	}

	@Test
	void validateNotNullWithNotNullInputDoesNothing(){
		String argumentName = "someArgument"
		def input = new Object()

		Validator.validateNotNull(input, argumentName)
	}

	@Test(expected = NullPointerException.class)
	void validateNotNullWithNullInputThrowsNullPointerException(){
		String argumentName = "someArgument"

		Validator.validateNotNull(null, argumentName)
	}
}

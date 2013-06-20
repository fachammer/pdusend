package at.fabianachammer.pdusend.util.validation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class ValidationExceptionTest {

	@Test
	void getValueNameReturnsGivenValueNameInConstructor() {
		String valueName = "value"
		ValidationException ve = new ValidationException(null, valueName, null)
		
		String actualValueName = ve.valueName
		
		assertEquals(valueName, actualValueName)
	}

	@Test
	void getValueReturnsGivenValueInConstructor(){
		def value = new Object()
		ValidationException ve = new ValidationException(null, null, value)
		
		def actualValue = ve.value
		
		assertEquals(value, actualValue)
	}
}

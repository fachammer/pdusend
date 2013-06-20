package at.fabianachammer.pdusend.util.validation

/**
 * @author fabian
 *
 */
class Validator {

	private static String NOT_NULL_EXCEPTION_MESSAGE_FORMAT = "%s must be null, but was %s"
	private static String NULL_POINTER_EXCEPTION_MESSAGE_FORMAT = "%s must have a value, but was null"
	private static String VALUE_TOO_LOW_EXCEPTION_MESSAGE_FORMAT = "%s must be greater than or equal to %s, but was %s"
	private static String VALUE_TOO_LOW_OR_EQUALS_EXCEPTION_MESSAGE_FORMAT = "%s must be greater than %s, but was %s"
	private static String VALUE_TOO_GREAT_EXCEPTION_MESSAGE_FORMAT = "%s must be lower than or equal to %s, but was %s"
	private static String VALUE_TOO_GREAT_OR_EQUALS_EXCEPTION_MESSAGE_FORMAT = "%s must be lower than %s, but was %s"
	private static String VALUE_OUT_OF_RANGE_EXCEPTION_MESSAGE_FORMAT = "%s must be between %s and %s, but was %s"

	private value
	private String valueName

	Validator(value, String valueName){
		this.value = value
		this.valueName = valueName
	}

	void validateNull(){
		if(value != null){
			throwNotNullException()
		}
	}

	private void throwNotNullException(){
		String exceptionMessage = String.format(NOT_NULL_EXCEPTION_MESSAGE_FORMAT, valueName, value)
		
		throw new ValueNotNullException(exceptionMessage, valueName, value)
	}
	
	void validateNotNull(){
		if(value == null){
			throwNullPointerException()
		}
	}

	private void throwNullPointerException(){
		String exceptionMessage = String.format(NULL_POINTER_EXCEPTION_MESSAGE_FORMAT, valueName)
		
		throw new ValueNullException(exceptionMessage, valueName)
	}

	void validateGreaterThan(min){
		if(value <= min){
			throwValueTooLowOrEqualsException(min)
		}
	}

	private void throwValueTooLowOrEqualsException(min){
		String exceptionMessage = String.format(VALUE_TOO_LOW_OR_EQUALS_EXCEPTION_MESSAGE_FORMAT, valueName, min, value)
		
		throw new ValueTooLowException(exceptionMessage, valueName, value)
	}

	void validateGreaterThanOrEquals(min){
		if(value < min){
			throwValueTooLowException(min)
		}
	}

	private void throwValueTooLowException(min){
		String exceptionMessage = String.format(VALUE_TOO_LOW_EXCEPTION_MESSAGE_FORMAT, valueName, min, value)
		
		throw new ValueTooLowException(exceptionMessage, valueName, value)
	}

	void validateLowerThan(max){
		if(value >= max){
			throwValueTooGreatOrEqualsException(max)
		}
	}

	private void throwValueTooGreatOrEqualsException(max){
		String exceptionMessage = String.format(VALUE_TOO_GREAT_OR_EQUALS_EXCEPTION_MESSAGE_FORMAT, valueName, max, value)
		
		throw new ValueTooGreatException(exceptionMessage, valueName, value)
	}

	void validateLowerThanOrEquals(max){
		if(value > max){
			throwValueTooGreatException(max)
		}
	}

	private void throwValueTooGreatException(max){
		String exceptionMessage = String.format(VALUE_TOO_GREAT_EXCEPTION_MESSAGE_FORMAT, valueName, max, value)
		
		throw new ValueTooGreatException(exceptionMessage, valueName, value)
	}

	void validateBetweenInclusive(Range range){
		validateRangeNotNull(range)
		if(isValueOutOfRange(range)){
			throwValueOutOfRangeException(range)
		}
	}
	
	private void validateRangeNotNull(Range range){
		Validator v = new Validator(range, "range")
		v.validateNotNull()
	}

	private boolean isValueOutOfRange(Range range){
		return !range.contains(value)
	}

	private void throwValueOutOfRangeException(Range range){
		def min = range.min()
		def max = range.max()
		String exceptionMessage = String.format(VALUE_OUT_OF_RANGE_EXCEPTION_MESSAGE_FORMAT, valueName, min, max, value)
		
		throw new ValueOutOfRangeException(exceptionMessage, valueName, value)
	}
}

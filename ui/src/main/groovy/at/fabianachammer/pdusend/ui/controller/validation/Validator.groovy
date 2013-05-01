package at.fabianachammer.pdusend.ui.controller.validation

/**
 * Class for validating certain values against constraints.
 * @author fabian
 *
 */
class Validator {

	private static final def PLACE_HOLDER_PREFIX = "@"
	private static final def ARGUMENT = "$PLACE_HOLDER_PREFIX"+"argument"
	private static final def MIN = "$PLACE_HOLDER_PREFIX"+"min"
	private static final def MAX = "$PLACE_HOLDER_PREFIX"+"max"
	private static final def GREATER_THAN_VALIDATION_PATTERN = "$ARGUMENT must be greater than $MIN"
	private static final def GREATER_THAN_EQUALS_VALIDATION_PATTERN = "$ARGUMENT must be greater than or equals $MIN"
	private static final def LOWER_THAN_VALIDATION_PATTERN = "$ARGUMENT must be lower than $MAX"
	private static final def LOWER_THAN_EQUALS_VALIDATION_PATTERN = "$ARGUMENT must be lower than or equals $MAX"
	private static final def NOT_NULL_VALIDATION_PATTERN = "$ARGUMENT must not be null"

	/**
	 * Validates whether a given value is greater than a given minimum value and throws an IllegalArgumentException if not.
	 * @param min minimum value
	 * @param value value to be validated
	 * @param argumentName argument name of the validated value
	 */
	static <T> void validateGreaterThan(T min, Comparable<T> value, String argumentName){
		if(value.compareTo(min) <= 0){
			throw new IllegalArgumentException(replaceGreaterThanPlaceHolders(min, argumentName))
		}
	}

	/**
	 * Validates whether a given value is greater than or equals a given minimum value and throws an IllegalArgumentException if not.
	 * @param min minimum value
	 * @param value value to be validated
	 * @param argumentName argument name of the validated value
	 */
	static <T> void validateGreaterThanEquals(T min, Comparable<T> value, String argumentName){
		if(value.compareTo(min) < 0){
			throw new IllegalArgumentException(replaceGreaterThanEqualsPlaceHolders(min, argumentName))
		}
	}

	/**
	 * Validates whether a given value is lower than a given maximum value and throws an IllegalArgumentException if not.
	 * @param max maximum value
	 * @param value value to be validated
	 * @param argumentName argument name of the validated value
	 */
	static <T> void validateLowerThan(T max, Comparable<T> value, String argumentName){
		if(value.compareTo(max) >= 0){
			throw new IllegalArgumentException(replaceLowerThanPlaceHolders(max, argumentName))
		}
	}

	/**
	 * Validates whether a given value is lower than or equals a given maximum value and throws an IllegalArgumentException if not.
	 * @param max maximum value
	 * @param value value to be validated
	 * @param argumentName argument name of the validated value
	 */
	static <T> void validateLowerThanEquals(T max, Comparable<T> value, String argumentName){
		if(value.compareTo(max) > 0){
			throw new IllegalArgumentException(replaceLowerThanEqualsPlaceHolders(max, argumentName))
		}
	}

	/**
	 * Validates whether a given value is not null and throws an NullPointerException if not.
	 * @param value value to be validated
	 * @param argumentName argument name of the validated value
	 */
	static void validateNotNull(value, String argumentName){
		if(value == null){
			throw new NullPointerException(replaceNotNullPlaceHolders(argumentName))
		}
	}

	/**
	 * replaces the placeholders of the greater than validation pattern with actual values
	 * @param min maximum value
	 * @param value name of the argument that gets validated
	 * @return replaced string
	 */
	private static <T> String replaceGreaterThanPlaceHolders(T min, String argument){
		GREATER_THAN_VALIDATION_PATTERN.toString().replace(ARGUMENT, argument).replace(MIN, min.toString())
	}

	/**
	 * replaces the placeholders of the greater than equals validation pattern with actual values
	 * @param min maximum value
	 * @param value name of the argument that gets validated
	 * @return replaced string
	 */
	private static <T> String replaceGreaterThanEqualsPlaceHolders(T min, String argument){
		GREATER_THAN_EQUALS_VALIDATION_PATTERN.toString().replace(ARGUMENT, argument).replace(MIN, min.toString())
	}

	/**
	 * replaces the placeholders of the lower than validation pattern with actual values
	 * @param max minimum value
	 * @param argument name of the argument that gets validated
	 * @return replaced string
	 */
	private static <T> String replaceLowerThanPlaceHolders(T max, String argument){
		LOWER_THAN_VALIDATION_PATTERN.toString().replace(ARGUMENT, argument).replace(MAX, max.toString())
	}

	/**
	 * replaces the placeholders of the lower than equals validation pattern with actual values
	 * @param max minimum value
	 * @param argument name of the argument that gets validated
	 * @return replaced string
	 */
	private static <T> String replaceLowerThanEqualsPlaceHolders(T max, String argument){
		LOWER_THAN_EQUALS_VALIDATION_PATTERN.toString().replace(ARGUMENT, argument).replace(MAX, max.toString())
	}

	/**
	 * replaces the placeholders of the not null validation pattern with actual values
	 * @param argument name of the argument that gets validated
	 * @return replaced string
	 */
	private static <T> String replaceNotNullPlaceHolders(String argument){
		NOT_NULL_VALIDATION_PATTERN.toString().replace(ARGUMENT, argument)
	}
}

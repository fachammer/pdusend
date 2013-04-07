/**
 * 
 */
package at.fabianachammer.nsend.util;

/**
 * Utility class that provides methods for validating arguments.
 * 
 * @author fabian
 * 
 */
public final class Validation {

	private static final String REPLACEMENT_IDENTIFIER = "@";

	private static final String ARGUMENT_NAME_IDENTIFIER =
			REPLACEMENT_IDENTIFIER
					+ "argumentName";
	private static final String MAX_VALUE_IDENTIFIER = REPLACEMENT_IDENTIFIER
			+ "maxValue";
	private static final String MIN_VALUE_IDENTIFIER = REPLACEMENT_IDENTIFIER
			+ "minValue";
	private static final String COMPARE_VALUE_IDENTIFIER =
			REPLACEMENT_IDENTIFIER
					+ "compareValue";

	/*
	 * TODO: read exception messages from file
	 */
	private static final String GREATER_THAN_MESSAGE = ARGUMENT_NAME_IDENTIFIER
			+ " must not be greater than " + MAX_VALUE_IDENTIFIER;
	private static final String LOWER_THAN_MESSAGE = ARGUMENT_NAME_IDENTIFIER
			+ " must not be lower than " + MIN_VALUE_IDENTIFIER;
	private static final String NOT_EQUAL_MESSAGE = ARGUMENT_NAME_IDENTIFIER
			+ " must be exactly " + COMPARE_VALUE_IDENTIFIER;
	private static final String NULL_MESSAGE = ARGUMENT_NAME_IDENTIFIER
			+ " must not be null";
	private static final String NOT_BETWEEN_MESSAGE = ARGUMENT_NAME_IDENTIFIER
			+ " must be between " + MIN_VALUE_IDENTIFIER + " and "
			+ MAX_VALUE_IDENTIFIER;

	/**
	 * Private constructor because this is an utility class.
	 */
	private Validation() {
	}

	/**
	 * Creates an IllegalArgumentException for cases where a value being greater
	 * than a maximum value is illegal.
	 * 
	 * @param argumentName
	 *            name of the argument that is illegal
	 * @param maxValue
	 *            maximum value the argument should have had.
	 * @return IllegalArgumentException containing a meaningful exception
	 *         message
	 */
	private static <T> IllegalArgumentException createGreaterThanException(
			final String argumentName, final T maxValue) {
		String message =
				GREATER_THAN_MESSAGE.replaceFirst(
						ARGUMENT_NAME_IDENTIFIER, argumentName).replaceFirst(
						MAX_VALUE_IDENTIFIER, maxValue.toString());

		return new IllegalArgumentException(message);
	}
	
	/**
	 * Creates an IllegalArgumentException for cases where a value being lower
	 * than a minimum value is illegal.
	 * 
	 * @param argumentName
	 *            name of the argument that is illegal
	 * @param minValue
	 *            minimum value the argument should have had.
	 * @return IllegalArgumentException containing a meaningful exception
	 *         message
	 */
	private static <T> IllegalArgumentException createLowerThanException(
			final String argumentName, final T minValue) {
		String message =
				LOWER_THAN_MESSAGE.replaceFirst(
						ARGUMENT_NAME_IDENTIFIER, argumentName).replaceFirst(
						MIN_VALUE_IDENTIFIER, minValue.toString());

		return new IllegalArgumentException(message);
	}

	/**
	 * Creates an IllegalArgumentException for cases where a value being not
	 * equal to a compare value is illegal.
	 * 
	 * @param argumentName
	 *            name of the argument that is illegal
	 * @param compareValue
	 *            value the argument should have had
	 * @return IllegalArgumentException containing a meaningful exception
	 *         message
	 */
	private static <T> IllegalArgumentException createNotEqualException(
			final String argumentName, final T compareValue) {
		String message =
				NOT_EQUAL_MESSAGE.replaceFirst(
						ARGUMENT_NAME_IDENTIFIER, argumentName).replaceFirst(
						COMPARE_VALUE_IDENTIFIER, compareValue.toString());

		return new IllegalArgumentException(message);
	}

	/**
	 * Creates a NullPointerException for cases where a value being null is
	 * illegal.
	 * 
	 * @param argumentName
	 *            name of the argument that is illegal
	 * @return NullPointerException containing a meaningful exception message
	 */
	private static NullPointerException createNullException(
			final String argumentName) {
		String message =
				NULL_MESSAGE.replaceFirst(
						ARGUMENT_NAME_IDENTIFIER, argumentName);

		return new NullPointerException(message);
	}

	/**
	 * Creates an IllegalArgumentException for cases where a value being not
	 * between a range of values is illegal.
	 * 
	 * @param argumentName
	 *            name of the argument that is illegal
	 * @param minValue
	 *            minimum value the argument should have had
	 * @param maxValue
	 *            maximum value the argument should have had
	 * @return IllegalArgumentException containing a meaningful exception
	 *         message
	 */
	private static <T> IllegalArgumentException createNotBetweenException(
			final String argumentName, final T minValue, final T maxValue) {
		String message =
				NOT_BETWEEN_MESSAGE
						.replaceFirst(ARGUMENT_NAME_IDENTIFIER, argumentName)
						.replaceFirst(MIN_VALUE_IDENTIFIER, minValue.toString())
						.replaceFirst(MAX_VALUE_IDENTIFIER, maxValue.toString());

		return new IllegalArgumentException(message);
	}

	/**
	 * Throws an IllegalArgumentException, if the actual value is greater than a
	 * specified maximum value.
	 * 
	 * @param argument
	 *            actual value to check
	 * @param max
	 *            maximum value that the actual value can have
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	public static <T> void throwGreaterThan(Comparable<T> argument, T max,
			String argumentName) {
		if (argument.compareTo(max) > 0) {
			throw createGreaterThanException(argumentName, max);
		}
	}

	/**
	 * Throws an IllegalArgumentException, if the actual value is lower than a
	 * specified minimum value.
	 * 
	 * @param argument
	 *            actual value to check
	 * @param min
	 *            minimum value that the actual value can have
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	public static <T> void throwLowerThan(Comparable<T> argument, T min,
			String argumentName) {
		if (argument.compareTo(min) < 0) {
			throw createLowerThanException(argumentName, min);
		}
	}

	/**
	 * Throws an IllegalArgumentException, if the argument value is not equal to
	 * a specified value.
	 * 
	 * @param argument
	 *            value to check
	 * @param compareValue
	 *            value the argument has to be equal to
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	public static void throwNotEqual(Object argument, Object compareValue,
			String argumentName) {
		if (!argument.equals(compareValue)) {
			throw createNotEqualException(argumentName, compareValue.toString());
		}
	}

	/**
	 * Throws a NullPointerException, if the argument is null.
	 * 
	 * @param argument
	 *            value to check
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	public static void throwNull(Object argument, String argumentName) {
		if (argument == null) {
			throw createNullException(argumentName);
		}
	}

	/**
	 * Throws an IllegalArgumentException, if the argument doesn't lie between
	 * the min and max value.
	 * 
	 * @param argument
	 *            object the be checked
	 * @param minValue
	 *            minimum value of the object
	 * @param maxValue
	 *            maximum value of the object
	 * @param argumentName
	 *            name of the argument that gets checked (and printed out in the
	 *            exception message)
	 */
	public static <T> void throwNotBetween(Comparable<T> argument, T minValue,
			T maxValue, String argumentName) {
		if (argument.compareTo(minValue) < 0
				|| argument.compareTo(maxValue) > 0) {
			throw createNotBetweenException(argumentName, minValue, maxValue);
		}
	}

}

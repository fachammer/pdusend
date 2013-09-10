package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs if the value of an object was lower than a given value.
 * @author fabian
 *
 */
class ValueTooLowException extends ValidationException {

	ValueTooLowException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

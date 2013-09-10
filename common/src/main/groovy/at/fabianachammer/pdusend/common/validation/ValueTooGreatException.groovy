package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs if the value of an object was greater than a given value.
 * @author fabian
 *
 */
class ValueTooGreatException extends ValidationException {

	ValueTooGreatException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs when an object had a value when it should have been null.
 * @author fabian
 *
 */
class ValueNotNullException extends ValidationException {

	ValueNotNullException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

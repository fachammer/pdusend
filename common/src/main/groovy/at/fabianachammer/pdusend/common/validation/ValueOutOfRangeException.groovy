package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs if the value of an object is outside a given range.
 * @author fabian
 *
 */
class ValueOutOfRangeException extends ValidationException {

	ValueOutOfRangeException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

package at.fabianachammer.pdusend.util.validation

/**
 * @author fabian
 *
 */
class ValueTooLowException extends ValidationException {

	ValueTooLowException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

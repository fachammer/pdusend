package at.fabianachammer.pdusend.core.util.validation

/**
 * @author fabian
 *
 */
class ValueTooLowException extends ValidationException {

	ValueTooLowException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

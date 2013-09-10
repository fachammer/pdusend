package at.fabianachammer.pdusend.common.validation

/**
 * Base exception that occurs when the validation of an object fails.
 * @author fabian
 *
 */
class ValidationException extends Exception {

	final String valueName
	final value
	
	ValidationException(String message, String valueName, value){
		super(message, null)
		this.valueName = valueName
		this.value = value
	}
}

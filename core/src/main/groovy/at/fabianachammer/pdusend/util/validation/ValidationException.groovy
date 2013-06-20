package at.fabianachammer.pdusend.util.validation

/**
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

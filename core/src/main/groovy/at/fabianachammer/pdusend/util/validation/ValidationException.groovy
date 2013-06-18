package at.fabianachammer.pdusend.util.validation

/**
 * @author fabian
 *
 */
class ValidationException extends Exception {

	String valueName
	def value
	
	ValidationException(String message, String valueName, value){
		super(message)
		this.valueName = valueName
		this.value = value
	}
}

package at.fabianachammer.pdusend.util.validation

/**
 * @author fabian
 *
 */
class ValueNullException extends ValidationException {

	ValueNullException(String message, String valueName){
		super(message, valueName, null)
	}
}

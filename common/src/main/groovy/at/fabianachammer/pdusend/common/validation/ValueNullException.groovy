package at.fabianachammer.pdusend.common.validation

/**
 * @author fabian
 *
 */
class ValueNullException extends ValidationException {

	ValueNullException(String message, String valueName){
		super(message, valueName, null)
	}
}

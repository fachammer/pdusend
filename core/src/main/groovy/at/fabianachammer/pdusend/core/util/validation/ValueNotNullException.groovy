package at.fabianachammer.pdusend.core.util.validation

/**
 * @author fabian
 *
 */
class ValueNotNullException extends ValidationException {

	ValueNotNullException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

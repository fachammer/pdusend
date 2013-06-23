package at.fabianachammer.pdusend.util.validation

/**
 * @author fabian
 *
 */
class ValueHasIllegalClassException extends ValidationException {

	ValueHasIllegalClassException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

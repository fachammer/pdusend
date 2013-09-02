package at.fabianachammer.pdusend.common.validation

/**
 * @author fabian
 *
 */
class ValueOutOfRangeException extends ValidationException {

	ValueOutOfRangeException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

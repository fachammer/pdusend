package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs if an object has an illegal class.
 * @author fabian
 *
 */
class ValueHasIllegalClassException extends ValidationException {

	ValueHasIllegalClassException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

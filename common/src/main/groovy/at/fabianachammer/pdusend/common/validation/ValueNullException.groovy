package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs if an object was null when it should have had a value.
 * @author fabian
 *
 */
class ValueNullException extends ValidationException {

	ValueNullException(String message, String valueName){
		super(message, valueName, null)
	}
}

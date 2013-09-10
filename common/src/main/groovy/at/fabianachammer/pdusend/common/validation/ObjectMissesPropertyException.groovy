package at.fabianachammer.pdusend.common.validation

/**
 * {@link ValidationException} that occurs if an object doesn't provide a given property
 * @author fabian
 *
 */
class ObjectMissesPropertyException extends ValidationException {

	ObjectMissesPropertyException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

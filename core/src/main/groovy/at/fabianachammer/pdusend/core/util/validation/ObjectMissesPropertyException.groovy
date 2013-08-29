package at.fabianachammer.pdusend.core.util.validation

/**
 * @author fabian
 *
 */
class ObjectMissesPropertyException extends ValidationException {

	ObjectMissesPropertyException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

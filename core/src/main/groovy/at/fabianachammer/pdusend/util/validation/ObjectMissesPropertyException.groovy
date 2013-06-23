package at.fabianachammer.pdusend.util.validation

/**
 * @author fabian
 *
 */
class ObjectMissesPropertyException extends ValidationException {

	ObjectMissesPropertyException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

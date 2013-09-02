package at.fabianachammer.pdusend.common.validation

/**
 * @author fabian
 *
 */
class ObjectMissesPropertyException extends ValidationException {

	ObjectMissesPropertyException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

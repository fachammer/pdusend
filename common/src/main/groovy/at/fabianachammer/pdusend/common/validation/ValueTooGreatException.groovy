package at.fabianachammer.pdusend.common.validation

/**
 * @author fabian
 *
 */
class ValueTooGreatException extends ValidationException {

	ValueTooGreatException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

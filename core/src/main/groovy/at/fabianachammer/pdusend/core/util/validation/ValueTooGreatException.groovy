package at.fabianachammer.pdusend.core.util.validation

/**
 * @author fabian
 *
 */
class ValueTooGreatException extends ValidationException {

	ValueTooGreatException(String message, String valueName, value){
		super(message, valueName, value)
	}
}

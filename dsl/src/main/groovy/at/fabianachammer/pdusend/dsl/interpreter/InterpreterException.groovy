package at.fabianachammer.pdusend.dsl.interpreter

/**
 * Exception that occurs during the execution of a pdusend DSL script.
 * @author fabian
 *
 */
class InterpreterException extends Exception {

	InterpreterException(String message){
		super(message)
	}
}

package at.fabianachammer.pdusend.dsl.interpreter;

/**
 * Interface for observing actions on a pdusend dsl Interpreter.
 * 
 * @author fabian
 * 
 */
interface InterpreterObserver {

	/**
	 * Gets called when the interpretation of an script is finished.
	 */
	void onInterpretationFinished();

	/**
	 * Gets called when the interpretation throws an exception.
	 * 
	 * @param e InterpretationException that was thrown
	 */
	void onInterpreterException(InterpreterException e);
}

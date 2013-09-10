package at.fabianachammer.pdusend.dsl.interpreter

/**
 * Interface for classes that interpret the pdusend DSL.
 * Follows the Observer pattern and provides a hook for InterpreterObservers.
 * @author fabian
 *
 */
interface Interpreter {

	/**
	 * Interprets the given GroovyCodeSource as pdusend DSL code.
	 * @param sourceCode pdusend code to interpret
	 */
	void interpret(GroovyCodeSource sourceCode)

	void addObserver(InterpreterObserver observer)
	void removeObserver(InterpreterObserver observer)
}
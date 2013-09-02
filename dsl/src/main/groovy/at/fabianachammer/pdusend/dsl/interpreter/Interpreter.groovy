package at.fabianachammer.pdusend.dsl.interpreter

/**
 * @author fabian
 *
 */
interface Interpreter {
	
	void interpret(GroovyCodeSource sourceCode)
	void addObserver(InterpreterObserver observer)
	void removeObserver(InterpreterObserver observer)
}
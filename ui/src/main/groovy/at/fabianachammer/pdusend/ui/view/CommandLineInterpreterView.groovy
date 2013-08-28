package at.fabianachammer.pdusend.ui.view

import at.fabianachammer.pdusend.dsl.interpreter.InterpreterException;

/**
 * view that presents data from the interpreter on the command line.
 * @author fabian
 *
 */
class CommandLineInterpreterView implements InterpreterView {

	@Override
	public void onInterpretationFinished() {
		println "interpretation finished"
	}

	@Override
	public void onInterpreterException(InterpreterException e) {
		println "exception: " + e.message
	}
}

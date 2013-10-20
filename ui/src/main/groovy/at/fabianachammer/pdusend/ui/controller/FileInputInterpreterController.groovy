package at.fabianachammer.pdusend.ui.controller

import groovy.transform.InheritConstructors;

import org.gcontracts.annotations.Requires

import at.fabianachammer.pdusend.dsl.interpreter.Interpreter
import at.fabianachammer.pdusend.ui.view.InterpreterView

/**
 * controller class that handles input in form of a file path.
 * @author fabian
 * 
 */
@InheritConstructors
class FileInputInterpreterController extends
InterpreterController {

	/**
	 * minimum size of the argument input.
	 */
	private static final int MIN_SIZE = 2

	@Override
	@Requires({input != null && input.length >= MIN_SIZE})
	protected GroovyCodeSource processInput(final String[] input) {
		File scriptFile = new File(input[1])
		return new GroovyCodeSource(scriptFile)
	}
}

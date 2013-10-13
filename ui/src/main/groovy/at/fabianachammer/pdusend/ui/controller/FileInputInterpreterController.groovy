package at.fabianachammer.pdusend.ui.controller

import org.gcontracts.annotations.Requires

import at.fabianachammer.pdusend.dsl.interpreter.Interpreter
import at.fabianachammer.pdusend.ui.view.InterpreterView

/**
 * controller class that handles input in form of a file path.
 * @author fabian
 * 
 */
class FileInputInterpreterController extends
InterpreterController {

	/**
	 * minimum size of the argument input.
	 */
	private static final int MIN_SIZE = 2

	FileInputInterpreterController(Interpreter interpreter){
		super(interpreter)
	}

	FileInputInterpreterController(Interpreter interpreter, InterpreterView view){
		super(interpreter, view)
	}

	@Override
	@Requires({input != null && input.length >= MIN_SIZE})
	protected GroovyCodeSource processInput(final String[] input) {
		File scriptFile = new File(input[1])
		return new GroovyCodeSource(scriptFile)
	}
}

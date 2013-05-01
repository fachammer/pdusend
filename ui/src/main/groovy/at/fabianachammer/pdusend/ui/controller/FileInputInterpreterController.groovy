package at.fabianachammer.pdusend.ui.controller

import java.io.File

import at.fabianachammer.pdusend.ui.view.InterpreterView
import at.fabianachammer.pdusend.ui.controller.validation.Validator
import groovy.lang.GroovyCodeSource
import net.sf.oval.constraint.Size
import net.sf.oval.guard.Guarded
import net.sf.oval.constraint.NotNull

/**
 * controller class that handles input in form of a file path.
 * @author fabian
 * 
 */
@Guarded
class FileInputInterpreterController extends
InterpreterController {

	/**
	 * minimum size of the argument input.
	 */
	private static final int MIN_SIZE = 2

	FileInputInterpreterController(){
		super()
	}

	FileInputInterpreterController(InterpreterView view){
		super(view)
	}

	@Override
	protected GroovyCodeSource processInput(final String[] input) {
		Validator.validateNotNull(input, "input")
		Validator.validateGreaterThanEquals(MIN_SIZE, input.length, "input length")
		File scriptFile = new File(input[1])
		return new GroovyCodeSource(scriptFile)
	}
}

package at.fabianachammer.pdusend.ui.controller

import groovy.lang.GroovyCodeSource
import at.fabianachammer.pdusend.ui.controller.validation.Validator
import at.fabianachammer.pdusend.ui.view.InterpreterView;

/**
 * @author fabian
 *
 */
class TextInputInterpreterController extends InterpreterController {

	private static final String SCRIPT_CLASS_NAME = "SendText"

	private static final MIN_SIZE = 1
	
	TextInputInterpreterController(){
		super()
	}
	
	TextInputInterpreterController(InterpreterView view){
		super(view)
	}

	@Override
	protected GroovyCodeSource processInput(String... input) {
		Validator.validateNotNull(input, "input")
		Validator.validateGreaterThanEquals(MIN_SIZE, input.length, "input length")

		StringBuilder sb = new StringBuilder()
		input.each{
			sb.append("$it ")
		}

		sb.deleteCharAt(sb.size()-1)

		return new GroovyCodeSource("send "+sb.toString(), SCRIPT_CLASS_NAME, "")
	}
}

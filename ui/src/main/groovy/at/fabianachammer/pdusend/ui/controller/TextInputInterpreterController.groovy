package at.fabianachammer.pdusend.ui.controller

import groovy.transform.InheritConstructors;

import org.gcontracts.annotations.Requires

import at.fabianachammer.pdusend.dsl.interpreter.Interpreter
import at.fabianachammer.pdusend.ui.view.InterpreterView

/**
 * @author fabian
 *
 */
@InheritConstructors
class TextInputInterpreterController extends InterpreterController {

	private static final String SCRIPT_CLASS_NAME = "SendText"

	private static final MIN_SIZE = 1
	
	@Override
	@Requires({input != null && input.length >= MIN_SIZE})
	protected GroovyCodeSource processInput(String... input) {
		StringBuilder sb = new StringBuilder()
		input.each{
			sb.append("$it ")
		}

		sb.deleteCharAt(sb.size()-1)

		return new GroovyCodeSource("send "+sb.toString(), SCRIPT_CLASS_NAME, "")
	}
}

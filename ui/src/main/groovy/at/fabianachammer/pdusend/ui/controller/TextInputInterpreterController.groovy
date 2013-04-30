package at.fabianachammer.pdusend.ui.controller

import groovy.lang.GroovyCodeSource

/**
 * @author fabian
 *
 */
class TextInputInterpreterController extends InterpreterController {

	private static final String SCRIPT_CLASS_NAME = "SendText"

	@Override
	protected GroovyCodeSource processInput(String... input) {
		if(input == null) {
			throw new NullPointerException("input may not be null")
		}

		if(input.length < 1){
			throw new IllegalArgumentException("input must be greater than zero elements")
		}

		StringBuilder sb = new StringBuilder()
		input.each{
			sb.append("$it ")
		}

		sb.deleteCharAt(sb.size()-1)

		return new GroovyCodeSource("send "+sb.toString(), SCRIPT_CLASS_NAME, "")
	}
}

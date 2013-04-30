package at.fabianachammer.pdusend.ui.controller

import at.fabianachammer.pdusend.ui.view.CommandLineView

/**
 * Factory class that creates InterpreterControllers based on the command-line
 * input.
 * 
 * @author fabian
 * 
 */
final class InterpreterControllerFactory {

	/**
	 * private constructor because this is a utility class.
	 */
	private InterpreterControllerFactory() {
	}

	/**
	 * Creates a InterpreterController based on the command-line input.
	 * 
	 * @param input
	 *            command-line input of the application
	 * @return InterpreterController that can handle the command-line input, or nu
	 */
	static InterpreterController create(final String[] input) {
		if(input == null) {
			throw new NullPointerException("input may not be null")
		}

		if(input.length == 0){
			throw new IllegalArgumentException("input length must be greater than zero")
		}

		if(input[0].equalsIgnoreCase("-f")){
			return new FileInputInterpreterController()
		}

		return new TextInputInterpreterController()
	}
}

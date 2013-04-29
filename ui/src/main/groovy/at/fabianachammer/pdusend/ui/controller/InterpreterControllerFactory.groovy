package at.fabianachammer.pdusend.ui.controller

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
	 * @return InterpreterController that can handle the command-line input
	 */
	static InterpreterController create(final String[] input) {
		return new FileInputInterpreterController()
	}
}

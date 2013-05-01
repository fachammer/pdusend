package at.fabianachammer.pdusend.ui.controller

import at.fabianachammer.pdusend.ui.view.CommandLineHelpView
import at.fabianachammer.pdusend.ui.view.CommandLineInterpreterView

/**
 * Factory class that creates InterpreterControllers based on the command-line
 * input.
 * 
 * @author fabian
 * 
 */
final class ControllerFactory {

	/**
	 * private constructor because this is a utility class.
	 */
	private ControllerFactory() {
	}

	/**
	 * Creates a InterpreterController based on the command-line input.
	 * 
	 * @param input
	 *            command-line input of the application
	 * @return InterpreterController that can handle the command-line input, or nu
	 */
	static Controller create(final String[] input) {
		if(input == null || input.length == 0 || input[0].equalsIgnoreCase("-help")) {
			return new HelpController(new CommandLineHelpView())
		}

		if(input[0].equalsIgnoreCase("-f")){
			return new FileInputInterpreterController(new CommandLineInterpreterView())
		}

		return new TextInputInterpreterController(new CommandLineInterpreterView())
	}
}

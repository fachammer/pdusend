package at.fabianachammer.pdusend.ui.controller

import at.fabianachammer.pdusend.Pdusend;
import at.fabianachammer.pdusend.dsl.interpreter.Interpreter
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterImpl
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

	private static final Interpreter INTERPRETER_IMPL = new InterpreterImpl(Pdusend.createNetworkSenderFactory().createNetworkSender())

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
		// TODO: maybe command line parsing with apache commons
		if(needsHelpController(input)) {
			return new HelpController(new CommandLineHelpView())
		}

		if(input[0].equalsIgnoreCase("-f")){
			return new FileInputInterpreterController(INTERPRETER_IMPL, new CommandLineInterpreterView())
		}

		return new TextInputInterpreterController(INTERPRETER_IMPL, new CommandLineInterpreterView())
	}
	
	private static boolean needsHelpController(String[] input){
		input == null || input.length == 0 || input[0].equalsIgnoreCase("-help")
	}
}

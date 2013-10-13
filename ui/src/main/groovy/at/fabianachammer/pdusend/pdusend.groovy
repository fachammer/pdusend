package at.fabianachammer.pdusend

import at.fabianachammer.pdusend.dsl.interpreter.Interpreter
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterImpl
import at.fabianachammer.pdusend.ui.controller.Controller
import at.fabianachammer.pdusend.ui.controller.ControllerFactory


/**
 * Entry point for the pdusend command-line application.
 * @author fabian
 *
 */
class pdusend {

	/**
	 * starts the pdusend command-line application.
	 * @param args currently only file input is permitted (args[0] = "-f", args[1] = filepath)
	 */
	static main(args) {
		Interpreter interpreter = new InterpreterImpl(Pdusend.createNetworkSenderFactory().createNetworkSender())
		Controller controller = ControllerFactory.create(interpreter, args)
		controller.process(args)
	}
}

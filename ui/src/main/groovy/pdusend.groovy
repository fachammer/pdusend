import at.fabianachammer.pdusend.ui.controller.InterpreterController
import at.fabianachammer.pdusend.ui.controller.InterpreterControllerFactory;
import at.fabianachammer.pdusend.ui.view.CommandLineView


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
		InterpreterController controller = InterpreterControllerFactory.create(args)
		controller.addView(new CommandLineView())
		controller.process(args)
	}
}

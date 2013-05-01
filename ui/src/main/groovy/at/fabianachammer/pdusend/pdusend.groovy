package at.fabianachammer.pdusend

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
		Controller controller = ControllerFactory.create(args)
		controller.process(args)
	}
}

import at.fabianachammer.pdusend.ui.controller.InterpreterController
import at.fabianachammer.pdusend.ui.controller.InterpreterControllerFactory;
import at.fabianachammer.pdusend.ui.view.CommandLineView


/**
 * @author fabian
 *
 */
class pdusend {

	static main(args) {
		InterpreterController controller = InterpreterControllerFactory.create(args)
		controller.view = new CommandLineView()
		controller.process(args)
	}

}

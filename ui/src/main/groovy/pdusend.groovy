import at.fabianachammer.pdusend.ui.controller.InterpreterController
import at.fabianachammer.pdusend.ui.controller.InterpreterControllerFactory;


/**
 * @author fabian
 *
 */
class pdusend {

	static main(args) {
		InterpreterController controller = InterpreterControllerFactory.create(args)
		
		controller.process(args)
	}

}

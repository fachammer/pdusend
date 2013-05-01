package at.fabianachammer.pdusend.ui.controller

/**
 * Interface for controllers of the pdusend applications.
 * @author fabian
 *
 */
interface Controller {
	
	/**
	 * processes the input from the command-line
	 * @param input input from the command-line
	 */
	void process(String... input)
}

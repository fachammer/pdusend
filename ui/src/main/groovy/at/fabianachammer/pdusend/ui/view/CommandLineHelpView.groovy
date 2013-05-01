package at.fabianachammer.pdusend.ui.view

import at.fabianachammer.pdusend.ui.model.Help

/**
 * @author fabian
 *
 */
class CommandLineHelpView implements HelpView {

	@Override
	public void showHelp(Help help) {
		println help.text
	}

}

package at.fabianachammer.pdusend.ui.view

import at.fabianachammer.pdusend.ui.model.Help

/**
 * interface for views that present the help data of pdusend.
 * @author fabian
 *
 */
interface HelpView {
	
	/**
	 * shows the help data of pdusend
	 */
	void showHelp(Help help)
}

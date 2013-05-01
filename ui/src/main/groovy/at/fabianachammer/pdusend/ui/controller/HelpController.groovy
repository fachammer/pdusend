package at.fabianachammer.pdusend.ui.controller

import at.fabianachammer.pdusend.ui.model.Help
import at.fabianachammer.pdusend.ui.view.HelpView

/**
 * controller for the Help model.
 * @author fabian
 *
 */
class HelpController implements Controller {

	/**
	 * views that show the help data.
	 */
	ArrayList<HelpView> views = new ArrayList<HelpView>()

	/**
	 * creates a HelpController and adds the specified view
	 * @param view view to add to the HelpController
	 */
	HelpController(HelpView view){
		addView(view)
	}

	@Override
	void process(String... input) {
		Help help = new Help()
		views.each{ it.showHelp(help) }
	}

	/**
	 * adds a help view to the controller.
	 * @param view view to add
	 */
	void addView(HelpView view){
		views.add(view)
	}

	/**
	 * removes a help view from the controller.
	 * @param view view to remove
	 */
	void removeView(HelpView view){
		views.remove(view)
	}
}

package at.fabianachammer.pdusend.ui.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fabianachammer.pdusend.ui.view.CommandLineHelpView;
import at.fabianachammer.pdusend.ui.view.CommandLineInterpreterView;

/**
 * @author fabian
 *
 */
class ControllerFactoryTest {

	@Test
	void createWithFileOptionAsFirstArgumentReturnsInstanceOfFileInputInterpreterControllerWithCommandLineInterpreterView() {
		String[] input = ["-f"]

		InterpreterController actual = ControllerFactory.create(input)

		assertTrue(actual instanceof FileInputInterpreterController)
		assertTrue(actual.views[0] instanceof CommandLineInterpreterView)
	}

	@Test
	void createWithGreaterThanZeroLengthInputReturnsInstanceOfTextInputInterpreterControllerWithCommandLineInterpreterView(){
		String[] input = ["send"]

		InterpreterController actual = ControllerFactory.create(input)

		assertTrue(actual instanceof TextInputInterpreterController)
		assertTrue(actual.views[0] instanceof CommandLineInterpreterView)
	}

	@Test
	void createWithZeroLengthInputReturnsInstanceOfHelpController(){
		String[] input = []

		Controller actual = ControllerFactory.create(input)

		assertTrue(actual instanceof HelpController)
	}

	@Test
	void createWithHelpOptionAsFirstArgumentReturnsInstanceOfHelpController(){
		String[] input = ["-help"]

		Controller actual = ControllerFactory.create(input)

		assertTrue(actual instanceof HelpController)
	}

	@Test
	void createWithNullReturnsInstanceOfHelpController(){
		Controller actual = ControllerFactory.create(null)

		assertTrue(actual instanceof HelpController)
	}
}

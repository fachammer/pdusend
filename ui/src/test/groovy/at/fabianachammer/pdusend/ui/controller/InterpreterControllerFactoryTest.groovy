package at.fabianachammer.pdusend.ui.controller;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class InterpreterControllerFactoryTest {

	@Test
	void createWithFileOptionAsFirstArgumentReturnsInstanceOfFileInputInterpreterController() {
		String[] input = ["-f"]

		InterpreterController actual = InterpreterControllerFactory.create(input)

		assertTrue(actual instanceof FileInputInterpreterController)
	}

	@Test
	void createWithGreaterThanZeroLengthInputReturnsInstanceOfTextInputInterpreterController(){
		String[] input = ["send"]

		InterpreterController actual = InterpreterControllerFactory.create(input)

		assertTrue(actual instanceof TextInputInterpreterController)
	}

	@Test(expected=IllegalArgumentException.class)
	void createWithZeroLengthInputThrowsIllegalArgumentException(){
		String[] input = []

		InterpreterControllerFactory.create(input)
	}

	@Test(expected = NullPointerException.class)
	void createWithNullThrowsNullPointerException(){
		InterpreterControllerFactory.create(null)
	}
}

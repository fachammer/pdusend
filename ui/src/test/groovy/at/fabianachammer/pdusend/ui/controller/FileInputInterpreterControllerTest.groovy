package at.fabianachammer.pdusend.ui.controller

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.rules.ExpectedException;

import at.fabianachammer.pdusend.ui.view.InterpreterView
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterImpl;

/**
 * @author fabian
 *
 */
class FileInputInterpreterControllerTest {

	String validInputFilePath = "src/test/resources/sendPositiveInteger.pdusend"
	String invalidDirectoryPath = "/asdf"
	File validInput
	File invalidInput

	@Before
	void setUp(){
		validInput = new File(validInputFilePath)
		invalidInput = new File(invalidDirectoryPath)
	}

	@Test
	void processInputWithValidFileReturnsGroovyCodeSourceWithScriptTextEqualToTheInputFile() {
		InterpreterView viewDummy = mock(InterpreterView.class)
		FileInputInterpreterController controller = new FileInputInterpreterController(viewDummy)
		String[] input = ["-f", validInputFilePath]
		GroovyCodeSource expected = new GroovyCodeSource(validInput)

		GroovyCodeSource actual = controller.processInput(input)

		assertEquals(expected.scriptText, actual.scriptText)
	}

	@Test(expected = IOException.class)
	void processInputWithInvalidFileThrowsIOException(){
		InterpreterView viewDummy = mock(InterpreterView.class)
		FileInputInterpreterController controller = new FileInputInterpreterController(viewDummy)
		String[] input = ["-f", invalidDirectoryPath]

		controller.processInput(input)
	}

	@Test(expected = IllegalArgumentException.class)
	void processInputWithDirectoryThrowsIllegalArgumentException(){
		InterpreterView viewDummy = mock(InterpreterView.class)
		FileInputInterpreterController controller = new FileInputInterpreterController(viewDummy)
		String[] input = ["-f", "src/test/resources"]

		controller.processInput(input)
	}

	@Test(expected = NullPointerException.class)
	void processWithNullThrowsNullPointerException(){
		FileInputInterpreterController controller = new FileInputInterpreterController(mock(InterpreterView.class))

		controller.process(null)
	}

	@Test(expected = IllegalArgumentException.class)
	void processWithTooLessParameterInputThrowsIllegalArgumentException(){
		String[] tooLessParameterInput = ["-f"]
		InterpreterController controller = new FileInputInterpreterController(mock(InterpreterView.class))

		controller.process(tooLessParameterInput)
	}
}

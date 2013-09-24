package at.fabianachammer.pdusend.ui.controller

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.After
import org.junit.Before
import org.junit.Test

import at.fabianachammer.pdusend.ui.view.InterpreterView
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterImpl

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
}
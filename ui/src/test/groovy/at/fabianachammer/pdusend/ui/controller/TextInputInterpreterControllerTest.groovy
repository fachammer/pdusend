package at.fabianachammer.pdusend.ui.controller

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.Test
import at.fabianachammer.pdusend.dsl.interpreter.Interpreter
import at.fabianachammer.pdusend.ui.view.InterpreterView 

/**
 * @author fabian
 *
 */
class TextInputInterpreterControllerTest {

	@Test
	void processInputWithValidInputReturnsGroovyCodeSourceWithEqualScriptText(){
		InterpreterController controller = new TextInputInterpreterController(mock(Interpreter), mock(InterpreterView))
		String[] input = ["1", "on", "lo"]
		GroovyCodeSource expected = new GroovyCodeSource("send 1 on lo", "SendScript", "")
		
		GroovyCodeSource actual = controller.processInput(input)
		
		assertEquals(expected.scriptText, actual.scriptText)
	}

}

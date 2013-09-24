package at.fabianachammer.pdusend.ui.controller

import static org.junit.Assert.*

import org.junit.Test

/**
 * @author fabian
 *
 */
class TextInputInterpreterControllerTest {

	@Test
	void processInputWithValidInputReturnsGroovyCodeSourceWithEqualScriptText(){
		InterpreterController controller = new TextInputInterpreterController()
		String[] input = ["1", "on", "lo"]
		GroovyCodeSource expected = new GroovyCodeSource("send 1 on lo", "SendScript", "")
		
		GroovyCodeSource actual = controller.processInput(input)
		
		assertEquals(expected.scriptText, actual.scriptText)
	}

}

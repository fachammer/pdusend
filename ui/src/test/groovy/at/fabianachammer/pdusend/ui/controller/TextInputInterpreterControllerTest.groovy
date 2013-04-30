package at.fabianachammer.pdusend.ui.controller;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class TextInputInterpreterControllerTest {

	@Test(expected = NullPointerException.class)
	void processInputWithNullThrowsNullPointerException() {
		TextInputInterpreterController controller = new TextInputInterpreterController()
		
		controller.processInput(null)
	}
	
	@Test(expected = IllegalArgumentException.class)
	void processInputWithZeroInputLengthThrowsIllegalArgumentException(){
		TextInputInterpreterController controller = new TextInputInterpreterController()
		
		controller.processInput(new String[0])
	}
	
	@Test
	void processInputWithValidInputReturnsGroovyCodeSourceWithEqualScriptText(){
		TextInputInterpreterController controller = new TextInputInterpreterController()
		String[] input = ["1", "on", "lo"]
		GroovyCodeSource expected = new GroovyCodeSource("send 1 on lo", "SendScript", "")
		
		GroovyCodeSource actual = controller.processInput(input)
		
		assertEquals(expected.scriptText, actual.scriptText)
	}

}

package at.fabianachammer.pdusend.ui.controller;

import java.io.File
import java.io.IOException

import at.fabianachammer.pdusend.ui.view.InterpreterView
import groovy.lang.GroovyCodeSource
import net.sf.oval.constraint.Size
import net.sf.oval.guard.Guarded

/**
 * controller class that handles input in form of a file path.
 * @author fabian
 * 
 */
class FileInputInterpreterController extends
InterpreterController {

	FileInputInterpreterController(){
		super()
	}
	
	FileInputInterpreterController(InterpreterView view){
		super(view)
	}

	@Override
	protected GroovyCodeSource processInput(final String[] input) {
		if(input.length < 2){
			throw new IllegalArgumentException("input has to be at least two elements long")
		}
		File scriptFile = new File(input[1])
		return new GroovyCodeSource(scriptFile)
	}
}

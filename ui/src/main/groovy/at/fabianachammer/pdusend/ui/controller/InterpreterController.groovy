package at.fabianachammer.pdusend.ui.controller;

import groovy.lang.GroovyCodeSource
import at.fabianachammer.pdusend.dsl.Interpreter
import at.fabianachammer.pdusend.ui.view.InterpreterView
import groovy.transform.PackageScope

/**
 * abstract class for handling command-line arguments and returning a Groovy
 * code source for evaluating the input.
 * 
 * @author fabian
 * 
 */
public abstract class InterpreterController {

	/**
	 * interpreter that interprets the input scripts.
	 */
	@PackageScope Interpreter interpreter

	/**
	 * view that shows the interpreter data.
	 */
	InterpreterView view

	InterpreterController(){
		interpreter = new Interpreter()
	}

	InterpreterController(final InterpreterView view){
		this()
		setView(view)
	}

	/**
	 * processes the given input from the command-line.
	 * @param input command-line input to be processed
	 */
	void process(String[] input){
		GroovyCodeSource source = processInput(input)
		interpreter.interpret(source)
	}

	void setView(InterpreterView view){
		if(this.view!=null) {
			interpreter.removeObserver(this.view)
		}
		this.view = view
		interpreter.addObserver(view)
	}

	/**
	 * Processes the given input from the command-line and returns a Groovy code
	 * source from it.
	 * 
	 * @param input
	 *            command-line input from the user
	 * @return Groovy code source generated from the input
	 * @throws IOException
	 *             if the input couldn't be parsed from the
	 *             InterpreterController
	 */
	protected abstract GroovyCodeSource processInput(String[] input)
}

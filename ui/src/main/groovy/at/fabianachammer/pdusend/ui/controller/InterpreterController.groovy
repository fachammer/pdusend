package at.fabianachammer.pdusend.ui.controller;

import groovy.lang.GroovyCodeSource
import at.fabianachammer.pdusend.dsl.interpreter.Interpreter;
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterImpl;
import at.fabianachammer.pdusend.ui.view.InterpreterView
import groovy.transform.PackageScope
import net.sf.oval.Check
import net.sf.oval.ConstraintViolation
import net.sf.oval.guard.Guard

/**
 * abstract class for handling command-line arguments and returning a Groovy
 * code source for evaluating the input.
 * 
 * @author fabian
 * 
 */
public abstract class InterpreterController implements Controller{

	/**
	 * interpreter that interprets the input scripts.
	 */
	Interpreter interpreter

	/**
	 * view that shows the interpreter data.
	 */
	ArrayList<InterpreterView> views

	/**
	 * creates a new InterpreterController without any views.
	 */
	InterpreterController(){
		interpreter = new InterpreterImpl()
		views = new ArrayList<InterpreterView>()
	}

	/**
	 * creates a new InterpreterController with the specified view.
	 * @param view view to add to the controller
	 */
	InterpreterController(final InterpreterView view){
		this()
		addView(view)
	}

	@Override
	void process(String... input){
		GroovyCodeSource source = processInput(input)
		interpreter.interpret(source)
	}

	/**
	 * adds a new view to this controller.
	 * @param view view to add
	 */
	void addView(InterpreterView view){
		this.views.add(view)
		interpreter.addObserver(view)
	}

	/**
	 * removes a view from this controller.
	 * @param view view to remove
	 */
	void removeView(InterpreterView view){
		this.views.remove(view)
		interpreter.removeObserver(view)
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

package at.fabianachammer.pdusend.dsl.interpreter;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.control.CompilationUnit.PrimaryClassNodeOperation
import org.codehaus.groovy.control.customizers.ImportCustomizer

/**
 * This class interprets pdusend dsl scripts.
 * @author fabian
 *
 */
class InterpreterImpl implements Interpreter{

	GroovyShell shell
	List<InterpreterObserver> observers

	/**
	 * creates a new interpreter for interpreting pdusend dsl scripts.
	 */
	InterpreterImpl(){
		observers = new ArrayList<InterpreterObserver>()
		def config = new CompilerConfiguration()
		def importCustomizer = new ImportCustomizer()
		importCustomizer.addImport("at.fabianachammer.pdusend.core.NetworkSender")
		importCustomizer.addStarImport("at.fabianachammer.pdusend.core.type")

		config.addCompilationCustomizers(importCustomizer)
		
		shell = new GroovyShell(config)
	}

	/**
	 * interprets the given pdusend dsl script.
	 * @param script script to be interpreted
	 */
	void interpret(GroovyCodeSource script){
		try{
			shell.evaluate(script)
			notifyObservers()
		}
		catch(Exception e){
			notifyObservers(new InterpreterException(e.getMessage()))
		}
	}

	/**
	 * adds an observer to the current list of observers.
	 * @param observer observer to be added
	 */
	void addObserver(InterpreterObserver observer){
		observers.add(observer)
	}

	/**
	 * removes an observer from the current list of observers.
	 * @param observer observer to be removed
	 */
	void removeObserver(InterpreterObserver observer){
		observers.remove(observer)
	}

	/**
	 * notifies all observers that the interpretation of a pdusend dsl input script has finished.
	 */
	private void notifyObservers(){
		observers.each{ it.onInterpretationFinished() }
	}

	/**
	 * notifies all observers that an InterpreterException has occured.
	 * @param e InterpreterException that occured
	 */
	private void notifyObservers(InterpreterException e){
		observers.each{ it.onInterpreterException(e) }
	}
}





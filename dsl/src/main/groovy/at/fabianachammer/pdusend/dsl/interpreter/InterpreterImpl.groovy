package at.fabianachammer.pdusend.dsl.interpreter

import at.fabianachammer.pdusend.Pdusend
import at.fabianachammer.pdusend.dsl.DSL
import at.fabianachammer.pdusend.sender.NetworkSender

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

/**
 * This class interprets pdusend dsl scripts.
 * @author fabian
 *
 */
class InterpreterImpl implements Interpreter{

	private NetworkSender sender
	
	GroovyShell shell
	List<InterpreterObserver> observers

	/**
	 * creates a new interpreter for interpreting pdusend dsl scripts.
	 */
	InterpreterImpl(sender){
		this.sender = Pdusend.createNetworkSenderFactory().createNetworkSender()
		observers = new ArrayList<InterpreterObserver>()
		def config = new CompilerConfiguration()
		def importCustomizer = new ImportCustomizer()
		importCustomizer.addStarImport("at.fabianachammer.pdusend.type")
		config.addCompilationCustomizers(importCustomizer)
		
		shell = new GroovyShell(config)
	}

	/**
	 * interprets the given pdusend dsl script.
	 * @param script script to be interpreted
	 */
	void interpret(GroovyCodeSource script){
		try{
			def dslScript = shell.evaluate("{ it -> $script.scriptText}")
			
			DSL dsl = new DSL(sender)
			dsl.with(dslScript)
			
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

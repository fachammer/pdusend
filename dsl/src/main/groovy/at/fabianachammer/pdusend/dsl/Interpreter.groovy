package at.fabianachammer.pdusend.dsl;

import java.security.CodeSource
import org.aspectj.org.eclipse.jdt.core.dom.CompilationUnit
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
class Interpreter{

	Vocabulary vocabulary
	GroovyShell shell
	List<InterpreterObserver> observers

	/**
	 * creates a new interpreter for interpreting pdusend dsl scripts.
	 */
	Interpreter(){
		observers = new ArrayList<InterpreterObserver>()
		vocabulary = new Vocabulary()
		def binding = new Binding(vocabulary.binding())
		def config = new CompilerConfiguration()
		def importCustomizer = new ImportCustomizer()

		importCustomizer.addImport("at.fabianachammer.pdusend.Sender")
		importCustomizer.addStarImport("at.fabianachammer.pdusend.type")
		importCustomizer.addStaticStar("at.fabianachammer.pdusend.type.HardwareAddressType")
		importCustomizer.addStaticStar("at.fabianachammer.pdusend.type.EtherType")
		importCustomizer.addStaticStar("at.fabianachammer.pdusend.type.ArpOperation")

		config.addCompilationCustomizers(importCustomizer)

		shell = new GroovyShell(binding, config)
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





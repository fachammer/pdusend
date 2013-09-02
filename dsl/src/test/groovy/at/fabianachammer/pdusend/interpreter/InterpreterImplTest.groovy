package at.fabianachammer.pdusend.interpreter;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.dsl.interpreter.Interpreter;
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterException;
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterImpl;
import at.fabianachammer.pdusend.dsl.interpreter.InterpreterObserver;
import at.fabianachammer.pdusend.sender.NetworkSender
import at.fabianachammer.pdusend.type.DataUnit

import org.junit.Test;

class InterpreterImplTest {

	@Test
	public void test() {
		def sender = {NetworkInterface networkInterface, DataUnit dataUnit -> println networkInterface.getName()} as NetworkSender
		Interpreter interpreter = new InterpreterImpl(sender)
		def observer = new InterpreterObserver(){
			void onInterpretationFinished() { println 'finished'};
			void onInterpreterException(InterpreterException arg0) { println(arg0.message)};
		}
		interpreter.addObserver(observer)
		interpreter.interpret(new GroovyCodeSource('send new AtomicDataUnit(1) on NetworkInterface.getByName("lo")', 'pdusend', ''))
		
	}

}

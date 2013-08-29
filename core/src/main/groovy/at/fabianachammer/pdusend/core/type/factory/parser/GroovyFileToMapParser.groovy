package at.fabianachammer.pdusend.core.type.factory.parser

import at.fabianachammer.pdusend.core.util.validation.Validator
import java.io.File
import java.util.Map;

/**
 * @author fabian
 *
 */
class GroovyFileToMapParser implements FileToMapParser {

	private GroovyShell shell
	
	GroovyFileToMapParser(){
		shell = new GroovyShell()
	}
	
	@Override
	Map parse(File fileToParse) {
		def codeResult = evaluateGroovySource(fileToParse)
		validateGroovyResult(codeResult)
		
		return codeResult
	}
	
	private validateGroovyResult(codeResult){
		Validator v = new Validator(codeResult, "code result")
		v.validateInstanceOf(Map.class)
	}
	
	private def evaluateGroovySource(File groovySource){
		shell.evaluate(groovySource)
	}
}

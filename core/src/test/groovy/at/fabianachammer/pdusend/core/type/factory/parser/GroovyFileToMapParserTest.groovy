package at.fabianachammer.pdusend.core.type.factory.parser;

import static org.junit.Assert.*

import org.codehaus.groovy.control.CompilationFailedException;
import org.junit.Test
import org.junit.Before
import org.junit.After

import at.fabianachammer.pdusend.core.type.factory.parser.FileToMapParser;
import at.fabianachammer.pdusend.core.type.factory.parser.GroovyFileToMapParser;
import at.fabianachammer.pdusend.core.util.validation.ValueHasIllegalClassException

/**
 * @author fabian
 *
 */
class GroovyFileToMapParserTest {

	private File temp

	@Before
	void setUp(){
		temp = new File("temp.groovy")
	}

	@After
	void tearDown(){
		temp.delete()
	}

	@Test
	void parseWithValidGroovyFileWhichContainsMapWithSizeInBitsAttributeReturnsMapWithSizeInBitsAttribute() {
		String groovySource =
				"""
		[
			sizeInBits: 1
		]
		"""
		temp << groovySource
		def expected = [sizeInBits: 1]
		FileToMapParser parser = new GroovyFileToMapParser()

		def actual = parser.parse(temp)

		assertEquals(expected, actual)
	}

	@Test
	void parseWithValidGroovyFileWhichContainsMapWithSizeInBitsAttributeAndPredefinedValuesReturnsMapWithSizeInBitsAttributeAndPredefinedValues(){
		FileToMapParser parser = new GroovyFileToMapParser()
		String groovySource = 
		"""
		[
			sizeInBits: 1,
			values: [
				zero: 0, 
				one: 1
			]
		]
		"""
		temp << groovySource
		def expected = [sizeInBits: 1, values: [zero: 0, one: 1]]

		def actual = parser.parse(temp)

		assertEquals(expected, actual)
	}
	
	@Test(expected = ValueHasIllegalClassException.class)
	void parseWithInvalidGroovyFileWhichIsNotAMapThrowsIllegalArgumentException(){
		FileToMapParser parser = new GroovyFileToMapParser()
		temp << """ return "not a map" """
		parser.parse(temp)
	}
	
	@Test(expected = CompilationFailedException.class)
	void parseWithNotCompilableGroovyFileThrowsCompilationFailedException(){
		String notCompilableGroovySource = 
		"""
		[ sizeInBits: 1
		"""
		temp << notCompilableGroovySource
		FileToMapParser parser = new GroovyFileToMapParser()
		
		parser.parse(temp)
	}
}

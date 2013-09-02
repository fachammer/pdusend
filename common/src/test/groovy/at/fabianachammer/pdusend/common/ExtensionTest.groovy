package at.fabianachammer.pdusend.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

class ExtensionTest {
	
	@Before
	void setUp(){
		Extension.extend()
	}

	@Test
	void extendMakesHasBitSetOnIndexMethodOnNumber() {
		((Number) 1).hasBitSetOnIndex(1)
	}
	
	@Test
	void extendMakesSetBitOnIndexMethodOnNumber() {
		((Number) 1).orBitOnIndex(1)
	}
	
	@Test
	void extendMakesBitCountMethodOnNumber() {
		((Number) 1).bitCount()
	}
	
	@Test
	void extendMakesToByteArrayMethodOnNumber() {
		((Number) 1).toByteArray(1)
	}

}

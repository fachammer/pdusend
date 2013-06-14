package at.fabianachammer.pdusent.type.factory;

import static org.junit.Assert.*;

import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.factory.AtomicDataUnitFactory
import at.fabianachammer.pdusend.type.factory.DataUnitFactory
import org.junit.Test;

/**
 * @author fabian
 *
 */
class AtomicDataUnitFactoryTest {

	@Test
	void makeWithBitSizeReturnsInstanceOfAtomicDataUnitFactory(){
		int bitSize = 1
		
		DataUnitFactory dataUnitFactory = AtomicDataUnitFactory.make(bitSize)
		
		assertTrue(dataUnitFactory instanceof AtomicDataUnitFactory)
	}
	
	@Test
	void createDataUnitReturnsInstanceOfAtomicDataUnit(){
		int bitSize = 1
		DataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.make(bitSize)
		
		DataUnit dataUnit = atomicDataUnitFactory.createDataUnit()
		
		assertTrue(dataUnit instanceof AtomicDataUnit)
	}
	
	@Test
	void makeWithBitSizeReturnsAtomicDataUnitFactoryThatCreatesDataUnitsWithSizeOfGivenBitSize(){
		int bitSize = 1
		DataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.make(bitSize)
		DataUnit atomicDataUnit = atomicDataUnitFactory.createDataUnit()
		int expectedSize = bitSize
		
		int actualSize = atomicDataUnit.sizeInBits()
	}
}

package at.fabianachammer.pdusend.type;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.type.AtomicDataUnitGenerator;
import at.fabianachammer.pdusend.type.CompositeDataUnit;
import at.fabianachammer.pdusend.type.CompositeDataUnitGenerator;
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator;
import at.fabianachammer.pdusend.common.validation.ValueNullException;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class CompositeDataUnitFactoryTest {

	@Test
	void makeFromDataUnitFactoriesReturnsInstanceOfCompositeDataUnitFactory() {
		DataUnitGenerator[] childDataUnitFactories = [AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)]
		
		DataUnitGenerator dataUnitFacotry = CompositeDataUnitGenerator.makeFromDataUnitFactories(childDataUnitFactories)
		
		assertTrue(dataUnitFacotry instanceof CompositeDataUnitGenerator)
	}
	
	@Test(expected = ValueNullException.class)
	void makeFromDataUnitFactoriesWithNullThrowsValueNullException(){
		CompositeDataUnitGenerator.makeFromDataUnitFactories(null)
	}

	@Test
	void createDataUnitReturnsInstanceOfCompositeDataUnit(){
		DataUnitGenerator[] childDataUnitFactories = [AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)]
		DataUnitGenerator compositeDataUnitFactory = CompositeDataUnitGenerator.makeFromDataUnitFactories(childDataUnitFactories)
		
		DataUnit dataUnit = compositeDataUnitFactory.generateDataUnit()
		
		assertTrue(dataUnit instanceof CompositeDataUnit)
	}
	
	@Test
	void createDataUnitWithAtomicDataUnitFactoryAsChildDataUnitFactoryReturnsCompositeDataUnitWithAtomicDataUnitAsChild(){
		DataUnitGenerator atomicDataUnitFactory = AtomicDataUnitGenerator.makeFromDataUnitSizeInBits(1)
		DataUnitGenerator compositeDataUnitFactory = CompositeDataUnitGenerator.makeFromDataUnitFactories(atomicDataUnitFactory)
		
		DataUnit dataUnit = compositeDataUnitFactory.generateDataUnit()
		
		assertEquals(1, dataUnit.sizeInBits())
		assertArrayEquals([0] as byte[], dataUnit.encode())
	}
}

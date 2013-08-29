package at.fabianachammer.pdusend.core.type.factory;

import static org.junit.Assert.*;
import at.fabianachammer.pdusend.core.type.factory.AtomicDataUnitFactory;
import at.fabianachammer.pdusend.core.type.factory.CompositeDataUnitFactory;
import at.fabianachammer.pdusend.core.type.factory.DataUnitFactory;
import at.fabianachammer.pdusend.core.type.CompositeDataUnit;
import at.fabianachammer.pdusend.core.type.DataUnit
import at.fabianachammer.pdusend.core.util.validation.ValueNullException;

import org.junit.Test;

/**
 * @author fabian
 *
 */
class CompositeDataUnitFactoryTest {

	@Test
	void makeFromDataUnitFactoriesReturnsInstanceOfCompositeDataUnitFactory() {
		DataUnitFactory[] childDataUnitFactories = [AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)]
		
		DataUnitFactory dataUnitFacotry = CompositeDataUnitFactory.makeFromDataUnitFactories(childDataUnitFactories)
		
		assertTrue(dataUnitFacotry instanceof CompositeDataUnitFactory)
	}
	
	@Test(expected = ValueNullException.class)
	void makeFromDataUnitFactoriesWithNullThrowsValueNullException(){
		CompositeDataUnitFactory.makeFromDataUnitFactories(null)
	}

	@Test
	void createDataUnitReturnsInstanceOfCompositeDataUnit(){
		DataUnitFactory[] childDataUnitFactories = [AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)]
		DataUnitFactory compositeDataUnitFactory = CompositeDataUnitFactory.makeFromDataUnitFactories(childDataUnitFactories)
		
		DataUnit dataUnit = compositeDataUnitFactory.createDataUnit()
		
		assertTrue(dataUnit instanceof CompositeDataUnit)
	}
	
	@Test
	void createDataUnitWithAtomicDataUnitFactoryAsChildDataUnitFactoryReturnsCompositeDataUnitWithAtomicDataUnitAsChild(){
		DataUnitFactory atomicDataUnitFactory = AtomicDataUnitFactory.makeFromDataUnitSizeInBits(1)
		DataUnitFactory compositeDataUnitFactory = CompositeDataUnitFactory.makeFromDataUnitFactories(atomicDataUnitFactory)
		
		DataUnit dataUnit = compositeDataUnitFactory.createDataUnit()
		
		assertEquals(1, dataUnit.sizeInBits())
		assertArrayEquals([0] as byte[], dataUnit.encode())
	}
}

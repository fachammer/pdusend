package at.fabianachammer.pdusend.type

import static org.junit.Assert.*
import at.fabianachammer.pdusend.type.AtomicDataUnitGenerator
import at.fabianachammer.pdusend.type.CompositeDataUnit
import at.fabianachammer.pdusend.type.CompositeDataUnitGenerator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator

import org.gcontracts.PreconditionViolation
import org.junit.Test

/**
 * @author fabian
 *
 */
class CompositeDataUnitGeneratorTest {

	@Test
	void generateDataUnitWithTwoAtomicDataUnitChildsReturnsCompositeDataUnitWithConcatenatedBitsOfChildren(){
		DataUnitGenerator[] childDataUnitGenerators = [new AtomicDataUnitGenerator(1), new AtomicDataUnitGenerator(2)]
		DataUnitGenerator compositeDataUnitGenerator = new CompositeDataUnitGenerator([childDataUnit1: childDataUnitGenerators[0], childDataUnit2: childDataUnitGenerators[1]])
		
		DataUnit dataUnit = compositeDataUnitGenerator.generateDataUnit([childDataUnit1: 0b1, childDataUnit2: 0b11])
		
		assertEquals(3, dataUnit.sizeInBits())
		assertArrayEquals([0b111] as byte[], dataUnit.encode())
	}
	
	@Test
	void generateDataUnitWithOneAtomicDataUnitGeneratorReturnsBitsOfThatAtomicDataUnit(){
		DataUnitGenerator atomicDataUnitGenerator = new AtomicDataUnitGenerator(1)
		DataUnitGenerator compositeDataUnitGenerator = new CompositeDataUnitGenerator([atomicDataUnit: atomicDataUnitGenerator])
		
		DataUnit dataUnit = compositeDataUnitGenerator.generateDataUnit([atomicDataUnit: 0b1])
		
		assertEquals(1, dataUnit.sizeInBits())
		assertArrayEquals([0b1] as byte[], dataUnit.encode())
	}
	
	@Test
	void generateDataUnitWithOneAtomicDataUnitReturnsDataUnitWithAttributeOfGivenAtomicDataUnitInGeneratorMap(){
		DataUnitGenerator atomicDataUnitGenerator = new AtomicDataUnitGenerator(1)
		DataUnitGenerator compositeDataUnitGenerator = new CompositeDataUnitGenerator([atomicDataUnit: atomicDataUnitGenerator])
	
		DataUnit dataUnit = compositeDataUnitGenerator.generateDataUnit([atomicDataUnit: 2])
		
		assertEquals(new AtomicDataUnit(1, [2] as byte[]), dataUnit.atomicDataUnit)
	}
	
	@Test
	void generateDataUnitWithTwoAtomicDataUnitsReturnsDataUnitWithTwoAttributesForTheTwoAtomicDataUnits(){
		DataUnitGenerator[] childDataUnitGenerators = [new AtomicDataUnitGenerator(1), new AtomicDataUnitGenerator(2)]
		DataUnitGenerator compositeDataUnitGenerator = new CompositeDataUnitGenerator([childDataUnit1: childDataUnitGenerators[0], childDataUnit2: childDataUnitGenerators[1]])
		
		DataUnit dataUnit = compositeDataUnitGenerator.generateDataUnit([childDataUnit1: 0, childDataUnit2: 3])
		
		assertEquals(new AtomicDataUnit(1, [0] as byte[]), dataUnit.childDataUnit1)
		assertEquals(new AtomicDataUnit(2, [3] as byte[]), dataUnit.childDataUnit2)
	}
}

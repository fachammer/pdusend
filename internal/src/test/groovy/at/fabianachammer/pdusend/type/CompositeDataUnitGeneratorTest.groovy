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
		DataUnitGenerator[] childDataUnitGenerators = [new AtomicDataUnitGenerator('someId1', 1), new AtomicDataUnitGenerator('someId2', 2)]
		DataUnitGenerator compositeDataUnitGenerator = new CompositeDataUnitGenerator('someOtherId', [childDataUnit1: childDataUnitGenerators[0], childDataUnit2: childDataUnitGenerators[1]])
		
		DataUnit dataUnit = compositeDataUnitGenerator.generateDataUnit([childDataUnit1: 0b1, childDataUnit2: 0b11])
		
		assertEquals(3, dataUnit.sizeInBits())
		assertArrayEquals([0b111] as byte[], dataUnit.encode())
	}
	
	@Test
	void generateDataUnitWithOneAtomicDataUnitGeneratorReturnsBitsOfThatAtomicDataUnit(){
		DataUnitGenerator atomicDataUnitGenerator = new AtomicDataUnitGenerator('someId', 1)
		DataUnitGenerator compositeDataUnitGenerator = new CompositeDataUnitGenerator('someOtherId', [atomicDataUnit: atomicDataUnitGenerator])
		
		DataUnit dataUnit = compositeDataUnitGenerator.generateDataUnit([atomicDataUnit: 0b1])
		
		assertEquals(1, dataUnit.sizeInBits())
		assertArrayEquals([0b1] as byte[], dataUnit.encode())
	}
}

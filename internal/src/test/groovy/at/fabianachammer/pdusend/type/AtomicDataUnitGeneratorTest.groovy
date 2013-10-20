package at.fabianachammer.pdusend.type

import static org.junit.Assert.*
import at.fabianachammer.pdusend.type.AtomicDataUnit
import at.fabianachammer.pdusend.type.AtomicDataUnitGenerator
import at.fabianachammer.pdusend.type.DataUnit
import at.fabianachammer.pdusend.type.DataUnitGenerator
import at.fabianachammer.pdusend.common.validation.*

import org.gcontracts.PreconditionViolation
import org.junit.Test

class AtomicDataUnitGeneratorTest {
	
	@Test(expected = PreconditionViolation)
	void createWithDataUnitSizeInBitsLowerThanOneThrowsPreconditionViolation(){
		new AtomicDataUnitGenerator(0)
	}

	@Test
	void generateDataUnitWithBigIntegerReturnsInstanceOfAtomicDataUnitWithDataAndSizeInBitsSetRight(){
		int dataUnitSizeInBits = 1
		DataUnitGenerator atomicDataUnitGenerator = new AtomicDataUnitGenerator(dataUnitSizeInBits)

		DataUnit dataUnit = atomicDataUnitGenerator.generateDataUnit((BigInteger) 1)

		assertTrue(dataUnit instanceof AtomicDataUnit)
		assertArrayEquals([1] as byte[], dataUnit.encode())
		assertEquals(1, dataUnit.sizeInBits())
	}
	
	@Test
	void generateDataUnitWithByteArrayReturnsInstanceOfAtomicDataUnitWithDataAndSizeInBitsSetRight(){
		int dataUnitSizeInBits = 1
		DataUnitGenerator atomicDataUnitGenerator = new AtomicDataUnitGenerator(dataUnitSizeInBits)

		DataUnit dataUnit = atomicDataUnitGenerator.generateDataUnit([1] as byte[])

		assertTrue(dataUnit instanceof AtomicDataUnit)
		assertArrayEquals([1] as byte[], dataUnit.encode())
		assertEquals(1, dataUnit.sizeInBits())
	}
	
	@Test(expected = PreconditionViolation)
	void generateDataUnitWithNonNumberAndNonByteArrayValueThrowsPreconditionViolation(){
		DataUnitGenerator atomicDataUnitGenerator = new AtomicDataUnitGenerator(1)
		atomicDataUnitGenerator.generateDataUnit(new Object())
	}
}

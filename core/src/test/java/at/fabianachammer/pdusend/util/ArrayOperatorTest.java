package at.fabianachammer.pdusend.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author fabian
 * 
 */
public class ArrayOperatorTest {

	@Test
	public final void testArrayEqualsWithDifferentArrayElementsOnArrayOperatorReturnsFalse() {
		Byte[] comparableArray = { 0, 1 };
		Byte[] differentArray = { 0, 2 };

		assertFalse(ArrayOperator.arrayEquals(comparableArray,
				differentArray));
	}

	@Test
	public final void testArrayEqualsWithEqualArrayElementsOnArrayOperatorReturnsTrue() {
		Byte[] comparableArray = { 0, 1 };
		Byte[] sameArray = { 0, 1 };

		assertTrue(ArrayOperator.arrayEquals(comparableArray,
				sameArray));
	}

	@Test
	public final void testArrayEqualsWithDifferentSizeArraysOnArrayOperatorReturnsFalse() {
		Byte[] comparableArray = { 0, 1 };
		Byte[] differentSizeArray = { 0, 1, 0 };

		assertFalse(ArrayOperator.arrayEquals(comparableArray,
				differentSizeArray));
	}

	@Test
	public final void testArrayEqualsWithEqualByteArrayElementsOnArrayOperatorReturnsTrue() {
		byte[] comparableArray = { 0, 1 };
		byte[] sameArray = { 0, 1 };

		assertTrue(ArrayOperator.arrayEquals(comparableArray,
				sameArray));
	}

	@Test
	public final void testArrayEqualsWithDifferentByteArrayElementsOnArrayOperatorReturnsFalse() {
		byte[] comparableArray = { 0, 1 };
		byte[] differentArray = { 0, 2 };

		assertFalse(ArrayOperator.arrayEquals(comparableArray,
				differentArray));
	}
}
/**
 * 
 */
package at.fabianachammer.nsend.util;

import static at.fabianachammer.nsend.util.Validation.throwGreaterThan;
import static at.fabianachammer.nsend.util.Validation.throwLowerThan;
import static at.fabianachammer.nsend.util.Validation.throwNotBetween;
import static at.fabianachammer.nsend.util.Validation.throwNotEqual;
import static at.fabianachammer.nsend.util.Validation.throwNull;

import org.junit.Test;

/**
 * Tests the {@link Validation} class.
 * 
 * @author fabian
 * 
 */
public class ValidationTest {

	@Test(expected = IllegalArgumentException.class)
	public final void testThrowGreaterThanWhenArgumentIsGreaterThanMaxThrowsIllegalArgumentException() {
		final Integer maxValue = 0;
		final Comparable<Integer> greaterThanMaxValue = 1;

		throwGreaterThan(greaterThanMaxValue, maxValue, "anyArgumentName");
	}

	@Test
	public final void testThrowGreaterThanWhenArgumentIsLowerThanMaxPasses() {
		final Integer maxValue = 0;
		final Comparable<Integer> lowerThanMaxValue = -1;

		throwGreaterThan(lowerThanMaxValue, maxValue, "anyArgumentName");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testThrowLowerThanWhenArgumentIsGreaterThanMinThrowsIllegalArgumentException() {
		final Integer minValue = 1;
		final Comparable<Integer> lowerThanMinValue = 0;

		throwLowerThan(lowerThanMinValue, minValue, "anyArgumentName");
	}

	@Test
	public final void testThrowLowerThanWhenArgumentIsGreaterThanMinPasses() {
		final Integer minValue = 0;
		final Integer greaterThanMinValue = 1;

		throwLowerThan(greaterThanMinValue, minValue, "anyArgumentName");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testThrowNotEqualWhenArgumentIsNotEqualToCompareValueThrowsIllegalArgumentException() {
		final Object anyCompareValue = 0;
		final Object nonEqualToCompareValue = 1;

		throwNotEqual(
				nonEqualToCompareValue, anyCompareValue, "anyArgumentName");
	}

	@Test
	public final void testThrowNotEqualWhenArgumentIsEqualToCompareValuePasses() {
		final Object anyCompareValue = 0;
		final Object equalToCompareValue = 0;

		throwNotEqual(equalToCompareValue, anyCompareValue, "anyArgumentName");
	}

	@Test(expected = NullPointerException.class)
	public final void testThrowNullWhenArgumentIsNullThrowsNullPointerException() {
		final Object nullObject = null;

		throwNull(nullObject, "anyArgumentName");
	}

	@Test
	public final void testThrowNullWhenArgumentIsNotNullPasses() {
		final Object notNullObject = 0;

		throwNull(notNullObject, "anyArgumentName");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testThrowNotBetweenWhenValueIsGreaterThanSpecifiedRangeThrowsIllegalArgumentException() {
		final Integer minValue = 0;
		final Integer maxValue = 1;
		final Comparable<Integer> greaterThanRangeValue = 2;

		throwNotBetween(
				greaterThanRangeValue, minValue, maxValue, "anyArgumentName");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testThrowNotBetweenWhenValueIsLowerThanSpecifiedRangeThrowsIllegalArgumentException() {
		final Integer minValue = 1;
		final Integer maxValue = 2;
		final Comparable<Integer> lowerThanRangeValue = 0;

		throwNotBetween(
				lowerThanRangeValue, minValue, maxValue, "anyArgumentName");

	}

	@Test
	public final void testThrowNotBetweenWhenValueIsBetweenSpecifiedRangePasses() {
		final Integer minValue = 0;
		final Integer maxValue = 2;
		final Comparable<Integer> inRangeValue = 1;

		throwNotBetween(inRangeValue, minValue, maxValue, "anyArgumentName");
	}
}

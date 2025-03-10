package jfree.data;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class RangeTest {
    private Range exampleRange;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * Setup method executed before each test.
     * Initializes a sample range (-1, 1) for reuse in tests.
     */
    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
    }

    // -------------- Central Value Tests --------------

    /**
     * Test case: Get central value of the range.
     * Test strategy: The Javadoc states "Returns the central (or median) value for the range."
     * This test verifies the central value calculation for a simple range.
     */
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, exampleRange.getCentralValue(), .000000001d);
    }

    /**
     * Test case: Central value for a positive range.
     * Test strategy: The method should return the midpoint of the range.
     * Expected output: (2+6)/2 = 4.0
     */
    @Test
    public void testCentralValuePositiveRange() {
        Range range = new Range(2, 6);
        assertEquals(4.0, range.getCentralValue(), 0.0001);
    }

    /**
     * Test case: Central value for a negative range.
     * Test strategy: The method should return the midpoint of the range.
     * Expected output: (-6 + -2)/2 = -4.0
     */
    @Test
    public void testCentralValueNegativeRange() {
        Range range = new Range(-6, -2);
        assertEquals(-4.0, range.getCentralValue(), 0.0001);
    }

    /**
     * Test case: Central value when the lower and upper bounds are the same.
     * Test strategy: The method should return the same value.
     * Expected output: 5.0
     */
    @Test
    public void testCentralValueSameBounds() {
        Range range = new Range(5, 5);
        assertEquals(5.0, range.getCentralValue(), 0.0001);
    }

    /**
     * Test case: Central value for an extreme range.
     * Test strategy: This tests large value handling.
     * Expected output: ( -MAX_VALUE + MAX_VALUE ) / 2 = 0.0
     */
    @Test
    public void testCentralValueExtremeRange() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(0.0, range.getCentralValue(), 0.0001);
    }

    // -------------- Lower Bound Tests --------------

    /**
     * Test case: Get lower bound of the range.
     * Test strategy: The Javadoc states "Returns the lower bound for the range."
     * This test verifies the lower bound for a known range.
     */
    @Test
    public void lowerBoundShouldBeNegativeOne() {
        assertEquals("Lower bound should be -1", -1, exampleRange.getLowerBound(), .000000001d);
    }

    /**
     * Test case: Lower bound for a positive range.
     * Test strategy: The method should return the minimum value in the range.
     * Expected output: 2.0
     */
    @Test
    public void testLowerBoundPositiveRange() {
        Range range = new Range(2, 6);
        assertEquals(2.0, range.getLowerBound(), 0.0001);
    }

    /**
     * Test case: Lower bound for a negative range.
     * Test strategy: The method should return the minimum value in the range.
     * Expected output: -6.0
     */
    @Test
    public void testLowerBoundNegativeRange() {
        Range range = new Range(-6, -2);
        assertEquals(-6.0, range.getLowerBound(), 0.0001);
    }

    /**
     * Test case: Lower bound for an extreme range.
     * Test strategy: This tests handling of large double values.
     * Expected output: -Double.MAX_VALUE
     */
    @Test
    public void testLowerBoundExtremeRange() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(-Double.MAX_VALUE, range.getLowerBound(), 0.0001);
    }

    // -------------- Upper Bound Tests --------------

    /**
     * Test case: Get upper bound of the range.
     * Test strategy: The Javadoc states "Returns the upper bound for the range."
     * This test verifies the upper bound for a known range.
     */
    @Test
    public void upperBoundShouldBeOne() {
        assertEquals("Upper bound should be 1", 1, exampleRange.getUpperBound(), .000000001d);
    }

    /**
     * Test case: Upper bound for a positive range.
     * Test strategy: The method should return the maximum value in the range.
     * Expected output: 6.0
     */
    @Test
    public void testUpperBoundPositiveRange() {
        Range range = new Range(2, 6);
        assertEquals(6.0, range.getUpperBound(), 0.0001);
    }

    /**
     * Test case: Upper bound for a negative range.
     * Test strategy: The method should return the maximum value in the range.
     * Expected output: -2.0
     */
    @Test
    public void testUpperBoundNegativeRange() {
        Range range = new Range(-6, -2);
        assertEquals(-2.0, range.getUpperBound(), 0.0001);
    }

    /**
     * Test case: Upper bound for an extreme range.
     * Test strategy: This tests handling of large double values.
     * Expected output: Double.MAX_VALUE
     */
    @Test
    public void testUpperBoundExtremeRange() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, range.getUpperBound(), 0.0001);
    }

    /**
     * Test case: Upper bound when the range includes positive infinity.
     * Test strategy: The method should return Double.POSITIVE_INFINITY.
     * Expected output: Double.POSITIVE_INFINITY
     */
    @Test
    public void testUpperBoundPositiveInfinity() {
        Range range = new Range(-1000, Double.POSITIVE_INFINITY);
        assertEquals(Double.POSITIVE_INFINITY, range.getUpperBound(), 0.0001);
    }

    // -------------- Contains Method Tests --------------

    /**
     * Test case: Check if the range contains zero.
     * Test strategy: The Javadoc states "Returns true if the specified value is within the range."
     * Since zero is within the default range (-1,1), the result should be true.
     */
    @Test
    public void testRangeContainsZero() {
        assertTrue(exampleRange.contains(0));
    }

    /**
     * Test case: Check if the range contains a value within its bounds.
     * Test strategy: The method should return true if the value is within the given range.
     * Expected output: true (3 is within -5 to 5).
     */
    @Test
    public void testRangeContainsValueWithin() {
        assertTrue(new Range(-5, 5).contains(3));
    }

    /**
     * Test case: Check if the range does not contain a value outside its bounds.
     * Test strategy: The method should return false if the value is outside the range.
     * Expected output: false (-1 is not within 1 to 10).
     */
    @Test
    public void testRangeDoesNotContainValue() {
        assertFalse(new Range(1, 10).contains(-1));
    }

    /**
     * Test case: Check boundary value at the lower bound.
     * Test strategy: The method should return true if the value is equal to the lower bound.
     * Expected output: true (-1 is the lower bound of exampleRange).
     */
    @Test
    public void testRangeContainsLowerBound() {
        assertTrue(exampleRange.contains(-1));
    }

    /**
     * Test case: Check boundary value at the upper bound.
     * Test strategy: The method should return true if the value is equal to the upper bound.
     * Expected output: true (1 is the upper bound of exampleRange).
     */
    @Test
    public void testRangeContainsUpperBound() {
        assertTrue(exampleRange.contains(1));
    }

    /**
     * Test case: Check if the range contains an extreme value.
     * Test strategy: If the value is much larger or smaller than the range, return false.
     * Expected output: false (100 is not within -1 to 1).
     */
    @Test
    public void testRangeDoesNotContainExtremeValue() {
        assertFalse(exampleRange.contains(100));
    }

    // -------------- Combine Method Tests --------------

    /**
     * Test case: Combining two null ranges should return null.
     * Test strategy: The Javadoc states "If both ranges are null, the return value is null."
     * Expected output: null.
     */
    @Test
    public void testCombineNullRanges() {
        assertNull(Range.combine(null, null));
    }

    /**
     * Test case: Combining a null range with a valid range should return the valid range.
     * Test strategy: The Javadoc states "If one range is null, return the other range."
     * Expected output: The non-null range.
     */
    @Test
    public void testCombineOneNullRange() {
        Range range = new Range(1, 10);
        assertEquals(range, Range.combine(range, null));
    }

    /**
     * Test case: Combine two non-overlapping positive ranges.
     * Test strategy: The combined range should extend from the lower bound of the first to the upper bound of the second.
     * Expected output: Range(1, 20).
     */
    @Test
    public void testCombineNonOverlappingRanges() {
        Range range1 = new Range(1, 10);
        Range range2 = new Range(15, 20);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(1, 20), combined);
    }

    /**
     * Test case: Combine two overlapping ranges.
     * Test strategy: The combined range should merge overlapping regions.
     * Expected output: Range(-5, 10).
     */
    @Test
    public void testCombineOverlappingRanges() {
        Range range1 = new Range(-5, 5);
        Range range2 = new Range(3, 10);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(-5, 10), combined);
    }

    /**
     * Test case: Combine two adjacent ranges.
     * Test strategy: The combined range should include both ranges without gaps.
     * Expected output: Range(5, 15).
     */
    @Test
    public void testCombineAdjacentRanges() {
        Range range1 = new Range(5, 10);
        Range range2 = new Range(10, 15);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(5, 15), combined);
    }

    /**
     * Test case: Combine two identical ranges.
     * Test strategy: The combined range should remain unchanged.
     * Expected output: Range(3, 7).
     */
    @Test
    public void testCombineIdenticalRanges() {
        Range range1 = new Range(3, 7);
        Range range2 = new Range(3, 7);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(3, 7), combined);
    }

    /**
     * Test case: Combine two extreme ranges (infinity cases).
     * Test strategy: The combined range should handle extreme values.
     * Expected output: Range(-∞, ∞).
     */
    @Test
    public void testCombineExtremeRanges() {
        Range range1 = new Range(Double.NEGATIVE_INFINITY, -100);
        Range range2 = new Range(100, Double.POSITIVE_INFINITY);
        Range combined = Range.combine(range1, range2);
        assertEquals(new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY), combined);
    }
}
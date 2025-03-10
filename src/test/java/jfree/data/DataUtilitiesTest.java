package jfree.data;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DataUtilitiesTest {
    private Mockery mockingContext;
    private Values2D values2D;
    private KeyedValues keyedValues;

    @Before
    public void setUp() {
        mockingContext = new Mockery();
        values2D = mockingContext.mock(Values2D.class);
        keyedValues = mockingContext.mock(KeyedValues.class);
    }

// ---------------------------- calculateColumnTotal Tests ----------------------------
    /**
     * Test case: Calculate column total with valid data.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks the basic case of summing positive values in a column.
     */
    @Test
    public void testCalculateColumnTotalForTwoValues() {

        // Example
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(2));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values2D).getValue(1, 0);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // #verify
        assertEquals("Column total should be 10.0", 10.0, result, .000000001d);
    }


    /**
     * Test case: Calculate column total with positive values.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks the basic case of summing positive values in a column.
     */
    @Test
    public void testCalculateColumnTotalForPositiveValues() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(2));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values2D).getValue(1, 0);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // verify
        assertEquals("Column total should be 10.0", 10.0, result, .000000001d);
    }

    /**
     * Test case: Calculate column total with negative values.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks the basic case of summing negative values in a column.
     */
    @Test
    public void testCalculateColumnTotalForNegativeValues() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(2));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(-7.5));
            oneOf(values2D).getValue(1, 0);
            will(returnValue(-2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // verify
        assertEquals("Column total should be -10.0", -10.0, result, .000000001d);
    }

    /**
     * Test case: Calculate column total with mixed values.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks the case of summing both positive and negative values in a column.
     */
    @Test
    public void testCalculateColumnTotalForMixedValues() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(2));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values2D).getValue(1, 0);
            will(returnValue(-2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // verify
        assertEquals("Column total should be 5.0", 5.0, result, .000000001d);
    }

    /**
     * Test case: Calculate column total with an empty data set.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that an empty data set results in zero.
     */
    @Test
    public void testCalculateColumnTotalForEmptyDataSet() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(0));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // verify
        assertEquals("Column total should be 0.0", 0.0, result, .000000001d);
    }

    /**
     * Test case: Calculate column total with a single value.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that a single value in the column returns that value.
     */
    @Test
    public void testCalculateColumnTotalForSingleValue() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(1));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(7.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // verify
        assertEquals("Column total should be 7.5", 7.5, result, .000000001d);
    }



    /**
     * Test case: Calculate column total with invalid data, returns zero.
     * Test strategy: The Javadoc states "With invalid input, a total of zero will be returned.".
     * This tests that invalid data (empty dataset) results in zero.
     */
    @Test
    public void CalculateColumnTotalEmptyDataSet() {


        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getRowCount();
            will(returnValue(0));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values2D, 0);
        // #verify
        assertEquals("Column total should be 0.0", 0.0, result, .000000001d);
    }


    /**
     * Test case: Calculate column total with null data.
     * Test strategy: The Javadoc states "Returns the sum of the values in one column of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCalculateColumnTotalForNullData() {
//        // exercise
//        DataUtilities.calculateColumnTotal(null, 0);
//    }

    // ---------------------------- calculateRowTotal Tests ----------------------------
    /**
     * Test case: Calculate row total with valid data.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned." This test checks the basic case of summing a row.
     */
    @Test
    public void testCalculateRowTotalValidDataPositiveValues() {

        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getColumnCount();
            will(returnValue(3));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(1.0));
            oneOf(values2D).getValue(0, 1);
            will(returnValue(2.0));
            oneOf(values2D).getValue(0, 2);
            will(returnValue(3.0));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values2D, 0);
        // #verify
        assertEquals("Row total should be 6.0", 6.0, result, .000000001d);
    }

    /**
     * Test case: Calculate row total with negative values.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned." This test checks the basic case of summing a row.
     */
    @Test
    public void testCalculateRowTotalValidDataNegativeValues() {

        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getColumnCount();
            will(returnValue(3));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(-1.0));
            oneOf(values2D).getValue(0, 1);
            will(returnValue(-2.0));
            oneOf(values2D).getValue(0, 2);
            will(returnValue(-3.0));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values2D, 0);

        // #verify
        assertEquals("Row total should be -6.0", -6.0, result, .000000001d);
    }

    /**
     * Test case: Calculate row total with null data.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that null data results in an InvalidParameterException.
     */
//    @Test(expected = InvalidParameterException.class)
//    public void testCalculateRowTotalNullData() {
//        // exercise
//        DataUtilities.calculateRowTotal(null, 0);
//    }

    /**
     * Test case: Calculate row total with empty data set.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that an empty data set results in zero.
     */
    @Test
    public void testCalculateRowTotalEmptyDataSet() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getColumnCount();
            will(returnValue(0));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values2D, 0);

        // verify
        assertEquals("Row total should be 0.0", 0.0, result, .000000001d);
    }

    /**
     * Test case: Calculate row total with mixed values.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks the case of summing both positive and negative values in a row.
     */
    @Test
    public void testCalculateRowTotalMixedValues() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getColumnCount();
            will(returnValue(3));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(1.0));
            oneOf(values2D).getValue(0, 1);
            will(returnValue(-2.0));
            oneOf(values2D).getValue(0, 2);
            will(returnValue(3.0));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values2D, 0);

        // verify
        assertEquals("Row total should be 2.0", 2.0, result, .000000001d);
    }

    /**
     * Test case: Calculate row total with a single value.
     * Test strategy: The Javadoc states "Returns the sum of the values in one row of the supplied data table.
     * With invalid input, a total of zero will be returned."
     * This test checks that a single value in the row returns that value.
     */
    @Test
    public void testCalculateRowTotalSingleValue() {
        mockingContext.checking(new Expectations() {{
            oneOf(values2D).getColumnCount();
            will(returnValue(1));
            oneOf(values2D).getValue(0, 0);
            will(returnValue(7.5));
        }});

        // exercise
        double result = DataUtilities.calculateRowTotal(values2D, 0);

        // verify
        assertEquals("Row total should be 7.5", 7.5, result, .000000001d);
    }


    // ---------------------------- createNumberArray2D Tests ----------------------------

    /**
     * Test case: Create a 2D Number array with an empty double array.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This tests the creation of the 2D array when the input array is empty.
     */
    @Test
    public void testCreateNumberArray2DEmptyArray() {

        // #exercise
        double[][] data = {};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // #verify
        assertEquals("Number array length should be 0", 0, result.length);
    }
    /**
     * Test case: Create a 2D Number array with valid data.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks the basic case of converting a 2D array of doubles to a 2D array of Numbers.
     */
    @Test
    public void testCreateNumberArray2DValidData() {
        double[][] data = {
                {1.1, 2.2},
                {3.3, 4.4}
        };

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        Number[][] expected = {
                {1.1, 2.2},
                {3.3, 4.4}
        };
        assertArrayEquals("2D Number array should match the expected values", expected, result);
    }

    /**
     * Test case: Create a 2D Number array with null data.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks that null data results in an InvalidParameterException.
     */
    @Test(expected = InvalidParameterException.class)
    public void testCreateNumberArray2DNullData() {
        // exercise
        try {
            DataUtilities.createNumberArray2D(null);
        } catch (InvalidParameterException e) {
            System.out.println("InvalidParameterException");
        }
    }


    /**
     * Test case: Create a 2D Number array with a single row.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks the creation of the 2D array when the input array has a single row.
     */
    @Test
    public void testCreateNumberArray2DSingleRow() {
        double[][] data = {
                {1.1, 2.2, 3.3}
        };

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        Number[][] expected = {
                {1.1, 2.2, 3.3}
        };
        assertArrayEquals("2D Number array should match the expected values", expected, result);
    }

    /**
     * Test case: Create a 2D Number array with a single column.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks the creation of the 2D array when the input array has a single column.
     */
    @Test
    public void testCreateNumberArray2DSingleColumn() {
        double[][] data = {
                {1.1},
                {2.2},
                {3.3}
        };

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        Number[][] expected = {
                {1.1},
                {2.2},
                {3.3}
        };
        assertArrayEquals("2D Number array should match the expected values", expected, result);
    }

    /**
     * Test case: Create a 2D Number array with null row.
     * Test strategy: The Javadoc states "Constructs an array of arrays of Number objects from a corresponding structure containing double primitives."
     * This test checks that a null row in the input array results in an InvalidParameterException.
     */
    @Test(expected = InvalidParameterException.class)
    public void testCreateNumberArray2DNullRow() {
        double[][] data = {
                {1.1, 2.2},
                null
        };

        // exercise
        DataUtilities.createNumberArray2D(data);
    }

    // ---------------------------- getCumulativePercentages Tests ----------------------------

    /**
     * Test case: Get cumulative percentages with valid data.
     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
     *  This tests the creation of cumulative percentages array with valid values.
     */
    @Test
    public void testGetCumulativePercentagesValidData() {

        mockingContext.checking(new Expectations() {{
            allowing(keyedValues).getItemCount();
            will(returnValue(3));
            allowing(keyedValues).getValue(0);
            will(returnValue(5));
            allowing(keyedValues).getValue(1);
            will(returnValue(9));
            allowing(keyedValues).getValue(2);
            will(returnValue(2));
            allowing(keyedValues).getKey(0);
            will(returnValue(0));
            allowing(keyedValues).getKey(1);
            will(returnValue(1));
            allowing(keyedValues).getKey(2);
            will(returnValue(2));
        }});

        // exercise
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

        // #verify
        assertEquals("First cumulative percentage should be 0.3125", 0.3125, result.getValue(0).doubleValue(), .000000001d);
        assertEquals("Second cumulative percentage should be 0.875", 0.875, result.getValue(1).doubleValue(), .000000001d);
        assertEquals("Third cumulative percentage should be 1.0", 1.0, result.getValue(2).doubleValue(), .000000001d);
    }


    /**
     * Test case: Get cumulative percentages with null data.
     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
     * This test checks that null data results in an InvalidParameterException.
     */
    @Test(expected = InvalidParameterException.class)
    public void testGetCumulativePercentagesNullData() {
        // exercise
        DataUtilities.getCumulativePercentages(null);
    }

    /**
     * Test case: Get cumulative percentages with empty data.
     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
     * This test checks that an empty data set results in an empty cumulative percentages set.
     */
    @Test
    public void testGetCumulativePercentagesEmptyData() {
        mockingContext.checking(new Expectations() {{
            allowing(keyedValues).getItemCount();
            will(returnValue(0));
        }});

        // exercise
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

        // verify
        assertEquals("Cumulative percentages should be empty", 0, result.getItemCount());
    }
    /**
     * Test case: Get cumulative percentages with single value.
     * Test strategy: The Javadoc states "Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance."
     * This test checks that a single value results in a cumulative percentage of 1.0.
     */
    @Test
    public void testGetCumulativePercentagesSingleValue() {
        mockingContext.checking(new Expectations() {{
            allowing(keyedValues).getItemCount();
            will(returnValue(1));
            allowing(keyedValues).getValue(0);
            will(returnValue(5));
            allowing(keyedValues).getKey(0);
            will(returnValue(0));
        }});

        // exercise
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

        // verify
        assertEquals("Cumulative percentage should be 1.0", 1.0, result.getValue(0).doubleValue(), .000000001d);
    }
    // ---------------------------- createNumberArray Tests ----------------------------


    /**
     * Test case: Create a Number array with valid data.
     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
     * This test checks the basic case of converting an array of doubles to an array of Numbers.
     */
    @Test
    public void testCreateNumberArrayValidData() {
        double[] data = {1.1, 2.2, 3.3};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        Number[] expected = {1.1, 2.2, 3.3};
        assertArrayEquals("Number array should match the expected values", expected, result);
    }

    /**
     * Test case: Create a Number array with null data.
     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
     * This test checks that null data results in an InvalidParameterException.
     */
    @Test(expected = InvalidParameterException.class)
    public void testCreateNumberArrayNullData() {
        // exercise
        DataUtilities.createNumberArray(null);
    }

    /**
     * Test case: Create a Number array with an empty array.
     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
     * This test checks the creation of the array when the input array is empty.
     */
    @Test
    public void testCreateNumberArrayEmptyArray() {
        double[] data = {};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        Number[] expected = {};
        assertArrayEquals("Number array should be empty", expected, result);
    }

    /**
     * Test case: Create a Number array with a single value.
     * Test strategy: The Javadoc states "Constructs an array of Number objects from an array of double primitives."
     * This test checks that a single value in the input array returns that value.
     */
    @Test
    public void testCreateNumberArraySingleValue() {
        double[] data = {7.5};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        Number[] expected = {7.5};
        assertArrayEquals("Number array should match the expected value", expected, result);
    }
}

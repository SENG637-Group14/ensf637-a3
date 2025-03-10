/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2013, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ---------------------
 * DateTickUnitType.java
 * ---------------------
 * (C) Copyright 2009, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 09-Jan-2009 : Version 1 (DG);
 *
 */

package org.jfree.chart.axis;

import jfree.chart.axis.DateTickUnit;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * An enumeration of the unit types for a {@link DateTickUnit} instance.
 *
 * @since 1.0.13
 */
public class DateTickUnitType implements Serializable {

    /** Year. */
    public static final jfree.chart.axis.DateTickUnitType YEAR
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.YEAR", Calendar.YEAR);

    /** Month. */
    public static final jfree.chart.axis.DateTickUnitType MONTH
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.MONTH", Calendar.MONTH);

    /** Day. */
    public static final jfree.chart.axis.DateTickUnitType DAY
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.DAY", Calendar.DATE);


    /** Hour. */
    public static final jfree.chart.axis.DateTickUnitType HOUR
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.HOUR",
                    Calendar.HOUR_OF_DAY);

    /** Minute. */
    public static final jfree.chart.axis.DateTickUnitType MINUTE
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.MINUTE", Calendar.MINUTE);

    /** Second. */
    public static final jfree.chart.axis.DateTickUnitType SECOND
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.SECOND", Calendar.SECOND);

    /** Millisecond. */
    public static final jfree.chart.axis.DateTickUnitType MILLISECOND
            = new jfree.chart.axis.DateTickUnitType("DateTickUnitType.MILLISECOND",
                    Calendar.MILLISECOND);

    /** The name. */
    private String name;

    /** The corresponding field value in Java's Calendar class. */
    private int calendarField;

    /**
     * Private constructor.
     *
     * @param name  the name.
     * @param calendarField  the calendar field.
     */
    private DateTickUnitType(String name, int calendarField) {
        this.name = name;
        this.calendarField = calendarField;
    }

    /**
     * Returns the calendar field.
     *
     * @return The calendar field.
     */
    public int getCalendarField() {
        return this.calendarField;
    }

    /**
     * Returns a string representing the object.
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns <code>true</code> if this object is equal to the specified
     * object, and <code>false</code> otherwise.
     *
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jfree.chart.axis.DateTickUnitType)) {
            return false;
        }
        jfree.chart.axis.DateTickUnitType t = (jfree.chart.axis.DateTickUnitType) obj;
        if (!this.name.equals(t.toString())) {
            return false;
        }
        return true;
    }

    /**
     * Ensures that serialization returns the unique instances.
     *
     * @return The object.
     *
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(jfree.chart.axis.DateTickUnitType.YEAR)) {
            return jfree.chart.axis.DateTickUnitType.YEAR;
        }
        else if (this.equals(jfree.chart.axis.DateTickUnitType.MONTH)) {
            return jfree.chart.axis.DateTickUnitType.MONTH;
        }
        else if (this.equals(jfree.chart.axis.DateTickUnitType.DAY)) {
            return jfree.chart.axis.DateTickUnitType.DAY;
        }
        else if (this.equals(jfree.chart.axis.DateTickUnitType.HOUR)) {
            return jfree.chart.axis.DateTickUnitType.HOUR;
        }
        else if (this.equals(jfree.chart.axis.DateTickUnitType.MINUTE)) {
            return jfree.chart.axis.DateTickUnitType.MINUTE;
        }
        else if (this.equals(jfree.chart.axis.DateTickUnitType.SECOND)) {
            return jfree.chart.axis.DateTickUnitType.SECOND;
        }
        else if (this.equals(jfree.chart.axis.DateTickUnitType.MILLISECOND)) {
            return jfree.chart.axis.DateTickUnitType.MILLISECOND;
        }
        return null;
    }

}

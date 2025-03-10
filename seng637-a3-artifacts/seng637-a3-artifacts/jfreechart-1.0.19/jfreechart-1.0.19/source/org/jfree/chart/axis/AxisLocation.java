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
 * -----------------
 * AxisLocation.java
 * -----------------
 * (C) Copyright 2003-2013, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nick Guenther;
 *
 * Changes:
 * --------
 * 02-May-2003 : Version 1 (DG);
 * 03-Jul-2003 : Added isTopOrBottom() and isLeftOrRight() methods (DG);
 * 13-Aug-2003 : Fixed readResolve() bug (id=788202) (DG);
 * 24-Mar-2004 : Added static getOpposite() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 22-Mar-2007 : Added getOpposite() method, suggested by Nick Guenther (DG);
 * 02-Jul-2013 : Use ParamChecks (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;
import jfree.chart.util.ParamChecks;

/**
 * Used to indicate the location of an axis on a 2D plot, prior to knowing the
 * orientation of the plot.
 */
public final class AxisLocation implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -3276922179323563410L;

    /** Axis at the top or left. */
    public static final jfree.chart.axis.AxisLocation TOP_OR_LEFT = new jfree.chart.axis.AxisLocation(
            "AxisLocation.TOP_OR_LEFT");

    /** Axis at the top or right. */
    public static final jfree.chart.axis.AxisLocation TOP_OR_RIGHT = new jfree.chart.axis.AxisLocation(
            "AxisLocation.TOP_OR_RIGHT");

    /** Axis at the bottom or left. */
    public static final jfree.chart.axis.AxisLocation BOTTOM_OR_LEFT = new jfree.chart.axis.AxisLocation(
            "AxisLocation.BOTTOM_OR_LEFT");

    /** Axis at the bottom or right. */
    public static final jfree.chart.axis.AxisLocation BOTTOM_OR_RIGHT = new jfree.chart.axis.AxisLocation(
            "AxisLocation.BOTTOM_OR_RIGHT");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private AxisLocation(String name) {
        this.name = name;
    }

    /**
     * Returns the location that is opposite to this location.
     *
     * @return The opposite location.
     *
     * @since 1.0.5
     */
    public jfree.chart.axis.AxisLocation getOpposite() {
        return getOpposite(this);
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
     * @param obj  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jfree.chart.axis.AxisLocation)) {
            return false;
        }
        jfree.chart.axis.AxisLocation location = (jfree.chart.axis.AxisLocation) obj;
        if (!this.name.equals(location.toString())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.name.hashCode();
        return hash;
    }

    /**
     * Returns the location that is opposite to the supplied location.
     *
     * @param location  the location (<code>null</code> not permitted).
     *
     * @return The opposite location.
     */
    public static jfree.chart.axis.AxisLocation getOpposite(jfree.chart.axis.AxisLocation location) {
        ParamChecks.nullNotPermitted(location, "location");
        jfree.chart.axis.AxisLocation result = null;
        if (location == jfree.chart.axis.AxisLocation.TOP_OR_LEFT) {
            result = jfree.chart.axis.AxisLocation.BOTTOM_OR_RIGHT;
        }
        else if (location == jfree.chart.axis.AxisLocation.TOP_OR_RIGHT) {
            result = jfree.chart.axis.AxisLocation.BOTTOM_OR_LEFT;
        }
        else if (location == jfree.chart.axis.AxisLocation.BOTTOM_OR_LEFT) {
            result = jfree.chart.axis.AxisLocation.TOP_OR_RIGHT;
        }
        else if (location == jfree.chart.axis.AxisLocation.BOTTOM_OR_RIGHT) {
            result = jfree.chart.axis.AxisLocation.TOP_OR_LEFT;
        }
        else {
            throw new IllegalStateException("AxisLocation not recognised.");
        }
        return result;
    }

    /**
     * Ensures that serialization returns the unique instances.
     *
     * @return The object.
     *
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(jfree.chart.axis.AxisLocation.TOP_OR_RIGHT)) {
            return jfree.chart.axis.AxisLocation.TOP_OR_RIGHT;
        }
        else if (this.equals(jfree.chart.axis.AxisLocation.BOTTOM_OR_RIGHT)) {
            return jfree.chart.axis.AxisLocation.BOTTOM_OR_RIGHT;
        }
        else if (this.equals(jfree.chart.axis.AxisLocation.TOP_OR_LEFT)) {
            return jfree.chart.axis.AxisLocation.TOP_OR_LEFT;
        }
        else if (this.equals(jfree.chart.axis.AxisLocation.BOTTOM_OR_LEFT)) {
            return jfree.chart.axis.AxisLocation.BOTTOM_OR_LEFT;
        }
        return null;
    }

}

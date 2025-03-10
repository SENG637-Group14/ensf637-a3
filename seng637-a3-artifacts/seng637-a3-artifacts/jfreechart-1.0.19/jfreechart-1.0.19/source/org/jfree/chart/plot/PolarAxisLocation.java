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
 * ----------------------
 * PolarAxisLocation.java
 * ----------------------
 * (C) Copyright 2009, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 25-Nov-2009 : Version 1 (DG);
 *
 */

package org.jfree.chart.plot;

import jfree.chart.plot.PolarPlot;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate the location of an axis on a {@link PolarPlot}.
 *
 * @since 1.0.14
 */
public final class PolarAxisLocation implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -3276922179323563410L;

    /** Axis left of north. */
    public static final jfree.chart.plot.PolarAxisLocation NORTH_LEFT
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.NORTH_LEFT");

    /** Axis right of north. */
    public static final jfree.chart.plot.PolarAxisLocation NORTH_RIGHT
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.NORTH_RIGHT");

    /** Axis left of south. */
    public static final jfree.chart.plot.PolarAxisLocation SOUTH_LEFT
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.SOUTH_LEFT");

    /** Axis right of south. */
    public static final jfree.chart.plot.PolarAxisLocation SOUTH_RIGHT
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.SOUTH_RIGHT");

    /** Axis above east. */
    public static final jfree.chart.plot.PolarAxisLocation EAST_ABOVE
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.EAST_ABOVE");

    /** Axis below east. */
    public static final jfree.chart.plot.PolarAxisLocation EAST_BELOW
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.EAST_BELOW");

    /** Axis above west. */
    public static final jfree.chart.plot.PolarAxisLocation WEST_ABOVE
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.WEST_ABOVE");

    /** Axis below west. */
    public static final jfree.chart.plot.PolarAxisLocation WEST_BELOW
            = new jfree.chart.plot.PolarAxisLocation("PolarAxisLocation.WEST_BELOW");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private PolarAxisLocation(String name) {
        this.name = name;
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
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof jfree.chart.plot.PolarAxisLocation)) {
            return false;
        }
        jfree.chart.plot.PolarAxisLocation location = (jfree.chart.plot.PolarAxisLocation) obj;
        if (!this.name.equals(location.toString())) {
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
        if (this.equals(jfree.chart.plot.PolarAxisLocation.NORTH_RIGHT)) {
            return jfree.chart.plot.PolarAxisLocation.NORTH_RIGHT;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.NORTH_LEFT)) {
            return jfree.chart.plot.PolarAxisLocation.NORTH_LEFT;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.SOUTH_RIGHT)) {
            return jfree.chart.plot.PolarAxisLocation.SOUTH_RIGHT;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.SOUTH_LEFT)) {
            return jfree.chart.plot.PolarAxisLocation.SOUTH_LEFT;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.EAST_ABOVE)) {
            return jfree.chart.plot.PolarAxisLocation.EAST_ABOVE;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.EAST_BELOW)) {
            return jfree.chart.plot.PolarAxisLocation.EAST_BELOW;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.WEST_ABOVE)) {
            return jfree.chart.plot.PolarAxisLocation.WEST_ABOVE;
        }
        else if (this.equals(jfree.chart.plot.PolarAxisLocation.WEST_BELOW)) {
            return jfree.chart.plot.PolarAxisLocation.WEST_BELOW;
        }
        return null;
    }

}

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
 * --------------
 * DialShape.java
 * --------------
 * (C) Copyright 2003-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 20-Aug-2003 : Version 1 (DG);
 * 12-Nov-2007 : Implemented hashCode() (DG);
 *
 */

package org.jfree.chart.plot;

import jfree.chart.plot.MeterPlot;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Used to indicate the background shape for a
 * {@link MeterPlot}.
 */
public final class DialShape implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -3471933055190251131L;

    /** Circle. */
    public static final jfree.chart.plot.DialShape CIRCLE = new jfree.chart.plot.DialShape("DialShape.CIRCLE");

    /** Chord. */
    public static final jfree.chart.plot.DialShape CHORD = new jfree.chart.plot.DialShape("DialShape.CHORD");

    /** Pie. */
    public static final jfree.chart.plot.DialShape PIE = new jfree.chart.plot.DialShape("DialShape.PIE");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private DialShape(String name) {
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
     * @param obj  the other object.
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jfree.chart.plot.DialShape)) {
            return false;
        }
        jfree.chart.plot.DialShape shape = (jfree.chart.plot.DialShape) obj;
        if (!this.name.equals(shape.toString())) {
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
        return this.name.hashCode();
    }

    /**
     * Ensures that serialization returns the unique instances.
     *
     * @return The object.
     *
     * @throws ObjectStreamException if there is a problem.
     */
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(jfree.chart.plot.DialShape.CIRCLE)) {
            return jfree.chart.plot.DialShape.CIRCLE;
        }
        else if (this.equals(jfree.chart.plot.DialShape.CHORD)) {
            return jfree.chart.plot.DialShape.CHORD;
        }
        else if (this.equals(jfree.chart.plot.DialShape.PIE)) {
            return jfree.chart.plot.DialShape.PIE;
        }
        return null;
    }

}

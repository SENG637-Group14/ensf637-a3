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
 * --------------------
 * ItemLabelAnchor.java
 * --------------------
 * (C) Copyright 2003-2013, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 29-Apr-2003 : Version 1 (DG);
 * 19-Feb-2004 : Moved to org.jfree.chart.labels package, added readResolve()
 *               method (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0
 *               release (DG);
 *
 */

package org.jfree.chart.labels;

import jfree.chart.plot.CategoryPlot;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * An enumeration of the positions that a value label can take, relative to an
 * item in a {@link CategoryPlot}.
 */
public final class ItemLabelAnchor implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -1233101616128695658L;

    /** CENTER. */
    public static final jfree.chart.labels.ItemLabelAnchor CENTER
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.CENTER");

    /** INSIDE1. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE1
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE1");

    /** INSIDE2. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE2
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE2");

    /** INSIDE3. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE3
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE3");

    /** INSIDE4. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE4
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE4");

    /** INSIDE5. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE5
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE5");

    /** INSIDE6. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE6
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE6");

    /** INSIDE7. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE7
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE7");

    /** INSIDE8. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE8
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE8");

    /** INSIDE9. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE9
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE9");

    /** INSIDE10. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE10
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE10");

    /** INSIDE11. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE11
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE11");

    /** INSIDE12. */
    public static final jfree.chart.labels.ItemLabelAnchor INSIDE12
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.INSIDE12");

    /** OUTSIDE1. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE1
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE1");

    /** OUTSIDE2. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE2
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE2");

    /** OUTSIDE3. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE3
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE3");

    /** OUTSIDE4. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE4
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE4");

    /** OUTSIDE5. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE5
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE5");

    /** OUTSIDE6. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE6
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE6");

    /** OUTSIDE7. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE7
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE7");

    /** OUTSIDE8. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE8
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE8");

    /** OUTSIDE9. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE9
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE9");

    /** OUTSIDE10. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE10
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE10");

    /** OUTSIDE11. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE11
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE11");

    /** OUTSIDE12. */
    public static final jfree.chart.labels.ItemLabelAnchor OUTSIDE12
        = new jfree.chart.labels.ItemLabelAnchor("ItemLabelAnchor.OUTSIDE12");

    /** The name. */
    private String name;

    /**
     * Private constructor.
     *
     * @param name  the name.
     */
    private ItemLabelAnchor(String name) {
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
        if (!(obj instanceof jfree.chart.labels.ItemLabelAnchor)) {
            return false;
        }
        jfree.chart.labels.ItemLabelAnchor that = (jfree.chart.labels.ItemLabelAnchor) obj;
        if (!this.name.equals(that.toString())) {
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
        jfree.chart.labels.ItemLabelAnchor result = null;
        if (this.equals(jfree.chart.labels.ItemLabelAnchor.CENTER)) {
            result = jfree.chart.labels.ItemLabelAnchor.CENTER;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE1)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE1;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE2)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE2;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE3)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE3;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE4)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE4;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE5)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE5;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE6)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE6;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE7)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE7;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE8)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE8;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE9)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE9;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE10)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE10;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE11)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE11;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.INSIDE12)) {
            result = jfree.chart.labels.ItemLabelAnchor.INSIDE12;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE1)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE1;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE2)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE2;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE3)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE3;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE4)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE4;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE5)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE5;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE6)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE6;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE7)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE7;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE8)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE8;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE9)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE9;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE10)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE10;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE11)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE11;
        }
        else if (this.equals(jfree.chart.labels.ItemLabelAnchor.OUTSIDE12)) {
            result = jfree.chart.labels.ItemLabelAnchor.OUTSIDE12;
        }
        return result;
    }

}

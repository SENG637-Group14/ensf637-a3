/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
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
 * ---------------------------
 * CategoryLabelPositions.java
 * ---------------------------
 * (C) Copyright 2004-2014, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Jan-2004 : Version 1 (DG);
 * 17-Feb-2004 : Added equals() method (DG);
 * 05-Nov-2004 : Adjusted settings for UP_90 and DOWN_90 (DG);
 * 02-Jul-2013 : Use ParamChecks (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;

import jfree.chart.axis.CategoryLabelPosition;
import jfree.chart.axis.CategoryLabelWidthType;
import jfree.chart.util.ParamChecks;

import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * Records the label positions for a category axis.  Instances of this class
 * are immutable.
 */
public class CategoryLabelPositions implements Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -8999557901920364580L;

    /** STANDARD category label positions. */
    public static final jfree.chart.axis.CategoryLabelPositions
        STANDARD = new jfree.chart.axis.CategoryLabelPositions(
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_CENTER), // TOP
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.TOP_CENTER), // BOTTOM
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.CENTER_RIGHT,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.30f), // LEFT
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.30f) // RIGHT
        );

    /** UP_90 category label positions. */
    public static final jfree.chart.axis.CategoryLabelPositions
        UP_90 = new jfree.chart.axis.CategoryLabelPositions(
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_LEFT,
                TextAnchor.CENTER_LEFT, -Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.30f), // TOP
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.CENTER_RIGHT,
                TextAnchor.CENTER_RIGHT, -Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.30f), // BOTTOM
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_CENTER,
                TextAnchor.BOTTOM_CENTER, -Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.CATEGORY, 0.9f), // LEFT
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.TOP_CENTER,
                TextAnchor.TOP_CENTER, -Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.CATEGORY, 0.90f) // RIGHT
        );

    /** DOWN_90 category label positions. */
    public static final jfree.chart.axis.CategoryLabelPositions
        DOWN_90 = new jfree.chart.axis.CategoryLabelPositions(
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_RIGHT,
                TextAnchor.CENTER_RIGHT, Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.30f), // TOP
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.CENTER_LEFT,
                TextAnchor.CENTER_LEFT, Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.30f), // BOTTOM
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.TOP_CENTER,
                TextAnchor.TOP_CENTER, Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.CATEGORY, 0.90f), // LEFT
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_CENTER,
                TextAnchor.BOTTOM_CENTER, Math.PI / 2.0,
                jfree.chart.axis.CategoryLabelWidthType.CATEGORY, 0.90f) // RIGHT
        );

    /** UP_45 category label positions. */
    public static final jfree.chart.axis.CategoryLabelPositions UP_45
        = createUpRotationLabelPositions(Math.PI / 4.0);

    /** DOWN_45 category label positions. */
    public static final jfree.chart.axis.CategoryLabelPositions DOWN_45
        = createDownRotationLabelPositions(Math.PI / 4.0);

    /**
     * Creates a new instance where the category labels angled upwards by the
     * specified amount.
     *
     * @param angle  the rotation angle (should be &lt; Math.PI / 2.0).
     *
     * @return A category label position specification.
     */
    public static jfree.chart.axis.CategoryLabelPositions createUpRotationLabelPositions(
            double angle) {
        return new jfree.chart.axis.CategoryLabelPositions(
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_LEFT,
                TextAnchor.BOTTOM_LEFT, -angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f), // TOP
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.TOP_RIGHT,
                TextAnchor.TOP_RIGHT, -angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f), // BOTTOM
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_RIGHT,
                TextAnchor.BOTTOM_RIGHT, -angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f), // LEFT
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.TOP_LEFT,
                TextAnchor.TOP_LEFT, -angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f) // RIGHT
        );
    }

    /**
     * Creates a new instance where the category labels angled downwards by the
     * specified amount.
     *
     * @param angle  the rotation angle (should be &lt; Math.PI / 2.0).
     *
     * @return A category label position specification.
     */
    public static jfree.chart.axis.CategoryLabelPositions createDownRotationLabelPositions(
            double angle) {
        return new jfree.chart.axis.CategoryLabelPositions(
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_RIGHT,
                TextAnchor.BOTTOM_RIGHT, angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f), // TOP
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.TOP, TextBlockAnchor.TOP_LEFT,
                TextAnchor.TOP_LEFT, angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f), // BOTTOM
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.RIGHT, TextBlockAnchor.TOP_RIGHT,
                TextAnchor.TOP_RIGHT, angle,
                jfree.chart.axis.CategoryLabelWidthType.RANGE, 0.50f), // LEFT
            new jfree.chart.axis.CategoryLabelPosition(
                RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_LEFT,
                TextAnchor.BOTTOM_LEFT, angle,
                CategoryLabelWidthType.RANGE, 0.50f) // RIGHT
        );
    }

    /**
     * The label positioning details used when an axis is at the top of a
     * chart.
     */
    private jfree.chart.axis.CategoryLabelPosition positionForAxisAtTop;

    /**
     * The label positioning details used when an axis is at the bottom of a
     * chart.
     */
    private jfree.chart.axis.CategoryLabelPosition positionForAxisAtBottom;

    /**
     * The label positioning details used when an axis is at the left of a
     * chart.
     */
    private jfree.chart.axis.CategoryLabelPosition positionForAxisAtLeft;

    /**
     * The label positioning details used when an axis is at the right of a
     * chart.
     */
    private jfree.chart.axis.CategoryLabelPosition positionForAxisAtRight;

    /**
     * Default constructor.
     */
    public CategoryLabelPositions() {
        this.positionForAxisAtTop = new jfree.chart.axis.CategoryLabelPosition();
        this.positionForAxisAtBottom = new jfree.chart.axis.CategoryLabelPosition();
        this.positionForAxisAtLeft = new jfree.chart.axis.CategoryLabelPosition();
        this.positionForAxisAtRight = new jfree.chart.axis.CategoryLabelPosition();
    }

    /**
     * Creates a new position specification.
     *
     * @param top  the label position info used when an axis is at the top
     *             (<code>null</code> not permitted).
     * @param bottom  the label position info used when an axis is at the
     *                bottom (<code>null</code> not permitted).
     * @param left  the label position info used when an axis is at the left
     *              (<code>null</code> not permitted).
     * @param right  the label position info used when an axis is at the right
     *               (<code>null</code> not permitted).
     */
    public CategoryLabelPositions(jfree.chart.axis.CategoryLabelPosition top,
                                  jfree.chart.axis.CategoryLabelPosition bottom, jfree.chart.axis.CategoryLabelPosition left,
                                  jfree.chart.axis.CategoryLabelPosition right) {

        ParamChecks.nullNotPermitted(top, "top");
        ParamChecks.nullNotPermitted(bottom, "bottom");
        ParamChecks.nullNotPermitted(left, "left");
        ParamChecks.nullNotPermitted(right, "right");

        this.positionForAxisAtTop = top;
        this.positionForAxisAtBottom = bottom;
        this.positionForAxisAtLeft = left;
        this.positionForAxisAtRight = right;
    }

    /**
     * Returns the category label position specification for an axis at the
     * given location.
     *
     * @param edge  the axis location.
     *
     * @return The category label position specification.
     */
    public jfree.chart.axis.CategoryLabelPosition getLabelPosition(RectangleEdge edge) {
        jfree.chart.axis.CategoryLabelPosition result = null;
        if (edge == RectangleEdge.TOP) {
            result = this.positionForAxisAtTop;
        }
        else if (edge == RectangleEdge.BOTTOM) {
            result = this.positionForAxisAtBottom;
        }
        else if (edge == RectangleEdge.LEFT) {
            result = this.positionForAxisAtLeft;
        }
        else if (edge == RectangleEdge.RIGHT) {
            result = this.positionForAxisAtRight;
        }
        return result;
    }

    /**
     * Returns a new instance based on an existing instance but with the top
     * position changed.
     *
     * @param base  the base (<code>null</code> not permitted).
     * @param top  the top position (<code>null</code> not permitted).
     *
     * @return A new instance (never <code>null</code>).
     */
    public static jfree.chart.axis.CategoryLabelPositions replaceTopPosition(
            jfree.chart.axis.CategoryLabelPositions base, jfree.chart.axis.CategoryLabelPosition top) {

        ParamChecks.nullNotPermitted(base, "base");
        ParamChecks.nullNotPermitted(top, "top");

        return new jfree.chart.axis.CategoryLabelPositions(top,
            base.getLabelPosition(RectangleEdge.BOTTOM),
            base.getLabelPosition(RectangleEdge.LEFT),
            base.getLabelPosition(RectangleEdge.RIGHT));
    }

    /**
     * Returns a new instance based on an existing instance but with the bottom
     * position changed.
     *
     * @param base  the base (<code>null</code> not permitted).
     * @param bottom  the bottom position (<code>null</code> not permitted).
     *
     * @return A new instance (never <code>null</code>).
     */
    public static jfree.chart.axis.CategoryLabelPositions replaceBottomPosition(
            jfree.chart.axis.CategoryLabelPositions base, jfree.chart.axis.CategoryLabelPosition bottom) {

        ParamChecks.nullNotPermitted(base, "base");
        ParamChecks.nullNotPermitted(bottom, "bottom");

        return new jfree.chart.axis.CategoryLabelPositions(
            base.getLabelPosition(RectangleEdge.TOP),
            bottom,
            base.getLabelPosition(RectangleEdge.LEFT),
            base.getLabelPosition(RectangleEdge.RIGHT));
    }

    /**
     * Returns a new instance based on an existing instance but with the left
     * position changed.
     *
     * @param base  the base (<code>null</code> not permitted).
     * @param left  the left position (<code>null</code> not permitted).
     *
     * @return A new instance (never <code>null</code>).
     */
    public static jfree.chart.axis.CategoryLabelPositions replaceLeftPosition(
            jfree.chart.axis.CategoryLabelPositions base, jfree.chart.axis.CategoryLabelPosition left) {

        ParamChecks.nullNotPermitted(base, "base");
        ParamChecks.nullNotPermitted(left, "left");

        return new jfree.chart.axis.CategoryLabelPositions(
            base.getLabelPosition(RectangleEdge.TOP),
            base.getLabelPosition(RectangleEdge.BOTTOM),
            left,
            base.getLabelPosition(RectangleEdge.RIGHT));
    }

    /**
     * Returns a new instance based on an existing instance but with the right
     * position changed.
     *
     * @param base  the base (<code>null</code> not permitted).
     * @param right  the right position (<code>null</code> not permitted).
     *
     * @return A new instance (never <code>null</code>).
     */
    public static jfree.chart.axis.CategoryLabelPositions replaceRightPosition(
            jfree.chart.axis.CategoryLabelPositions base, CategoryLabelPosition right) {

        ParamChecks.nullNotPermitted(base, "base");
        ParamChecks.nullNotPermitted(right, "right");
        return new jfree.chart.axis.CategoryLabelPositions(
            base.getLabelPosition(RectangleEdge.TOP),
            base.getLabelPosition(RectangleEdge.BOTTOM),
            base.getLabelPosition(RectangleEdge.LEFT),
            right);
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
        if (!(obj instanceof jfree.chart.axis.CategoryLabelPositions)) {
            return false;
        }

        jfree.chart.axis.CategoryLabelPositions that = (jfree.chart.axis.CategoryLabelPositions) obj;
        if (!this.positionForAxisAtTop.equals(that.positionForAxisAtTop)) {
            return false;
        }
        if (!this.positionForAxisAtBottom.equals(
                that.positionForAxisAtBottom)) {
            return false;
        }
        if (!this.positionForAxisAtLeft.equals(that.positionForAxisAtLeft)) {
            return false;
        }
        if (!this.positionForAxisAtRight.equals(that.positionForAxisAtRight)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result = 19;
        result = 37 * result + this.positionForAxisAtTop.hashCode();
        result = 37 * result + this.positionForAxisAtBottom.hashCode();
        result = 37 * result + this.positionForAxisAtLeft.hashCode();
        result = 37 * result + this.positionForAxisAtRight.hashCode();
        return result;
    }
}

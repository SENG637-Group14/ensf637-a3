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
 * GridArrangement.java
 * --------------------
 * (C) Copyright 2005-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 08-Feb-2005 : Version 1 (DG);
 * 03-Dec-2008 : Implemented missing methods, and fixed bugs reported in
 *               patch 2370487 (DG);
 *
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import jfree.chart.block.Arrangement;
import jfree.chart.block.Block;
import jfree.chart.block.BlockContainer;
import jfree.chart.block.LengthConstraintType;
import jfree.chart.block.RectangleConstraint;
import org.jfree.ui.Size2D;

/**
 * Arranges blocks in a grid within their container.
 */
public class GridArrangement implements Arrangement, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -2563758090144655938L;

    /** The rows. */
    private int rows;

    /** The columns. */
    private int columns;

    /**
     * Creates a new grid arrangement.
     *
     * @param rows  the row count.
     * @param columns  the column count.
     */
    public GridArrangement(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Adds a block and a key which can be used to determine the position of
     * the block in the arrangement.  This method is called by the container
     * (you don't need to call this method directly) and gives the arrangement
     * an opportunity to record the details if they are required.
     *
     * @param block  the block.
     * @param key  the key (<code>null</code> permitted).
     */
    @Override
    public void add(jfree.chart.block.Block block, Object key) {
        // can safely ignore
    }

    /**
     * Arranges the blocks within the specified container, subject to the given
     * constraint.
     *
     * @param container  the container (<code>null</code> not permitted).
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The size following the arrangement.
     */
    @Override
    public Size2D arrange(jfree.chart.block.BlockContainer container, Graphics2D g2,
                          jfree.chart.block.RectangleConstraint constraint) {
        jfree.chart.block.LengthConstraintType w = constraint.getWidthConstraintType();
        jfree.chart.block.LengthConstraintType h = constraint.getHeightConstraintType();
        if (w == jfree.chart.block.LengthConstraintType.NONE) {
            if (h == jfree.chart.block.LengthConstraintType.NONE) {
                return arrangeNN(container, g2);
            }
            else if (h == jfree.chart.block.LengthConstraintType.FIXED) {
                return arrangeNF(container, g2, constraint);
            }
            else if (h == jfree.chart.block.LengthConstraintType.RANGE) {
                // find optimum height, then map to range
                return arrangeNR(container, g2, constraint);
            }
        }
        else if (w == jfree.chart.block.LengthConstraintType.FIXED) {
            if (h == jfree.chart.block.LengthConstraintType.NONE) {
                // find optimum height
                return arrangeFN(container, g2, constraint);
            }
            else if (h == jfree.chart.block.LengthConstraintType.FIXED) {
                return arrangeFF(container, g2, constraint);
            }
            else if (h == jfree.chart.block.LengthConstraintType.RANGE) {
                // find optimum height and map to range
                return arrangeFR(container, g2, constraint);
            }
        }
        else if (w == jfree.chart.block.LengthConstraintType.RANGE) {
            // find optimum width and map to range
            if (h == jfree.chart.block.LengthConstraintType.NONE) {
                // find optimum height
                return arrangeRN(container, g2, constraint);
            }
            else if (h == jfree.chart.block.LengthConstraintType.FIXED) {
                // fixed width
                return arrangeRF(container, g2, constraint);
            }
            else if (h == LengthConstraintType.RANGE) {
                return arrangeRR(container, g2, constraint);
            }
        }
        throw new RuntimeException("Should never get to here!");
    }

    /**
     * Arranges the container with no constraint on the width or height.
     *
     * @param container  the container (<code>null</code> not permitted).
     * @param g2  the graphics device.
     *
     * @return The size.
     */
    protected Size2D arrangeNN(jfree.chart.block.BlockContainer container, Graphics2D g2) {
        double maxW = 0.0;
        double maxH = 0.0;
        List blocks = container.getBlocks();
        Iterator iterator = blocks.iterator();
        while (iterator.hasNext()) {
            jfree.chart.block.Block b = (jfree.chart.block.Block) iterator.next();
            if (b != null) {
                Size2D s = b.arrange(g2, jfree.chart.block.RectangleConstraint.NONE);
                maxW = Math.max(maxW, s.width);
                maxH = Math.max(maxH, s.height);
            }
        }
        double width = this.columns * maxW;
        double height = this.rows * maxH;
        jfree.chart.block.RectangleConstraint c = new jfree.chart.block.RectangleConstraint(width, height);
        return arrangeFF(container, g2, c);
    }

    /**
     * Arranges the container with a fixed overall width and height.
     *
     * @param container  the container (<code>null</code> not permitted).
     * @param g2  the graphics device.
     * @param constraint  the constraint (<code>null</code> not permitted).
     *
     * @return The size following the arrangement.
     */
    protected Size2D arrangeFF(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {
        double width = constraint.getWidth() / this.columns;
        double height = constraint.getHeight() / this.rows;
        List blocks = container.getBlocks();
        for (int c = 0; c < this.columns; c++) {
            for (int r = 0; r < this.rows; r++) {
                int index = r * this.columns + c;
                if (index >= blocks.size()) {
                    break;
                }
                jfree.chart.block.Block b = (jfree.chart.block.Block) blocks.get(index);
                if (b != null) {
                    b.setBounds(new Rectangle2D.Double(c * width, r * height,
                            width, height));
                }
            }
        }
        return new Size2D(this.columns * width, this.rows * height);
    }

    /**
     * Arrange with a fixed width and a height within a given range.
     *
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeFR(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        jfree.chart.block.RectangleConstraint c1 = constraint.toUnconstrainedHeight();
        Size2D size1 = arrange(container, g2, c1);

        if (constraint.getHeightRange().contains(size1.getHeight())) {
            return size1;
        }
        else {
            double h = constraint.getHeightRange().constrain(size1.getHeight());
            jfree.chart.block.RectangleConstraint c2 = constraint.toFixedHeight(h);
            return arrange(container, g2, c2);
        }
    }

    /**
     * Arrange with a fixed height and a width within a given range.
     *
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeRF(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        jfree.chart.block.RectangleConstraint c1 = constraint.toUnconstrainedWidth();
        Size2D size1 = arrange(container, g2, c1);

        if (constraint.getWidthRange().contains(size1.getWidth())) {
            return size1;
        }
        else {
            double w = constraint.getWidthRange().constrain(size1.getWidth());
            jfree.chart.block.RectangleConstraint c2 = constraint.toFixedWidth(w);
            return arrange(container, g2, c2);
        }
    }

    /**
     * Arrange with a fixed width and no height constraint.
     *
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeRN(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        jfree.chart.block.RectangleConstraint c1 = constraint.toUnconstrainedWidth();
        Size2D size1 = arrange(container, g2, c1);

        if (constraint.getWidthRange().contains(size1.getWidth())) {
            return size1;
        }
        else {
            double w = constraint.getWidthRange().constrain(size1.getWidth());
            jfree.chart.block.RectangleConstraint c2 = constraint.toFixedWidth(w);
            return arrange(container, g2, c2);
        }
    }

    /**
     * Arrange with a fixed height and no width constraint.
     *
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeNR(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        jfree.chart.block.RectangleConstraint c1 = constraint.toUnconstrainedHeight();
        Size2D size1 = arrange(container, g2, c1);

        if (constraint.getHeightRange().contains(size1.getHeight())) {
            return size1;
        }
        else {
            double h = constraint.getHeightRange().constrain(size1.getHeight());
            jfree.chart.block.RectangleConstraint c2 = constraint.toFixedHeight(h);
            return arrange(container, g2, c2);
        }
    }

    /**
     * Arrange with ranges for both the width and height constraints.
     *
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeRR(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        Size2D size1 = arrange(container, g2, jfree.chart.block.RectangleConstraint.NONE);

        if (constraint.getWidthRange().contains(size1.getWidth())) {
            if (constraint.getHeightRange().contains(size1.getHeight())) {
                return size1;
            }
            else {
                // width is OK, but height must be constrained
                double h = constraint.getHeightRange().constrain(
                        size1.getHeight());
                jfree.chart.block.RectangleConstraint cc = new jfree.chart.block.RectangleConstraint(
                        size1.getWidth(), h);
                return arrangeFF(container, g2, cc);
            }
        }
        else {
            if (constraint.getHeightRange().contains(size1.getHeight())) {
                // height is OK, but width must be constrained
                double w = constraint.getWidthRange().constrain(
                        size1.getWidth());
                jfree.chart.block.RectangleConstraint cc = new jfree.chart.block.RectangleConstraint(w,
                        size1.getHeight());
                return arrangeFF(container, g2, cc);

            }
            else {
                double w = constraint.getWidthRange().constrain(
                        size1.getWidth());
                double h = constraint.getHeightRange().constrain(
                        size1.getHeight());
                jfree.chart.block.RectangleConstraint cc = new jfree.chart.block.RectangleConstraint(w, h);
                return arrangeFF(container, g2, cc);
            }
        }
    }

    /**
     * Arrange with a fixed width and a height within a given range.
     *
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeFN(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        double width = constraint.getWidth() / this.columns;
        jfree.chart.block.RectangleConstraint bc = constraint.toFixedWidth(width);
        List blocks = container.getBlocks();
        double maxH = 0.0;
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                int index = r * this.columns + c;
                if (index >= blocks.size()) {
                    break;
                }
                jfree.chart.block.Block b = (jfree.chart.block.Block) blocks.get(index);
                if (b != null) {
                    Size2D s = b.arrange(g2, bc);
                    maxH = Math.max(maxH, s.getHeight());
                }
            }
        }
        jfree.chart.block.RectangleConstraint cc = constraint.toFixedHeight(maxH * this.rows);
        return arrange(container, g2, cc);
    }

    /**
     * Arrange with a fixed height and no constraint for the width.
     *
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     *
     * @return The size of the arrangement.
     */
    protected Size2D arrangeNF(BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {

        double height = constraint.getHeight() / this.rows;
        jfree.chart.block.RectangleConstraint bc = constraint.toFixedHeight(height);
        List blocks = container.getBlocks();
        double maxW = 0.0;
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                int index = r * this.columns + c;
                if (index >= blocks.size()) {
                    break;
                }
                jfree.chart.block.Block b = (Block) blocks.get(index);
                if (b != null) {
                    Size2D s = b.arrange(g2, bc);
                    maxW = Math.max(maxW, s.getWidth());
                }
            }
        }
        RectangleConstraint cc = constraint.toFixedWidth(maxW * this.columns);
        return arrange(container, g2, cc);
    }

    /**
     * Clears any cached layout information retained by the arrangement.
     */
    @Override
    public void clear() {
        // nothing to clear
    }

    /**
     * Compares this layout manager for equality with an arbitrary object.
     *
     * @param obj  the object.
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof jfree.chart.block.GridArrangement)) {
            return false;
        }
        jfree.chart.block.GridArrangement that = (jfree.chart.block.GridArrangement) obj;
        if (this.columns != that.columns) {
            return false;
        }
        if (this.rows != that.rows) {
            return false;
        }
        return true;
    }

}

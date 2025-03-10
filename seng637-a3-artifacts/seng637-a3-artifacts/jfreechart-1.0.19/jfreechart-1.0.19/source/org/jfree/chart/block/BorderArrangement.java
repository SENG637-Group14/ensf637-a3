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
 * BorderArrangement.java
 * ----------------------
 * (C) Copyright 2004-2013, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 22-Oct-2004 : Version 1 (DG);
 * 08-Feb-2005 : Updated for changes in RectangleConstraint (DG);
 * 24-Feb-2005 : Improved arrangeRR() method (DG);
 * 03-May-2005 : Implemented Serializable and added equals() method (DG);
 * 13-May-2005 : Fixed bugs in the arrange() method (DG);
 * 08-Apr-2008 : Fixed bug in arrangeFF() method where width is too small for
 *               left and right blocks (DG);
 * 21-Nov-2013 : Fixed bug #1084 (DG);
 *
 */

package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import jfree.chart.block.Arrangement;
import jfree.chart.block.Block;
import jfree.chart.block.BlockContainer;
import jfree.chart.block.LengthConstraintType;
import jfree.chart.block.RectangleConstraint;
import jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.Size2D;
import org.jfree.util.ObjectUtilities;

/**
 * An arrangement manager that lays out blocks in a similar way to
 * Swing's BorderLayout class.
 */
public class BorderArrangement implements Arrangement, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = 506071142274883745L;

    /** The block (if any) at the center of the layout. */
    private jfree.chart.block.Block centerBlock;

    /** The block (if any) at the top of the layout. */
    private jfree.chart.block.Block topBlock;

    /** The block (if any) at the bottom of the layout. */
    private jfree.chart.block.Block bottomBlock;

    /** The block (if any) at the left of the layout. */
    private jfree.chart.block.Block leftBlock;

    /** The block (if any) at the right of the layout. */
    private jfree.chart.block.Block rightBlock;

    /**
     * Creates a new instance.
     */
    public BorderArrangement() {
    }

    /**
     * Adds a block to the arrangement manager at the specified edge.
     * If the key is not an instance of {@link RectangleEdge} the block will
     * be added in the center.
     *
     * @param block  the block (<code>null</code> permitted).
     * @param key  the edge (an instance of {@link RectangleEdge}) or
     *             <code>null</code> for the center block.
     */
    @Override
    public void add(Block block, Object key) {

        if (!(key instanceof RectangleEdge)) { // catches null also
            this.centerBlock = block;
        }
        else {
            RectangleEdge edge = (RectangleEdge) key;
            if (edge == RectangleEdge.TOP) {
                this.topBlock = block;
            }
            else if (edge == RectangleEdge.BOTTOM) {
                this.bottomBlock = block;
            }
            else if (edge == RectangleEdge.LEFT) {
                this.leftBlock = block;
            }
            else if (edge == RectangleEdge.RIGHT) {
                this.rightBlock = block;
            }
        }
    }

    /**
     * Arranges the items in the specified container, subject to the given
     * constraint.
     *
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     *
     * @return The block size.
     */
    @Override
    public Size2D arrange(jfree.chart.block.BlockContainer container, Graphics2D g2,
                          jfree.chart.block.RectangleConstraint constraint) {
        jfree.chart.block.RectangleConstraint contentConstraint
                = container.toContentConstraint(constraint);
        Size2D contentSize = null;
        jfree.chart.block.LengthConstraintType w = contentConstraint.getWidthConstraintType();
        jfree.chart.block.LengthConstraintType h = contentConstraint.getHeightConstraintType();
        if (w == jfree.chart.block.LengthConstraintType.NONE) {
            if (h == jfree.chart.block.LengthConstraintType.NONE) {
                contentSize = arrangeNN(container, g2);
            }
            else if (h == jfree.chart.block.LengthConstraintType.FIXED) {
                throw new RuntimeException("Not implemented.");
            }
            else if (h == jfree.chart.block.LengthConstraintType.RANGE) {
                throw new RuntimeException("Not implemented.");
            }
        }
        else if (w == jfree.chart.block.LengthConstraintType.FIXED) {
            if (h == jfree.chart.block.LengthConstraintType.NONE) {
                contentSize = arrangeFN(container, g2, constraint.getWidth());
            }
            else if (h == jfree.chart.block.LengthConstraintType.FIXED) {
                contentSize = arrangeFF(container, g2, constraint);
            }
            else if (h == jfree.chart.block.LengthConstraintType.RANGE) {
                contentSize = arrangeFR(container, g2, constraint);
            }
        }
        else if (w == jfree.chart.block.LengthConstraintType.RANGE) {
            if (h == jfree.chart.block.LengthConstraintType.NONE) {
                throw new RuntimeException("Not implemented.");
            }
            else if (h == jfree.chart.block.LengthConstraintType.FIXED) {
                throw new RuntimeException("Not implemented.");
            }
            else if (h == jfree.chart.block.LengthConstraintType.RANGE) {
                contentSize = arrangeRR(container, constraint.getWidthRange(),
                        constraint.getHeightRange(), g2);
            }
        }
        assert contentSize != null; 
        return new Size2D(container.calculateTotalWidth(contentSize.getWidth()),
                container.calculateTotalHeight(contentSize.getHeight()));
    }

    /**
     * Performs an arrangement without constraints.
     *
     * @param container  the container.
     * @param g2  the graphics device.
     *
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeNN(jfree.chart.block.BlockContainer container, Graphics2D g2) {
        double[] w = new double[5];
        double[] h = new double[5];
        if (this.topBlock != null) {
            Size2D size = this.topBlock.arrange(g2, jfree.chart.block.RectangleConstraint.NONE);
            w[0] = size.width;
            h[0] = size.height;
        }
        if (this.bottomBlock != null) {
            Size2D size = this.bottomBlock.arrange(g2,
                    jfree.chart.block.RectangleConstraint.NONE);
            w[1] = size.width;
            h[1] = size.height;
        }
        if (this.leftBlock != null) {
            Size2D size = this.leftBlock.arrange(g2, jfree.chart.block.RectangleConstraint.NONE);
            w[2] = size.width;
            h[2] = size.height;
       }
        if (this.rightBlock != null) {
            Size2D size = this.rightBlock.arrange(g2, jfree.chart.block.RectangleConstraint.NONE);
            w[3] = size.width;
            h[3] = size.height;
        }

        h[2] = Math.max(h[2], h[3]);
        h[3] = h[2];

        if (this.centerBlock != null) {
            Size2D size = this.centerBlock.arrange(g2,
                    jfree.chart.block.RectangleConstraint.NONE);
            w[4] = size.width;
            h[4] = size.height;
        }
        double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
        double centerHeight = Math.max(h[2], Math.max(h[3], h[4]));
        double height = h[0] + h[1] + centerHeight;
        if (this.topBlock != null) {
            this.topBlock.setBounds(new Rectangle2D.Double(0.0, 0.0, width,
                    h[0]));
        }
        if (this.bottomBlock != null) {
            this.bottomBlock.setBounds(new Rectangle2D.Double(0.0,
                    height - h[1], width, h[1]));
        }
        if (this.leftBlock != null) {
            this.leftBlock.setBounds(new Rectangle2D.Double(0.0, h[0], w[2],
                    centerHeight));
        }
        if (this.rightBlock != null) {
            this.rightBlock.setBounds(new Rectangle2D.Double(width - w[3],
                    h[0], w[3], centerHeight));
        }

        if (this.centerBlock != null) {
            this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0],
                    width - w[2] - w[3], centerHeight));
        }
        return new Size2D(width, height);
    }

    /**
     * Performs an arrangement with a fixed width and a range for the height.
     *
     * @param container  the container.
     * @param g2  the graphics device.
     * @param constraint  the constraint.
     *
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeFR(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {
        Size2D size1 = arrangeFN(container, g2, constraint.getWidth());
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
     * Arranges the container width a fixed width and no constraint on the
     * height.
     *
     * @param container  the container.
     * @param g2  the graphics device.
     * @param width  the fixed width.
     *
     * @return The container size after arranging the contents.
     */
    protected Size2D arrangeFN(jfree.chart.block.BlockContainer container, Graphics2D g2,
                               double width) {
        double[] w = new double[5];
        double[] h = new double[5];
        jfree.chart.block.RectangleConstraint c1 = new jfree.chart.block.RectangleConstraint(width, null,
                jfree.chart.block.LengthConstraintType.FIXED, 0.0, null,
                jfree.chart.block.LengthConstraintType.NONE);
        if (this.topBlock != null) {
            Size2D size = this.topBlock.arrange(g2, c1);
            w[0] = size.width;
            h[0] = size.height;
        }
        if (this.bottomBlock != null) {
            Size2D size = this.bottomBlock.arrange(g2, c1);
            w[1] = size.width;
            h[1] = size.height;
        }
        jfree.chart.block.RectangleConstraint c2 = new jfree.chart.block.RectangleConstraint(0.0,
                new Range(0.0, width), jfree.chart.block.LengthConstraintType.RANGE,
                0.0, null, jfree.chart.block.LengthConstraintType.NONE);
        if (this.leftBlock != null) {
            Size2D size = this.leftBlock.arrange(g2, c2);
            w[2] = size.width;
            h[2] = size.height;
        }
        if (this.rightBlock != null) {
            double maxW = Math.max(width - w[2], 0.0);
            jfree.chart.block.RectangleConstraint c3 = new jfree.chart.block.RectangleConstraint(0.0,
                    new Range(Math.min(w[2], maxW), maxW),
                    jfree.chart.block.LengthConstraintType.RANGE, 0.0, null,
                    jfree.chart.block.LengthConstraintType.NONE);
            Size2D size = this.rightBlock.arrange(g2, c3);
            w[3] = size.width;
            h[3] = size.height;
        }

        h[2] = Math.max(h[2], h[3]);
        h[3] = h[2];

        if (this.centerBlock != null) {
            jfree.chart.block.RectangleConstraint c4 = new jfree.chart.block.RectangleConstraint(width - w[2]
                    - w[3], null, jfree.chart.block.LengthConstraintType.FIXED, 0.0, null,
                    jfree.chart.block.LengthConstraintType.NONE);
            Size2D size = this.centerBlock.arrange(g2, c4);
            w[4] = size.width;
            h[4] = size.height;
        }
        double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
        return arrange(container, g2, new jfree.chart.block.RectangleConstraint(width, height));
    }

    /**
     * Performs an arrangement with range constraints on both the vertical
     * and horizontal sides.
     *
     * @param container  the container.
     * @param widthRange  the allowable range for the container width.
     * @param heightRange  the allowable range for the container height.
     * @param g2  the graphics device.
     *
     * @return The container size.
     */
    protected Size2D arrangeRR(jfree.chart.block.BlockContainer container,
                               Range widthRange, Range heightRange,
                               Graphics2D g2) {
        double[] w = new double[5];
        double[] h = new double[5];
        if (this.topBlock != null) {
            jfree.chart.block.RectangleConstraint c1 = new jfree.chart.block.RectangleConstraint(widthRange,
                    heightRange);
            Size2D size = this.topBlock.arrange(g2, c1);
            w[0] = size.width;
            h[0] = size.height;
        }
        if (this.bottomBlock != null) {
            Range heightRange2 = Range.shift(heightRange, -h[0], false);
            jfree.chart.block.RectangleConstraint c2 = new jfree.chart.block.RectangleConstraint(widthRange,
                    heightRange2);
            Size2D size = this.bottomBlock.arrange(g2, c2);
            w[1] = size.width;
            h[1] = size.height;
        }
        Range heightRange3 = Range.shift(heightRange, -(h[0] + h[1]));
        if (this.leftBlock != null) {
            jfree.chart.block.RectangleConstraint c3 = new jfree.chart.block.RectangleConstraint(widthRange,
                    heightRange3);
            Size2D size = this.leftBlock.arrange(g2, c3);
            w[2] = size.width;
            h[2] = size.height;
        }
        Range widthRange2 = Range.shift(widthRange, -w[2], false);
        if (this.rightBlock != null) {
            jfree.chart.block.RectangleConstraint c4 = new jfree.chart.block.RectangleConstraint(widthRange2,
                    heightRange3);
            Size2D size = this.rightBlock.arrange(g2, c4);
            w[3] = size.width;
            h[3] = size.height;
        }

        h[2] = Math.max(h[2], h[3]);
        h[3] = h[2];
        Range widthRange3 = Range.shift(widthRange, -(w[2] + w[3]), false);
        if (this.centerBlock != null) {
            jfree.chart.block.RectangleConstraint c5 = new jfree.chart.block.RectangleConstraint(widthRange3,
                    heightRange3);
            Size2D size = this.centerBlock.arrange(g2, c5);
            w[4] = size.width;
            h[4] = size.height;
        }
        double width = Math.max(w[0], Math.max(w[1], w[2] + w[4] + w[3]));
        double height = h[0] + h[1] + Math.max(h[2], Math.max(h[3], h[4]));
        if (this.topBlock != null) {
            this.topBlock.setBounds(new Rectangle2D.Double(0.0, 0.0, width,
                    h[0]));
        }
        if (this.bottomBlock != null) {
            this.bottomBlock.setBounds(new Rectangle2D.Double(0.0,
                    height - h[1], width, h[1]));
        }
        if (this.leftBlock != null) {
            this.leftBlock.setBounds(new Rectangle2D.Double(0.0, h[0], w[2],
                    h[2]));
        }
        if (this.rightBlock != null) {
            this.rightBlock.setBounds(new Rectangle2D.Double(width - w[3],
                    h[0], w[3], h[3]));
        }

        if (this.centerBlock != null) {
            this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0],
                    width - w[2] - w[3], height - h[0] - h[1]));
        }
        return new Size2D(width, height);
    }

    /**
     * Arranges the items within a container.
     *
     * @param container  the container.
     * @param constraint  the constraint.
     * @param g2  the graphics device.
     *
     * @return The container size after the arrangement.
     */
    protected Size2D arrangeFF(BlockContainer container, Graphics2D g2,
                               jfree.chart.block.RectangleConstraint constraint) {
        double[] w = new double[5];
        double[] h = new double[5];
        w[0] = constraint.getWidth();
        if (this.topBlock != null) {
            jfree.chart.block.RectangleConstraint c1 = new jfree.chart.block.RectangleConstraint(w[0], null,
                    jfree.chart.block.LengthConstraintType.FIXED, 0.0,
                    new Range(0.0, constraint.getHeight()),
                    jfree.chart.block.LengthConstraintType.RANGE);
            Size2D size = this.topBlock.arrange(g2, c1);
            h[0] = size.height;
        }
        w[1] = w[0];
        if (this.bottomBlock != null) {
            jfree.chart.block.RectangleConstraint c2 = new jfree.chart.block.RectangleConstraint(w[0], null,
                    jfree.chart.block.LengthConstraintType.FIXED, 0.0, new Range(0.0,
                    constraint.getHeight() - h[0]), jfree.chart.block.LengthConstraintType.RANGE);
            Size2D size = this.bottomBlock.arrange(g2, c2);
            h[1] = size.height;
        }
        h[2] = constraint.getHeight() - h[1] - h[0];
        if (this.leftBlock != null) {
            jfree.chart.block.RectangleConstraint c3 = new jfree.chart.block.RectangleConstraint(0.0,
                    new Range(0.0, constraint.getWidth()),
                    jfree.chart.block.LengthConstraintType.RANGE, h[2], null,
                    jfree.chart.block.LengthConstraintType.FIXED);
            Size2D size = this.leftBlock.arrange(g2, c3);
            w[2] = size.width;
        }
        h[3] = h[2];
        if (this.rightBlock != null) {
            jfree.chart.block.RectangleConstraint c4 = new jfree.chart.block.RectangleConstraint(0.0,
                    new Range(0.0, Math.max(constraint.getWidth() - w[2], 0.0)),
                    jfree.chart.block.LengthConstraintType.RANGE, h[2], null,
                    LengthConstraintType.FIXED);
            Size2D size = this.rightBlock.arrange(g2, c4);
            w[3] = size.width;
        }
        h[4] = h[2];
        w[4] = constraint.getWidth() - w[3] - w[2];
        jfree.chart.block.RectangleConstraint c5 = new RectangleConstraint(w[4], h[4]);
        if (this.centerBlock != null) {
            this.centerBlock.arrange(g2, c5);
        }

        if (this.topBlock != null) {
            this.topBlock.setBounds(new Rectangle2D.Double(0.0, 0.0, w[0],
                    h[0]));
        }
        if (this.bottomBlock != null) {
            this.bottomBlock.setBounds(new Rectangle2D.Double(0.0, h[0] + h[2],
                    w[1], h[1]));
        }
        if (this.leftBlock != null) {
            this.leftBlock.setBounds(new Rectangle2D.Double(0.0, h[0], w[2],
                    h[2]));
        }
        if (this.rightBlock != null) {
            this.rightBlock.setBounds(new Rectangle2D.Double(w[2] + w[4], h[0],
                    w[3], h[3]));
        }
        if (this.centerBlock != null) {
            this.centerBlock.setBounds(new Rectangle2D.Double(w[2], h[0], w[4],
                    h[4]));
        }
        return new Size2D(constraint.getWidth(), constraint.getHeight());
    }

    /**
     * Clears the layout.
     */
    @Override
    public void clear() {
        this.centerBlock = null;
        this.topBlock = null;
        this.bottomBlock = null;
        this.leftBlock = null;
        this.rightBlock = null;
    }

    /**
     * Tests this arrangement for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof jfree.chart.block.BorderArrangement)) {
            return false;
        }
        jfree.chart.block.BorderArrangement that = (jfree.chart.block.BorderArrangement) obj;
        if (!ObjectUtilities.equal(this.topBlock, that.topBlock)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.bottomBlock, that.bottomBlock)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.leftBlock, that.leftBlock)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.rightBlock, that.rightBlock)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.centerBlock, that.centerBlock)) {
            return false;
        }
        return true;
    }
}

/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * A class that draw line implementing interface Shape tool.
 * @author tvo93
 * @version November
 */
public class Line implements ShapeTools {
    
    /**
     * Create Line.
     */
    private Line2D myLine;
    
    /**
     * The x coordinate.
     */
    private int myXCoor;
    
    /**
     * The coordinate.
     */
    private int myYCoor;

    @Override
    public Shape setStartPoint(final Point thePoint) {
        myXCoor = thePoint.x;
        myYCoor = thePoint.y;
        myLine = new Line2D.Double(myXCoor, myYCoor, myXCoor, myYCoor);
        return myLine;
    }

    @Override
    public Shape setEndPoint(final Point thePoint) {
        myLine.setLine(myXCoor, myYCoor, thePoint.x, thePoint.y);       
        return myLine;
    }
   
}

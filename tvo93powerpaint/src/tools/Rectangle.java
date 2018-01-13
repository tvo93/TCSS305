/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A class that draw rectangle implementing interface Shape tool.
 * @author tvo93
 * @version November
 */
public class Rectangle implements ShapeTools {
    
    /**
     * create rectangle.
     */
    private Rectangle2D.Double myRectangle = new Rectangle2D.Double(); 
    
    /**
     * The x coordinate.
     */
    private int myX;
    
    /**
     * the y coordinate.
     */
    private int myY;
    
    @Override
    public Shape setStartPoint(final Point thePoint) {
        myX = thePoint.x;
        myY = thePoint.y;      
        myRectangle = (Rectangle2D.Double) myRectangle.clone();
        myRectangle.setFrameFromDiagonal(myX, myY, thePoint.x, thePoint.y);     
        return myRectangle;
    }
    
    @Override
    public Shape setEndPoint(final Point thePoint) {
        myRectangle.setFrameFromDiagonal(myX, myY, thePoint.x, thePoint.y);  
        return myRectangle;
    }

}

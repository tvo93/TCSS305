/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Ellipse tool that implements interface Shape tool.
 * @author tvo93
 * @version November
 */
public class Ellipse implements ShapeTools {
   
    /**
     * create an ellipse.
     */
    private Ellipse2D.Double myEllipse = new Ellipse2D.Double();
    
    /**
     * the X coordinate.
     */
    private int myXCoor;
    
    /**
     * The y coordinate.
     */
    private int myYCoor;
   
    @Override
    public Shape setStartPoint(final Point thePoint) {
        myXCoor = thePoint.x;
        myYCoor = thePoint.y;
        myEllipse = (Ellipse2D.Double) myEllipse.clone(); 
      
      
        myEllipse.setFrameFromDiagonal(myXCoor, myYCoor, thePoint.x, thePoint.y); 
        return myEllipse;
    }
   
    @Override
    public Shape setEndPoint(final Point thePoint) {
        myEllipse.setFrameFromDiagonal(myXCoor, myYCoor, thePoint.x, thePoint.y);
        return myEllipse;
    }

}

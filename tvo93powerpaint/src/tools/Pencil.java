/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * A class that draw pencil implementing interface Shape tool.
 * @author tvo93
 * @version November
 */
public class Pencil implements ShapeTools {
    
    /**
     *  create pencil.
     */
    private Path2D myPencil;

    @Override
    public Shape setStartPoint(final Point thePoint) {
        myPencil = new Path2D.Double(); 
        myPencil.moveTo(thePoint.x, thePoint.y);     
        return myPencil;
     
    }

    @Override
    public Shape setEndPoint(final Point thePoint) {
        myPencil.lineTo(thePoint.x, thePoint.y);
        return myPencil;
    }
    
    
}

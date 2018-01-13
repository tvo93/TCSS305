/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */


package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * Create interface for all 4 tools.
 * @author tvo93
 * @version 2/20/2016
 */
public interface ShapeTools {
    
    /**
     * Get the start point of the shape.
     * 
     * @param thePoint the start point.
     * @return the start point.
     */
    Shape setStartPoint(final Point thePoint);
  
    /**
     * Get the end point of the shape.
     * 
     * @param thePoint the end point.
     * @return return the the end point.
     */
    Shape setEndPoint(final Point thePoint);
    
}

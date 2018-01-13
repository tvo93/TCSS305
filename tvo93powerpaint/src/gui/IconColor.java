/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */
 
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;

/**
 * A class that draw an circle used as an icon.
 * @author tvo93
 * @version 2/12/2016
 */
public class IconColor implements Icon {
    
    /**
     * The icon height.
     */
    private static final int HEIGHT = 15;
    /**
     * Width of the icon.
     */
    private static final int WIDTH = 15;
    /**
     * Color.
     */
    private final Color myColor;
   
    /**
     * A method that store color.
     * @param theColor color chooser.
     */
    public IconColor(final Color theColor) {
        myColor = theColor; 
        
    }

    @Override
    public int getIconHeight() {
        return HEIGHT;
    }

    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    /**
     * Use graphic 2d to draw icon and set up its color.
     * @param theC the component
     * @param theG the graphics
     * @param theX the x coordinate
     * @param theY the y coordinate
     */
    public void paintIcon(final Component theC,
                          final Graphics theG,
                          final int theX,
                          final int theY) {
        
        final Graphics2D g = (Graphics2D) theG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(myColor);
        g.fill3DRect(theX, theY, HEIGHT, WIDTH, true);
        g.draw3DRect(theX, theY, HEIGHT, WIDTH, true);
       
    }

}

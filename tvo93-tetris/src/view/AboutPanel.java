/*
    * TCSS 305 
    * Assignment 6 - Tetris
    */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * A class that displays the key controls for the game.
 * @author tvo93
 * @version 3/3/2015
 */
public class AboutPanel extends JPanel {

    /** A generated serialization ID. */
    private static final long serialVersionUID = -7690987297957539252L;
     
    /**
     * The font size for text.
     */
    private static final int FONT_SIZE = 15;
    
    /**
     * The scale of string coordinate.
     */
    private static final int SCALE = 3;
    
    /**
     * The size of board panel.
     */
    private static final Dimension SIZE = new Dimension(150, 150);
    
    
    /**
     * Constructs for the class.
     */
    public AboutPanel() {
        super();  
      
        setBackground(Color.BLACK);
        setPreferredSize(SIZE);
       
        // print out all labels
        setApperance();
      
    }
    
    /**
     * A method that sets up and displays components for the panel.
     */
    public final void setApperance() {
        // Title
        final TitledBorder title = new TitledBorder("Control Keys");
        title.setTitleColor(Color.RED);
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);        
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
       
        g.setFont(new Font("", Font.ITALIC, FONT_SIZE));
        g.setColor(Color.WHITE);
        final String left = "* LEFT key: move left";
        final String right = "* RIGHT key: move right";
        final String down = "* DOWN key: move down 1 step";
        final String rotateCW = "* A key: rotate clock wise";
        final String rotateCCW = "* D key: rotate counter clock wise";
        final String pauseResume = "* P/R key: pause/resume the game";
        final String space = "* SPACE key: hard drop";
        
        final int xCoor = 10;
        final int yCoor = 60;
        final String[] list = new String[] {left, right, down
                        , rotateCW, rotateCCW, space, pauseResume};
        for (int i = 0; i < list.length; i++) {
            g.drawString(list[i], xCoor, yCoor + yCoor * i / SCALE);
        }
        
       
    }
}

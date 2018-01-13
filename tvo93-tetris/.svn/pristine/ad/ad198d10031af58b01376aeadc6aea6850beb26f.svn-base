/*
    * TCSS 305 
    * Assignment 6 - Tetris
    */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;

import model.Block;
import model.Board;
import model.Point;




/**
 * Board Panel to display the Board game on the panel.
 * @author tvo93
 * @version 3/2/2016
 */
public class TetrisBoardPanel extends JPanel implements Observer {

  
    /** A generated serialization ID. */
    private static final long serialVersionUID = 1L;
    
    /**
     * The scale for drawing grid.
     */
    private static final int SCALE = 10;
    
    /**
     * The hide color.
     */
    private final Color myHidenColor =  new Color(0, 0, 0, 200);
   
    /**
     * Tetris block lists.
     */
    private final List<Block[]> myTetrisBlocks;
    
    /**
     * check if game is over.
     */
    private boolean myGameOverIsViewable;
    
    /** 
     * A flag to indicate True if End Game is being drawn.
     */
    private boolean myPauseIsViewable;
    
    /**
     *  A flag to indicate true if random color is choosen.
     */
    private boolean myColorMode;
   
    /** 
     * A flag to indicate True if rectangle is being drawn.
     */
    private boolean myRectIsViewable;
    
    /** 
     * A flag to indicate True if triangle is being drawn.
     */
    private boolean myTriIsViewable;
    
    /** 
     * A flag to indicate True if oval is being drawn.
     */
    private boolean myOvalIsViewable;
    
    /** 
     * A flag to indicate True if smile is being drawn.
     */
    private boolean mySmileIsViewable;
    
    /** A flag to indicate True if Grid is being drawn.*/
    private boolean myGridIsViewable;
    
    /** The size that will be used for string message. */
    private int myFontSize;
    
    /** The grid scale. */
    private int myGridScale;
    
   
    /**
     * Constructs the BoardPanel.
     */
    public TetrisBoardPanel() {
        super();       
        myTetrisBlocks = new ArrayList<Block[]>();      
        setBackground(Color.BLACK);     
        setVisible(true);    
        
       
    }

    /**
     * Repaints the component with the specified graphics context.
     * Draw the pieces.
     * @param theGraphics the graphic context
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);   
        
        final int scale = getWidth() / 10;
        Color color = null;
        // chose different color if color mode is choosen
        if (myColorMode) {
            color = randomColor();
        } else {
            color = Color.GREEN;
        }
        
        // draw different shapes
        if (myRectIsViewable) {
            drawRectShape(g, scale, color);
        } else if (myTriIsViewable) {
            drawTriShape(g, scale, color);
        } else if (myOvalIsViewable) {
            drawOvalShape(g, scale, color);
        } else if (mySmileIsViewable) {
            drawSmileShape(g, scale, color);
        }
        
        // draw grid
        if (myGridIsViewable) {         
            drawGridPanel(g);                  
        }  
      
        // draw Strings
        if (myGameOverIsViewable) {       
            drawMessage(g, scale, "GAME OVER");
        } else if (myPauseIsViewable) {
            drawMessage(g, scale, "    PAUSED");
        }
    }
   
    /**
     * Repaints the component with the specified graphics context.
     * Draw the pieces.
     * @param theGraphics the graphic context
     */
    public void paintColorMode(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        final int scale = getWidth() / 10;
        if (myColorMode) {
            if (myRectIsViewable) {
                drawRectShape(g, scale, randomColor());
            } else if (myTriIsViewable) {
                drawTriShape(g, scale, randomColor());
            } else if (myOvalIsViewable) {
                drawOvalShape(g, scale, randomColor());
            } else {
                drawSmileShape(g, scale, randomColor());
            }
        }
    }
    
    /**
     * Draw rectangle shape to display for blocks.
     * @param theGraphics the graphics
     * @param theScale the scale
     * @param theColor the color 
     */
    public void drawRectShape(final Graphics theGraphics, 
                              final int theScale, final Color theColor) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        for (int i = 0; i < myTetrisBlocks.size(); i++) {         
            final Block[] blockArray = myTetrisBlocks.get(i);        
            for (int j = 0; j < blockArray.length; j++) {
               
                final Point point = new Point(j *  theScale
                                        , i *  theScale);             
                if (blockArray[j] != null) {
                    g.setColor(theColor);
             
                    g.fillRoundRect(point.x(), getHeight() - point.y() - theScale, 
                                    theScale,  theScale, theScale / 2,  theScale / 2);

                }      
                
            }
        }
    }
   
    /**
     * Draw triangle shape to display for blocks.
     * @param theGraphics the graphics
     * @param theScale the scale
     * @param theColor the color 
     */
    public void drawTriShape(final Graphics theGraphics, 
                              final int theScale, final Color theColor) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        for (int i = 0; i < myTetrisBlocks.size(); i++) {         
            final Block[] blockArray = myTetrisBlocks.get(i);        
            for (int j = 0; j < blockArray.length; j++) {
               
                final Point point = new Point(j *  theScale
                                        , i *  theScale);             
                if (blockArray[j] != null) {
                    g.setColor(theColor);  
                    final Path2D.Double triangle = new Path2D.Double();
                    triangle.moveTo(point.x() + theScale / 2, getHeight() 
                                    - point.y() - theScale);
                    triangle.lineTo(point.x(), getHeight() - point.y());
                    triangle.lineTo(point.x() + theScale, getHeight() - point.y());
                    triangle.closePath();
                    g.fill(triangle);
                  
                   
                  
                    

                }      
                
            }
        }
    }
   
    /**
     * Draw ellipse shape to display for blocks.
     * @param theGraphics the graphics
     * @param theScale the scale
     * @param theColor the color 
     */
    public void drawOvalShape(final Graphics theGraphics, 
                              final int theScale, final Color theColor) {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        for (int i = 0; i < myTetrisBlocks.size(); i++) {         
            final Block[] blockArray = myTetrisBlocks.get(i);        
            for (int j = 0; j < blockArray.length; j++) {
               
                final Point point = new Point(j *  theScale
                                        , i *  theScale);             
                if (blockArray[j] != null) {
                    g.setColor(theColor);
                    g.fillRoundRect(point.x(), getHeight() - point.y() - theScale, 
                                    theScale,  theScale, theScale,  theScale);
                    
                    

                }      
                
            }
        }
    }
    
    /**
     * Draw Smile shape to display for blocks.
     * @param theGraphics the graphics
     * @param theScale the scale
     * @param theColor the color 
     */
    public void drawSmileShape(final Graphics theGraphics, 
                              final int theScale, final Color theColor) {     
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;
        
        //local instance fields
        final int scale1 = 3;
        final int scale2 = 4;
        final int scale3 = 5;
        
        for (int i = 0; i < myTetrisBlocks.size(); i++) {     
            final Block[] blockArray = myTetrisBlocks.get(i);       
            for (int j = 0; j < blockArray.length; j++) {
                final Point point = new Point(j *  theScale
                                              , i *  theScale);             
                if (blockArray[j] != null) {
                    g.setColor(Color.RED);
                    g.setStroke(new BasicStroke(2));
                    g.drawOval(point.x(), getHeight() - point.y() - theScale, 
                                    theScale,  theScale);
                    g.setColor(theColor);
                    g.fillOval(point.x() + scale1 * theScale / (scale2 * scale3), 
                               getHeight() - point.y() 
                               - theScale + theScale / scale2, 
                               theScale / scale3,  theScale / scale3);
                    g.fillOval(point.x() + (scale3 * 2 + scale1) * theScale 
                               / (scale2 * scale3), getHeight() - point.y() 
                               - theScale + theScale / scale2,
                               theScale / scale3,  theScale / scale3);

                    g.drawArc(point.x() + theScale / scale2, getHeight() - point.y() 
                              - theScale + theScale / scale1,  theScale / 2,  theScale / 2,
                        scale1 * scale1 * scale2 * scale3, scale1 * scale1 * scale2 * scale3);
                    repaint();
                }
  
            }
        }
    }
   
   /**
    * Random color.  
    * @return color
    */
    public Color randomColor() {
        final Random rand = new Random();
        final int r = rand.nextInt(255);
        final int g = rand.nextInt(255);
        final int b = rand.nextInt(255);
        
        return new Color(r, g, b).brighter();
    }
    
    
    /**
     * Draw end game.
     * @param theGraphics the graphics 
     * @param theScale the scale
     * @param theString the string message
     */
    public void drawMessage(final Graphics2D theGraphics, 
                             final int theScale, final String theString) {
        final Graphics2D g = theGraphics;
        g.setPaint(myHidenColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setPaint(Color.RED);
        g.setFont(new Font("Osaka", Font.BOLD, getFontSize()));
        g.drawString(theString, getWidth() / theScale + theScale, 
                     getHeight() / 2);
       
    }
    

    
    /**
     * Set game over.
     * @param theOver the game over
     */
    public void setGameOver(final boolean theOver) {
        myGameOverIsViewable = theOver;
    }
    
    /**
     * Set end game.
     * @param theE the end game
     */
    public void setPause(final boolean theE) {
        myPauseIsViewable = theE;
    }
    
    /**
     * Set Grid.
     * @param theG the grid
     */
    public void setGrid(final boolean theG) {
        myGridIsViewable = theG;
    }
    
    
    /**
     * get font size for the string message.
     * @return font size
     */
    public int getFontSize() {
        return myFontSize;
    }
    
    /**
     * set up the font size for the string message.
     * @param theFontSize the font size value
     */
    public void setFontSize(final int theFontSize) {
        myFontSize = theFontSize;
    }
    
    /**
     * get scale for grid.
     * @return the grid scale
     */
    public int getGridScale() {
        return myGridScale;
    }
    
    /**
     * Set color for blocks.
     * @param theColor the color
     */
    public void setColorMode(final boolean theColor) {
        myColorMode = theColor;
    }
   
    /**
     * get random color.
     * @return color
     */
    public boolean isModeColor() {
        return myColorMode;
    }
    /**
     * Set the grid scale.
     * @param theGridScale the grid scale
     */
    public void setGridScale(final int theGridScale) {
        myGridScale = theGridScale;
    }
    
    /**
     * Display the board by rectangle shape.
     * @param theRect the rectangle
     */
    public void setRect(final boolean theRect) {
        myRectIsViewable = theRect;
       
    }
    
    /**
     * Display the board by triangle shape.
     * @param theTri the triangle
     */
    public void setTriangle(final boolean theTri) {
        myTriIsViewable = theTri;
       
    }
    
    /**
     * Display the board by oval shape.
     * @param theO the oval
     */
    public void setOval(final boolean theO) {
        myOvalIsViewable = theO;
       
    }
    
    /**
     * Display the board by smile shape.
     * @param theS the smile
     */
    public void setSmile(final boolean theS) {
        mySmileIsViewable = theS;
       
    }
    
    
    /**
     * True if Oval is selected or otherwise.
     * @return oval shape is view able or not
     */
    public boolean isOval() {
        return myOvalIsViewable;
    }
    
    /**
     * True if Rectangle is selected or otherwise.
     * @return rectangle shape is view able or not
     */
    public boolean isRect() {
        return myRectIsViewable;
    }
    
    /**
     * True if Triangle is selected or otherwise.
     * @return triangle shape is view able or not
     */
    public boolean isTri() {
        return myTriIsViewable;
    }
    
    /**
     * True if smile is selected or otherwise.
     * @return smile shape is view able or not
     */
    public boolean isSmile() {
        return mySmileIsViewable;
    }
    
    /**
     * Display Grid in panel.
     * @param theGraphics the graphic
     */
    public void drawGridPanel(final Graphics2D theGraphics) {    
       
        final Graphics2D g = (Graphics2D) theGraphics;
        g.setColor(Color.GRAY);
        final Line2D.Double line = new Line2D.Double();
        g.setStroke(new BasicStroke(1f));
        
     // use loop to draw the horizontal lines
        for (int i = 0; i < getWidth(); i += getWidth() / SCALE) {
            line.setLine(i,  0,  i , getHeight());
            g.draw(line);
     
        }
            
        // use loop to draw the vertical lines
        for (int i = 0; i < getHeight(); i += getWidth() / SCALE) {
            line.setLine(0,  i , getWidth(), i);
            g.draw(line);
          
        }
            
    }
    
    @Override
    public void update(final Observable theObservable, final Object theData) {
        
        if (theObservable instanceof Board && theData instanceof List) {
            myTetrisBlocks.clear();           
            for (int i = 0; i < ((List<?>) theData).size(); i++) {
                final Block[] blockArray = (Block[]) ((List<?>) theData).get(i);
                myTetrisBlocks.add(blockArray);
            }
        }
        
        repaint(); 
    }
    
}


/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package gui;

import action.EllipseAction;
import action.LineAction;
import action.PencilAction;
import action.RectangleAction;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;



import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import tools.Ellipse;
import tools.Line;
import tools.Pencil;
import tools.Rectangle;
import tools.ShapeTools;






/**
 * The graphical user interface for the PowerPaint program.
 * @author tvo93
 * @version 2/10/2016
 */
public class PowerPaintGUI {

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    
    /**
     * The Panel for the GUI.
     */
    private final PowerPaintPanel myPanel;
    /**
     * The frame for the program.
     */
    private final JFrame myFrame;
    
    /**
     * Constructs for the GUI.
     */
    public PowerPaintGUI() {
        super();
        myFrame = new JFrame("PowerPaint");
        myPanel = new PowerPaintPanel();
        
            
        // position the frame in the center of the screen
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                            SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);   
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setPreferredSize(myPanel.getPreferredSize());
        myFrame.pack();
    }
    
    /**
     * Start the GUI.
     */
    public void start() {
        
        // create menu bar
        final MenuBar menuBar = new MenuBar(myFrame, myPanel);
        myFrame.add(menuBar);
        myFrame.setJMenuBar(menuBar);
        
        // create tool bar
        final ToolBar toolBar = new ToolBar();
        
        
        // create Icons
        final ImageIcon pencilIcon = new ImageIcon("images/pencil.gif");
        final ImageIcon lineIcon = new ImageIcon("images/line.gif"); 
        final ImageIcon rectangleIcon = new ImageIcon("images/rectangle.gif"); 
        final ImageIcon ellipseIcon = new ImageIcon("images/ellipse.gif");
       
        
        // create 4 tools
        final ShapeTools lineTool = new Line();
        final ShapeTools ellipseTool = new Ellipse();
        final ShapeTools pencilTool = new Pencil();
        final ShapeTools rectangleTool = new Rectangle();
        
        // create actions
        final Action pencil;
        final Action line;
        final Action rectangle;
        final Action ellipse;
       
        pencil = new PencilAction(myPanel, "Pencil",  pencilIcon, 
                                    pencilTool, Integer.valueOf(KeyEvent.VK_P));
        line = new LineAction(myPanel, "Line", lineIcon,
                                lineTool, Integer.valueOf(KeyEvent.VK_L));
        rectangle = new RectangleAction(myPanel, "Rectangle", rectangleIcon, 
                                          rectangleTool, Integer.valueOf(KeyEvent.VK_R));
        ellipse = new EllipseAction(myPanel, "Ellipse", ellipseIcon, 
                                      ellipseTool, Integer.valueOf(KeyEvent.VK_E));
        
        // create action and add in tool bar
        final Action [] toolActions = {pencil, line, rectangle, ellipse};
        
        for (final Action action : toolActions) {
            menuBar.createToolMenuButton(action);
            toolBar.createToolBarButton(action);
        }
        
        // add components to JFrame
        myFrame.add(myPanel, BorderLayout.CENTER);
        myFrame.add(toolBar, BorderLayout.SOUTH);
        
        // add image logo 
        myFrame.setIconImage(new ImageIcon("images/icon.png").getImage());
        
        myFrame.setVisible(true);
        myFrame.pack();
    }
      

}

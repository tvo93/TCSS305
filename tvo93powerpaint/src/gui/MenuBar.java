/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */

package gui;

import action.ColorAction;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * A class that create menu bar for the JFrame.
 * @author tvo93
 * @version 2/10/2016
 */
public class MenuBar extends JMenuBar {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 1L;
    
    /**
     * The initial thickness.
     */
    private static final int INITIAL_THICKNESS = 5;
    
    /**
     * The maximum thickness.
     */
    private static final int MAX_THICKNESS = 20;
    
    /**
     * The minor tick spacing for the FPS slider.
     */
    private static final int MINOR_TICK_SPACING = 1;
    
    /**
     * The major tick spacing for the FPS slider.
     */
    private static final int MAJOR_TICK_SPACING = 5;
    

    /** A button group for radio buttons. */
    private final ButtonGroup myGroup = new ButtonGroup();
   
    /** A Tool menu. */
    private final JMenu myToolMenu = new JMenu("Tools");
    
    /**
     * Color Action.
     */
    private final ColorAction myColorAction;
    
    /**
     * The panel for the GUI.
     */
    private final PowerPaintPanel myPanel;
    
    /**
     * Constructs for the class.
     * @param theFrame the JFrame
     * @param thePanel the panel
     */
    public MenuBar(final JFrame theFrame, final PowerPaintPanel thePanel) {
        super();
        myPanel = thePanel;
        myColorAction =  new ColorAction(myPanel); 
        add(fileMenuBar(theFrame));
        add(optionMenuBar(theFrame));
        add(toolMenuBar(theFrame));
        add(helpMenuBar(theFrame));
    }
    
    /**
     * File menu bar for the frame.
     * @param theFrame the JFrame 
     * @return file menu bar
     */
    public JMenu fileMenuBar(final JFrame theFrame) {
        // create file menu bar
        final JMenu fileMenu = new JMenu("File");
        
        // create 2 menu items
        final JMenuItem undoAll = new JMenuItem("Undo all changes");
        
        final JMenuItem exit = new JMenuItem("Exit");
        
        // add mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        undoAll.setMnemonic(KeyEvent.VK_U);
        exit.setMnemonic(KeyEvent.VK_X);
        
        // add action
        undoAll.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
              
                myPanel.removeShape();
                
                myPanel.repaint();
            }
        });
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
            }
        });
        
        // add menu items to file menu bar
        fileMenu.add(undoAll);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        return fileMenu;       
    }
    
    /**
     * Option menu bar for the frame.
     * @param theFrame the JFrame 
     * @return option menu bar
     */
    public JMenu optionMenuBar(final JFrame theFrame) {
        // create option menu bar
        final JMenu optionMenu = new JMenu("Options");
        
        // create 3 menu items
        final JCheckBoxMenuItem equalShape = new JCheckBoxMenuItem("Square/Circle");
        final JMenu thickness = new JMenu("Thickness");
        final JMenuItem color = new JMenuItem(myColorAction);
        
        // add mnemonics
        optionMenu.setMnemonic(KeyEvent.VK_O);
        equalShape.setMnemonic(KeyEvent.VK_S);
        thickness.setMnemonic(KeyEvent.VK_T);
        color.setMnemonic(KeyEvent.VK_C);
        
        // create JSlider for thickness
        final JSlider slider;
        slider = new JSlider(SwingConstants.HORIZONTAL, 0, MAX_THICKNESS,
                               INITIAL_THICKNESS);
        slider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(MINOR_TICK_SPACING);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        
        // add action for equalShape
        equalShape.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {  
                myPanel.setSquareCircle(equalShape.isSelected()); 
                myPanel.repaint();
            }
        });
        
        // add slider to thickness
        thickness.add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final JSlider thickness = (JSlider) theEvent.getSource();      
                if (!thickness.getValueIsAdjusting()) {
                    final int thicknessValue = thickness.getValue();
                    myPanel.setStroke(thicknessValue);
                }          
                
            }
        });
        
        // add menu items to option menu bar
        optionMenu.add(equalShape);
        optionMenu.addSeparator();
        optionMenu.add(thickness);
        optionMenu.addSeparator();
        optionMenu.add(color);
        
        return optionMenu;
    }
    
    /**
     * tool menu bar for the frame.
     * @param theFrame the JFrame 
     * @return tool menu bar
     */
    public JMenu toolMenuBar(final JFrame theFrame) {
        myToolMenu.setMnemonic(KeyEvent.VK_T);
        return myToolMenu;
        
    }
    
    /**
     * Creates a radio button menu item, associates an action with the button,
     * adds the button to a button group, adds the button to the Tools menu.
     * 
     * @param theAction the Action to associate with the new button being
     *            created
     */
    public void createToolMenuButton(final Action theAction) {
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);
        myGroup.add(createdButton);
        myToolMenu.add(createdButton);  
    }
    
    /**
     * Help menu bar for the frame.
     * @param theFrame the JFrame 
     * @return help menu bar
     */
    public JMenu helpMenuBar(final JFrame theFrame) {
        // create help menu
        final JMenu helpMenu = new JMenu("Help");
        
        // create about menu item
        final JMenuItem about = new JMenuItem("About...");
        
        // add action for About menu item
        about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                
                // create logo and set scale
                final ImageIcon icon = new ImageIcon("images/icon.png");
                final Image img = icon.getImage();
                final Image newImage = img.getScaledInstance(75, 50, 
                                        java.awt.Image.SCALE_SMOOTH);
                final ImageIcon newIcon = new ImageIcon(newImage);
                
                // pop up a dialog message
                JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint\n Spring 2016\n"
                             + "<Thuan Vo>", "About", 
                             JOptionPane.INFORMATION_MESSAGE, newIcon);
            }
        });
        
        // add mnemonic
        helpMenu.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);
        
        helpMenu.add(about);
        
        return helpMenu;
    }
    
  
}

/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */
 

package action;


import gui.IconColor;
import gui.PowerPaintPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;

/**
 * A color action class.
 * @author tvo93
 * @version November 2015
 */
public class ColorAction extends AbstractAction {
    
    /**
     * The generate serial.
     */
    private static final long serialVersionUID = 4157744992377886105L;
    
    /**
     * JPanel.
     */
    private final PowerPaintPanel myPanel;
    
    /**
     * Construct the color action.
     * @param thePanel draw panel
     */
    public ColorAction(final PowerPaintPanel thePanel) {
       
        super("Color...");
        myPanel = thePanel;
      
        putValue(Action.SMALL_ICON, new IconColor(thePanel.getColor()));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C); 
        putValue(Action.SELECTED_KEY, true);
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Color color = JColorChooser.showDialog(null, "Color Chooser", 
                                                     new Color(51, 0, 111));
        
        if (color != null) {
            myPanel.setColor(color);
            putValue(Action.SMALL_ICON, new IconColor(color)); 
            myPanel.repaint();
           
        }    
    } 
    
    
}

/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */
package action;

import gui.PowerPaintPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;


import tools.ShapeTools;



/**
 * pencil action for the GUI.
 * @author tvo93
 * @version 10 October 2015
 */
public class PencilAction  extends AbstractAction implements ActionListener {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 2431495374140330942L;
   
    /** The pencil tool.*/
    private final ShapeTools myPencil;
    
    /**
     * The panel.
     */
    private final PowerPaintPanel myPanel;
  
    /**
   * Construct the pencil action.
   * @param thePanel the Panel for the program
   * @param theName the Name of action
   * @param theIcon the icon of action
   * @param thePencil the shape
   * @param theMnemonic the mnemonic
   */
    public PencilAction(final PowerPaintPanel thePanel, final String theName, 
                      final ImageIcon theIcon,
                      final ShapeTools thePencil,
                      final Integer theMnemonic) {
        super(theName, theIcon);
        myPanel = thePanel;
        myPencil = thePencil;
        putValue(MNEMONIC_KEY, theMnemonic);
        putValue(Action.SELECTED_KEY, false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setShapeTools(myPencil);
    }

}

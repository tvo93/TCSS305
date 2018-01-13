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
 * Line action for the GUI.
 * @author tvo93
 * @version 10 October 2015
 */
public class LineAction  extends AbstractAction implements ActionListener {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 2431495374140330942L;
    /**
     * The panel.
     */
    private final PowerPaintPanel myPanel;
    
    /** The pencil tool.*/
    private final ShapeTools myLine;
    
   
  /**
   * Construct the line action.
   * @param thePanel the Panel.
   * @param theName the Name of action
   * @param theIcon the icon of action
   * @param theLine the shape
   * @param theMnemonic the mnemonic
   */
    public LineAction(final PowerPaintPanel thePanel,
                      final String theName, 
                      final ImageIcon theIcon,
                      final ShapeTools theLine,
                      final Integer theMnemonic) {
        super(theName, theIcon);
        myPanel = thePanel;
        myLine = theLine;
        putValue(MNEMONIC_KEY, theMnemonic);
        putValue(Action.SELECTED_KEY, false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setShapeTools(myLine);
    }

}

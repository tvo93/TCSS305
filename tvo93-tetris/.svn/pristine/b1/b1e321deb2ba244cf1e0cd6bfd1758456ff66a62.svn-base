/*
    * TCSS 305 
    * Assignment 6 - Tetris
    */

package action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.TetrisBoardPanel;
import view.TetrisGUI;

/**
 * Resume Action class that is used to resume  the game.
 * @author tvo93
 * @version 3/2/2016
 */
public class ResumeAction extends KeyAdapter {
    
  /**
   * The GUI.
   */
    private final TetrisGUI myGui;
    
    /**
     * The board panel.
     */
    private final TetrisBoardPanel myBoardPanel;
    
    /** Construct for the resume action class.
     * @param theBoardPanel the Board for the game
     * @param theGUI the gui for the program
     */  
    public ResumeAction(final TetrisBoardPanel theBoardPanel, final TetrisGUI theGUI) {
        super();
        myBoardPanel = theBoardPanel;
        myGui = theGUI;
    }
   
    /**
     * Action listener for resume action when pressed.
     * @param theEvent the event
     */
    public void keyPressed(final KeyEvent theEvent) {
        if (theEvent.getKeyCode() == KeyEvent.VK_R) {
            myGui.setPause(false);
            myGui.pauseGame();
            myBoardPanel.setPause(false);
            myBoardPanel.repaint();
           
        }
    }
  
}


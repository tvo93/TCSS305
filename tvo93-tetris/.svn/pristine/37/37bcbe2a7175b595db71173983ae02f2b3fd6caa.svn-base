/*
    * TCSS 305 
    * Assignment 6 - Tetris
    */

package action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;

/**
 * Key Action class that is used to display to control the game.
 * @author tvo93
 * @version 3/2/2016
 */
public class KeyAction extends KeyAdapter {
    
    /**A board object that includes logics.*/
    private final Board myBoard;

    /** Piece will move if true and false other wise. */
    private boolean myMove; 
    /** Construct for the key action class.
     * @param theBoard the Board for the game
     */  
    public KeyAction(final Board theBoard) {
        super();
        myBoard = theBoard;
    }
    
    /**
     * Set the piece move able.
     * @param theMove the move
     */
    public void setMoveAble(final boolean theMove) {
        myMove = theMove;
    }
   
    /**
     * A method that call moving methods from board class.
     * @param theEvent the event
     */
    public void keyPressed(final KeyEvent theEvent) {
        if (myMove) {
        // Allow user to use key controls to play game
            switch (theEvent.getKeyCode()) {
                // move left
                case KeyEvent.VK_LEFT:
                    myBoard.left();
                    break;
                    // move right
                case KeyEvent.VK_RIGHT:
                    myBoard.right();
                    break;
                    // rotate clock wise
                case KeyEvent.VK_A:
                    myBoard.rotateCW();
                    break;
                    // rotate counter close wise
                case KeyEvent.VK_D:
                    myBoard.rotateCCW();
                    break;
                    // move down 1 step
                case KeyEvent.VK_DOWN:
                    myBoard.down();
                    break;
                    // hard drop
                case KeyEvent.VK_SPACE:
                    myBoard.drop();
                    break;
   
                default:
                    break;
            }
        }
            
    }
  
}


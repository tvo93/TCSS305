/*
    * TCSS 305 
    * Assignment 6 - Tetris
    */

package view;

import action.KeyAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Timer;


import model.Board;


/**
 * A class that creates menu bar for the frame.
 * @author tvo93
 * @version 2/26/2016
 */
public class MenuBar extends JMenuBar implements PropertyChangeListener {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 1L;

    /** The delay (in milliseconds) for default level. */
    private static final int EASY = 400;
    /** The delay (in milliseconds) for middle level. */
    private static final int MIDDLE = 300;
    /** The delay (in milliseconds) for hard level. */
    private static final int HARD = 200;
    
    /**
     * easy string.
     */
    private static final String EASY_STRING = "easy";
 
    /**
     * middle string.
     */
    private static final String MIDDLE_STRING = "middle";
    /**
     * hard string.
     */
    private static final String HARD_STRING = "hard";
    /**
     * A menu item for new game.
     */
    private final JMenuItem myNewGame = new JMenuItem("New Game");
    
    /**
     * A menu item for end game.
     */
    private final JMenuItem myEndGame = new JMenuItem("End Game");
    
    /**
     * UW color.
     */
    private final Color myMenuUWColor =  new Color(51, 0, 111);
    
    /**
     * Board panel.
     */
    private final TetrisBoardPanel myBoardPanel;
    /**
     * Easy radio button.
     */
    private final JRadioButtonMenuItem myEasy = new JRadioButtonMenuItem("Easy");   
    /** 
     * A board.
     */
    private final Board myBoard;
    
    /** The timer that controls the movement of blocks. */
    private final Timer myMoveTimer;
    
    /**
     * Score panel.
     */
    private final ScorePanel myScorePanel;
    /**
     * The GUI for program.
     */
    private final TetrisGUI myGui;
  
    /**
     * the Key to move tetris on the board.
     */
    private final KeyAction myKeyAction;
    
    /**
     * Constructs for the menu bar.
     * @param theGui the gui
     * @param theFrame the JFrame
     * @param theBoardPanel the Board Panel
     * @param theBoard the Board
     * @param theScorePanel the score panel
     * @param theTimer the timer
     * @param theKey the key 
     */
    public MenuBar(final TetrisGUI theGui, final JFrame theFrame, 
                   final TetrisBoardPanel theBoardPanel
                   , final Board theBoard, final ScorePanel theScorePanel
                   , final Timer theTimer, final KeyAction theKey) {
        super();   
        myBoardPanel = theBoardPanel;
        myBoard = theBoard;
        myScorePanel = theScorePanel;
        myMoveTimer = theTimer;
        myGui = theGui;
        myKeyAction = theKey;
        start(theFrame);
        this.addPropertyChangeListener(this);
    }
    
    /**
     * Start the menu.
     * @param theFrame the frame
     */
    public void start(final JFrame theFrame) {
        add(createFileMenu(theFrame));
        add(createOptionMenu());
        add(createShapeMenu());
        add(createLevelMenu());
        add(createHelpMenu());
    }
    /**
     * Create file menu.
     * @return file menu
     * @param theFrame the frame
     */
    public final JMenu createFileMenu(final JFrame theFrame) {

        // set up quit option
        final JMenuItem quitButton = new JMenuItem("Quit");
        quitButton.setMnemonic(KeyEvent.VK_Q);
        quitButton.setAccelerator(KeyStroke.getKeyStroke('Q', KeyEvent.CTRL_MASK));
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
            }
        });
        
        // Add menu items in file menu
        final JMenu menuFile = new JMenu("File");
        menuFile.setForeground(myMenuUWColor);
        
        // set mnemonic to menu items
        menuFile.setMnemonic(KeyEvent.VK_F);
        myNewGame.setMnemonic(KeyEvent.VK_N);
        myEndGame.setMnemonic(KeyEvent.VK_E);
        
        // add menu items to menu file
        menuFile.add(newGameMenuItem());
        menuFile.addSeparator();
        menuFile.add(endGameMenuItem());
        menuFile.addSeparator();
        menuFile.add(quitButton);
        
        // set color for background and foreground 
        for (final Component c : menuFile.getMenuComponents()) {
            setBackForeGround(c);
        }    
        // get all file menu item here
        return menuFile;      
    }
    
    /**
     * Create help menu.
     * @return help menu
     */
    public final JMenu createHelpMenu() {
        // create help menu
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(myMenuUWColor);
        helpMenu.setMnemonic(KeyEvent.VK_H);
       
        // about menu item
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        about.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_MASK));
        final ImageIcon icon = new ImageIcon("label.png");
        about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myBoardPanel, gameInstructions(), 
                                              "About", JOptionPane.OK_OPTION, icon);
            }  
        });
        
     // Score and rule menu item
        final JMenuItem scoreRule = new JMenuItem("Score...");
        scoreRule.setMnemonic(KeyEvent.VK_S);
        scoreRule.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        scoreRule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // show message dialog
                JOptionPane.showMessageDialog(null,  scoreAndRule(),
                                        "Score And Rule", JOptionPane.YES_NO_OPTION);     
            }          
        });
        
        
        helpMenu.add(about);
        helpMenu.addSeparator();
        helpMenu.add(scoreRule);
        // set color for background and foreground 
        for (final Component c : helpMenu.getMenuComponents()) {
            setBackForeGround(c);
        }    
        return helpMenu;
    }
    
    /**
     * Create options menu.
     * @return option menu
     */
    public final JMenu createOptionMenu() {
        // create option menu
        final JMenu optionMenu = new JMenu("Options");
        optionMenu.setForeground(myMenuUWColor);
        optionMenu.setMnemonic(KeyEvent.VK_O);
        
        // create group buttons
        final ButtonGroup group = new ButtonGroup();
        
        // add radio buttons
        final JRadioButtonMenuItem small = new JRadioButtonMenuItem("Small");   
        final JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium");   
        final JRadioButtonMenuItem large = new JRadioButtonMenuItem("Large");
        
        // default size selected
        medium.setSelected(true);
        
        // add action listener for radio buttons
        small.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("small", null, null);
            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("medium", null, null);
            }
        });
        
        large.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("large", null, null);
            }
        });
        
        // create JMenu button
        final JMenu size = new JMenu("Size");
        size.setMnemonic(KeyEvent.VK_S);
        size.setOpaque(true);
   
        // add radio buttons to group button
        group.add(small);
        group.add(medium);
        group.add(large);
        
        // add radio buttons to size menu 
        size.add(small);
        size.add(medium);
        size.add(large);
        
        // create grid option and its action
        final JCheckBoxMenuItem gridButton = new JCheckBoxMenuItem("Grid");
        gridButton.setMnemonic(KeyEvent.VK_G);
        gridButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
              //grid will be true if we select the check box     
                myBoardPanel.setGrid(gridButton.isSelected()); 
                myBoardPanel.repaint();
            }
        });
        
        // create color mode option and its action
        final JCheckBoxMenuItem colorButton = new JCheckBoxMenuItem("Color Mode");
        colorButton.setMnemonic(KeyEvent.VK_C);
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
              //color mode will be true if we select the check box     
                myBoardPanel.setColorMode(colorButton.isSelected()); 
                myBoardPanel.repaint();
            }
        });
        
        // add size and grid buttons to option menu
        optionMenu.add(size);
        optionMenu.add(gridButton);
        optionMenu.add(colorButton);
        
        // set color for background and foreground 
        for (final Component c : size.getMenuComponents()) {
            setBackForeGround(c);
        }        
        for (final Component c : optionMenu.getMenuComponents()) {
            setBackForeGround(c);
        }
       
        return optionMenu;
    }
    
    /**
     * Create options menu.
     * @return option menu
     */
    public final JMenu createLevelMenu() {
        // create level menu
        final JMenu levelMenu = new JMenu("Levels");
        levelMenu.setForeground(myMenuUWColor);
        levelMenu.setMnemonic(KeyEvent.VK_L);
        
        // create group buttons
        final ButtonGroup group = new ButtonGroup();
        
        // add radio buttons
          
        final JRadioButtonMenuItem middle = new JRadioButtonMenuItem("Middle");   
        final JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Hard");
        
        // default level selected
        myEasy.setSelected(true);
        
        // add action listener for radio buttons
        myEasy.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(EASY_STRING, null, null);
            }
        });
        middle.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(MIDDLE_STRING, null, null);
            }
        });
        
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(HARD_STRING, null, null);
            }
        });
        
        // add radio buttons to group button
        group.add(myEasy);
        group.add(middle);
        group.add(hard);
    
        
       
        
  
        levelMenu.add(myEasy);
        levelMenu.add(middle);
        levelMenu.add(hard);
        
        // set color for background and foreground        
        for (final Component c : levelMenu.getMenuComponents()) {
            setBackForeGround(c);
        }
       
        return levelMenu;
    }
    
   
    /**
     * Menu that show different shapes.
     * @return shape menu
     */
    public final JMenu createShapeMenu() {
        final JMenu shapeMenu = new JMenu("Shapes");
        shapeMenu.setForeground(myMenuUWColor);
        shapeMenu.setMnemonic(KeyEvent.VK_S);
        
        // create group buttons
        final ButtonGroup group = new ButtonGroup();
        
        // add radio buttons
        final JRadioButtonMenuItem smile = new JRadioButtonMenuItem("Smile");
        final JRadioButtonMenuItem oval = new JRadioButtonMenuItem("Oval");   
        final JRadioButtonMenuItem rect = new JRadioButtonMenuItem("Rectangle"); 
        final JRadioButtonMenuItem tri = new JRadioButtonMenuItem("Triangle");
       
       
        
        // default shape selected
        smile.setSelected(true);
        myBoardPanel.setSmile(true);
        // add action listener for radio buttons
        smile.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("smile", null, null);
            }
        });
        oval.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("oval", null, null);
            }
        });
        
        tri.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("triangle", null, null);
            }
        });
        
        rect.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange("rectangle", null, null);
            }
        });
        

        // add radio buttons to group
        group.add(smile);
        group.add(oval);
        group.add(tri);
        group.add(rect);
        
        // add radio buttons to shape menu
        shapeMenu.add(smile);
        shapeMenu.add(oval);
        shapeMenu.add(tri);
        shapeMenu.add(rect);
        
        // set color for background and foreground 
        for (final Component c : shapeMenu.getMenuComponents()) {
            setBackForeGround(c);
        }    
        return shapeMenu;

    }
    
    /**
     * Create new game item.
     * @return new menu item
     */
    public final JMenuItem newGameMenuItem() {
        myNewGame.setMnemonic(KeyEvent.VK_N);    
        myNewGame.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));
        myNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.newGame();
                myMoveTimer.start();
                myKeyAction.setMoveAble(true);
                myNewGame.setEnabled(false);
                myEndGame.setEnabled(true);
                myEasy.setSelected(true);
                myMoveTimer.setDelay(EASY);
                myBoardPanel.setSmile(true);
                myBoardPanel.setGameOver(false); 
                myBoardPanel.setColorMode(false);
                myBoardPanel.repaint();
                myScorePanel.resetGame();
            }
            
        });
        return myNewGame;
    }
      
    
    /**
     * Create end game item.
     * @return new menu item
     */
    public final JMenuItem endGameMenuItem() {
        // set up end game
        myEndGame.setMnemonic(KeyEvent.VK_E);
        myEndGame.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_MASK));
        myEndGame.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myGui.gameOver();
                myBoardPanel.repaint();
            }
            
        });
       
        return myEndGame;
    }
    
    /**
     * Create game instruction by using string builder.
     * @return string builder message
     */
    public String gameInstructions() {
        final StringBuilder str = new StringBuilder(400);
        str.append(" Wellcome to Tetris game \n"
                        + "How to play?\n"
                        + "Press LEFT key to move left.\n"
                        + "Press RIGHT key to move right.\n"
                        + "Press DOWN key to move down 1 step.\n"
                        + "Press A arrow key to rotate clock wise.\n"
                        + "Press D arrow key to rotate counter clock wise.\n"
                        + "Press SPACE key to make a hard drop quickly.\n"
                        + "Press P/R key to pause/resume the game.\n"
                        + "        ~~~~GOOD LUCK~~~~"
                        + "\n\n ALL IMAGES IN THIS PROJECT ARE FROM COOLTEXT.COM");
        return str.toString();
    }
    
    /**
     * Set color for background and foreground.
     * @param theC the component
     */
    public final void setBackForeGround(final Component theC) {
        theC.setBackground(Color.BLACK);
        theC.setForeground(Color.green);
    }
    /**
     * Create Score and rule instruction by using string builder.
     * @return string builder
     */
    public String scoreAndRule() {
        final StringBuilder str = new StringBuilder(450);
        str.append("Score is counted based on cleared lines. \n"
                        + "How to calculate for score? \n"
                        + "Add points to the score when lines are cleared. \n"
                        + "For example: \n"
                        + "Level 1: 1 line = 40 pts, 2 lines = 100 pts\n"
                        + "         3 lines = 300 pts, 4 lines = 1200 pts\n"
                        + "Level n: 1 line = 40 * (n) pts, 2 lines = 100 * (n) pts\n"
                        + "         3 lines = 300 * (n) pts, 4 lines = 1200 * (n) pts\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~ GOOD LUCK ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                   );
        
        return str.toString();
    }
   
    /**
     * Property change for sizes.
     * @param theP the property
     */
    public void propertyChange(final PropertyChangeEvent theP) {
        if (EASY_STRING.equals(theP.getPropertyName())) {
            myMoveTimer.setDelay(EASY);
        } else if (MIDDLE_STRING.equals(theP.getPropertyName())) {
            myMoveTimer.setDelay(MIDDLE);
        } else if (HARD_STRING.equals(theP.getPropertyName())) {
            myMoveTimer.setDelay(HARD);
        } 
    
    }
    
 
}

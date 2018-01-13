/*
    * TCSS 305 
    * Assignment 6 - Tetris
    */

package view;

import action.KeyAction;
import action.PauseAction;
import action.ResumeAction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


import model.Board;

/**
 * GUI class for the Tetris program.
 * @author tvo93
 * @version 3/2/2016
 */
public class TetrisGUI implements Observer, PropertyChangeListener  {
  
    /**The width of the game board.*/
    private static final int WIDTH = 10;
    
    /** The delay (in milliseconds) for default level. */
    private static final int EASY = 400;
    
    /**The height of the game board.*/
    private static final int HEIGHT = 20;
    
    /** The dimension for small game size. */
    private static final Dimension SMALL_BOARD = new Dimension(200, 400);
    
    /** The dimension for medium game size. */
    private static final Dimension MEDIUM_BOARD = new Dimension(300, 600);
    
    /** The dimension for large game size. */
    private static final Dimension LARGE_BOARD = new Dimension(400, 800);
    
    /** The small font size. */
    private static final int SMALL_FONT_SIZE = 25;
    
    /** The medium font size. */
    private static final int MEDIUM_FONT_SIZE = 35;
    
    /** The large font size. */
    private static final int LARGE_FONT_SIZE = 45;
    
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /**
     * The board object that include all pieces and its logics.
     */
    private final Board myBoard = new Board(WIDTH, HEIGHT);
   
    /**
     * The JFrame of the game.
     */
    private final JFrame myFrame;
    
   
    /**
     * The board panel.
     */
    private final TetrisBoardPanel myBoardPanel;
    
    /** The timer that controls the movement of blocks. */
    private final Timer myMoveTimer;
   
    /**
     * The key action that allows user to access the keys.
     */
    private final KeyAction myKeyAction;
    /**
     * The menu bar.
     */
    private final MenuBar myMenuBar;
    
    /**
     * Preview panel.
     */
    private final PreviewPanel myPreviewPanel;
    
    /**
     * About panel.
     */
    private final AboutPanel myAboutPanel;
    
    /**
     * Score panel.
     */
    private final ScorePanel myScorePanel;
    
    /** Pause game. */
    private boolean myPause;
    
    /**
     * Constructs for tetris gui.
     */
    public TetrisGUI() {
        myFrame = new JFrame("Tetris");
        
        myKeyAction = new KeyAction(myBoard);
       
        myBoardPanel = new TetrisBoardPanel();
        
        myPreviewPanel = new PreviewPanel(myBoardPanel);
        myAboutPanel = new AboutPanel();
        
       
        myMoveTimer = new Timer(EASY, null);
        myScorePanel = new ScorePanel(myBoard, myMoveTimer);
        myMenuBar = new MenuBar(this, myFrame, myBoardPanel, myBoard,
                                myScorePanel, myMoveTimer, myKeyAction);
        
        myMenuBar.addPropertyChangeListener(this);
   
        myBoardPanel.addKeyListener(myKeyAction);
       
        
             
    }
    
    /**
     * start the GUI by setting up components and listeners.
     */
    public void start() { 
        
        
       
        
        // add observers       
        myBoard.addObserver(myBoardPanel);
        myBoard.addObserver(myPreviewPanel);
        myBoard.addObserver(this);
        
       // add menu bar to JFrame
        myFrame.setJMenuBar(myMenuBar);  
     
        myKeyAction.setMoveAble(true);
        
        // call set up panels
        setupCenter();
        setupEast();
        //setupListeners();
        
        // not able to click on new game and end game
        myMenuBar.newGameMenuItem().setEnabled(false);
        myMenuBar.endGameMenuItem().setEnabled(false);
        
        myFrame.setIconImage(new ImageIcon("icon.png").getImage());
        myFrame.add(myBoardPanel, BorderLayout.CENTER);
              
        // position the frame in the center of the screen
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2,
                    SCREEN_SIZE.height / 2 -   myFrame.getHeight() / 2);
     
        // default font size
        myBoardPanel.setFontSize(MEDIUM_FONT_SIZE);
     // default board panel size
        setBoardSize(MEDIUM_BOARD);
        // some other set up for JFrame
        myMenuBar.setBackground(Color.LIGHT_GRAY);
        myMenuBar.setForeground(Color.BLACK);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(false);
        myFrame.setVisible(true);    
        myFrame.pack();
    }
    
    /**
     * Set the size for the board.
     * @param theSize the size 
     */
    public void setBoardSize(final Dimension theSize) {
        myBoardPanel.setPreferredSize(theSize);    
        myFrame.pack();
    }
    
    /**
     * Sets up the JPanel for the CENTER region.
     */
    private void setupCenter() {
        // create images 
        final ImageIcon icon1 = new ImageIcon("text.png"); 
        final JLabel image1 = new JLabel(icon1, JLabel.CENTER);
        myBoardPanel.add(image1);
        final ImageIcon icon2 = new ImageIcon("text2.png"); 
        final JLabel image2 = new JLabel(icon2, JLabel.CENTER);
        myBoardPanel.add(image2);
        
        final JButton start = new JButton("Start Game");
        
        start.setBackground(Color.LIGHT_GRAY);
        start.setForeground(Color.BLUE);
        
        start.setOpaque(true);
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                setupListeners();
                myBoard.newGame();                
                myMoveTimer.start();
                myMenuBar.newGameMenuItem().setEnabled(false);
                myMenuBar.endGameMenuItem().setEnabled(true);
                start.setVisible(false);
                image1.setVisible(false);
                image2.setVisible(false);
            }
            
        });
        
        
    
        myBoardPanel.add(start);
       
        myBoardPanel.setFocusable(true);
        myBoardPanel.requestFocusInWindow();
        final PauseAction p = new PauseAction(myBoardPanel, this);
        final ResumeAction r = new ResumeAction(myBoardPanel, this);
        myBoardPanel.addKeyListener(p);
        myBoardPanel.addKeyListener(r);
        
       
        myFrame.add(myBoardPanel, BorderLayout.CENTER);
    }
    
    /**
     * Sets up the JPanel for the EAST region.
     */
    private void setupEast() {
  
        // create a panel that holds for other panels by using box layout
        final JPanel eastPanel = new JPanel();
        final BoxLayout b = new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS);
        
     
        // Add up components for eastPanel
        eastPanel.setLayout(b);
        eastPanel.setBackground(Color.GRAY);
        eastPanel.add(myPreviewPanel); 
        eastPanel.add(myScorePanel);
        eastPanel.add(myAboutPanel);
        myFrame.add(eastPanel, BorderLayout.EAST);
    }
    
    /**
     * Property change for sizes.
     * @param theP the property
     */
    public void propertyChange(final PropertyChangeEvent theP) {
        if ("smile".equals(theP.getPropertyName())) {
            myBoardPanel.setRect(false);
            myBoardPanel.setTriangle(false);
            myBoardPanel.setOval(false);
            myBoardPanel.setSmile(true);
            myBoardPanel.repaint();
            myPreviewPanel.repaint();
        } else if ("small".equals(theP.getPropertyName())) {
            setBoardSize(SMALL_BOARD);
            myBoardPanel.setFontSize(SMALL_FONT_SIZE);
           
        } else if ("oval".equals(theP.getPropertyName())) {
            myBoardPanel.setRect(false);
            myBoardPanel.setTriangle(false);
            myBoardPanel.setOval(true);
            myBoardPanel.setSmile(false);
            myBoardPanel.repaint();
            myPreviewPanel.repaint();
        } else if ("triangle".equals(theP.getPropertyName())) {
            myBoardPanel.setRect(false);
            myBoardPanel.setTriangle(true);
            myBoardPanel.setOval(false);
            myBoardPanel.setSmile(false);
            myBoardPanel.repaint();
            myPreviewPanel.repaint();
        } else if ("rectangle".equals(theP.getPropertyName())) {
            myBoardPanel.setRect(true);
            myBoardPanel.setTriangle(false);
            myBoardPanel.setOval(false);
            myBoardPanel.setSmile(false);
            myBoardPanel.repaint();
            myPreviewPanel.repaint();
        } else if ("medium".equals(theP.getPropertyName())) {    
            setBoardSize(MEDIUM_BOARD);
            myBoardPanel.setFontSize(MEDIUM_FONT_SIZE);
            
        } else if ("large".equals(theP.getPropertyName())) {
            setBoardSize(LARGE_BOARD);
            myBoardPanel.setFontSize(LARGE_FONT_SIZE);

        }
    }
    
    /**
     * A method that is used to pause or resume the game.
     */
    public void pauseGame() {
        if (myPause) { // Pause the game
            myMoveTimer.stop();
            myBoardPanel.removeKeyListener(myKeyAction);
        } else { // Resume the game
            myMoveTimer.start();
            myBoardPanel.addKeyListener(myKeyAction);
            
        }
    }
    
    /**
     * pause game.
     * @return true if game is pause and false otherwise
     */
    public boolean isPause() {
        return myPause;
    }
   
   /**
    * set the pause value.
    * @param thePause the pause
    */
    public void setPause(final boolean thePause) {
        myPause = thePause;
    }
   
    
    /** Sets up event listeners. */
    public void setupListeners() {
        myMoveTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {              
                myBoard.step();       
             
            }
        });     
       
    }
    
    /**
     * Game over.
     */
    public void gameOver() {
        myMoveTimer.stop();
        myBoardPanel.setGameOver(true);
        myKeyAction.setMoveAble(false);
        myBoardPanel.setSmile(false);
        myBoardPanel.setTriangle(false);
        myBoardPanel.setOval(false);
        myBoardPanel.setRect(false);
        myMenuBar.newGameMenuItem().setEnabled(true);
        myMenuBar.endGameMenuItem().setEnabled(false);
        myFrame.validate();  
    }
    
    @Override
    public void update(final Observable theObservable, final Object theData) {
        if (theObservable instanceof Board && theData instanceof Boolean) {
            gameOver();
        }
    }


   
}

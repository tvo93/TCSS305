package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import model.Board;

/**
 * A panel represents and updates scores.
 * @author tvo93
 * @version 3/11/2016
 */
public class ScorePanel extends JPanel implements Observer {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 1L;

    /** The default size of the panel. */
    private static final Dimension PANEL_SIZE = new Dimension(100, 50);
    
    /** The Constant for requirement to get level up. */
    private static final int CONSTANT_LEVEL = 5;
    
    /** Hard level. */
    private static final int HARD_LEVEL = 9;
    
    /** The default message for lines label. */
    private static final String LINES = "Lines:   ";
    
    /** The default message for score label. */
    private static final String SCORE = "Score:  ";
    
    /** The default message for level label. */
    private static final String LEVEL = "Level:   ";
    
    /** Level two delay constant.*/
    private static final int MEDIUM_DELAY = 300;
    
    /** Level three delay constant.*/
    private static final int HARD_DELAY = 200;
    
    /**
     * Print out a message for cleared line left.
     */
    private static final String LINE_LEFT = "Lines Left: ";
    
    /** The default point for 1 cleared line. */
    private static final int ONE_LINE_SCORE = 40;   
    
    /** The default point for 2 cleared line. */
    private static final int TWO_LINE_SCORE = 100;
    
    /** The default point for 3 cleared line. */
    private static final int THREE_LINE_SCORE = 300;
    
    /** The default point for 4 cleared line. */
    private static final int FOUR_LINE_SCORE = 1200;
    /** Level three constant.*/
    private static final int LEVEL_THREE = 3;
    /** The my lines to level. */
    private int myLevelUp = CONSTANT_LEVEL;
   
    /** The cleared line. */
    private int myClearedLine;
   
    /** The level for game. */
    private int myLevel;
    
    /** The clear lines for game. */
    private int myLine;
    
    /**
     * The line left to get level up.
     */
    private int myLineLeft;
    
    /** The score. */
    private int myScore;
        
    /** The default timer for the tetris. */
    private final Timer myTimer;
      
    /** The Line Left Label. */
    private JLabel myLineLeftLabel;
    
    /** The Line Label. */
    private JLabel myLineLabel;
    
    /** The Level Label. */
    private JLabel myLevelLabel;
    
    /** The Score Label. */
    private JLabel myScoreLabel;
    
    /**
     * The board panel.
     */
   // private final TetrisBoardPanel myBoardPanel;
    
    /**
     * Constructs for score panel.
     * @param theBoard the board
     * @param theTimer the timer
     */
    public ScorePanel(final Board theBoard,
                       final Timer theTimer) {
        super();   
        myTimer = theTimer;
        final Board board = theBoard;
        board.addObserver(this);        
        start();
        displayPanel();
        setUpBorder();
    }

    /**
     * start the panel.
     */
    public final void start() {
        setPreferredSize(PANEL_SIZE);
        setBackground(Color.BLACK);     
        myLevel = 1;
        myLine = 0;
        myScore = 0;
        myLineLeft = myLevelUp;
        myLineLabel = new JLabel(LINES + myClearedLine);
        myLineLeftLabel = new JLabel(LINE_LEFT + myLineLeft);
        myLevelLabel = new JLabel(LEVEL + myLevel);  
        myScoreLabel = new JLabel(SCORE + myScore);      
    }
    
    /**
     * Display the panel.
     */
    public final void displayPanel() {
        final int fontSize = 14;
        final Box box = new Box(BoxLayout.PAGE_AXIS);
        final JLabel[] listLabel = new JLabel[]{myLineLabel
                        , myScoreLabel, myLineLeftLabel, myLevelLabel};
        for (int i = 0; i < listLabel.length; i++) {
            listLabel[i].setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, fontSize));
            listLabel[i].setForeground(Color.WHITE);
            box.add(listLabel[i]);
        }
        add(box);
    }
    
    /** 
     * Set up border.
     */
    public final void setUpBorder() {
        final TitledBorder title = new TitledBorder("Score");
        title.setTitleColor(Color.RED);
        title.setTitleJustification(TitledBorder.CENTER);
        setBorder(title);
    }
    
    @Override
    public void update(final Observable theObservable, final Object theData) {
        if (theObservable instanceof Board && theData instanceof Integer[]) {
            
            final int clearedLines = ((Integer[]) theData).length;
           
            calculateScore(clearedLines);
            levelUp();
            myLineLabel.setText(LINES + myLine);        
            myLevelLabel.setText(LEVEL + myLevel);
            repaint();
        }
        
    }
    
    /**
     * Calculate the score.
     * @param theLine the line
     */
    public void calculateScore(final int theLine) {
        myLine += theLine;      
        if (myLine == 1) {
            myScore += myLevel * ONE_LINE_SCORE;
        } else if (myLine == 2) {
            myScore += myLevel * TWO_LINE_SCORE;
        } else if (myLine == LEVEL_THREE) {
            myScore += myLevel * THREE_LINE_SCORE;
        } else if (myLine == LEVEL_THREE + 1) {
            myScore += myLevel * FOUR_LINE_SCORE;
        } 
        myScoreLabel.setText(SCORE + myScore);
    }
    
    /**
     * Reset the game when users choose new game.
     */
    public void resetGame() {
        myScore = 0;
        myLevel = 1;
        myClearedLine = 0;
        myLineLeft = myLevelUp - myLevel + 1;
        myLineLabel.setText(LINES + myClearedLine);
        myLineLeftLabel.setText(LINE_LEFT + myLineLeft);
        myLevelLabel.setText(LEVEL + myLevel);
        myScoreLabel.setText(SCORE + myScore);     
    }
    
    /**
     * LEVEL count.
     */
    public void levelCount() {
        if (myLine >= myLevelUp) {
            myLevelUp += CONSTANT_LEVEL;
            myLevel++;
        } 
    }
    
    /**
     * Level up if keep increasing cleared lines.
     */
    public void levelUp() {
    // Change piece shape on the board panel if level is up
        if (myLine >= CONSTANT_LEVEL && myLine < HARD_LEVEL) {
            myTimer.setDelay(MEDIUM_DELAY);          
        } else if (myLine > HARD_LEVEL) {
            myTimer.setDelay(HARD_DELAY);                 
        }
    }
}

/*
 * TCSS 305 - SnapShop
 */


package gui;


import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * The graphical user interface for the SnapShop program.
 * @author tvo93
 * @version 1/31/2016
 */
public class SnapShopGUI extends JFrame {
    
    /** A generated Serialization ID. */
    private static final long serialVersionUID = 1L;

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /**
     * The width of panel at the beginning.
     */
    private static final int SET_UP_WIDTH = 347;
    
    /**
     * The width of panel at the beginning.
     */
    private static final int SET_UP_HEIGHT = 257;
    
    /**
     * The space's dimension between option buttons in south panel.
     */
    private static final int EMPTY_SPACE = 4;
    
    /**
     *  The Save button.
     */
    private final JButton mySave = new JButton("Save As...");
   
    /**
     * The close button.
     */
    private final JButton myClose = new JButton("Close Image");
    
    /**
     * The panel for all filter buttons.
     */
    private final JPanel myWestPanel = new JPanel();
   
    /**
     * The panel for options button.
     */
    private final JPanel mySouthPanel = new JPanel();
    
    /**
     * The label image.
     */
    private final JLabel myLabel = new JLabel();
   
    /**
     * The Image.
     */
    private PixelImage myImage;
    
    /**
     * File chooser for opening and saving files.
     */
    private final JFileChooser myChooser = new JFileChooser();
   
    /**
     * The minimum size of GUI after open an image.
     */
    private int myMinH;
    
    /**
     * The maximum size of GUI after open an image.
     */
    private int myMinW;
   
    /**
     * Constructs a new SnapShopGUI.
     */
    public SnapShopGUI() {
        super("TCSS 305 SnapShop");   
    }
    
    /**
     * Start the program.
     */
    public void start() {
        
        // call filterButtons to create 7 buttons
        filterButtons(new EdgeDetectFilter());
        filterButtons(new EdgeHighlightFilter());
        filterButtons(new FlipHorizontalFilter());
        filterButtons(new FlipVerticalFilter());
        filterButtons(new GrayscaleFilter());
        filterButtons(new SharpenFilter());
        filterButtons(new SoftenFilter());  
        
        setUpGUI(); // call for setUpGUI class
        
        // locate and set up the Jframe
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
        setMinimumSize(setUpSize());
        setVisible(true);
        setResizable(true);
        pack();
    }
    
    /**
     * Set up the outlook of GUI.
     */
    public void setUpGUI() {
        // create the panels and buttons for jFrame
        final JPanel centerPanel = new JPanel(new GridBagLayout());    
      
       // final JPanel southPanel = new JPanel(new FlowLayout());
        final GridLayout gb = new GridLayout(7, 1);
       
        // create icons
        final Icon openIcon = new ImageIcon("icons/open.gif");
        final Icon saveIcon = new ImageIcon("icons/save.gif");
        final Icon closeIcon = new ImageIcon("icons/close.gif");
        
        // add icons to buttons
        final JButton openB = new JButton("Open", openIcon);
        mySave.setIcon(saveIcon);
        myClose.setIcon(closeIcon);
        
        
        // add button in panels
        centerPanel.add(myLabel);
        mySouthPanel.add(openB);
        mySouthPanel.add(mySave);
        mySouthPanel.add(myClose);
        
        // add action listener to buttons to make it works
        openB.addActionListener(new OpenListener());
        mySave.addActionListener(new SaveListener());
        myClose.addActionListener(new CloseListener());
        
        // add all panels to the master panel to display in JFrame
        myWestPanel.setLayout(gb);
        add(myWestPanel, BorderLayout.WEST);
        add(mySouthPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
       
        enableButtons(false); // check buttons enable or not 
    }
   
    /**
     * Dimension at the beginning (not loading image).
     * @return dimension
     */
    public Dimension setUpSize() {
        return new Dimension(SET_UP_WIDTH, SET_UP_HEIGHT);
    }
    
    
    /**
     * A method that disable or enable buttons.
     * @param theCheck is true when all the buttons are enable.
     */
    private void enableButtons(final boolean theCheck) {
        // make a list for all 7 filer buttons 
        final Component[] display = myWestPanel.getComponents();
        
        // set enable or not for each buttons
        for (int i = 0; i < display.length; i++) {
            if (theCheck) {  // enable buttons   
                display[i].setEnabled(true);
                mySave.setEnabled(true);
                myClose.setEnabled(true);
               
            }  else {  // disable buttons otherwise
                display[i].setEnabled(false);
                mySave.setEnabled(false);
                myClose.setEnabled(false);            
            }   
        }
       
    }
    
    /**
     * A method that creates all filter buttons 
     * and do their effects if the users click on
     * after loading an image.
     * @param theFilter specific the button filters
     */
    // This idea I got it from the lecture slides
    private void filterButtons(final Filter theFilter) {        
        // create all buttons
        final JButton filterButton = new JButton(theFilter.getDescription());
        // add listener for each button
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFilter.filter(myImage); // filter effects image  
             // result after clicking on filter buttons
                myLabel.setIcon(new ImageIcon(myImage)); 
                
            }
        });
        myWestPanel.add(filterButton); // locate button on panel    
    }
   
        
    
    /**
     * An action listener that shows the open dialog.
     * @author tvo93
     * @version 2/3/2016
     */
    private class OpenListener implements ActionListener { 
         
         /**
          * Select images.
          * @param theEvent the Event
          */
        public void actionPerformed(final ActionEvent theEvent) {
            
            // get current directory to open it
            myChooser.setCurrentDirectory(new java.io.File("."));
            // pop up a open dialog
            final int result = myChooser.showOpenDialog(SnapShopGUI.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    // load image and display image in the GUI
                    myImage = PixelImage.load(myChooser.getSelectedFile());
                    myLabel.setIcon(new ImageIcon(myImage));   
                    myMinH = myImage.getHeight()  + 2 * mySouthPanel.getHeight()
                            + EMPTY_SPACE;
                    myMinW = myImage.getWidth() + myWestPanel.getWidth() 
                            + EMPTY_SPACE * EMPTY_SPACE;
                 
                    setMinimumSize(new Dimension(myMinW, myMinH));
                    enableButtons(true); // enable for all buttons
                    pack();
             
                } catch (final IOException e) {
                    //  a message for wrong image type
                    JOptionPane.showMessageDialog(null, 
                                  "The selected file did not contain an image!",
                                  "Error!", 0);               
                }                   
            }        
        }
    }
    
    /** An action listener that shows the save dialog.
     * @author tvo93
     * @version 2/3/2016
     */
    private  class SaveListener implements ActionListener {
        
       /**
        * A method thats allows users to to save.
        * @param theEvent the Event.
        */
        public void actionPerformed(final ActionEvent theEvent) {
            final int result = myChooser.showSaveDialog(null);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    // save image in the current directory
                    myImage.save(myChooser.getSelectedFile());  
                    myLabel.setIcon(new ImageIcon(myImage));
                   
                    setMinimumSize(new Dimension(myMinW, myMinH));
                    enableButtons(true);
                    pack();
                
                } catch (final IOException e) {
                    // a message if there is no file to save
                    JOptionPane.showMessageDialog(null, 
                                        "No file saved!");                       
                }          
            }     
        }
    }
            
    
    /** 
     * An action listener that closes image.
     * @author tvo93
     * @version 2/3/2016
     */
    
    private class CloseListener implements ActionListener {
        
        /**
         * A method that removes the image.
         * @param theEvent the event. 
         */
        public void actionPerformed(final ActionEvent theEvent) {
            myLabel.setIcon(null); // delete image
            enableButtons(false); // button enable choices go back to the beginning 
            setMinimumSize(setUpSize()); // original size
            pack();
            
        }
    }
   
    
    
}
/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */



package gui;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * The JToolBar for this GUI example.
 * 
 * @author tvo93
 * @version 2/12/2016
 */
public class ToolBar extends JToolBar {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 1L;

    /** A button group for the mutually exclusive tool bar buttons. */ 
    private final ButtonGroup myGroup;

    /**
     * Construct the ToolBar.
     */
    public ToolBar() {
        super();
        myGroup = new ButtonGroup();
    }

    /**
     * Create a JToggleButton for the ToolBar.
     * 
     * @param theAction to associate with the created JToggleButton
     */
    public void createToolBarButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        myGroup.add(toggleButton);
        myGroup.clearSelection();
        add(toggleButton);
    }

}

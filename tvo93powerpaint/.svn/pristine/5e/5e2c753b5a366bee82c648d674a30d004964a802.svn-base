/*
 * TCSS 305
 * Assignment 5 - PowerPaint
 */


package gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * This program starts the PowerPaint.
 * @author tvo93
 * @version 10 October 2015
 */
public final class PowerPaintMain {
    
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }
    
    /**
     * Set the look and feel for the GUI program.
     */
    private static void setLookAndFeel() {
        // This code is from example code providing on canvas
        try {          
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }
        
    }
    
    
    /**
     * The main method, invokes the PowerPaint GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new PowerPaintGUI().start();
            }
        });
        
    }

}

/*
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * Represents default behavior for Vehicle subclasses.
 * @author tvo93
 * @version 1/26/2016
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /**
     * The x-coordinate.
     */
    private int myX;
    
    /**
     * The y-coordinate.
     */
    private int myY;
    
    /**
     * The start x-coordinate.
     */
    private final int myStartX;
    
    /**
     * The initial y-coordinate.
     */
    private final int myStartY;
    
    /**
     * The vehicle direction.
     */
    private Direction myDirection;
    
    /**
     * The initial vehicle direction.
     */
    private final Direction myStartDirection;
    
    /**
     * The amount of time if the vehicle is dead.
     */
    private final int myDeathTime; 
   
    /**
     * The name of vehicle.
     */
    private String myName;
    
    /**
     * The time the vehicle wait to revived.
     */
    private int myWaitTime;

    /**
     * Constructor for abstractVehicle class.
     * @author tvo93
     * @version 
     * @param theX the x coordinate of vehicle
     * @param theY the y coordinate of vehicle
     * @param theDirection the direction of vehicle
     * @param theDeathTime the time of vehicle to wait for alive.
     * @param theName the name of vehicle
     */
    public AbstractVehicle(final int theX, final int theY,
                           final Direction theDirection, 
                           final int theDeathTime, final String theName) {
          
        myX = theX;
        myStartY = theY;
        myY = theY;
        myStartDirection = theDirection;
        myDirection = myStartDirection;
        myDeathTime = theDeathTime;
        myWaitTime = theDeathTime;
        myName = theName;
        myStartX = myX;
        
    }
   

    /** {@inheritDoc} */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return !theTerrain.equals(Terrain.WALL);        
    }

    /** {@inheritDoc} */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction vehicleDirection = getDirection(); 
        
       // common behavior's of vehicle
        if (theNeighbors.get(getDirection()).equals(Terrain.WALL)) {
            vehicleDirection = getDirection().left();
            vehicleDirection = getDirection().right();
        }
        return vehicleDirection;
    }

    /** {@inheritDoc} */
    @Override
    public void collide(final Vehicle theOther) {   
        if (isAlive() && theOther.isAlive()
            && theOther.getDeathTime() < myDeathTime) {
            myWaitTime = 0; 
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public int getDeathTime() {
        return myDeathTime; 
    }

    /** {@inheritDoc} */
    @Override
    public String getImageFileName() {
        String checkImage = ""; 
        if (isAlive()) {
            // get vehicle's alive name
            checkImage = getClass().getSimpleName() + ".gif";
        } else {
            // get vehicle's dead name
            checkImage = getClass().getSimpleName() + "_dead.gif";
        }
        return checkImage; 
    }
    
   

    /** {@inheritDoc} */
    @Override
    public Direction getDirection() {
        return myDirection; 
    }

    /** {@inheritDoc} */
    @Override
    public int getX() {
        return myX;
    }

    /** {@inheritDoc} */
    @Override
    public int getY() {
        return myY;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAlive() {
        return myWaitTime == myDeathTime;
    }

    /** {@inheritDoc} */
    @Override
    public void poke() {
        if (isAlive()) {
            myDirection = Direction.random(); 
        } else {   
            myWaitTime++; 
        }
    }

    /** {@inheritDoc} */
    @Override
    public void reset() {
             
        myX = myStartX;
        myY = myStartY;
        myDirection = myStartDirection;
        myWaitTime = myDeathTime;
    }

    /** {@inheritDoc} */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(final int theX) {
        myX = theX;

    }

    /** {@inheritDoc} */
    @Override
    public void setY(final int theY) {
        myY = theY;

    }
    
    /**
     * Return a name of vehicle.
     * @return name of vehicle
     */
    public String toString() {
        if (isAlive()) {
            // vehicles name will be displayed when they are alive
            myName = getClass().getSimpleName();
            
        } else {
            // poke and the waiting time number will be displayed if
            // they are dead
            myName = "poke: " + myWaitTime;
        }
        return myName;
    }
    
    /**
     * Time for waiting to be alive again.
     * @return wait time
     */
    public int getWaitTime() {
        return myWaitTime;
    }
}

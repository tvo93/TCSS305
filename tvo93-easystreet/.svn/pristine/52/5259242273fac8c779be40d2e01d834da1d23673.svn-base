/*
 * TCSS 305 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Represent for TAXI vehicle.
 * @author tvo93
 * @version 1/26/2016
 */
public class Taxi extends AbstractVehicle {
    
    /**
     * Time to get back to alive.
     */
    private static final int DEATH_TIME = 10;
    
    /**
     * Time to wait for the red light.
     */
    private static final int PASS_RED = 3;
    
    /**
     * Count number when the taxi wait for read light.
     */
    private int myCount;
    
    /**
     * Get light.
     */
    private Light myLight;
    
    /**
     * Constructs for car class.
     * @param theX x coordinate
     * @param theY y coordinate
     * @param theDir direction
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Taxi");
        myCount = 0;
    }
    
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean taxPass = false;
        myLight = theLight;
        // taxi can pass on street and through green or yellow light
        if (theTerrain == (Terrain.STREET) || (theTerrain == (Terrain.LIGHT)
                        && theLight == (Light.GREEN)) || (theTerrain == (Terrain.LIGHT)
                        && theLight == (Light.YELLOW))) {         
            taxPass = true;
            
        } else if (theTerrain == (Terrain.LIGHT) && theLight == (Light.RED)) {           
            taxPass = false; // stop if red light
            if (getCount() > PASS_RED) {
                taxPass = true; // pass if stay on red more than 3 block cycle
            }
        }
        return taxPass;

    } 
   
    
   
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        // car prefers run straight ahead
        Direction taxDir = getDirection();
        // count for block cycle if the taxi stay on red light
        if (theNeighbors.get(taxDir).equals(Terrain.LIGHT) && myLight == Light.RED) {
            myCount++;
        } else {
            myCount = 0;
        }
        
        
        // check if in front of the taxi are grass, wall or trail
        if (theNeighbors.get(taxDir).equals(Terrain.GRASS) 
                        || theNeighbors.get(taxDir).equals(Terrain.WALL)
                        || theNeighbors.get(taxDir).equals(Terrain.TRAIL)) {
            // taxi will turn left if possible
            if (theNeighbors.get(taxDir.left()).equals(Terrain.LIGHT)
                            || theNeighbors.get(taxDir.left()).equals(Terrain.STREET)) {
                taxDir = getDirection().left();
            } else if (theNeighbors.get(taxDir.right()).equals(Terrain.LIGHT)
                            || theNeighbors.get(taxDir.right()).equals(Terrain.STREET)) {
                taxDir = getDirection().right(); // turn right otherwise
            } else {
                taxDir = taxDir.reverse(); // turn around if can not turn left or right
            }
        }
                
        return taxDir; 
    }
    
    /**
     * Return the count number when the taxi is waiting for the red light.
     * @return the count number
     */
    private int getCount() {
        return myCount;
    }
    
    
    @Override
    public String toString() {
        String name = "";
        if (isAlive()) {
            
            // vehicles name will be displayed when they are alive
            name = "Stop Count: " + myCount;
            
        } else {
            // poke and the waiting time number will be displayed if
            // they are dead
            name = "poke: " + getWaitTime();
        }
        
        return name;
    }
}

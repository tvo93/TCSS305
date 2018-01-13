/*
 * TCSS 305 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Represent for atv vehicle.
 * @author tvo93
 * @version 1/26/2016
 */
public class Atv extends AbstractVehicle {
    
    /**
     * Time to get back to alive.
     */
    private static final int DEATH_TIME = 20;
    
    /**
     * Constructs for atv class.
     * @param theX x coordinate
     * @param theY y coordinate
     * @param theDir direction
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Atv");
    }
 
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return Direction.random(); // atv always run randomly on any terrain except wall
    }
    
}

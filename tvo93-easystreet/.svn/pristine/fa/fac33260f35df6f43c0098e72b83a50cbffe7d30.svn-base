/*
 * TCSS 305 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Represent for Human.
 * @author tvo93
 * @version 1/26/2016
 */
public class Human extends AbstractVehicle {
    
    /**
     * Time to get back to alive.
     */
    private static final int DEATH_TIME = 50;
    
    /**
     * The terrain.
     */
    private final Terrain myTerrain;
    /**
     * Constructs for human class.
     * @param theX x coordinate
     * @param theY y coordinate
     * @param theDir direction
     * @param theTerrain the terrain
     */
    public Human(final int theX, final int theY, final Direction theDir,
                 final Terrain theTerrain) {
        super(theX, theY, theDir, DEATH_TIME, "Human");
        myTerrain = theTerrain;
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean humPass = false;
        
        // Human starts on trail or grass only
        if (myTerrain.equals(Terrain.TRAIL) 
                        || myTerrain.equals(Terrain.GRASS)) {
            humPass = theTerrain.equals(myTerrain);
         // human starts on street and through lights
        } else if (myTerrain.equals(Terrain.STREET)) {
            humPass = theTerrain.equals(Terrain.STREET) 
                            || theTerrain.equals(Terrain.LIGHT);
        } 
        return humPass;  
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction humanDir = Direction.random(); //human is random direction 
        
        if (myTerrain.equals(Terrain.STREET) || myTerrain.equals(Terrain.LIGHT)) {
            while (!theNeighbors.get(humanDir).equals(Terrain.STREET) 
                  && !theNeighbors.get(humanDir).equals(Terrain.LIGHT)) {
                humanDir = Direction.random();
                
            }
        } else if (myTerrain.equals(Terrain.GRASS) || myTerrain.equals(Terrain.TRAIL)) {
            while (!theNeighbors.get(humanDir).equals(Terrain.TRAIL) 
                            && !theNeighbors.get(humanDir).equals(Terrain.GRASS)) {
                humanDir = Direction.random();
            }
            
        }
        return humanDir;
            
    }
}

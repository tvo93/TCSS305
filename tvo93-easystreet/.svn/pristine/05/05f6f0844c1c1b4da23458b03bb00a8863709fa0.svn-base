/*
 * TCSS 305 - Easy Street
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test for class truck.
 * @author tvo93
 * @version 1/28/2016
 *
 */
public class TruckTest {
    /**
     * Truck vehicle.
     */
    private Truck myTruck;
   
 
    /**
     * Test before the class.
     */
    @Before
    public void setUp() {
        myTruck = new Truck(1, 1, Direction.SOUTH);
    }

   
    /**
     * Test constructor.
     */
    @Test
    public void testTruckConstructor() {
        assertEquals("Truck x coordinate not initialized correctly!", 1, myTruck.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 1, myTruck.getY());
        assertEquals("Truck direction not initialized correctly!", 
                     Direction.SOUTH, myTruck.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, myTruck.getDeathTime());
        
    }
    
    /**
     * Test method for canPass method.
     */
    @Test
    public void testCanPass() {
        
        for (final Terrain t : Terrain.values()) {
            for (final Light l : Light.values()) {
           // truck pass on street and light only
              // ignore lights
                if (t == Terrain.STREET || t == Terrain.LIGHT) {
                    assertTrue("Trucks can pass " + t, 
                         myTruck.canPass(t, l));
                } else {
                    assertFalse("Trucks can not pass" + t, 
                          myTruck.canPass(t, l));
                }
            }
        }
    }

     
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map, Light)}.
     */
    @Test
    public void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        for (final Terrain ter1 : Terrain.values()) {
            for (final Terrain ter2 : Terrain.values()) {
                for (final Terrain ter3 : Terrain.values()) {
                    for (final Terrain ter4 : Terrain.values()) {
                        neighbors.put(Direction.NORTH, ter1);
                        neighbors.put(Direction.WEST, ter2); 
                        neighbors.put(Direction.EAST, ter3); 
                        neighbors.put(Direction.SOUTH,  ter4); 
                        neighbors.put(Direction.random(), Terrain.STREET);
                        neighbors.put(Direction.random(), Terrain.LIGHT);              
                        final Direction dir = myTruck.chooseDirection(neighbors);
                        if (onStreetOrLight(neighbors)) {  
                            assertNotEquals(dir, Direction.NORTH);
                        } else {
                            assertEquals(dir, Direction.SOUTH);       
                        }
                    }
                }
            }
        }
         
    }
    
    /**
     * Returns true if pass on street or light.
     * @param theNeighbors All of the adjacent squares of this vehicle.
     * @return true 
     */
    private boolean onStreetOrLight(final Map<Direction, Terrain> theNeighbors) {
        boolean isStreetLight = false; 
        if ((theNeighbors.get(myTruck.getDirection()) == Terrain.STREET 
            || theNeighbors.get(myTruck.getDirection()) == Terrain.LIGHT)
            || (theNeighbors.get(myTruck.getDirection().left()) == Terrain.STREET 
            || theNeighbors.get(myTruck.getDirection().left()) == Terrain.LIGHT)
            || (theNeighbors.get(myTruck.getDirection().right()) == Terrain.STREET 
            || theNeighbors.get(myTruck.getDirection().right()) == Terrain.LIGHT)) {
            isStreetLight = true;
        }
        return isStreetLight;
    }
    

}

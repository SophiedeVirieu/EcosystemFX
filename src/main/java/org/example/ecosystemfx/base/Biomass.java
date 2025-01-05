package org.example.ecosystemfx.base;

import org.example.ecosystemfx.ihm.IHM;

import java.util.ArrayList;
import java.util.List;

public abstract class Biomass {

    /**
     * Enum representing the different species possible in the ecosystem.
     */
    public enum species {CRAB, JELLY_FISH, SEAGULL, DEER, WOLF, HEDGEHOG,
        SNAKE, FALCON, SHARK, TURTLE, HUMAN};
    
    /**
     * Enum representing the different types of terrains.
     */
    public enum grounds {WATER, SAND, PLAIN, FOREST};

    protected List<species> predators;
    
    /**
     * List of authorized terrain types for this Biomass.
     */
    protected List<grounds> ground;
    
    /**
     * Number of calories contained in this Biomass.
     */
    public int calories;

    /**
     * Number of bites this Biomass can endure before being eaten.
     */
    public int bites;

    /**
    * Coordinates of the position of the Biomass.
    */
    protected int x;
    protected int y;

    /**
     * Sets the x and y coordinate of the Biomass, ensuring that the new position
     * is located on a valid terrain.
     * 
     * - If the current terrain is not valid for this Biomass, it searches for
     *   a valid terrain nearby.
     * - The search expands in the x, -x, y and -y directions further and further until a valid terrain is found.
     * 
     * 
     * @param y The proposed new y coordinate.
     */
    public void setY(int y) {
        if (this.ground.contains(Terrain2D.getTerrain(this.x, this.y))) {
            this.y = y;
        } else {
            int radius = 1;

            //search a near authorized terrain
            while (true) {
                List<int[]> positions = new ArrayList<>();

                for (int dy = -radius; dy <= radius; dy++) {
                    int dx = radius - Math.abs(dy);

                    positions.add(new int[] {this.x + dx, this.y + dy});
                    positions.add(new int[] {this.x - dx, this.y + dy});
                    positions.add(new int[] {this.x + dx, this.y - dy});
                    positions.add(new int[] {this.x - dx, this.y - dy});
                }

                for (int[] pos : positions) {
                    int newX = pos[0];
                    int newY = pos[1];

                    if (isValidPosition(newX, newY) && this.ground.contains(Terrain2D.getTerrain(newX, newY))) {
                        this.x = newX;
                        this.y = newY;
                        return;
                    }
                }

                radius++;
            }
        }
    }

   /**
     * Constructor for the Biomass class.
     * Initializes a Biomass instance with coordinates (x, y).
     * Initializes the predator and terrain lists.
     * 
     * @param x The initial x coordinate of the biomass.
     * @param y The initial y coordinate of the biomass.
     */
    public Biomass(int x, int y){
        this.x = x;
        this.y = y;
        this.bites = 0;
        this.predators = new ArrayList<species>();
        this.ground = new ArrayList<grounds>();
    }

    /**
     * Checks if a given position (x, y) is valid within the terrain's boundaries.
     * 
     * The terrain's boundaries are defined by the dimensions width and height.
     * 
     * @param x The x coordinate of the position to check.
     * @param y The y coordinate of the position to check.
     * @return true if the position is within boundaries, false otherwise.
     */
    protected static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < IHM.width && y >= 0 && y < IHM.height;
    }

}

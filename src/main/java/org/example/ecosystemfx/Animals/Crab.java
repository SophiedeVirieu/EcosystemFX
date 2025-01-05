package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Crab extends Animal {

    /**
     * Constructor for the Crab class.
     * 
     * - Initializes a Crab instance with the specified coordinates (x, y).
     * - Sets its attributes : species, predators, allowed terrains, possible food, speed, and calories.
     * - Ensures that the position is on valid terrain using setY(y).
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Crab(int x, int y) {
        super(x, y);
        this.specie = species.CRAB;

        this.predators.add(species.SEAGULL);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.WATER);
        this.myFood.add(TerrainResources.food.ALGAE);
        this.myFood.add(TerrainResources.food.FISH);

        this.speed = 2;
        this.calories = 4;

        setY(y);

    }

    /**
     * Returns the color associated with the Crab.
     * 
     * - The color is defined as black.
     * 
     * @return A Color object representing the crab's color.
     */
    @Override
    public Color getColor() {return new Color(0 / 255.0, 0 / 255.0, 0 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Crab.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the crab.
     * - If valid, creates a new Crab instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Crab(this.x +1, this.y));
            super.reproduce();
        }
    }
}

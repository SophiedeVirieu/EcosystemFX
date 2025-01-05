package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

/**
     * Constructor for the Deer class.
     * 
     * - Initializes a Crab instance with the specified coordinates (x, y).
     * - Sets its attributes : species, predators, allowed terrains, possible food, speed, and calories.
     * - Ensures that the position is on valid terrain using setY(y).
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
public class Deer extends Animal {
    public Deer(int x, int y) {
        super(x, y);
        this.specie = species.DEER;

        this.predators.add(species.WOLF);
        this.myFood.add(TerrainResources.food.GRASS);
        this.myFood.add(TerrainResources.food.BERRIES);
        this.ground.add(grounds.FOREST);
        this.ground.add(grounds.PLAIN);

        this.speed = 4;
        this.calories = 8;

        setY(y);
    }

    /**
     * Returns the color associated with the Deer.
     * 
     * - The color is defined as brown.
     * 
     * @return A `Color` object representing the deer's color.
     */
    @Override
    public Color getColor() {return new Color(139 / 255.0, 69 / 255.0, 19 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Deer.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the deer.
     * - If valid, creates a new Deer instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Deer(this.x + 1, this.y));
            super.reproduce();
        }
    }
}

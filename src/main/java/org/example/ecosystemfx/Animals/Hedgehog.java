package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Hedgehog extends Animal {

    /**
     * Constructor for the Hedgehog class.
     * 
     * - Initializes a Hedgehog instance with the specified coordinates (x, y).
     * - Sets its attributes: species, predators, possible food, allowed terrains, speed, camouflage, and calories.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Hedgehog(int x, int y) {
        super(x, y);
        this.specie = species.HEDGEHOG;

        this.predators.add(species.FALCON);
        this.predators.add(species.WOLF);
        this.myFood.add(TerrainResources.food.GRASS);
        this.myFood.add(TerrainResources.food.BERRIES);
        this.ground.add(grounds.FOREST);
        this.ground.add(grounds.PLAIN);

        this.speed = 1;
        this.calories = 5;

        setY(y);
    }

    /**
     * Returns the color associated with the Hedgehog.
     * 
     * - The color is defined as dark brown.
     * 
     * @return A `Color` object representing the hedgehog's color.
     */
    @Override
    public Color getColor() {return new Color(105 / 255.0, 42 / 255.0, 42 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Hedgehog.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the hedgehog.
     * - If valid, creates a new Hedgehog instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Hedgehog(this.x + 1, this.y));
            super.reproduce();
        }
    }
}

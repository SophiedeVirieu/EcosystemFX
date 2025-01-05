package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class JellyFish extends Animal {

    /**
     * Constructor for the JellyFish class.
     * 
     * - Initializes a JellyFish instance with the specified coordinates (x, y).
     * - Sets its attributes: species, predators, possible food, allowed terrains, speed and calories.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public JellyFish(int x, int y) {
        super(x, y);
        this.specie = species.JELLY_FISH;

        this.predators.add(species.TURTLE);
        this.myFood.add(TerrainResources.food.FISH);
        this.ground.add(grounds.WATER);

        this.speed = 1;
        this.calories = 3;

        setY(y);
    }

    /**
     * Returns the color associated with the JellyFish.
     * 
     * - The color is defined as turquoise.
     * 
     * @return A `Color` object representing the jellyfish's color.
     */
    @Override
    public Color getColor() {return new Color(0 / 255.0, 206 / 255.0, 209 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the JellyFish.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the jellyfish.
     * - If valid, creates a new JellyFish instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new JellyFish(this.x + 1, this.y));
            super.reproduce();
        }
    }
}

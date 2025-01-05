package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Seagull extends Animal {

    /**
     * Constructor for the Seagull class.
     * 
     * - Initializes a Seagull instance with the specified coordinates (x, y).
     * - Sets its attributes: species, preys, predators, possible food, allowed terrains, speed, and calories.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Seagull(int x, int y) {
        super(x, y);
        this.specie = species.SEAGULL;

        this.preys.add(species.CRAB);
        this.preys.add(species.SNAKE);
        this.predators.add(species.FALCON);
        this.predators.add(species.SNAKE);
        this.predators.add(species.SHARK);
        this.myFood.add(TerrainResources.food.FISH);
        this.ground.add(grounds.WATER);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.PLAIN);

        this.speed = 3;
        this.calories = 4;

        setY(y);
    }

    /**
     * Returns the color associated with the Seagull.
     * 
     * - The color is defined as white.
     * 
     * @return A `Color` object representing the seagull's color.
     */
    @Override
    public Color getColor() {return new Color(255 / 255.0, 255 / 255.0, 255 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Seagull.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the seagull.
     * - If valid, creates a new Seagull instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Seagull(this.x + 1, this.y));
            super.reproduce();
        }
    }
}

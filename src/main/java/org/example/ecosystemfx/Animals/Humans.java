package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Humans extends Animal {

    /**
     * Constructor for the Humans class.
     * 
     * - Initializes a Humans instance with the specified coordinates (x, y).
     * - Sets its attributes: species, prey, allowed terrains, possible food, speed, and calories.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Humans(int x, int y) {
        super(x, y);
        this.specie = species.HUMAN;

        this.preys.add(specie.CRAB);
        this.preys.add(specie.DEER);
        this.preys.add(specie.SNAKE);
        this.preys.add(specie.WOLF);
        this.preys.add(specie.SEAGULL);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.PLAIN);
        this.ground.add(grounds.FOREST);
        this.myFood.add(TerrainResources.food.BERRIES);

        this.speed = 3;
        this.calories = 12;

        setY(y);

    }

    /**
     * Returns the color associated with Humans.
     * 
     * - The color is defined as black.
     * 
     * @return A `Color` object representing the humans' color.
     */
    @Override
    public Color getColor() {return new Color(0 / 255.0, 0 / 255.0, 0 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of Humans.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for humans.
     * - If valid, creates a new Humans instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Humans(this.x +1, this.y));
            super.reproduce();
        }
    }
}

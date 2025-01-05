package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Turtle extends Animal {
    private final Color color = new Color(0 / 255.0, 100 / 255.0, 0 / 255.0, 1);

    /**
     * Constructor for the Turtle class.
     * 
     * - Initializes a Turtle instance with the specified coordinates (x, y).
     * - Sets its attributes: species, preys, predators, allowed terrains, food, speed, and calories.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Turtle(int x, int y) {
        super(x, y);
        this.specie = species.TURTLE;

        this.preys.add(species.JELLY_FISH);
        this.predators.add(species.FALCON);
        this.predators.add(species.SHARK);
        this.myFood.add(TerrainResources.food.ALGAE);
        this.ground.add(grounds.WATER);
        this.ground.add(grounds.SAND);

        this.speed = 1;
        this.calories = 9;

        setY(y);
    }

    /**
     * Returns the color associated with the Turtle.
     * 
     * - The color is defined as dark green.
     * 
     * @return A `Color` object representing the turtle's color.
     */
    @Override
    public Color getColor() {return new Color(0 / 255.0, 100 / 255.0, 0 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Turtle.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the turtle.
     * - If valid, creates a new Turtle instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Turtle(this.x + 1, this.y));
            super.reproduce();
        }
    }

}

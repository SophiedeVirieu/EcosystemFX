package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Snake extends Animal {

    /**
     * Constructor for the Snake class.
     * 
     * - Initializes a Snake instance with the specified coordinates (x, y).
     * - Sets its attributes: species, preys, predators, allowed terrains, speed, and calories.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Snake(int x, int y) {
        super(x, y);
        this.specie = species.SNAKE;

        this.preys.add(species.CRAB);
        this.predators.add(species.SEAGULL);
        this.predators.add(species.FALCON);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.PLAIN);

        this.speed = 2;
        this.calories = 5;

        setY(y);
    }

    /**
     * Returns the color associated with the Snake.
     * 
     * - The color is defined as green.
     * 
     * @return A `Color` object representing the snake's color.
     */
    @Override
    public Color getColor() {return new Color(34 / 255.0, 139 / 255.0, 34 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Snake.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the snake.
     * - If valid, creates a new Snake instance at the adjacent position and adds it
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

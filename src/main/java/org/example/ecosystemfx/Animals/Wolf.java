package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Wolf extends Animal {

    /**
     * Constructor for the Wolf class.
     * 
     * - Initializes a Wolf instance with the specified coordinates (x, y).
     * - Sets its attributes: species, preys, allowed terrains, speed, and possible food.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Wolf(int x, int y) {
        super(x, y);
        this.specie = species.WOLF;

        this.preys.add(species.DEER);
        this.preys.add(species.HEDGEHOG);
        this.ground.add(grounds.FOREST);
        this.ground.add(grounds.PLAIN);

        this.speed = 4;

        setY(y);
    }

    /**
     * Returns the color associated with the Wolf.
     * 
     * - The color is defined as a slate gray.
     * 
     * @return A `Color` object representing the wolf's color.
     */
    @Override
    public Color getColor() {return new Color(112 / 255.0, 128 / 255.0, 144 / 255.0, 1);}

    /**
     * Handles the reproduction behavior of the Wolf.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the wolf.
     * - If valid, creates a new Wolf instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Wolf(this.x + 1, this.y));
            super.reproduce();
        }
    }

}

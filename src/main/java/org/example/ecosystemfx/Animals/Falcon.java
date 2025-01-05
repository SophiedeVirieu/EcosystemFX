package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Falcon extends Animal {

     /**
     * Constructor for the Falcon class.
     * 
     * - Initializes a Falcon instance with the specified coordinates (x, y).
     * - Sets its attributes: species, preys, predators, allowed terrains, speed, and camouflage.
     * - Ensures that the position is on valid terrain using `setY(y)`.
     * 
     * @param x The initial x coordinate.
     * @param y The initial y coordinate.
     */
    public Falcon(int x, int y) {
        super(x, y);
        this.specie = species.FALCON;

        this.preys.add(species.SEAGULL);
        this.preys.add(species.SNAKE);
        this.preys.add(species.HEDGEHOG);
        this.predators.add(species.WOLF);
        this.ground.add(grounds.PLAIN);

        this.speed = 4;

        setY(y);
    }

        /**
     * Returns the color associated with the Falcon.
     * 
     * - The color is defined as grey.
     * 
     * @return A `Color` object representing the falcon's color.
     */
    @Override
    public Color getColor() {return new Color(169 / 255.0, 169 / 255.0, 169 / 255.0, 1);}

     /**
     * Handles the reproduction behavior of the Falcon.
     * 
     * - Checks if the terrain adjacent to the current position (x+1, y) is valid
     *   for the falcon.
     * - If valid, creates a new Falcon instance at the adjacent position and adds it
     *   to the simulation.
     * - Calls the superclass `reproduce` method to lower satiety.
     */
    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Falcon(this.x + 1, this.y));
            super.reproduce();
        }
    }
}

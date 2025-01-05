package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.base.Terrain2D;

public class Snake extends Animal {

    public Snake(int x, int y) {
        super(x, y);
        this.specie = species.SNAKE;

        this.preys.add(species.CRAB);
        this.predators.add(species.SEAGULL);
        this.predators.add(species.FALCON);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.PLAIN);

        this.speed = 2;
        this.camouflage = 3;
        this.calories = 5;

        setY(y);
    }
    @Override
    public Color getColor() {return new Color(34 / 255.0, 139 / 255.0, 34 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new Turtle(this.x + 1, this.y);
            super.reproduce();
        }
    }
}

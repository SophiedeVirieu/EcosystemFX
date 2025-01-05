package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Wolf extends Animal {

    public Wolf(int x, int y) {
        super(x, y);
        this.specie = species.WOLF;

        this.preys.add(species.DEER);
        this.preys.add(species.HEDGEHOG);
        this.ground.add(grounds.FOREST);
        this.ground.add(grounds.PLAIN);

        this.speed = 4;
        this.camouflage = 3;

        setY(y);
    }

    @Override
    public Color getColor() {return new Color(112 / 255.0, 128 / 255.0, 144 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Wolf(this.x + 1, this.y));
            super.reproduce();
        }
    }

}

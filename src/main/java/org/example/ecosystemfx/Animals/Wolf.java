package org.example.ecosystemfx.Animals;

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
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new Wolf(this.x + 1, this.y);
            super.reproduce();
        }
    }

}

package org.example.ecosystemfx.Animals;

import org.example.ecosystemfx.base.Terrain2D;

public class Falcon extends Animal {

    public Falcon(int x, int y) {
        super(x, y);
        this.specie = species.FALCON;

        this.preys.add(species.SEAGULL);
        this.preys.add(species.SNAKE);
        this.preys.add(species.HEDGEHOG);
        this.predators.add(species.WOLF);
        this.ground.add(grounds.PLAIN);

        this.speed = 4;
        this.camouflage = 1;
    }

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new Falcon(this.x + 1, this.y);
            super.reproduce();
        }
    }
}

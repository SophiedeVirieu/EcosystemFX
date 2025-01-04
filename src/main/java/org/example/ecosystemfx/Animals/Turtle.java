package org.example.ecosystemfx.Animals;

import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Terrain2D;

public class Turtle extends Animal {

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
        this.camouflage = 1;
        this.calories = 9;
    }

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new Turtle(this.x + 1, this.y);
            super.reproduce();
        }
    }

}

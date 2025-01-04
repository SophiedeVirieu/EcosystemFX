package org.example.ecosystemfx.Animals;

import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Terrain2D;

public class Hedgehog extends Animal {

    public Hedgehog(int x, int y) {
        super(x, y);
        this.specie = species.HEDGEHOG;

        this.predators.add(species.FALCON);
        this.predators.add(species.WOLF);
        this.myFood.add(TerrainResources.food.GRASS);
        this.myFood.add(TerrainResources.food.BERRIES);
        this.ground.add(grounds.FOREST);
        this.ground.add(grounds.PLAIN);

        this.speed = 1;
        this.camouflage = 2;
        this.calories = 5;
    }

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new Hedgehog(this.x + 1, this.y);
            super.reproduce();
        }
    }
}

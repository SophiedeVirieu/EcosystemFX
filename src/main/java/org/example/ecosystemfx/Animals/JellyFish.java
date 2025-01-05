package org.example.ecosystemfx.Animals;

import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Terrain2D;

public class JellyFish extends Animal {

    public JellyFish(int x, int y) {
        super(x, y);
        this.specie = species.JELLY_FISH;

        this.predators.add(species.TURTLE);
        this.myFood.add(TerrainResources.food.FISH);
        this.ground.add(grounds.WATER);

        this.speed = 1;
        this.camouflage = 1;
        this.calories = 3;

        setY(y);
    }

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new JellyFish(this.x + 1, this.y);
            super.reproduce();
        }
    }
}

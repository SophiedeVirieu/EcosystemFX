package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
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
    public Color getColor() {return new Color(0 / 255.0, 206 / 255.0, 209 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new JellyFish(this.x + 1, this.y);
            super.reproduce();
        }
    }
}

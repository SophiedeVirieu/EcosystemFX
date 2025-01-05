package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Terrain2D;

public class Crab extends Animal {

    public Crab(int x, int y) {
        super(x, y);
        this.specie = species.CRAB;

        this.predators.add(species.SEAGULL);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.WATER);
        this.myFood.add(TerrainResources.food.ALGAE);
        this.myFood.add(TerrainResources.food.FISH);

        this.speed = 2;
        this.camouflage = 3;
        this.calories = 4;

        setY(y);

    }
    @Override
    public Color getColor() {return new Color(0 / 255.0, 0 / 255.0, 0 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            new Crab(this.x +1, this.y);
            super.reproduce();
        }
    }
}

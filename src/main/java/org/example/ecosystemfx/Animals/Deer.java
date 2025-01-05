package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Deer extends Animal {
    public Deer(int x, int y) {
        super(x, y);
        this.specie = species.DEER;

        this.predators.add(species.WOLF);
        this.myFood.add(TerrainResources.food.GRASS);
        this.myFood.add(TerrainResources.food.BERRIES);
        this.ground.add(grounds.FOREST);
        this.ground.add(grounds.PLAIN);

        this.speed = 4;
        this.camouflage = 2;
        this.calories = 8;

        setY(y);
    }
    @Override
    public Color getColor() {return new Color(139 / 255.0, 69 / 255.0, 19 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Deer(this.x + 1, this.y));
            super.reproduce();
        }
    }
}

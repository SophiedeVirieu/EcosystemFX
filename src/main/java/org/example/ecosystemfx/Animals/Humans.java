package org.example.ecosystemfx.Animals;
import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Humans extends Animal {

    public Humans(int x, int y) {
        super(x, y);
        this.specie = species.HUMAN;

        this.preys.add(specie.CRAB);
        this.preys.add(specie.DEER);
        this.preys.add(specie.SNAKE);
        this.preys.add(specie.WOLF);
        this.preys.add(specie.SEAGULL);
        this.ground.add(grounds.SAND);
        this.ground.add(grounds.PLAIN);
        this.ground.add(grounds.FOREST);
        this.myFood.add(TerrainResources.food.BERRIES);

        this.speed = 3;
        this.camouflage = 2;
        this.calories = 12;

        setY(y);

    }
    @Override
    public Color getColor() {return new Color(0 / 255.0, 0 / 255.0, 0 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Humans(this.x +1, this.y));
            super.reproduce();
        }
    }
}

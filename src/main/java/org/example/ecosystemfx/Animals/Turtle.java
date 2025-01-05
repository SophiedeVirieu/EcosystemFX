package org.example.ecosystemfx.Animals;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;

public class Turtle extends Animal {
    private final Color color = new Color(0 / 255.0, 100 / 255.0, 0 / 255.0, 1);

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

        setY(y);
    }
    @Override
    public Color getColor() {return new Color(0 / 255.0, 100 / 255.0, 0 / 255.0, 1);}

    @Override
    public void reproduce() {
        if (this.ground.contains(Terrain2D.getTerrain(this.x +1, this.y))) {
            Simulation.animals.add(new Turtle(this.x + 1, this.y));
            super.reproduce();
        }
    }

}

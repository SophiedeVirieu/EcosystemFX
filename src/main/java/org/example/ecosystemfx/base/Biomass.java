package org.example.ecosystemfx.base;

import org.example.ecosystemfx.ihm.IHM;

import java.util.ArrayList;
import java.util.List;

public abstract class Biomass {

    public enum species {CRAB, JELLY_FISH, SEAGULL, DEER, WOLF, HEDGEHOG,
        SNAKE, FALCON, SHARK, TURTLE, HUMAN};
    public enum grounds {WATER, SAND, PLAIN, FOREST};
    public enum meteos {SUN, RAIN}; //voir si on en fait quelque chose

    protected List<species> predators;
    protected List<grounds> ground;
    public int calories;
    public int bites;
    protected int x;
    protected int y;

    public void setY(int y) {
        if (this.ground.contains(Terrain2D.getTerrain(this.x, this.y))) {
            this.y = y;
        } else {
            int radius = 1;

            //search a near authorized terrain
            while (true) {
                List<int[]> positions = new ArrayList<>();

                for (int dy = -radius; dy <= radius; dy++) {
                    int dx = radius - Math.abs(dy);

                    positions.add(new int[] {this.x + dx, this.y + dy});
                    positions.add(new int[] {this.x - dx, this.y + dy});
                    positions.add(new int[] {this.x + dx, this.y - dy});
                    positions.add(new int[] {this.x - dx, this.y - dy});
                }

                for (int[] pos : positions) {
                    int newX = pos[0];
                    int newY = pos[1];

                    if (isValidPosition(newX, newY) && this.ground.contains(Terrain2D.getTerrain(newX, newY))) {
                        this.x = newX;
                        this.y = newY;
                        return;
                    }
                }

                radius++;
            }
        }
    }

    public Biomass(int x, int y){
        this.x = x;
        this.y = y;
        this.bites = 0;
        this.predators = new ArrayList<species>();
        this.ground = new ArrayList<grounds>();
    }

    protected static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < IHM.width && y >= 0 && y < IHM.height;
    }

}

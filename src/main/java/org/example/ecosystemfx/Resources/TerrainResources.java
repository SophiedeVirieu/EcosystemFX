package org.example.ecosystemfx.Resources;

import javafx.scene.paint.Color;
import org.example.ecosystemfx.base.Biomass;

public abstract class TerrainResources extends Biomass {

    public enum food {FISH, ALGAE, GRASS, BERRIES};
    protected int calories;
    protected int TTR_Max; // Time To Regenerate in number of rounds
    protected int TTR;
    protected int x;
    protected int y;

    public abstract Color getColor();

    public void setBites(int bites) {
        if (bites > 3) {
            Alive = false;
        }
        this.bites = bites;
    }
    public void setAlive(boolean alive) {
        if (alive) {
            this.bites = 3;
        }
        Alive = alive;
    }

    protected boolean Alive; // If the plant as not been eaten yet, it's alive, if not, it's dead

    public TerrainResources(int x, int y, boolean alive, int TTR, int TTR_Max, int calories) {
        super(x, y);
        Alive = alive;
        this.y = y;
        this.x = x;
        this.TTR = TTR;
        this.TTR_Max = TTR_Max;
        this.calories = calories;
        this.bites = 3; // a resource is eaten in once
    }


    // Getter
    public int getCalories() {
        return calories;
    }

    public int getTTR() {
        return TTR;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTTR_Max() {
        return TTR_Max;
    }

    public boolean isAlive() {
        return Alive;
    }

    // Methode de class
    public void Regeneartion(int weather) {
        if (!this.Alive) {
            if (this.TTR <= 0) {
                this.TTR = 0;
                this.Alive = true;
            } else {
                this.TTR -= 1*weather;
            }
        }
    }

    public void eaten() {
        this.Alive = false;
        this.TTR = this.TTR_Max;
    }
}

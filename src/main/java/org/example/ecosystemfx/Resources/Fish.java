package org.example.ecosystemfx.Resources;

import javafx.scene.paint.Color;

public class Fish extends TerrainResources {
    public Fish(int x, int y) {
        super(x,y, true,0,3,4);
    }
    @Override
    public Color getColor() {return new Color(0 / 255.0, 72 / 255.0, 204 / 255.0, 1);}
}

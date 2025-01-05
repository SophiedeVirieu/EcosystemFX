package org.example.ecosystemfx.Resources;

import javafx.scene.paint.Color;

public class Algae extends TerrainResources {

    public Algae(int x, int y) {
        super(x,y, true, 0,2,2);
    }
    @Override
    public Color getColor() {return new Color(200 / 255.0, 200 / 250.0, 0 / 255.0, 1);}
}

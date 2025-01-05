package org.example.ecosystemfx.Resources;

import javafx.scene.paint.Color;

public class Berry extends TerrainResources {
    public Berry(int x, int y) {
        super(x,y, true, 0,2,3);
    }
    @Override
    public Color getColor() {return new Color(178 / 255.0, 34 / 255.0, 34 / 255.0, 1);}
}

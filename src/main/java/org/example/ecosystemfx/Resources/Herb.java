package org.example.ecosystemfx.Resources;

import javafx.scene.paint.Color;

public class Herb extends TerrainResources {
    public Herb(int x, int y) {
        super(x,y, true,0,1,1);
    }
    @Override
    public Color getColor() {return new Color(111 / 255.0, 125 / 255.0, 29 / 255.0, 1);}
}

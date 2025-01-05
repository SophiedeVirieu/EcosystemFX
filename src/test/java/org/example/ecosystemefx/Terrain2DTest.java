package org.example.ecosystemefx;

import org.example.ecosystemfx.base.Biomass;
import org.example.ecosystemfx.base.Terrain2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Terrain2DTest {

    @Test
    void testTerrainGeneration() {
        Terrain2D terrain = new Terrain2D(10, 10);
        assertNotNull(terrain, "Terrain2D instance should not be null.");
    }

    @Test
    void testGetTerrain() {
        Terrain2D terrain = new Terrain2D(10, 10);
        Biomass.grounds ground = Terrain2D.getTerrain(0, 0);
        assertNotNull(ground, "Ground type should not be null.");
    }

    @Test
    void testGetCanvas() {
        Terrain2D terrain = new Terrain2D(10, 10);
        assertNotNull(terrain.getCanvas(), "Canvas should not be null.");
    }
}

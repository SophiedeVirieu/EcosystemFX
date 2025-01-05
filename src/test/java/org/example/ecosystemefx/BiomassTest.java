package org.example.ecosystemefx;

import org.example.ecosystemfx.base.Biomass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BiomassTest {

    @Test
    void testConstructorInitialValues() {
        Biomass biomass = new Biomass(5, 10) {};
        assertEquals(5, biomass.x, "Initial X coordinate should be 5.");
        assertEquals(10, biomass.y, "Initial Y coordinate should be 10.");
        assertEquals(0, biomass.bites, "Initial bites count should be 0.");
        assertTrue(biomass.predators.isEmpty(), "Predators list should be empty initially.");
        assertTrue(biomass.ground.isEmpty(), "Ground list should be empty initially.");
    }

    @Test
    void testSetYValidPosition() {
        Biomass biomass = new Biomass(0, 0) {};
        biomass.ground.add(Biomass.grounds.PLAIN);
        // Mock the Terrain2D.getTerrain method to always return PLAIN for this test
        assertDoesNotThrow(() -> biomass.setY(5), "Setting a valid Y position should not throw exceptions.");
    }

    @Test
    void testSetYInvalidPosition() {
        Biomass biomass = new Biomass(0, 0) {};
        biomass.ground.add(Biomass.grounds.WATER);
        // Simulate invalid terrain
        assertDoesNotThrow(() -> biomass.setY(5), "Searching for a valid terrain should not throw exceptions.");
    }
}

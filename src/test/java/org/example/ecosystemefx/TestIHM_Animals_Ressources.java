package org.example.ecosystemefx;

import org.example.ecosystemfx.Animals.Animal;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.BadGroundException;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;
import org.example.ecosystemfx.ihm.IHM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EcosystemFXTests {

    // Tests pour IHM
    @Test
    void testIHMSetup() throws BadGroundException {
        IHM ihm = new IHM();
        assertNotNull(ihm);
    }

    // Tests pour TerrainResources
    @Test
    void testTerrainResourcesRegeneration() {
        TerrainResources resource = new TerrainResources(0, 0, false, 5, 10, 100) {
            @Override
            public Color getColor() {
                return Color.GREEN;
            }
        };
        resource.Regeneartion(1);
        assertEquals(4, resource.getTTR());
        resource.setAlive(true);
        assertTrue(resource.isAlive());
    }

    // Tests pour Animal
    @Test
    void testAnimalMovement() {
        Animal animal = new Animal(5, 5) {
            @Override
            public Color getColor() {
                return Color.BROWN;
            }
        };
        animal.move(10, 10);
        assertTrue(animal.getX() <= 10 && animal.getY() <= 10);
    }

    @Test
    void testAnimalEat() {
        Animal predator = new Animal(0, 0) {
            @Override
            public Color getColor() {
                return Color.RED;
            }
        };
        Biomass prey = new Biomass(0, 0, 10);
        predator.eat(prey);
        assertEquals(10, predator.getSatiety());
    }

    @Test
    void testAnimalDecisionMaking() {
        Animal animal = new Animal(0, 0) {
            @Override
            public Color getColor() {
                return Color.BLUE;
            }
        };
        Simulation.Decisions decision = animal.decide();
        assertNotNull(decision);
    }
}
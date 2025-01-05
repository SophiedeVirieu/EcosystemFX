package org.example.ecosystemfx.Animals;

import org.example.ecosystemfx.Resources.Algae;
import org.example.ecosystemfx.Resources.Fish;
import org.example.ecosystemfx.Resources.Herb;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.ecosystemfx.base.Biomass;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    @DisplayName("EATING TEST")
    void eat() {
        Crab c = new Crab(10,10);
        Algae a = new Algae(10,10);
        Fish f = new Fish(10,10);
        Herb h = new Herb(10,10);
        c.eat(a);
        assertEquals(c.calories, 4);
    }

    @Test
    void move() {
    }

    @Test
    void reproduce() {
    }

    @Test
    void detectFood() {
    }

    @Test
    void detectPredator() {
    }

    @Test
    void migrate() {
    }

    @Test
    void flee() {
    }

    @Test
    void attack() {
    }

    @Test
    void decide() {
    }

    @Test
    void getColor() {
    }
}
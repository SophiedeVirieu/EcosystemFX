package org.example.ecosystemfx.Animals;

import org.example.ecosystemfx.ihm.IHM;
import org.example.ecosystemfx.Resources.TerrainResources;
import org.example.ecosystemfx.base.Biomass;
import org.example.ecosystemfx.base.Terrain2D;
import org.example.ecosystemfx.base.Simulation;

import java.util.*;
import static java.lang.Integer.min;

public abstract class Animal extends Biomass {

    public int sat_max = 10;

    public enum sexes {M, F};
    private int satiety;
    protected Biomass.species specie;
    protected List<Biomass.species> preys;
    protected List<TerrainResources.food> myFood;
    protected int voracity;
    protected sexes sex;
    protected int speed;
    protected int camouflage;
    protected List<Integer> detectedFood;
    protected List<Integer> toFlee = new ArrayList<>(Arrays.asList(null, 0));
    public Biomass toEat = null;


    public Animal(int x, int y) {
        super(x, y);
        Random rand = new Random();
        this.satiety = rand.nextInt(sat_max-1)+1;
        this.voracity = rand.nextInt(10);

        if (this.satiety %2 == 0){this.sex = sexes.M;}
        else{this.sex = sexes.F;}

        this.preys = new ArrayList<Biomass.species>();
        this.myFood = new ArrayList<TerrainResources.food>();
        this.detectedFood = new ArrayList<>(Arrays.asList(null, 0));
    }

    /**
     * Simulates the attack of another biomass entity (prey or food).
     * The prey's "bites" counter is increased.
     * If the prey's bites exceed a threshold, the prey dies, and the predator increases its satiety.
     *
     * After eating, the animal resets its detected food coordinates, signifying that no food is currently in sight.
     *
     * @param eaten The biomass entity being eaten by the animal.
     *              It is supposed to be one of the animal's preys or food.
     */
    public void eat(Biomass eaten) {

        eaten.bites += 2;
        if (eaten.bites>3){ //if food is dead
            //Simulation is in charge of killing it
            this.satiety = min(sat_max, this.satiety + eaten.calories);
            this.toEat = null;
        }
        this.detectedFood.set(0, null); // means that the animal has no food in sight

    }

    /**
     * Moves the animal toward a specified target position (x_aim, y_aim).
     * The movement is calculated using a normalized vector scaled by the animal's speed.
     * The method ensures that the animal does not move out of bounds or into forbidden terrain.
     *
     * @param x_aim The x-coordinate of the target position.
     * @param y_aim The y-coordinate of the target position.
     */
    protected void move(int x_aim, int y_aim) {

        if (Math.sqrt(Math.pow(x_aim - this.x, 2) + Math.pow(y_aim - this.y, 2))<= this.speed){
            if (!Biomass.isValidPosition(x_aim, y_aim)
                        || !this.ground.contains(Terrain2D.getTerrain(x_aim, y_aim))){
                return;
            }
            this.x = x_aim;
            this.y = y_aim;
            return;
        }

        //Calculation of the vector
        int x_v = x_aim - this.x;
        int y_v = y_aim - this.y;
        double norm = Math.sqrt(x_v*x_v + y_v*y_v);

        //unit vector * speed
        if (this.speed > norm){norm = this.speed;} // don't go further than the aim

        x_v = (int)(x_v*this.speed / norm);
        y_v = (int)(y_v*this.speed / norm);

        //Move to the arrival case
        int new_x = this.x + x_v;
        int new_y = this.y + y_v;

        if (!Biomass.isValidPosition(new_x, new_y)){

            //out of bounds: don't move
            return;
        }
        else if (!this.ground.contains(Terrain2D.getTerrain(new_x, new_y))){

            //forbidden terrain : don't go so fast
            if (new_x>this.x){new_x = this.x+1;} else {new_x = Math.abs(this.x-1);}
            if (new_y>this.y){new_y = this.y+1;} else {new_y = Math.abs(this.y-1);}

            if (!this.ground.contains(Terrain2D.getTerrain(new_x, new_y))){
                System.out.println("pb of ground");
                return;
            }
        }

        this.x = new_x;
        this.y = new_y;

    }

    /**
     * Simulates the reproduction process.
     * This method lowers the animal's satiety.
     * It is intended to be called only if the animal is female.
     *
     * This method is overwritten in all the subclasses, in order to create another instance of animal.
     */
    public void reproduce(){
        this.satiety = 2;
    }

    /**
     * Searches for the nearest available food within the animal's detection radius given by its voracity.
     * The method scans for both terrain-based food resources and preys.
     * It updates the detectedFood list with the coordinates of the nearest food, if any.
     */
    protected void detectFood(){

        List<List<Integer>> detected = new ArrayList<>(); //the list of coordinates of detected food

        for (TerrainResources.food f : myFood){
            detected.addAll(Terrain2D.Neighbours(this.x, this.y, this.voracity, Terrain2D.foodToList.get(f)));
        }

        for (Animal a : Simulation.animals){
            if (this.preys.contains(a.specie) &&
                    Math.sqrt(Math.pow(a.x - this.x, 2) + Math.pow(a.y - this.y, 2)) < this.voracity){
                detected.add(List.of(a.x, a.y));
            }
        }

        // comparison of the distances
        List<Double> distances = new ArrayList<>();
        for (List<Integer> coords : detected){
            distances.add(Math.sqrt(Math.pow(coords.get(0) - this.x, 2) + Math.pow(coords.get(1) - this.y, 2)));
        }
        try {
            int indexMin = distances.indexOf(Collections.min(distances));
            List<Integer> pos_food = detected.get(indexMin);
            this.detectedFood.set(0, pos_food.get(0));
            this.detectedFood.set(1, pos_food.get(1));
        }
        catch (Exception e){ // if the list is empty (ie no food in sight)
            this.detectedFood.set(0, null);
        }

    }

    /**
     * Detects the nearest predator within a fixed radius around the animal.
     * If a predator is detected, its coordinates are saved in the toFlee list.
     * The method calculates distances to determine the closest predator.
     */
    public void detectPredator(){

        List<List<Integer>> detected = new ArrayList<>();
        int radius = 7;
        for (Animal a : Simulation.animals){
            if (a.x >= this.x - radius && a.x <= this.x + radius &&
                    a.y >= this.y - radius && a.y <= this.y + radius &&
                        this.predators.contains(a.specie)){

                detected.add(List.of(a.x, a.y));
            }
        }

        List<Double> distances = new ArrayList<>();
        for (List<Integer> coords : detected){
            distances.add(Math.sqrt(Math.pow(coords.get(0) - this.x, 2) + Math.pow(coords.get(1) - this.y, 2)));
        }
        try {
            int indexMin = distances.indexOf(Collections.min(distances));
            List<Integer> pos_pred = detected.get(indexMin);
            this.toFlee.set(0, pos_pred.get(0));
            this.toFlee.set(1, pos_pred.get(1));
        }
        catch (Exception e){ // if the list is empty (ie no predator in sight)
            this.toFlee.set(0, null);
        }

    }

    /**
     * Moves the animal to a random nearby location within a range of -5 to 5 units in both x and y directions.
     * The movement simulates random migration behavior. This method uses the `move` function for execution.
     */
    public void migrate(){
        // TODO : meteo
        Random rand = new Random();
        int xAim = rand.nextInt(-5, 5);
        int yAim = rand.nextInt(-5, 5);
        move(xAim, yAim);
    }

    /**
     * Moves the animal away from a detected predator.
     * The target position is calculated as the symmetric point of the predator's position with respect to the current position.
     * This method relies on the move() function.
     */
    public void flee(){
        move(2*this.x -this.toFlee.get(0), 2*this.y -this.toFlee.get(1));
    }

    /**
     * Moves the animal toward the detected food's position.
     * The food's coordinates are in the detectedFood list, and the move() function is used.
     */
    public void attack(){
        move(detectedFood.get(0), detectedFood.get(1));
    }

    /**
     * Determines the animal's next action.
     * The decision is made based on these criteria:
     * - If the animal is a female with maximum satiety, it decides to reproduce.
     * - If food is detected at the current position, the animal decides to eat.
     * - If a predator is detected, the animal decides to flee.
     * - If no food is detected nearby, the animal decides to migrate.
     * - Otherwise, the animal decides to attack.
     *
     * @return The next action to be performed by the animal, represented as a value from the Simulation.Decisions enum.
     */
    public Simulation.Decisions decide(){
        /* criteria between FLEE and ATTACK : what is the nearest */

        if (this.sex == sexes.F && this.satiety >= sat_max-1){
            return Simulation.Decisions.REPRODUCE;
        }

        List<Integer> detected = new ArrayList<>(); //the list of coordinates of detected food

        for (TerrainResources.food f : myFood){
            try {
                detected = Terrain2D.Neighbours(this.x, this.y, 0, Terrain2D.foodToList.get(f)).getFirst();
            }
            catch (NoSuchElementException e){ // if nothing is detected
                break;
            }
            if (detected.get(0) == this.x && detected.get(1) == this.y){
                // search the food to eat in the list
                for (TerrainResources r : Terrain2D.listTerrainResources){
                    if (r.getX() == this.x && r.getY() == this.y){
                        this.toEat = r;
                        r.eaten();
                        return Simulation.Decisions.EAT;
                    }
                }
            }
        }

        for (Animal a : Simulation.animals){
            if (this.x == a.x && this.y == a.y &&
                    this.preys.contains(a.specie)){
                this.toEat = a;
                return Simulation.Decisions.EAT;
            }
        }

        this.detectPredator();
        if (this.toFlee.getFirst() != null){
            return Simulation.Decisions.FLEE;
        }

        this.detectFood();
        // TODO : meteo
        if (this.detectedFood.get(0) == null){
            return Simulation.Decisions.MIGRATE;
        }
        return Simulation.Decisions.ATTACK;
    }

}

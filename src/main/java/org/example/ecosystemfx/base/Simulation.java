package org.example.ecosystemfx.base;
import org.example.ecosystemfx.Animals.*;
import org.example.ecosystemfx.Resources.TerrainResources;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import static org.example.ecosystemfx.base.Terrain2D.foodToList;

/**
 * The Simulation class runs the simulation turn by turn:
 * it proceeds as following for each turn :
 * Gather the intended action of each animal
 * In a predetermined order, give each animal the possibility to realize it
 * Ensure proper transmission of the information to the terrain class
 * End the turn by removing any dead animals, adding new ones, etc...
 *
 * @author Paul Morvan
 */

public class Simulation {
    /**
     * Represents possible behavioral decisions an animal can take each turn.
     * <\p>
     * Each decision corresponds to a specific action that animals may take within
     * the environment based on their internal state, environmental factors, and interactions
     * with other entities.
     * @see Animal
     * </\p>
     *<\p>
     * Enum values:
     * - REPRODUCE: Represents the decision to reproduce and create offspring. TODO : CHANGE THIS DESCIRPTION : Useless as is, explain the rules for reproducing/other....
     * - EAT: Represents the decision to consume food to replenish energy or resources.
     * - FLEE: Represents the decision to escape from predators or threats.
     * - ATTACK: Represents the decision to engage in offensive action towards another entity.
     * - MIGRATE: Represents the decision to move to a new location, possibly for better resources or conditions. </\p>
     */
    public enum Decisions {REPRODUCE, EAT, FLEE, ATTACK, MIGRATE} //Possible behaviors for the animals

    /**
     * Flag indicating the end of the simulation. 0 if still running, 1 if the simulation has ended.
     */
    private int end;
    /**
     * Holds the number of the current turn.
     */
    private int turn_number;
    /**
     * List of the animals currently in the simulation. Updates each turn
     */
    public static ArrayList<Animal> animals; //TODO : Is it a good idea to have it as static ?
    /**
     * Size of the simulation board [width, length]
     */
    private int[] sizewl; //TODO: Is it useful?
    //private terrain board;
    public static Random rand = new Random(); //TODO : Is it useful to have it as a class constant ? can I not create in the method where it is used ?

    public int pastWeather = 1;

    /**
     * This function runs the simulation.
     * @param size
     *      The size of the game board (width,height)
     * @param animal_numbers
     *      The number of animals from each species in order : Crab,Deer,Falcon,Hedgehog,JellyFish,Seagull,Snake,Turtle,Wolf
     * @throws BadGroundException if an animal is on a non-compatible terrain type
     */
    public Simulation(int[] size, int[] animal_numbers) throws BadGroundException {

        this.end = 0;
        this.turn_number = 0;
        init_terrain(size);
        init_animals(animal_numbers);
    }


    public void start_sim(){
        next_turn();
    }

    public void stop_sim(){
        this.end = 1;
        ending();
        System.out.println("Simulation ended.");
    }

    public void next_turn(){
        turn_simulation();
    }
    
    /**
     * Simulates a turn : It asks what each animal wants to do, then makes them do it.
     */
    public void turn_simulation() {
        this.turn_number += 1;
        ArrayList<Animal> flee = new ArrayList<Animal>();
        ArrayList<Animal> attack = new ArrayList<Animal>();
        ArrayList<Animal> reproduce = new ArrayList<Animal>();
        ArrayList<Animal> migrate = new ArrayList<Animal>();
        ArrayList<Animal> eat = new ArrayList<Animal>();
        for (Animal a : this.animals) { //Ask each animal what it wants to do
            switch (a.decide()) {
                case Decisions.FLEE:
                    flee.add(a);
                    break;
                case Decisions.ATTACK:
                    attack.add(a);
                    break;
                case Decisions.REPRODUCE:
                    reproduce.add(a);
                    break;
                case Decisions.MIGRATE:
                    migrate.add(a);
                    break;
                case Decisions.EAT:
                    eat.add(a);
                    break;
            }
        }
        for (Animal a : flee) { //Let the animal do what it wanted to do. Will have to be completed when the functions are implemented in Animal.java
            a.flee();
        }
        for (Animal a : attack) {
            a.attack();
        }
        for (Animal a : reproduce) {
            try {
                a.reproduce();
            }
            catch (ArrayIndexOutOfBoundsException e) { // bounds of the terrain reached
                a.x--;
                a.reproduce();
            }
        }
        for (Animal a : migrate) {
            a.migrate();
        }
        for (Animal a : eat) { //TODO : HAVE TO LOOK WHAT FOOD TO GIVE TO EAT TO THE ANIMAL based on its coordinates !
            int x = a.x;
            int y = a.y;

            /*
            Here, look for the thing the animal eats...
             */
            a.eat(a.toEat);
        }
        turn_end();
    }


    /**
     * Manages the end of each turn; removing killed animals, TODO : OTHER THINGS ??
     */
    private void turn_end(){
        ArrayList<Animal> survivors = new ArrayList<Animal>(); //Is a new array list each turn ?? Normally yes, bc it is a local variable
        for (int i = 0; i < animals.size(); i++) { //Kill related animals
            Animal a = animals.get(i);
            if (a.bites < 4) {
                survivors.add(a);
            }
            else{
                //a.kill(); //Does an animal have to do something when killed?

            }
        }
        int weather = weather(pastWeather);
        for (int i = 0; i < foodToList.size(); i++) {
            TerrainResources food = (TerrainResources) foodToList.get(i);
            food.Regeneartion(weather);
        }
        pastWeather = weather;
        this.animals = survivors; //Replace the list with the updated list of animals. DOES IT WORK ? SHOULD I COPY THE LIST BC SURVIVORS WILL BE DESTROYED AT END OF FUNTION CALL ?
    }

    private int weather(int pastWeather) {
        float propability = rand.nextFloat();
        if (pastWeather == 0){ // drought
            if (propability < 0.80){
                return 0;
            } else if (propability < 0.9){
                return 1;
            } else {
                return 2;
            }
        }else if (pastWeather == 1){ // normal weather
            if (propability < 0.9){
                return 1;
            }else if (propability < 0.95){
                return 0;
            }else {
                return 2;
            }
        }else { // rain
            if (propability < 0.75){
                return 2;
            }else if (propability < 0.9){
                return 1;
            }else {
                return 0;
            }
        }
    }


    /**
     * Manages the end of the simulation, printing information, and TODO : OTHER THINGS ?
     */
    public void ending(){
        /*
        This function ensures proper ending of the simulation. It should print/store information about the sim, and maybe close some things ?
         */
        //TODO
        System.out.println("Simulation ended.");
        System.out.println("Number of turns : " + this.turn_number);
        System.out.println("Number of animals at the end: " + this.animals.size());
        //TODO : Implement a way to know why the sim ended
    }

    /**
     * Initializes the terrain
     * @param size
     * size [width, length] of the simulation board
     * @see Simulation
     */
    private void init_terrain(int[] size){
        this.sizewl = new int[2];
        this.sizewl[0]=size[0];
        this.sizewl[1]=size[1];
    }


    /**
     * Creates the animals
     * @param animal_numbers
     * array containing the desired starting number of animals
     * [Crab,Deer,Falcon,Hedgehog,JellyFish,Seagull,Snake,Turtle,Wolf]
     * @throws BadGroundException when an animal is placed on an incompatible terrain type
     */
    private void init_animals(int[] animal_numbers) throws BadGroundException {
        int n = 0; //Index of each animal type
        this.animals = new ArrayList<Animal>(); //initializes the list
        for (int i = 0; i < animal_numbers.length; i++){
            for (int j = 0; j < animal_numbers[i]; j++){
                switch (i){ //Is there a better way to do this ?? Like by putting class references into an array ?
                    case 0:
                        this.animals.add(new Crab(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 1:
                        this.animals.add(new Deer(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 2:
                        this.animals.add(new Falcon(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 3:
                        this.animals.add(new Hedgehog(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 4:
                        this.animals.add(new JellyFish(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 5:
                        this.animals.add(new Seagull(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 6:
                        this.animals.add(new Snake(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 7:
                        this.animals.add(new Turtle(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                    case 8:
                        this.animals.add(new Wolf(rand.nextInt(sizewl[0]), rand.nextInt(sizewl[1])));
                        break;
                }
            }
            n += animal_numbers[i]; // Next index
        }
    }

    public ArrayList<Animal> getAnimals() {
        return new ArrayList<>(animals); // return a copy to preserve immutability
    }

    public static void main(String[] args) throws BadGroundException {
        Scanner scan = new Scanner(System.in);
        int[] size = new int[2];
        System.out.println("Enter the board width");
        size[0] = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the board height");
        size[1] = Integer.parseInt(scan.nextLine());

        System.out.println("Enter the number of animals with this syntax : ");
        System.out.println("Crab,Deer,Falcon,Hedgehog,JellyFish,Seagull,Snake,Turtle,Wolf");
        String s = scan.nextLine();
        String[] result = s.split("[,]");
        int[] animal_numbers = new int[result.length];
        try{
            for (int i = 0; i < result.length; i++){
                int x = Integer.parseInt(String.valueOf(result[i]));
                animal_numbers[i] = x;
            }
        }
        catch(Exception e){
            System.out.println("Please enter the correct data: ");
            System.out.println("Crab,Deer,Falcon,Hedgehog,JellyFish,Seagull,Snake,Turtle,Wolf");
        }

        Simulation sim = new Simulation(size, animal_numbers);

        while(sim.end == 0){sim.turn_simulation();}
        sim.ending();
    }
}

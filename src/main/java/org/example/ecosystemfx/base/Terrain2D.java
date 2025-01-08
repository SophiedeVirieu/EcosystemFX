package org.example.ecosystemfx.base;

import org.example.ecosystemfx.Animals.Animal;
import org.example.ecosystemfx.Resources.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class Terrain2D extends Canvas {
    private static int[][] heights;
    private static final int cellSize = 10;
    public static final List<List<Integer>> list_Fish = new ArrayList<>();
    public static final List<List<Integer>> list_Algae = new ArrayList<>();
    public static final List<List<Integer>> list_Herb = new ArrayList<>();
    public static final List<List<Integer>> list_Berry = new ArrayList<>();
    public static final Map<TerrainResources.food, List<List<Integer>>> foodToList = new HashMap<>();
    private final Canvas canvas;
    public static List<org.example.ecosystemfx.Resources.TerrainResources> listTerrainResources;
    private final Color fishColor = new Color(0 / 255.0, 72 / 255.0, 204 / 255.0, 1);
    private final Color algaeColor = new Color(200 / 255.0, 200 / 250.0, 0 / 255.0, 1);
    private final Color berryColor = new Color(178 / 255.0, 34 / 255.0, 34 / 255.0, 1);
    private final Color herbColor = new Color(111 / 255.0, 125 / 255.0, 29 / 255.0, 1);
    private final Color waterColor = new Color(63 / 255.0, 72 / 255.0, 204 / 255.0, 1);
    private final Color sandColor = new Color(255 / 255.0, 250 / 250.0, 0 / 255.0, 1);
    private final Color plainColor = new Color(181 / 255.0, 230 / 255.0, 29 / 255.0, 1);
    private final Color forestColor = new Color(34 / 255.0, 177 / 255.0, 76 / 255.0, 1);

    /**
     * Constructs a 2D terrain.
     * Initializes the terrain heights, smoothes the terrain, and associates resources with food types.
     *
     * @param width  The width of the terrain in number of cells.
     * @param height The height of the terrain in number of cells.
     */
    public Terrain2D(int width, int height) {
        super(width * cellSize, height * cellSize);
        heights = generateTerrain(width, height);
        smoothTerrain(heights, 5);
        canvas = new Canvas(width * cellSize, height * cellSize);
        listTerrainResources = new ArrayList<>();

        // Association between a list and a type of food
        foodToList.put(TerrainResources.food.FISH, list_Fish);
        foodToList.put(TerrainResources.food.ALGAE, list_Algae);
        foodToList.put(TerrainResources.food.GRASS, list_Herb);
        foodToList.put(TerrainResources.food.BERRIES, list_Berry);
    }

    /**
     * getter of the canva.
     *
     * @return The Canvas representing the terrain.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Generates a 2D array representing the terrain heights with random values.
     *
     * @param width  The width of the terrain in number of cells.
     * @param height The height of the terrain in number of cells.
     * @return A 2D array of integers representing terrain heights, between 0 and 255.
     */
    private int[][] generateTerrain(int width, int height) {
        int[][] terrain = new int[width][height];
        Random random = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                terrain[x][y] = random.nextInt(256); // Hauteurs entre 0 et 255
            }
        }

        return terrain;
    }

    /**
     * Smoothens the terrain by averaging the heights of neighboring cells over a specified number of iterations.
     *
     * @param terrain    The 2D array representing the terrain heights.
     * @param iterations The number of smoothing iterations to apply.
     */
    private void smoothTerrain(int[][] terrain, int iterations) {
        int width = terrain.length;
        int height = terrain[0].length;

        for (int iter = 0; iter < iterations; iter++) {
            int[][] temp = new int[width][height];
            int nb_voisin = 3;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int sum = 0;
                    int count = 0;

                    // Moyenne des voisins
                    for (int dx = -nb_voisin; dx <= nb_voisin; dx++) {
                        for (int dy = -nb_voisin; dy <= nb_voisin; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;

                            if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                                sum += terrain[nx][ny];
                                count++;
                            }
                        }
                    }
                    temp[x][y] = sum / count; // Moyenne des hauteurs voisines
                }
            }
            // Copier le terrain lissé temporaire vers l'original
            for (int x = 0; x < width; x++) {
                System.arraycopy(temp[x], 0, terrain[x], 0, height);
            }
        }
    }

    /**
     * Draws the terrain on the canvas. The color depends to the type of terrain.
     * Generates the terrain resources and draws them as colored dots.
     * The coordinates of the terrain resources are saved in lists matching the type of resource.
     * The addresses of the terrain resources objects are saved in a list.
     */
    public void draw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        for (int x = 0; x < heights.length; x++) {
            for (int y = 0; y < heights[x].length; y++) {
                int height = heights[x][y];

                if (height < 123) {
                    gc.setFill(waterColor);
                    gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    if (new Random().nextDouble() < 0.05) {
                        Fish fish = new Fish(x, y);
                        listTerrainResources.add(fish);
                        list_Fish.add(List.of(x, y));

                        gc.setFill(fishColor);
                        int centerX = x * cellSize + cellSize / 4;
                        int centerY = y * cellSize + cellSize / 4;
                        int diameter = cellSize / 2;
                        gc.fillOval(centerX, centerY, diameter, diameter);
                    }
                } else if (height < 125) {
                    gc.setFill(sandColor);
                    gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    if (new Random().nextDouble() < 0.05) {
                        Algae algae = new Algae(x, y);
                        listTerrainResources.add(algae);
                        list_Algae.add(List.of(x, y));

                        gc.setFill(algaeColor);
                        int centerX = x * cellSize + cellSize / 4;
                        int centerY = y * cellSize + cellSize / 4;
                        int diameter = cellSize / 2;
                        gc.fillOval(centerX, centerY, diameter, diameter);
                    }
                } else if (height < 129) {
                    gc.setFill(plainColor);
                    gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    if (new Random().nextDouble() < 0.05) {
                        Herb herb = new Herb(x, y);
                        listTerrainResources.add(herb);
                        list_Herb.add(List.of(x, y));

                        gc.setFill(herbColor);
                        int centerX = x * cellSize + cellSize / 4;
                        int centerY = y * cellSize + cellSize / 4;
                        int diameter = cellSize / 2;
                        gc.fillOval(centerX, centerY, diameter, diameter);
                    }
                } else {
                    gc.setFill(forestColor);
                    gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                    if (new Random().nextDouble() < 0.05) {
                        Berry berry = new Berry(x, y);
                        listTerrainResources.add(berry);
                        list_Berry.add(List.of(x, y));

                        gc.setFill(berryColor);
                        int centerX = x * cellSize + cellSize / 4;
                        int centerY = y * cellSize + cellSize / 4;
                        int diameter = cellSize / 2;
                        gc.fillOval(centerX, centerY, diameter, diameter);
                    }
                }
            }
        }
    }

    /**
     * Retrieves the terrain resources near a specified position within a given radius.
     *
     * @param x         The x-coordinate of the position.
     * @param y         The y-coordinate of the position.
     * @param sense     Ability to detect food, taken as the radius around the position.
     * @param Resources The list of resources to search within.
     * @return A list of coordinates representing the neighboring terrain resources.
     */
    public static List<List<Integer>> Neighbours(int x, int y, int sense, List<List<Integer>> Resources) {
        // Fonction that returns the terrain ressources taht are near the animal
        List<List<Integer>> SenseZone = new ArrayList<>();
        for (int i = x - sense; i < x + sense + 1; i++) {
            for (int j = y - sense; j < y + sense + 1; j++) {
                if (Resources.contains(List.of(i, j))){
                    for (int k=0; k < listTerrainResources.size(); k++) {
                        if (listTerrainResources.get(k).getX() == x && listTerrainResources.get(k).getY() == y) {
                            if (listTerrainResources.get(k).isAlive()) {
                                SenseZone.add(List.of(i, j));
                            }
                        }
                    }
                }
            }
        }
        return SenseZone;
    }

    /**
     * Clears and redraws the terrain on the canvas. 
     * The positions of animals and the alive status of the resources have been updated and should be taken into account.
     */
    public void updateAndDraw() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();

        // Clear the existing terrain
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());

        // Draw the terrain
        for (int x = 0; x < heights.length; x++) {
            for (int y = 0; y < heights[x].length; y++) {
                int height = heights[x][y];

                if (height < 123) {
                    gc.setFill(waterColor);
                } else if (height < 125) {
                    gc.setFill(sandColor);
                } else if (height < 129) {
                    gc.setFill(plainColor);
                } else {
                    gc.setFill(forestColor);
                }

                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        // Draw the alive resources
        for (TerrainResources resource : listTerrainResources) {
            if (resource.isAlive()) {
                gc.setFill(resource.getColor());

                int centerX = resource.getX() * cellSize + cellSize / 4;
                int centerY = resource.getY() * cellSize + cellSize / 4;
                int diameter = cellSize / 2;
                gc.fillOval(centerX, centerY, diameter, diameter);
            }
        }

        // Draw the animals
        for (Animal a : Simulation.animals) {
            gc.setFill(a.getColor());

            int centerX = a.x * cellSize + cellSize / 2;
            int centerY = a.y * cellSize + cellSize / 2;
            int diameter = cellSize;
            gc.fillOval(centerX, centerY, diameter, diameter);

        }
    }


    /**
     * Determines the type of terrain at a specific position.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     * @return The type of ground at the specified position : WATER, SAND, PLAIN, or FOREST.
     */
    public static Biomass.grounds getTerrain(int x, int y) {
        double height = heights[x][y];
        if (height < 123) {
            return Biomass.grounds.WATER;
        } else if (height < 125) {
            return Biomass.grounds.SAND;
        } else if (height < 129) {
            return Biomass.grounds.PLAIN;
        } else {
            return Biomass.grounds.FOREST;
        }
    }
}

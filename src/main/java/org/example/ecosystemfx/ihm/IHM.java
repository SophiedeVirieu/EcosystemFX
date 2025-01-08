package org.example.ecosystemfx.ihm;

import org.example.ecosystemfx.base.BadGroundException;
import org.example.ecosystemfx.base.Simulation;
import org.example.ecosystemfx.base.Terrain2D;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IHM extends Application {

    public static int width = 150;
    public static int height = 90;
    private Simulation simulation;

    @Override
    public void start(Stage primaryStage) throws BadGroundException {

        Terrain2D terrain = new Terrain2D(width, height);
        terrain.draw();

        simulation = new Simulation(new int[]{width, height}, new int[]{10, 100, 20, 30, 15, 36, 17, 38, 80});

        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button nextButton = new Button("Next turn");

        startButton.setOnAction(event -> simulation.start_sim());
        stopButton.setOnAction(event -> simulation.stop_sim());
        nextButton.setOnAction(event -> {
            simulation.next_turn();
            terrain.updateAndDraw();
        });

        // aligns the three buttons in a column
        VBox buttonBox = new VBox(10, startButton, nextButton, stopButton);
        buttonBox.setPadding(new Insets(10));

        // organizes the display
        BorderPane root = new BorderPane();

        root.setCenter(terrain.getCanvas());  // the terrain is in the center

        root.setRight(buttonBox); // the buttons are on the right

        int scaledWidth = width * 10;
        int scaledHeight = height * 10;
        Scene scene = new Scene(root, scaledWidth + 200, scaledHeight);  // +200 adds space for the buttons
        primaryStage.setTitle("Terrain with Controls");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

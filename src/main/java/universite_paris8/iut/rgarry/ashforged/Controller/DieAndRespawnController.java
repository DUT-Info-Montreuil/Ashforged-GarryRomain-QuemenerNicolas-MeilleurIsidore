package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class DieAndRespawnController {

    /**
     * Handles the start game action triggered by the user.
     * Loads the main game view from the specified FXML file,
     * sets up the scene and stage, and switches to full screen.
     * @param event The ActionEvent triggered by the UI component.
     */
    @FXML
    private void startGame(ActionEvent event) {
        try {
            // Locate the FXML resource for the game view
            URL fxmlLocation = getClass().getResource("/universite_paris8/iut/rgarry/ashforged/gameView.fxml");
            if (fxmlLocation == null) {
                // Log error if FXML file is not found
                System.err.println("Error: FXML file not found at /universite_paris8/iut/rgarry/ashforged/gameView.fxml");
                return;
            }
            // Load the FXML root node
            Parent gameRoot = FXMLLoader.load(fxmlLocation);

            // Retrieve the current stage from the event source node
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Create a new scene with specified resolution (1920x1080)
            Scene gameScene = new Scene(gameRoot, 1920, 1080);

            // Set the scene on the stage
            stage.setScene(gameScene);

            // Center the stage on the screen
            stage.centerOnScreen();

            // Enable full screen mode
            stage.setFullScreen(true);

            // Show the stage
            stage.show();
        } catch (Exception e) {
            // Log exception details if loading the game scene fails
            System.err.println("Failed to load game scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the quit game action triggered by the user.
     * Exits the entire application.
     * @param event The ActionEvent triggered by the UI component.
     */
    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
}

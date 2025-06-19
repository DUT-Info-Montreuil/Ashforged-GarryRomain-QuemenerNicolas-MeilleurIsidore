package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Controller for the "death and respawn" screen.
 *
 * Manages user actions after the player's death in the game, such as restarting the game
 * or quitting the application entirely.
 */
public class DieAndRespawnController {

    /**
     * Restarts the game by reloading the main game scene.
     *
     * @param event the action event triggered by clicking the "Respawn" or "Restart" button.
     */
    @FXML
    private void startGame(ActionEvent event) {
        try {
            URL fxmlLocation = getClass().getResource("/universite_paris8/iut/rgarry/ashforged/hello-view.fxml");
            if (fxmlLocation == null) {
                System.err.println("Error: FXML file not found at /universite_paris8/iut/rgarry/ashforged/hello-view.fxml");
                return;
            }

            Parent gameRoot = FXMLLoader.load(fxmlLocation);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene gameScene = new Scene(gameRoot, 1920, 1080);
            stage.setScene(gameScene);
            stage.centerOnScreen();
            stage.setFullScreen(true);
            stage.show();
        } catch (Exception e) {
            System.err.println("Failed to load game scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exits the application immediately.
     *
     * @param event the action event triggered by clicking the "Quit" button.
     */
    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
}

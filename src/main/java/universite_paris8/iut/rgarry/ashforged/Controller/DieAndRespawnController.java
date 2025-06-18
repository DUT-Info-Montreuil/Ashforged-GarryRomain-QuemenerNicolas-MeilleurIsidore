package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class DieAndRespawnController {

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

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
}
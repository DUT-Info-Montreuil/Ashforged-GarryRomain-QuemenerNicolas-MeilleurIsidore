package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TitleScreenController {

    @FXML
    private void startGame(ActionEvent event) {
        try {
            // Charge le fichier FXML du jeu — corrige le chemin si besoin (ici un exemple)
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/hello-view.fxml"));

            // Récupère la stage actuelle via le bouton cliqué
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Crée une nouvelle scène avec des dimensions fixes (par exemple 1920x1080)
            Scene gameScene = new Scene(gameRoot, 1920, 1080);

            // Remplace la scène actuelle par la nouvelle scène
            stage.setScene(gameScene);
            stage.centerOnScreen();
            stage.setFullScreen(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
}

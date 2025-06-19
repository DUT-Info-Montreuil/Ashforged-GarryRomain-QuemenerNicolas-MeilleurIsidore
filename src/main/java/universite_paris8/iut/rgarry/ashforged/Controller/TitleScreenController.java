package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.rgarry.ashforged.model.KeyMapping;

import java.net.URL;

public class TitleScreenController {

    /**
     * Démarre le jeu en chargeant la scène principale.
     * @param event événement déclenché par le clic sur "Start"
     */
    @FXML
    private void startGame(ActionEvent event) {
        try {
            // Charge le fichier FXML de la vue principale du jeu
            URL fxmlLocation = getClass().getResource("/universite_paris8/iut/rgarry/ashforged/gameView.fxml");
            if (fxmlLocation == null) {
                System.err.println("Error: FXML file not found at /universite_paris8/iut/rgarry/ashforged/gameView.fxml");
                return;
            }
            Parent gameRoot = FXMLLoader.load(fxmlLocation);

            // Récupère la fenêtre actuelle depuis l'événement
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Crée et applique la nouvelle scène
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
     * Ouvre une fenêtre modale pour configurer les touches de contrôle.
     * @param event événement déclenché par le clic sur "Key Mapping"
     */
    @FXML
    private void openKeyMapping(ActionEvent event) {
        javafx.scene.control.Dialog<Void> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Key Mapping");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Actions configurables et leurs labels visibles
        String[] actions = {"moveRight", "moveLeft", "jump"};
        String[] labels = {"Move Right", "Move Left", "Jump"};

        // Labels affichant les touches actuelles
        javafx.scene.control.Label[] keyLabels = new javafx.scene.control.Label[actions.length];

        for (int i = 0; i < actions.length; i++) {
            // Label de l'action (ex: Move Right)
            grid.add(new javafx.scene.control.Label(labels[i]), 0, i);

            // Label de la touche actuelle
            keyLabels[i] = new javafx.scene.control.Label(KeyMapping.getKey(actions[i]).getName());
            grid.add(keyLabels[i], 1, i);

            // Bouton pour changer la touche
            javafx.scene.control.Button changeBtn = new javafx.scene.control.Button("Change");
            int idx = i;

            changeBtn.setOnAction(e -> {
                keyLabels[idx].setText("Press a key...");
                grid.requestFocus();

                // Ecoute la prochaine touche pressée
                grid.setOnKeyPressed(ev -> {
                    KeyMapping.setKey(actions[idx], ev.getCode());
                    keyLabels[idx].setText(ev.getCode().getName());

                    // Désactive l'écoute après la première touche
                    grid.setOnKeyPressed(null);
                });
            });

            grid.add(changeBtn, 2, i);
        }

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);
        dialog.showAndWait();
    }

    /**
     * Quitte l'application proprement.
     * @param event événement déclenché par le clic sur "Quit"
     */
    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
}

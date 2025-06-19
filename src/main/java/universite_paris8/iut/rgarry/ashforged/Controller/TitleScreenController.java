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
    private void openKeyMapping(ActionEvent event) {
        javafx.scene.control.Dialog<Void> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Key Mapping");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        String[] actions = {"moveRight", "moveLeft", "jump"};
        String[] labels = {"Move Right", "Move Left", "Jump"};
        javafx.scene.control.Label[] keyLabels = new javafx.scene.control.Label[actions.length];

        for (int i = 0; i < actions.length; i++) {
            grid.add(new javafx.scene.control.Label(labels[i]), 0, i);
            keyLabels[i] = new javafx.scene.control.Label(KeyMapping.getKey(actions[i]).getName());
            grid.add(keyLabels[i], 1, i);
            javafx.scene.control.Button changeBtn = new javafx.scene.control.Button("Change");
            int idx = i;
            changeBtn.setOnAction(e -> {
                keyLabels[idx].setText("Press a key...");
                grid.requestFocus();
                grid.setOnKeyPressed(ev -> {
                    KeyMapping.setKey(actions[idx], ev.getCode());
                    keyLabels[idx].setText(ev.getCode().getName());
                    grid.setOnKeyPressed(null);
                });
            });
            grid.add(changeBtn, 2, i);
        }

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);
        dialog.showAndWait();
    }

    @FXML
    private void quitGame(ActionEvent event) {
        System.exit(0);
    }
}
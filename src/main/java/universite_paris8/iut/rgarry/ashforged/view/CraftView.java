package universite_paris8.iut.rgarry.ashforged.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.rgarry.ashforged.Controller.CraftController;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.io.IOException;

public class CraftView {
    private boolean windowOpen;

    public CraftView() {
        this.windowOpen = false;
    }

    public void openCraft(Character character) {
        if (!this.windowOpen) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Craft.fxml"));
                Parent root = loader.load();  // Charge le FXML et crée les nodes

                // Récupère le contrôleur associé au FXML
                CraftController controller = loader.getController();

                // Passe le personnage au contrôleur
                controller.setCharacter(character);

                Stage craftStage = new Stage();
                craftStage.setTitle("Fenêtre de Craft");
                craftStage.setScene(new Scene(root, 1000, 500));
                craftStage.setOnCloseRequest(e -> {
                    this.windowOpen = false;
                });
                craftStage.centerOnScreen();
                craftStage.show();

                this.windowOpen = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

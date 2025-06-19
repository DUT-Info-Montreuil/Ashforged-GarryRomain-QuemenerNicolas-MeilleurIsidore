package universite_paris8.iut.rgarry.ashforged.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.rgarry.ashforged.Controller.CraftController;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.io.IOException;

/**
 * CraftView handles the display of the crafting window (Craft.fxml).
 * It ensures that the crafting interface is only opened once at a time.
 */
public class CraftView {
    private boolean windowOpen; // Tracks whether the crafting window is currently open

    /**
     * Constructor initializes the crafting view with the window closed.
     */
    public CraftView() {
        this.windowOpen = false;
    }

    /**
     * Opens the crafting window and injects the current character into the CraftController.
     * Prevents multiple instances of the crafting window from being opened.
     *
     * @param character the character interacting with the crafting interface
     */
    public void openCraft(Character character) {
        if (!this.windowOpen) {
            try {
                // Load the FXML layout for the crafting interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Craft.fxml"));
                Parent root = loader.load(); // Parse the FXML and construct the UI components

                // Get the associated controller instance from the FXML
                CraftController controller = loader.getController();

                // Pass the character to the controller
                controller.setCharacter(character);

                // Create a new Stage (window) for the crafting interface
                Stage craftStage = new Stage();
                craftStage.setTitle("Crafting Window");
                craftStage.setScene(new Scene(root, 1000, 500));

                // Ensure windowOpen flag is reset when window closes
                craftStage.setOnCloseRequest(e -> {
                    this.windowOpen = false;
                });

                craftStage.centerOnScreen(); // Center the window
                craftStage.show(); // Display the window

                this.windowOpen = true; // Mark the window as open
            } catch (IOException e) {
                // If loading the FXML fails, throw a runtime exception
                throw new RuntimeException(e);
            }
        }
    }
}

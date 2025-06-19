package universite_paris8.iut.rgarry.ashforged.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.rgarry.ashforged.Controller.CraftController;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

import java.io.IOException;

// Class to manage the display of the crafting window for a character
public class CraftView {
    private boolean isWindowOpen; // Tracks whether the crafting window is open

    /**
     * Constructor for CraftView, initializes the window state to closed.
     */
    public CraftView() {
        this.isWindowOpen = false; // Initialize window state as closed
    }

    /**
     * Opens a crafting window for the specified character if no crafting window is already open.
     * Loads the FXML layout, sets up the controller with the character, and displays the window.
     *
     * @param character The character for whom the crafting window is opened.
     */
    public void openCraft(Character character) {
        // Check if a crafting window is already open
        if (!this.isWindowOpen) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Craft.fxml"));
                Parent root = loader.load(); // Load FXML and create the node hierarchy


                CraftController controller = loader.getController();


                controller.setCharacter(character);

                // Create and configure the crafting window
                Stage craftStage = new Stage();
                craftStage.setTitle("Crafting Window"); // Set window title
                craftStage.setScene(new Scene(root, 1000, 500)); // Set scene with 1000x500 dimensions
                // Handle window close event to update window state
                craftStage.setOnCloseRequest(e -> this.isWindowOpen = false);
                craftStage.centerOnScreen(); // Center the window on the screen
                craftStage.show(); // Display the window

                // Mark the window as open
                this.isWindowOpen = true;
            } catch (IOException e) {
                // Wrap and rethrow IOException as a RuntimeException
                throw new RuntimeException("Failed to load Craft.fxml", e);
            }
        }
    }
}
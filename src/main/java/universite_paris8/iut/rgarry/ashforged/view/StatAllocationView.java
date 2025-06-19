package universite_paris8.iut.rgarry.ashforged.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

// Class to display a graphical interface for allocating stat points to a character
public class StatAllocationView {

    /**
     * Displays a modal window to allow the user to allocate stat points to a character.
     * The window contains a grid with stat names, their current values, buttons to
     * increase or decrease values, and buttons to apply or cancel changes.
     *
     * @param personnage The character whose stats are to be modified.
     *                  The stats and available stat points are read and updated through this object.
     */
    public static void show(Character personnage) {
        // Create a new stage (window) for the stat allocation interface
        Stage window = new Stage();
        // Set the window to be modal, blocking interaction with other windows until closed
        window.initModality(Modality.APPLICATION_MODAL);
        // Set the title of the window
        window.setTitle("Stat Allocation");

        // Retrieve and clone stats to prevent direct modifications to the character's stats
        // originalStats: Holds the current stats of the character
        int[] originalStats = personnage.getStats();
        // initialStats: A backup of the initial stats to be used for comparison and to ensure stats don't go below the initial value
        int[] initialStats = originalStats.clone();
        // tempStats: Used for modifying the stats temporarily
        int[] tempStats = originalStats.clone();
        // originalStatPoints: Holds the available stat points the character has
        int originalStatPoints = personnage.getStatPoint();
        // tempStatPoints: Used for tracking the changes in available stat points
        int[] tempStatPoints = {originalStatPoints};

        // Initialize labels to display stat values
        // statLabels: An array to store the labels that display the current values of the stats
        Label[] statLabels = new Label[tempStats.length];
        // statNames: An array containing the names of the stats
        String[] statNames = {"Health", "Strength", "Speed", "Other"};

        // Create a label to display the number of remaining stat points
        Label pointsLeftLabel = new Label("Points left: " + tempStatPoints[0]);

        // Create a grid to organize the layout of the interface
        GridPane grid = new GridPane();
        // Set padding around the grid to provide some space around the content
        grid.setPadding(new Insets(20));
        // Set vertical gap between rows in the grid
        grid.setVgap(10);
        // Set horizontal gap between columns in the grid
        grid.setHgap(10);

        // Iterate through the stats to create labels and buttons for each stat
        for (int i = 0; i < tempStats.length; i++) {
            // Create a label for the stat name
            Label nameLabel = new Label(statNames[i] + ": ");
            // Create a label to display the current value of the stat
            statLabels[i] = new Label(String.valueOf(tempStats[i]));
            // Create a button to increase the stat value
            Button plusButton = new Button("+");
            // Create a button to decrease the stat value
            Button minusButton = new Button("-");

            // Capture the current index to be used in the event handlers
            int statIndex = i;

            // Set the action for the plus button
            plusButton.setOnAction(e -> {
                // Check if there are stat points remaining
                if (tempStatPoints[0] > 0) {
                    // Increment the stat value
                    tempStats[statIndex]++;
                    // Update the corresponding label with the new stat value
                    statLabels[statIndex].setText(String.valueOf(tempStats[statIndex]));
                    // Decrement the remaining stat points
                    tempStatPoints[0]--;
                    // Update the label displaying the remaining stat points
                    pointsLeftLabel.setText("Points left: " + tempStatPoints[0]);
                }
            });

            // Set the action for the minus button
            minusButton.setOnAction(e -> {
                // Check if the stat value is greater than its initial value
                if (tempStats[statIndex] > initialStats[statIndex]) {
                    // Decrement the stat value
                    tempStats[statIndex]--;
                    // Update the corresponding label with the new stat value
                    statLabels[statIndex].setText(String.valueOf(tempStats[statIndex]));
                    // Increment the remaining stat points
                    tempStatPoints[0]++;
                    // Update the label displaying the remaining stat points
                    pointsLeftLabel.setText("Points left: " + tempStatPoints[0]);
                }
            });

            // Add the stat name label to the grid
            grid.add(nameLabel, 0, i);
            // Add the stat value label to the grid
            grid.add(statLabels[i], 1, i);
            // Add the plus button to the grid
            grid.add(plusButton, 2, i);
            // Add the minus button to the grid
            grid.add(minusButton, 3, i);
        }

        // Add the label displaying the remaining stat points to the grid
        grid.add(pointsLeftLabel, 0, tempStats.length, 4, 1);

        // Create an "Apply" button
        Button applyButton = new Button("Apply");
        // Set the action for the apply button
        applyButton.setOnAction(e -> {
            // Update the character's stats with the modified stats
            personnage.setStats(tempStats.clone());
            // Update the character's available stat points
            personnage.setStatPoint(tempStatPoints[0]);
            // Close the window
            window.close();
        });

        // Create a "Close" button
        Button closeButton = new Button("Close");
        // Set the action for the close button
        closeButton.setOnAction(e -> window.close());

        // Add the apply button to the grid
        grid.add(applyButton, 0, tempStats.length + 1, 2, 1);
        // Add the close button to the grid
        grid.add(closeButton, 2, tempStats.length + 1, 2, 1);

        // Create a scene with the grid as the root node
        Scene scene = new Scene(grid, 400, 250);
        // Set the scene for the window
        window.setScene(scene);
        // Display the window and wait for it to be closed
        window.showAndWait();
    }
}




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
     * @param character The character whose stats are to be modified.
     *                  The stats and available stat points are read and updated through this object.
     * @throws NullPointerException if the character or its stats are null.
     */
    public static void show(Character character) {
        // Initialization of the modal window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // Prevents interaction with other windows
        window.setTitle("Stat Allocation"); // Window title

        // Retrieval and cloning of stats to avoid direct modifications
        int[] originalStats = character.getStats(); // Current character stats
        int[] initialStats = originalStats.clone(); // Backup of initial stats for comparison
        int[] tempStats = originalStats.clone(); // Temporary stats for modifications
        int originalStatPoints = character.getStatPoint(); // Available stat points
        int[] tempStatPoints = {originalStatPoints}; // Temporary points for tracking changes

        // Initialization of labels to display stat values
        Label[] statLabels = new Label[tempStats.length];
        String[] statNames = {"Health", "Strength", "Speed", "Other"}; // Stat names

        // Label to display remaining points
        Label pointsLeftLabel = new Label("Points left: " + tempStatPoints[0]);

        // Creation of the grid to organize the interface
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20)); // 20-pixel inner margin
        grid.setVgap(10); // Vertical gap between rows
        grid.setHgap(10); // Horizontal gap between columns

        // Loop to create a row for each stat with its name, value, and modification buttons
        for (int i = 0; i < tempStats.length; i++) {
            Label nameLabel = new Label(statNames[i] + ": "); // Stat name label
            statLabels[i] = new Label(String.valueOf(tempStats[i])); // Current stat value label
            Button plusButton = new Button("+"); // Button to increase the stat
            Button minusButton = new Button("-"); // Button to decrease the stat

            int statIndex = i; // Stat index for lambda expressions

            // Function: Increase a stat
            // Specification: Increments the stat at the given index if points are available,
            // updates the display of the value and remaining points.
            plusButton.setOnAction(e -> {
                if (tempStatPoints[0] > 0) {
                    tempStats[statIndex]++;
                    statLabels[statIndex].setText(String.valueOf(tempStats[statIndex]));
                    tempStatPoints[0]--;
                    pointsLeftLabel.setText("Points left: " + tempStatPoints[0]);
                }
            });

            // Function: Decrease a stat
            // Specification: Decrements the stat at the given index if it is greater than its initial value,
            // updates the display of the value and remaining points.
            minusButton.setOnAction(e -> {
                if (tempStats[statIndex] > initialStats[statIndex]) {
                    tempStats[statIndex]--;
                    statLabels[statIndex].setText(String.valueOf(tempStats[statIndex]));
                    tempStatPoints[0]++;
                    pointsLeftLabel.setText("Points left: " + tempStatPoints[0]);
                }
            });

            // Layout of elements in the grid
            grid.add(nameLabel, 0, i);
            grid.add(statLabels[i], 1, i);
            grid.add(plusButton, 2, i);
            grid.add(minusButton, 3, i);
        }

        // Adding the remaining points label, spanning 4 columns
        grid.add(pointsLeftLabel, 0, tempStats.length, 4, 1);

        // Function: Apply changes
        // Specification: Saves temporary stats to the character,
        // updates remaining stat points, and closes the window.
        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            character.setStats(tempStats.clone()); // Applies modified stats
            character.setStatPoint(tempStatPoints[0]); // Updates remaining points
            window.close(); // Closes the window
        });

        // Function: Cancel changes
        // Specification: Closes the window without saving changes.
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        // Adding buttons to the grid
        grid.add(applyButton, 0, tempStats.length + 1, 2, 1); // "Apply" button spans 2 columns
        grid.add(closeButton, 2, tempStats.length + 1, 2, 1); // "Close" button spans 2 columns

        // Creation and configuration of the scene
        Scene scene = new Scene(grid, 400, 250);
        window.setScene(scene);
        window.showAndWait(); // Displays the window and waits for it to close
    }
}
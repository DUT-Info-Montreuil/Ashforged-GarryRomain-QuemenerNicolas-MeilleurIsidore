package universite_paris8.iut.rgarry.ashforged.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class StatAllocationView {

    public static void show(Character personnage) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Stat Allocation");

        int[] originalStats = personnage.getStats();
        int[] initialStats = originalStats.clone();
        int[] tempStats = originalStats.clone();
        int originalStatPoints = personnage.getStatPoint();
        int[] tempStatPoints = {originalStatPoints};

        Label[] statLabels = new Label[tempStats.length];
        String[] statNames = {"Health", "Strength", "Speed", "Other"};

        Label pointsLeftLabel = new Label("Points left: " + tempStatPoints[0]);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        for (int i = 0; i < tempStats.length; i++) {
            Label nameLabel = new Label(statNames[i] + ": ");
            statLabels[i] = new Label(String.valueOf(tempStats[i]));
            Button plusButton = new Button("+");
            Button minusButton = new Button("-");

            int statIndex = i;

            plusButton.setOnAction(e -> {
                if (tempStatPoints[0] > 0) {
                    tempStats[statIndex]++;
                    statLabels[statIndex].setText(String.valueOf(tempStats[statIndex]));
                    tempStatPoints[0]--;
                    pointsLeftLabel.setText("Points left: " + tempStatPoints[0]);
                }
            });

            minusButton.setOnAction(e -> {
                if (tempStats[statIndex] > initialStats[statIndex]) {
                    tempStats[statIndex]--;
                    statLabels[statIndex].setText(String.valueOf(tempStats[statIndex]));
                    tempStatPoints[0]++;
                    pointsLeftLabel.setText("Points left: " + tempStatPoints[0]);
                }
            });

            grid.add(nameLabel, 0, i);
            grid.add(statLabels[i], 1, i);
            grid.add(plusButton, 2, i);
            grid.add(minusButton, 3, i);
        }

        grid.add(pointsLeftLabel, 0, tempStats.length, 4, 1);

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            personnage.setStats(tempStats.clone());
            personnage.setStatPoint(tempStatPoints[0]);
            window.close();
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        grid.add(applyButton, 0, tempStats.length + 1, 2, 1);
        grid.add(closeButton, 2, tempStats.length + 1, 2, 1);

        Scene scene = new Scene(grid, 400, 250);
        window.setScene(scene);
        window.showAndWait();
    }
}
package universite_paris8.iut.rgarry.ashforged.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CraftView {
    private boolean windowOpen;

    public CraftView(){
        this.windowOpen=false;
    }

    public void openCraft(){
        if(!this.windowOpen) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Craft.fxml"));
            Stage craftStage = new Stage();
            craftStage.setTitle("Fenêtre de Craft");
            try {
                craftStage.setScene(new Scene(loader.load()));
                this.windowOpen = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            craftStage.setOnCloseRequest(e -> {
                this.windowOpen=false;
            });
            craftStage.show();
        }


    }
}

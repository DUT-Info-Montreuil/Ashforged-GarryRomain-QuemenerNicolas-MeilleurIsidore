package universite_paris8.iut.rgarry.ashforged;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import universite_paris8.iut.rgarry.ashforged.model.Item.Item;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        stage.setTitle("Ashforged");
        stage.setScene(scene);
        stage.show();
        //applyGravity(personnage, GROUND_LEVEL);
    }
    public static void main(String[] args) {
        launch();
    }
}
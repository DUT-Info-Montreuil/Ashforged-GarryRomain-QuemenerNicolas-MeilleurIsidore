package universite_paris8.iut.rgarry.ashforged;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/title_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        stage.setTitle("Ashforged");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

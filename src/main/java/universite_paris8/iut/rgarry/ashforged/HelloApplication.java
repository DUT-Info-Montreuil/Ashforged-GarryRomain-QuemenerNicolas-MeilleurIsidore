package universite_paris8.iut.rgarry.ashforged;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import universite_paris8.iut.rgarry.ashforged.model.Item.Item;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final int GRAVITY = 1;

    public static Item epee = new Item("Epee de fer", 5);
    public static Item arc = new Item("Arc en bois", 3);
    public static Item arc1 = new Item("arc",4);

    // Creation of the main character
    public static Personnage personnage = new Personnage("Hero", 15, new int[]{1, 1, 10, 1},600, 250);

    // Creation of NPC
    public static Personnage paolo = new Personnage("Paolo", 15, new int[]{1, 1, 10, 1},600, 250);
    public static Personnage branda = new Personnage("Branda", 15, new int[]{1, 1, 10, 1},600, 250);
    public static Personnage terry = new Personnage("Terry", 15, new int[]{1, 1, 10, 1},600, 250);
    public static Personnage salome = new Personnage("Salome", 15, new int[]{1, 1, 10, 1},600, 250);

    private static final int GROUND_LEVEL = personnage.getY();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        stage.setTitle("Ashforged");
        stage.setScene(scene);
        stage.show();
        //applyGravity(personnage, GROUND_LEVEL);
    }

    public static Personnage getPersonnage() {
        return personnage;
    }

    public static void main(String[] args) {
        launch();
    }
}
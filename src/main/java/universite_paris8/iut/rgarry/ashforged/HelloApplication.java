package universite_paris8.iut.rgarry.ashforged;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import javafx.util.Duration;
import universite_paris8.iut.rgarry.ashforged.Item.Item;
import universite_paris8.iut.rgarry.ashforged.character.Personnage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final int GRAVITY = 1;

    public static Item epee = new Item("Epee de fer", 5);
    public static Item arc = new Item("Arc en bois", 3);
    public static Item arc1 = new Item("arc",4);
    public static Personnage personnage = new Personnage("Hero", 15, new int[]{1, 1, 1, 1},600, 150);

    private static final int GROUND_LEVEL = personnage.getY();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        scene.setOnKeyPressed(event -> handleKeyPress(event,personnage));

        stage.setTitle("Ashforged");
        stage.setScene(scene);
        stage.show();
        applyGravity(personnage);
    }

    private void applyGravity(Personnage personnage) {
        final double MAX_VELOCITY = 200;
        final double ACCELERATION = 0.2;
        final double[] velocityY = {0};

        Timeline gravityTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            if (personnage.getY() < GROUND_LEVEL) {
                velocityY[0] = Math.min(velocityY[0] + ACCELERATION, MAX_VELOCITY);
                personnage.setY((int) (personnage.getY() + velocityY[0]));
                System.out.println("Gravity applied: " + personnage.getY() + ", Velocity: " + velocityY[0]);
            } else {
                personnage.setY(GROUND_LEVEL);
                velocityY[0] = 0;
            }
        }));
        gravityTimeline.setCycleCount(Timeline.INDEFINITE);
        gravityTimeline.play();
    }

    public static Personnage getPersonnage(){
        return personnage;
    }

    private boolean isInventoryOpen = false;

    private void handleKeyPress(KeyEvent event, Personnage personnage) {
        final double MOVE_SPEED = 150;
        if (event.getCode() == KeyCode.SPACE) {
            if (personnage.getY() == GROUND_LEVEL) {
                personnage.setY((int) (personnage.getY() - MOVE_SPEED));
            }
        }
        if (event.getCode() == KeyCode.S) {
            personnage.setY((int) (personnage.getY() + MOVE_SPEED));
        }
        if (event.getCode() == KeyCode.Q) {
            personnage.setX((int) (personnage.getX() - MOVE_SPEED));
        }
        if (event.getCode() == KeyCode.D) {
            personnage.setX((int) (personnage.getX() + MOVE_SPEED));
        }
        if (event.getCode() == KeyCode.I) {
            System.out.println("Touche I Pressée");
            personnage.getInventory();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
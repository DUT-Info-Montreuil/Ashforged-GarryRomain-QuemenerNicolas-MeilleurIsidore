package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.rgarry.ashforged.HelloApplication;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;
import universite_paris8.iut.rgarry.ashforged.view.FieldView;
import universite_paris8.iut.rgarry.ashforged.view.PersonnageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane tilepane;
    @FXML
    private Pane paneperso;

    private final Personnage personnage = HelloApplication.getPersonnage();
    private final PersonnageController personnageController = new PersonnageController(personnage);

    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();
        FieldView fieldView = new FieldView(tilepane, field);
        PersonnageView personnageView = new PersonnageView(paneperso,personnage,personnageController, field);
        startTimeline();
    }

    private void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (personnageController.isSpacePressed()) {
                personnageController.handleJump();
            }
            if (personnageController.isQPressed()) {
                personnageController.handleLeft();
            }
            if (personnageController.isSPressed()) {
                personnageController.handleDown();
            }
            if (personnageController.isDPressed()) {
                personnageController.handleRight();
            }
            personnageController.applyGravity();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
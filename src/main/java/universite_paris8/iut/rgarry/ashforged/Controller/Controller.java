package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
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
    @FXML
    private Pane camera;

    private PersonnageView personnageView;
    private PersonnageController personnageController;
    private Personnage personnage;

    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();
        personnageController = new PersonnageController(tilepane, paneperso);
        personnage = personnageController.getPersonnage();
        FieldView fieldView = new FieldView(tilepane, field);

        this.personnageView = new PersonnageView(paneperso,personnage,personnageController, field);

        IntegerBinding conditionalBinding = Bindings.createIntegerBinding(() -> {
            int y = personnage.getY();
            if (y > 928) {
                System.out.println("test1");
                return -928+(1080/2); // équivalent à 928*(-1) + 0
            } else {
                System.out.println("test2");
                return -y+(1080/2);
            }
        },personnage.getYProperty());

        camera.translateXProperty().bind(personnage.getXProperty().multiply(-1).add(1920/2));
        camera.translateYProperty().bind(conditionalBinding);
        
        startTimeline();
    }

    private void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (personnageController.isSpacePressed()) {
                personnageController.handleJump();
            }
            if (personnageController.isQPressed()) {
                personnageController.handleLeft();
                this.personnageView.changeSprite('l');
            }
            if (personnageController.isSPressed()) {
                personnageController.handleDown();
            }
            if (personnageController.isDPressed()) {
                personnageController.handleRight();
                this.personnageView.changeSprite('r');
            }
            personnageController.applyGravity();
            personnageController.checkCollisionBottom();
            personnageController.checkCollisionLeft();
            personnageController.checkCollisionRight();
            personnageController.checkCollisionTop();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}

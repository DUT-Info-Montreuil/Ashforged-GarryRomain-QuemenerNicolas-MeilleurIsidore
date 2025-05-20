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
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;
import universite_paris8.iut.rgarry.ashforged.view.FieldView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane tilepane;
    @FXML
    private Pane paneperso;

    private final PersonnageController personnageController = new PersonnageController();
    private final Personnage personnage = PersonnageController.getPersonnage();


    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FieldView field = new FieldView(tilepane);

        paneperso.setPrefSize(field.longueur(), field.hauteur());

        Image perso = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/personnage.png").toExternalForm());
        ImageView perso2 = new ImageView(perso);

        perso2.setId("perso");

        perso2.translateXProperty().bind(personnage.getXProperty());
        perso2.translateYProperty().bind(personnage.getYProperty());

        paneperso.getChildren().add(perso2);
        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        personnageController.setupKeyHandlers(paneperso);
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
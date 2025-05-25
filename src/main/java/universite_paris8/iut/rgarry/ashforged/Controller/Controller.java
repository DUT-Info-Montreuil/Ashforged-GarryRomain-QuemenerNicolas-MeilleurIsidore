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
    private Pane healthBarContainer;
    @FXML
    private javafx.scene.shape.Rectangle healthBar;
    @FXML
    private javafx.scene.shape.Rectangle healthBarBackground;
    @FXML
    private javafx.scene.shape.Rectangle expBar;
    @FXML
    private javafx.scene.shape.Rectangle expBarBackground;

    @FXML
    private TilePane tilepane;
    @FXML
    private Pane paneperso;
    @FXML
    private Pane camera;

    @FXML
    private Pane AccesRapide1;
    @FXML
    private Pane AccesRapide2;
    @FXML
    private Pane AccesRapide3;
    @FXML
    private Pane AccesRapide4;
    @FXML
    private Pane AccesRapide5;
    @FXML
    private Pane AccesRapide6;
    @FXML
    private Pane AccesRapide7;
    @FXML
    private Pane AccesRapide8;

    @FXML
    private Pane ContainerInvontory;

    @FXML
    private TilePane Inventory;

    @FXML
    private Pane quit;


    private PersonnageView personnageView;
    private PersonnageController personnageController;
    private Personnage personnage;

    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();
        personnageController = new PersonnageController(tilepane, paneperso);
        personnage = personnageController.getPersonnage();

        Personnage personnage = PersonnageController.getPersonnage();
        double maxBarWidth = 200.0;
        healthBar.widthProperty().bind(
                personnage.healthProperty().divide((double) personnage.getMaxHealth()).multiply(maxBarWidth)
        );
        double maxExpBarWidth = 200.0;
        expBar.widthProperty().bind(
                Bindings.divide(personnage.expProperty(), personnage.expToNextLevelProperty()).multiply(maxExpBarWidth)
        );

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

        initaliseButton();

        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Ciel.png").toExternalForm());
        for(int i = 0; i<20;i++) {
            ImageView imageView = new ImageView(ciel);
            Inventory.getChildren().add(imageView);
        }


        startTimeline();
    }

    /***
     * Permet de gérer le déplacement du personnage dans le jeu et donc mettre à jour sa position.
     */
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

    public void initaliseButton(){
        AccesRapide8.setOnMouseClicked(event -> {
            System.out.println(8);
        });
        AccesRapide7.setOnMouseClicked(event -> {
            System.out.println(7);
        });
        AccesRapide6.setOnMouseClicked(event -> {
            System.out.println(6);
        });
        AccesRapide5.setOnMouseClicked(event -> {
            System.out.println(5);
        });
        AccesRapide4.setOnMouseClicked(event -> {
            System.out.println(4);
        });
        AccesRapide3.setOnMouseClicked(event -> {
            System.out.println(3);
        });
        AccesRapide2.setOnMouseClicked(event -> {
            System.out.println(2);
        });
        AccesRapide1.setOnMouseClicked(event -> {
            System.out.println(1);
            Inventory.setVisible(true);
            ContainerInvontory.setVisible(true);
            quit.setVisible(true);
        });
        quit.setOnMouseClicked(event -> {
            Inventory.setVisible(false);
            ContainerInvontory.setVisible(false);
            quit.setVisible(false);
        });

        AccesRapide1.setVisible(true);
    }

}

package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
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
    private Pane AccesRapide;
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

    private final int LimitLeftCam = 960;


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

        this.personnageView = new PersonnageView(paneperso, personnage, personnageController, field);

        IntegerBinding conditionalBindingY = Bindings.createIntegerBinding(() -> {
            int y = personnage.getY();
            if (y > 928) {
                return -928 + (1080 / 2); // équivalent à 928*(-1) + 0
            } else {
                return -y + (1080 / 2);
            }
        }, personnage.getYProperty());

        IntegerBinding conditionalBindingX = Bindings.createIntegerBinding(() -> {
            int x = personnage.getX();
            if (x < LimitLeftCam) {
                return -LimitLeftCam + (1920 / 2)-300; // équivalent à 928*(-1) + 0
            } else if (x > (((field.longueur() * 32) * 2) - 38) - 864) {
                return -((((field.longueur() * 32) * 2) - 38) - 864) + (1920 / 2)-300;
            } else {
                return (-x-300) + (1920 / 2);
            }
        }, personnage.getXProperty());

        camera.translateXProperty().bind(conditionalBindingX);
        camera.translateYProperty().bind(conditionalBindingY);

        initaliseButton();

        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Ciel.png").toExternalForm());
        Image inventoryCase = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/caseInventaire.png").toExternalForm());
        personnage.addToInventory(ItemStock.Usuable.golden_piece);

        for (int i = 0; i < 48; i++) {
            ImageView imageView = new ImageView(inventoryCase);
            int finalI1 = i;
            imageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ItemInterface outil = personnage.getInventory()[finalI1];
                    if (outil == null) {
                        System.out.println("Rien");
                    } else {
                        System.out.println(outil.getName());
                    }
                }
            });
            Inventory.getChildren().add(imageView);
        }
        System.out.println(field.longueur());
        paneperso.setMouseTransparent(true);

        for (int i = 0; i < tilepane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) tilepane.getChildren().get(i);
            imageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!imageView.getId().equals("ciel")) {
                        imageView.setImage(ciel);
                        imageView.setId("ciel");
                    }
                }
            });
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

    public void initaliseButton() {
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
            ContainerInvontory.setVisible(true);
        });
        quit.setOnMouseClicked(event -> {
            ContainerInvontory.setVisible(false);
        });

    }

}

package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.view.FieldView;
import universite_paris8.iut.rgarry.ashforged.view.CharacterView;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Pane healthBarContainer;
    @FXML
    private Rectangle healthBar;
    @FXML
    private Rectangle healthBarBackground;
    @FXML
    private Rectangle expBar;
    @FXML
    private Rectangle expBarBackground;
    @FXML
    private Rectangle lvl;
    @FXML
    private Label lvlLabel;
    @FXML
    private TilePane tilepane;
    @FXML
    private Pane paneperso;
    @FXML
    private Pane paneItem;
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
    private Pane ContainerInventory;

    @FXML
    private TilePane Inventory;

    @FXML
    private Pane quit;

    private final int LimitLeftCam = 960;

    private CharacterView personnageView;
    private CharacterController characterController;
    private Character personnage;

    private Timeline timeline;

    private Environment environment;

    private LinkedHashMap<ItemInterface, Image> inventory = new LinkedHashMap<>();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();




        // Initialisation de l'environnement
        environment = new Environment(field, List.of(), List.of());
        personnage = environment.getHero();

        characterController = new CharacterController(tilepane, paneperso, personnage);


        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        double maxBarWidth = 200.0;
        healthBar.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> (personnage.getHealth() / (double) personnage.getMaxHealth()) * maxBarWidth,
                        personnage.healthProperty(), personnage.healthProperty()
                )
        );
        expBar.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> (personnage.getExp() / (double) personnage.getExpToNextLevel()) * maxBarWidth,
                        personnage.expProperty(), personnage.expToNextLevelProperty()
                )
        );
        lvlLabel.textProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(personnage.getLevel()),
                personnage.levelProperty()
        ));

        FieldView fieldView = new FieldView(tilepane, field);
        this.personnageView = new CharacterView(paneperso, personnage, characterController, field);

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
                return -LimitLeftCam + (1920 / 2) - 300; // équivalent à 928*(-1) + 0
            } else if (x > (((field.getWidth() * 32) * 2) - 38) - 864) {
                return -((((field.getWidth() * 32) * 2) - 38) - 864) + (1920 / 2) - 300;
            } else {
                return (-x - 300) + (1920 / 2);
            }
        }, personnage.getXProperty());

        camera.translateXProperty().bind(conditionalBindingX);
        camera.translateYProperty().bind(conditionalBindingY);

        initializeButton();

        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/Ciel.png").toExternalForm());
        Image inventoryCase = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/caseInventaire.png").toExternalForm());
        personnage.addToInventory(ItemStock.Usuable.golden_piece);

        for (int i = 0; i < 48; i++) {
            ImageView imageView = new ImageView(inventoryCase);
            int finalI1 = i;

            imageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    HashMap<ItemInterface, Integer> inventoryMap = personnage.getInventory();
                    if (inventoryMap != null) {
                        List<ItemInterface> items = new ArrayList<>(inventoryMap.keySet());

                        if (finalI1 < items.size()) {
                            ItemInterface outil = items.get(finalI1);
                            if (outil == null) {
                                System.out.println("Rien");
                            } else {
                                int quantite = inventoryMap.get(outil);
                                System.out.println(outil.getName() + " x" + quantite);
                            }
                        } else {
                            System.out.println("Index hors limites");
                        }
                    }
                }
            });

            Inventory.getChildren().add(imageView);
        }



        System.out.println(field.getWidth());
        paneperso.setMouseTransparent(true);

        tilepane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println(field.getXView((int) event.getX()));
                System.out.println(field.getYView((int) event.getY()));
                if (field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY())) != 1) {
                    if (Math.abs(personnage.getX() - (int) (event.getX()))  < (64*3) && Math.abs(personnage.getY() - (int) (event.getY())) < (64*3)) {
                        System.out.println(personnage.getX());
                        System.out.println(personnage.getY());
                        field.setBlock(field.getXView((int) event.getX()), field.getYView((int) event.getY()), 1);
                        ImageView test = (ImageView) tilepane.getChildren().get((field.getXView((int) event.getX()) + (field.getYView((int) event.getY())) * field.getWidth()));
                        test.setImage(ciel);
                    }
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY())) == 1) {
                    ImageView test = (ImageView) tilepane.getChildren().get((field.getXView((int) event.getX()) + (field.getYView((int) event.getY())) * field.getWidth()));
                    test.setImage(inventoryCase);
                }
            }
        });
        startTimeline();
    }

    public void initializeInventory() {
        inventory.put(ItemStock.Weapon.stick, new Image(getClass().getResource("/chemin/stick.png").toExternalForm()));
        inventory.put(ItemStock.Weapon.bomb, new Image(getClass().getResource("/chemin/canne.png").toExternalForm()));
    }


    /***
     * Permet de gérer le déplacement du personnage dans le jeu et donc mettre à jour sa position.
     */
    private void startTimeline() {




        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            personnage.seDeplacer();
//            if (characterController.isQPressed()) {
//                characterController.moveCharacter(personnage, environment, 'l'); // Gauche
//                personnageView.changeSprite('l');
//            }
            if (characterController.isSPressed()) {
                characterController.moveCharacter(personnage, environment, 'd'); // Bas
            }
//            if (characterController.isDPressed()) {
//                characterController.moveCharacter(personnage, environment, 'r'); // Droite
//                personnageView.changeSprite('r');
//            }
            if (characterController.isSpacePressed()) {
                characterController.moveCharacter(personnage, environment, 'u'); // Haut
                // Si nécessaire, ajouter une logique pour le saut ou une autre action
            }
            characterController.checkCollision(personnage, environment, Environment.Direction.TOP);
            characterController.checkCollision(personnage, environment, Environment.Direction.BOTTOM);
//            characterController.checkCollision(personnage, environment, Environment.Direction.RIGHT);
//            characterController.checkCollision(personnage, environment, Environment.Direction.LEFT); // Vérifier les collisions
            characterController.applyGravity(environment); // Appliquer la gravité
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void initializeButton() {
        Image stick = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/stick.png").toExternalForm());
        Image canne = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/canne.png").toExternalForm());

        AccesRapide1.setOnMouseClicked(event -> {
            System.out.println(1);
            ContainerInventory.setVisible(true);
        });

        AccesRapide2.setOnMouseClicked(event -> {
            System.out.println(2);

            String item = personnage.findKey(personnage.getInventory(), 2);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });

        AccesRapide3.setOnMouseClicked(event -> {
            System.out.println(3);

            String item = personnage.findKey(personnage.getInventory(), 3);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });

        AccesRapide4.setOnMouseClicked(event -> {
            System.out.println(4);

            String item = personnage.findKey(personnage.getInventory(), 4);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });

        AccesRapide5.setOnMouseClicked(event -> {
            System.out.println(5);

            String item = personnage.findKey(personnage.getInventory(), 5);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });
        AccesRapide6.setOnMouseClicked(event -> {
            System.out.println(6);

            String item = personnage.findKey(personnage.getInventory(), 6);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });

        AccesRapide7.setOnMouseClicked(event -> {
            System.out.println(7);

            String item = personnage.findKey(personnage.getInventory(), 7);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });

        AccesRapide8.setOnMouseClicked(event -> {
            System.out.println(8);

            String item = personnage.findKey(personnage.getInventory(), 8);

            ImageView imageView = new ImageView(item);
            imageView.setFitWidth(32);   // taille personnalisée
            imageView.setFitHeight(32);
            imageView.setLayoutX(300);
            imageView.setLayoutY(300);

            paneItem.getChildren().clear(); // efface l'ancien item
            paneItem.getChildren().add(imageView); // ajoute l'item
        });

        quit.setOnMouseClicked(event -> {
            ContainerInventory.setVisible(false);
        });
    }

}

package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.Projectile.Arrow;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;
import universite_paris8.iut.rgarry.ashforged.view.CraftView;
import universite_paris8.iut.rgarry.ashforged.view.FieldView;
import universite_paris8.iut.rgarry.ashforged.view.CharacterView;
import universite_paris8.iut.rgarry.ashforged.view.MobView;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


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

    @FXML
    private Pane startScreen;

    private List<Pane> accesRapidePanes;

    private int compteur = 0;

    private final int LimitLeftCam = 960;

    private CharacterView personnageView;
    private CharacterController characterController;
    private Character personnage;

    private Timeline timeline;

    private Environment environment;

    private LinkedHashMap<ItemInterface, Image> inventory = new LinkedHashMap<>();

    private CraftView craftView;

    private MobView mobView;

    private ObservableList<Arrow> arrows;


    @FXML
    private void startGame() {
        startScreen.setVisible(false); // cacher l'écran titre

        paneperso.setVisible(true);
        tilepane.setVisible(true);

        // force le layout pour que tout soit recalculé
        paneperso.applyCss();
        paneperso.layout();
        tilepane.applyCss();
        tilepane.layout();

        // recentre la caméra après affichage
        camera.requestLayout();

        // lancer le jeu
        startTimeline();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();

        this.craftView = new CraftView();

        // Initialisation de l'environnement
        environment = new Environment(field);
        personnage = environment.getHero();
        arrows = FXCollections.observableArrayList();
        arrowListener();

        deathMenu();

        this.mobView = new MobView(environment, paneperso);

        characterController = new CharacterController(tilepane, paneperso, personnage);

        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        accesRapidePanes = List.of(
                AccesRapide2, AccesRapide3, AccesRapide4,
                AccesRapide5, AccesRapide6, AccesRapide7, AccesRapide8
        );
        initializeButton();


        FieldView fieldView = new FieldView(tilepane, field);
        this.personnageView = new CharacterView(paneperso, personnage, characterController, field);

        initialiseHealthBar();
        initialiseCamera(field);

        personnage.addToInventory(ItemStock.Usuable.golden_piece);
        personnage.addToInventory(ItemStock.Weapon.stone_pickaxe);
        personnage.addToInventory(ItemStock.Weapon.bow);


        paneperso.setMouseTransparent(true);
        updateInventory();

        initialiseClick(field);

        startTimeline();
    }

    /***
     * Permet de gérer le déplacement du personnage dans le jeu et donc mettre à jour sa position.
     */
    private void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (compteur == 300) compteur = 0;

            if (compteur % 100 == 0) {
                for (Npc npc : mobView.getNpcs()) {
                    npc.choisirDirectionAleatoire();
                }
                for (Mobs mob : mobView.getMobs()) {
                    mob.choisirDirectionAleatoire();
                }
            }

            for (Npc npc : mobView.getNpcs()) {
                npc.applyGravity(environment);
                npc.seDeplacer();
            }
            for (Mobs mob : mobView.getMobs()) {
                mob.action();
            }

            if (compteur % 150 == 0) {
                for (Mobs mob : mobView.getMobs()) {
                    System.out.println("mob attaque");
                    mob.attack();
                }
            }

            if (compteur % 300 == 0) {
                if (personnage.getHealth() + (personnage.getMaxHealth() / 10) <= personnage.getMaxHealth()) {
                    personnage.setHealth(personnage.getHealth() + (personnage.getMaxHealth() / 10));
                } else {
                    personnage.setHealth(personnage.getMaxHealth());
                }
            }
            //supprime flèche si fleche desactiver
            arrows.removeIf(arrow -> !arrow.isActive());
            for (Arrow arrow : arrows) {
                arrow.update(environment);
            }


            personnage.seDeplacer();
            personnage.applyGravity(environment);

            compteur += 1;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private void initializeButton() {
        AccesRapide1.setOnMouseClicked(event -> {
            ContainerInventory.setVisible(true);
            this.craftView.openCraft(personnage);

        });

        quit.setOnMouseClicked(event -> {
            ContainerInventory.setVisible(false);
        });

        for (int i = 0; i < accesRapidePanes.size(); i++) {
            Pane pane = accesRapidePanes.get(i);
            pane.getChildren().clear();

            ImageView iv = new ImageView();
            iv.setFitWidth(32);
            iv.setFitHeight(32);
            iv.setPreserveRatio(true);

            Image itemImage = getItemImageAt(i);  // utilise ta méthode qui combine findKey + getImage
            if (itemImage != null) {
                iv.setImage(itemImage);
                pane.getChildren().add(iv);
                iv.setLayoutX((pane.getPrefWidth() - iv.getFitWidth()) / 2);
                iv.setLayoutY((pane.getPrefHeight() - iv.getFitHeight()) / 2);
            }
        }


    }

    public Image getItemImageAt(int index) {
        ItemInterface item = personnage.findKey(personnage.getInventory(), index);
        if (item != null) {
            return item.getImage();
        }
        return null;
    }

    public void initialiseClick(Field field) {
        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/Ciel.png").toExternalForm());
        Image inventoryCase = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/caseInventaire.png").toExternalForm());
        tilepane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println(field.getXView((int) event.getX()));
                System.out.println(field.getYView((int) event.getY()));
                if (field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY())) != 1) {
                    if (Math.abs(personnage.getX() - (int) (event.getX())) < (64 * 3) && Math.abs(personnage.getY() - (int) (event.getY())) < (64 * 3)) {
                        if (personnage.getHoldingItem().getName().contains("pickaxe")) {
                            field.setBlock(field.getXView((int) event.getX()), field.getYView((int) event.getY()), 1);
                            ImageView test = (ImageView) tilepane.getChildren().get((field.getXView((int) event.getX()) + (field.getYView((int) event.getY())) * field.getWidth()));
                            test.setImage(ciel);
                        }
                    }
                }
                if (personnage.getHoldingItem().getName().contains("Bow")){
                    Arrow arrow = new Arrow(personnage.getX(),personnage.getY()-32);
                    arrows.add(arrow);
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY())) == 1) {
                    if (Math.abs(personnage.getX() - (int) (event.getX())) < (64 * 3) && Math.abs(personnage.getY() - (int) (event.getY())) < (64 * 3)) {
                        ImageView test = (ImageView) tilepane.getChildren().get((field.getXView((int) event.getX()) + (field.getYView((int) event.getY())) * field.getWidth()));
                        test.setImage(inventoryCase);
                    }
                }
            }
            updateInventory();
        });
    }

    public void updateInventory() {
        Inventory.getChildren().clear();
        for (int i = 0; i < 48; i++) {
            int finalI1 = i;
            ImageView imageView = new ImageView(getItemImageAt(i));


            imageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    HashMap<ItemInterface, Integer> inventoryMap = personnage.getInventory();
                    if (inventoryMap != null) {
                        List<ItemInterface> items = new ArrayList<>(inventoryMap.keySet());

                        if (finalI1 < items.size()) {
                            ItemInterface item = items.get(finalI1);
                            if (item == null) {
                                System.out.println("Rien");
                            } else {
                                personnage.setHoldingItem(item);
                                int quantite = inventoryMap.get(item);
                                System.out.println(item.getName() + "x" + quantite);
                            }
                        } else {
                            System.out.println("Index hors limites");
                        }
                    }
                }
            });

            Inventory.getChildren().add(imageView);
        }
    }

    public void initialiseCamera(Field field) {
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

        IntegerBinding conditionalBindingY = Bindings.createIntegerBinding(() -> {
            int y = personnage.getY();
            if (y > 928) {
                return -928 + (1080 / 2); // équivalent à 928*(-1) + 0
            } else {
                return -y + (1080 / 2);
            }
        }, personnage.getYProperty());

        camera.translateXProperty().bind(conditionalBindingX);
        camera.translateYProperty().bind(conditionalBindingY);
    }

    public void initialiseHealthBar() {
        double maxBarWidth = 200.0;
        healthBar.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> (personnage.getHealth() / (double) personnage.getMaxHealth()) * maxBarWidth,
                        personnage.healthProperty()
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
    }

    public void deathMenu() {
        personnage.healthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() <= 0) {
                System.out.println("The character is dead!");

                try {
                    // Arrêter le timeline du jeu
                    if (timeline != null) {
                        timeline.stop();
                    }

                    // Changer directement vers la page Game Over
                    Stage stage = (Stage) paneperso.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/dieAndRespawn.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

                    stage.setTitle("Ashforged - Game Over");
                    stage.setScene(scene);
                    stage.setFullScreen(true);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Erreur lors de l'affichage de l'écran Game Over");
                }
            }
        });

    }

    public void arrowListener() {
        arrows.addListener((ListChangeListener<Arrow>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Arrow arrow : change.getAddedSubList()) {
                        ImageView fleche =  new ImageView(new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/arrow.png").toExternalForm()));
                        fleche.translateXProperty().bind(arrow.xProperty());
                        fleche.translateYProperty().bind(arrow.yProperty());
                        arrow.setArrowImage(fleche);
                        paneperso.getChildren().add(fleche);
                    }
                }
                if (change.wasRemoved()) {
                    for (Arrow arrow : change.getRemoved()) {
                        paneperso.getChildren().remove(arrow.getArrowImage());
                    }
                }
            }
        });

    }
}
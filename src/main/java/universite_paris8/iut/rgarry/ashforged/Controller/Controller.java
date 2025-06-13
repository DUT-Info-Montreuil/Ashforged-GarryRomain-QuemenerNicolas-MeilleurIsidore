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
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;
import universite_paris8.iut.rgarry.ashforged.view.FieldView;
import universite_paris8.iut.rgarry.ashforged.view.CharacterView;

import java.net.URL;
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

    private CharacterView personnageView;
    private CharacterController characterController;
    private Character personnage;
    private List<Npc> npcs = new ArrayList<>();
    private List<Mobs> mobs = new ArrayList<>();

    private Timeline timeline;
    private Timeline npcMoveTimeline;

    private Environment environment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();

        // Initialisation de l'environnement
        environment = new Environment(field);
        personnage = environment.getHero();
        mobs = environment.getMobs();
        npcs = environment.getNpcs();

        Image pierreImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/caseInventaire.png").toExternalForm());
        Image terreImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/New Piskel-3.png.png").toExternalForm());
        Image paoloImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/NPC/leftPaolo.png").toExternalForm());

        for (Npc npc : npcs) {
            ImageView npcView;
            if(npc.getName().equals("Paolo")) {
                npcView = new ImageView(paoloImage);
            } else {
                npcView = new ImageView(pierreImage);
                if(npc.getName().equals("Branda")) {
                    npc.setX(200);
                    npc.setY(200);
                } else if(npc.getName().equals("Terry")) {
                    npc.setX(300);
                    npc.setY(300);
                } else if(npc.getName().equals("Salome")) {
                    npc.setX(400);
                    npc.setY(400);
                }
            }
            npcView.layoutXProperty().bind(npc.getXProperty().asObject());
            npcView.layoutYProperty().bind(npc.getYProperty().asObject());
            paneperso.getChildren().add(npcView);
        }

        for (Mobs mob : mobs) {
            ImageView mobView = new ImageView(terreImage);
            mobView.layoutXProperty().bind(mob.getXProperty().asObject());
            mobView.layoutYProperty().bind(mob.getYProperty().asObject());
            paneperso.getChildren().add(mobView);
        }

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
                return -928 + (1080 / 2);
            } else {
                return -y + (1080 / 2);
            }
        }, personnage.getYProperty());

        IntegerBinding conditionalBindingX = Bindings.createIntegerBinding(() -> {
            int x = personnage.getX();
            if (x < LimitLeftCam) {
                return -LimitLeftCam + (1920 / 2) - 300;
            } else if (x > (((field.getWidth() * 32) * 2) - 38) - 864) {
                return -((((field.getWidth() * 32) * 2) - 38) - 864) + (1920 / 2) - 300;
            } else {
                return (-x - 300) + (1920 / 2);
            }
        }, personnage.getXProperty());

        camera.translateXProperty().bind(conditionalBindingX);
        camera.translateYProperty().bind(conditionalBindingY);

        initializeButton();

        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Ciel.png").toExternalForm());
        Image inventoryCase = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/caseInventaire.png").toExternalForm());
        personnage.addToInventory(ItemStock.Usuable.golden_piece);

        for (int i = 0; i < 48; i++) {
            ImageView imageView = new ImageView(inventoryCase);
            int finalI1 = i;
            imageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ItemInterface[] inventory = personnage.getInventory();
                    if (inventory != null) {
                        ItemInterface outil = inventory[finalI1];
                        if (outil == null) {
                            System.out.println("Rien");
                        } else {
                            System.out.println(outil.getName());
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

    private void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            for (Npc npc : npcs) {
                npc.applyGravity(environment);
                npc.seDeplacer();
            }
            for (Mobs mob : mobs) {
                mob.applyGravity(environment);
                mob.seDeplacer();
            }
            personnage.seDeplacer();
            personnage.applyGravity(environment);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        npcMoveTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            for (Npc npc : npcs) {
                npc.choisirDirectionAleatoire();
            }
            for (Mobs mob : mobs) {
                mob.choisirDirectionAleatoire();
            }
        }));
        npcMoveTimeline.setCycleCount(Timeline.INDEFINITE);
        npcMoveTimeline.play();
    }

    public void initializeButton() {
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
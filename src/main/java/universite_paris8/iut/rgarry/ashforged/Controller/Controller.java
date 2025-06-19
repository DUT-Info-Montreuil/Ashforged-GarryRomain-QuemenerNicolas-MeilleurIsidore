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
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;
import universite_paris8.iut.rgarry.ashforged.view.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // UI components injected via FXML
    @FXML private Pane healthBarContainer;
    @FXML private Rectangle healthBar;
    @FXML private Rectangle healthBarBackground;
    @FXML private Rectangle expBar;
    @FXML private Rectangle expBarBackground;
    @FXML private Rectangle lvl;
    @FXML private Label lvlLabel;
    @FXML private TilePane tilepane;
    @FXML private Pane paneperso;
    @FXML private Pane paneItem;
    @FXML private Pane camera;
    @FXML private Pane AccesRapide1;
    @FXML private Pane AccesRapide2;
    @FXML private Pane ContainerInventory;
    @FXML private TilePane Inventory;
    @FXML private Pane quit;
    @FXML private Pane startScreen;

    private List<Pane> accesRapidePanes;
    private int compteur = 0; // counter for timeline cycles

    private final int LimitLeftCam = 960; // left limit for camera movement

    // Views and controllers
    private CharacterView personnageView;
    private CharacterController characterController;
    private Character personnage;

    private Timeline timeline; // game loop timer

    private Environment environment; // game environment holding entities, field, etc.

    private LinkedHashMap<ItemInterface, Image> inventory = new LinkedHashMap<>();

    private CraftView craftView;
    private MobView mobView;

    private List<Entity> entitiesToDie = new ArrayList<>(); // entities scheduled for removal

    private ObservableList<Arrow> arrows; // currently active arrows in game

    /**
     * Called when the user starts the game.
     * Hides the start screen, shows game UI, and starts the game loop.
     */
    @FXML
    private void startGame() {
        startScreen.setVisible(false); // hide start screen
        paneperso.setVisible(true);    // show player pane
        tilepane.setVisible(true);     // show tile pane

        // Force layout recalculation for UI components
        paneperso.applyCss();
        paneperso.layout();
        tilepane.applyCss();
        tilepane.layout();

        // Request camera to recenter after layout changes
        camera.requestLayout();

        // Start the main game loop
        startTimeline();
    }

    /**
     * JavaFX initialize method called after FXML loading.
     * Sets up the environment, player character, views, inventory, UI handlers, and starts timeline.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();

        this.craftView = new CraftView();

        // Initialize the game environment with the field
        environment = new Environment(field);

        // Get the player character (hero)
        personnage = environment.getHero();

        // Initialize arrow list and add listener for changes
        arrows = FXCollections.observableArrayList();
        arrowListener();

        // Setup death menu logic
        deathMenu();

        // Initialize mob view to manage mobs and NPCs
        this.mobView = new MobView(environment, paneperso);

        // Setup character controller for player movement and interaction
        characterController = new CharacterController(tilepane, paneperso, personnage);

        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        // Initialize field view with tiles
        FieldView fieldView = new FieldView(tilepane, field);

        // Initialize character view with player and controller
        this.personnageView = new CharacterView(paneperso, personnage, characterController, field);

        // Setup health bar and camera behavior
        initializeHealthBar();
        initializeCamera(field);

        // Add some starting items to player's inventory
        personnage.addToInventory(ItemStock.Usuable.golden_piece);
        personnage.addToInventory(ItemStock.Weapon.stone_pickaxe);
        personnage.addToInventory(ItemStock.Weapon.stone_sword);
        personnage.addToInventory(ItemStock.Weapon.bow);
        personnage.addToInventory(ItemStock.Usuable.wood);
        personnage.addToInventory(ItemStock.Usuable.wood);
        personnage.addToInventory(ItemStock.Usuable.wood);

        paneperso.setMouseTransparent(true);
        updateInventory();

        // Setup click handlers on the game field
        initializeClick(field);

        // Setup UI button handlers
        initializeButton();

        // Start the game loop timeline
        startTimeline();
    }

    /**
     * Opens the stat allocation window for the player.
     */
    private void openStatAllocationWindow() {
        StatAllocationView.show(personnage);
    }

    /**
     * Starts the game loop using a Timeline that runs every 10 ms.
     * Handles game logic: mob spawning, entity updates, attacks, health regen, arrow updates, movement, and gravity.
     */
    private void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (compteur == 300) compteur = 0; // reset counter periodically

            // Collect entities with zero or less health to remove
            for (Entity entity : environment.getEntities()) {
                if (entity.getHealth() <= 0) entitiesToDie.add(entity);
            }

            // Remove dead entities from scene and environment
            for (Entity entity : entitiesToDie) {
                paneperso.getChildren().remove(entity.getNode());
                environment.removeEntity(entity);
            }
            entitiesToDie.clear();

            // Spawn new mobs if fewer than 5 exist every 50 cycles
            if (compteur % 50 == 0) {
                if (environment.getMobs().size() < 5) System.out.println("Generate new mobs");
                environment.generateRandomMob();
                mobView.setMobsView();
            }

            // NPC and mobs choose random directions every 100 cycles
            if (compteur % 100 == 0) {
                for (Npc npc : mobView.getNpcs()) {
                    npc.choisirDirectionAleatoire();
                }
                for (Mobs mob : mobView.getMobs()) {
                    mob.choisirDirectionAleatoire();
                }
            }

            // Apply gravity and move NPCs
            for (Npc npc : mobView.getNpcs()) {
                npc.applyGravity(environment);
                npc.seDeplacer();
            }
            // Execute mob AI actions
            for (Mobs mob : mobView.getMobs()) {
                mob.action();
            }

            // Entities attack every 150 cycles
            if (compteur % 150 == 0) {
                for (Entity e : environment.getEntities()) {
                    e.attack();
                }
            }

            // Health regeneration every 300 cycles
            if (compteur % 300 == 0) {
                if (personnage.getHealth() > 0) {
                    personnage.setHealth(Math.min(personnage.getHealth() + (personnage.getMaxHealth() / 10), personnage.getMaxHealth()));
                }
            }

            // Remove inactive arrows from list and update active arrows
            arrows.removeIf(arrow -> !arrow.isActive());
            for (Arrow arrow : arrows) {
                arrow.update();
            }

            // Update player movement and apply gravity
            personnage.seDeplacer();
            personnage.applyGravity(environment);

            compteur += 1;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Initializes UI buttons and their mouse click handlers.
     */
    private void initializeButton() {
        AccesRapide1.setOnMouseClicked(event -> {
            ContainerInventory.setVisible(true);
        });

        quit.setOnMouseClicked(event -> {
            ContainerInventory.setVisible(false);
        });

        AccesRapide2.setOnMouseClicked(event -> {
            this.craftView.openCraft(personnage);
        });

        lvlLabel.setOnMouseClicked(event -> {
            openStatAllocationWindow();
        });
    }

    /**
     * Retrieves the image of the item at the specified inventory index.
     * @param index The inventory slot index.
     * @return The image of the item, or null if none exists.
     */
    public Image getItemImageAt(int index) {
        ItemInterface item = personnage.findKey(personnage.getInventory(), index);
        if (item != null) {
            return item.getImage();
        }
        return null;
    }

    /**
     * Initializes click handlers on the game field to handle player actions like attack, mining, placing blocks, and shooting arrows.
     * @param field The game field containing blocks.
     */
    public void initializeClick(Field field) {
        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png").toExternalForm());
        Image inventoryCase = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/caseInventaire.png").toExternalForm());

        tilepane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Attack with weapon if holding a weapon other than pickaxe
                if (personnage.getHoldingItem() instanceof ItemStock.Weapon && !personnage.getHoldingItem().getName().contains("pickaxe")) {
                    personnage.attack();
                }
                // Mining blocks with pickaxe if close enough and block is breakable
                else if (personnage.getHoldingItem().getName().contains("pickaxe")) {
                    if (Math.abs(personnage.getX() - (int) (event.getX())) < (64 * 3) && Math.abs(personnage.getY() - (int) (event.getY())) < (64 * 3)) {
                        if (field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY())) != 1) {
                            personnage.addToInventory(ItemStock.Tile.fromId(field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY()))));
                            field.setBlock(field.getXView((int) event.getX()), field.getYView((int) event.getY()), 1);
                            ImageView test = (ImageView) tilepane.getChildren().get((field.getXView((int) event.getX()) + (field.getYView((int) event.getY())) * field.getWidth()));
                            test.setImage(ciel);
                        }
                    }
                }
                // Shoot arrow if holding bow
                if (personnage.getHoldingItem().getName().contains("Bow")){
                    Arrow arrow = new Arrow(personnage.getX(),personnage.getY()-32, environment);
                    arrows.add(arrow);
                }
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                // Place block if holding a block and close enough to the target position
                if  (personnage.getHoldingItem() instanceof ItemStock.Tile){
                    if (Math.abs(personnage.getX() - (int) (event.getX())) < (64 * 3) && Math.abs(personnage.getY() - (int) (event.getY())) < (64 * 3)) {
                        if (field.block(field.getXView((int) event.getX()), field.getYView((int) event.getY())) == 1) {
                            field.setBlock(field.getXView((int) event.getX()), field.getYView((int) event.getY()),personnage.getHoldingItem().getId());
                            ImageView blockPoser = (ImageView) tilepane.getChildren().get((field.getXView((int) event.getX()) + (field.getYView((int) event.getY())) * field.getWidth()));
                            blockPoser.setImage(personnage.getHoldingItem().getImage());
                            personnage.removeFromInventory(personnage.getHoldingItem());
                        }
                    }
                }
            }
            // Update the inventory display after any action
            updateInventory();
        });
    }

    /**
     * Updates the inventory UI to display current items and allows selecting items on click.
     */
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
                            if (!(item == null)) {
                                personnage.setHoldingItem(item);
                                int quantite = inventoryMap.get(item);
                                System.out.println(item.getName() + "x" + quantite);
                            }
                        }
                    }
                }
            });

            Inventory.getChildren().add(imageView);
        }
    }

    public void initializeCamera(Field field) {
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

    public void initializeHealthBar() {
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
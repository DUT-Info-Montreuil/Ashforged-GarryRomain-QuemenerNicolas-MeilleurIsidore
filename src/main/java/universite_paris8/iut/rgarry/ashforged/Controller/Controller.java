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
import java.util.function.Consumer;

public class Controller implements Initializable {

    // --- FXML UI components ---
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
    @FXML private Pane quickAccess1;
    @FXML private Pane quickAccess2;
    @FXML private Pane containerInventory;
    @FXML private TilePane inventory;
    @FXML private Pane quit;
    @FXML private Pane startScreen;

    private List<Pane> quickAccessPanes;
    private int counter = 0;
    private final int limitLeftCam = 960;

    private CharacterView characterView;
    private CharacterController characterController;
    private Character character;

    private Timeline timeline;
    private Environment environment;

    private LinkedHashMap<ItemInterface, Image> inventoryMap = new LinkedHashMap<>();

    private CraftView craftView;
    private MobView mobView;
    private List<Entity> entitiesToRemove = new ArrayList<>();

    private ObservableList<Arrow> arrows;

    /**
     * Starts the game by hiding the start screen, showing game panes,
     * and starting the game loop (timeline).
     * Precondition: Start screen is visible.
     * Postcondition: Start screen hidden, game UI visible, game loop running.
     */
    @FXML
    private void startGame() {
        startScreen.setVisible(false); // hide start screen
        paneperso.setVisible(true);
        tilepane.setVisible(true);

        paneperso.applyCss();
        paneperso.layout();
        tilepane.applyCss();
        tilepane.layout();

        camera.requestLayout();

        startGameLoop();
    }

    /**
     * Initialization of the controller.
     * Sets up environment, character, views, UI bindings, and event handlers.
     * Called automatically after FXML load.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field field = new Field();
        this.craftView = new CraftView();
        environment = new Environment(field);
        character = environment.getHero();
        arrows = FXCollections.observableArrayList();
        addArrowListener();

        setupDeathMenu();

        this.mobView = new MobView(environment, paneperso);
        characterController = new CharacterController(tilepane, character);

        paneperso.setFocusTraversable(true);
        paneperso.requestFocus();

        new FieldView(tilepane, field);
        this.characterView = new CharacterView(paneperso, character, characterController, field);

        initializeHealthBar();
        initializeCamera(field);

        // Add initial items to inventory
        character.addToInventory(ItemStock.Usuable.golden_piece);
        character.addToInventory(ItemStock.Weapon.stone_pickaxe);
        character.addToInventory(ItemStock.Weapon.stone_sword);
        character.addToInventory(ItemStock.Weapon.bow);

        paneperso.setMouseTransparent(true);
        updateInventory();

        initializeClickHandler(field);
        initializeButtons();

        startGameLoop();
    }

    /**
     * Opens the statistics allocation window for the character.
     */
    private void openStatAllocationWindow() {
        StatAllocationView.show(character);
    }

    /**
     * Main game loop using JavaFX Timeline.
     * Updates entities, handles mob generation, movement, attacks, health regen, and arrow updates.
     * Runs indefinitely until stopped.
     */
    private void startGameLoop() {
        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (counter == 300) counter = 0;

            // Remove dead entities
            for (Entity entity : environment.getEntities()) {
                if (entity.getHealth() <= 0) entitiesToRemove.add(entity);
            }
            for (Entity entity : entitiesToRemove) {
                paneperso.getChildren().remove(entity.getNode());
                environment.removeEntity(entity);
            }
            entitiesToRemove.clear();

            // Mob generation every 50 ticks
            if (counter % 50 == 0) {
                if (environment.getMobs().size() < 5) System.out.println("Generating new mobs");
                environment.generateRandomMob();
                mobView.setMobsView();
            }

            // Randomize NPC and mob directions every 100 ticks
            if (counter % 100 == 0) {
                for (Npc npc : mobView.getNpcs()) {
                    npc.chooseRandomDirection();
                }
                for (Mobs mob : mobView.getMobs()) {
                    mob.chooseRandomDirection();
                }
            }

            // Apply gravity and move NPCs
            for (Npc npc : mobView.getNpcs()) {
                npc.applyGravity(environment);
                npc.move();
            }
            // Mobs perform their actions
            for (Mobs mob : mobView.getMobs()) {
                mob.action();
            }

            // Entities attack every 150 ticks
            if (counter % 150 == 0) {
                for (Entity e : environment.getEntities()) {
                    e.attack();
                }
            }

            // Health regeneration every 300 ticks
            if (counter % 300 == 0) {
                if (character.getHealth() > 0) {
                    character.setHealth(Math.min(character.getHealth() + (character.getMaxHealth() / 10), character.getMaxHealth()));
                }
            }

            // Remove inactive arrows
            arrows.removeIf(arrow -> !arrow.isActive());
            arrows.forEach(Arrow::update);

            character.move();
            character.applyGravity(environment);

            counter++;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Initializes button event handlers.
     * - Quick access buttons open inventory or craft windows.
     * - Quit button hides inventory.
     * - Level label opens stat allocation.
     */
    private void initializeButtons() {
        quickAccess1.setOnMouseClicked(event -> containerInventory.setVisible(true));
        quit.setOnMouseClicked(event -> containerInventory.setVisible(false));
        quickAccess2.setOnMouseClicked(event -> craftView.openCraft(character));
        lvlLabel.setOnMouseClicked(event -> openStatAllocationWindow());
    }

    /**
     * Returns the image of the item at the given inventory index.
     * @param index position in inventory
     * @return Image of the item or null if none
     */
    public Image getItemImageAt(int index) {
        ItemInterface item = character.findKey(character.getInventory(), index);
        return (item != null) ? item.getImage() : null;
    }

    /**
     * Initializes mouse click behavior on the game tile pane.
     * Handles attacks, mining, placing blocks, and shooting arrows based on mouse button and held item.
     * @param field current game field
     */
    public void initializeClickHandler(Field field) {
        Image skyImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png").toExternalForm());
        tilepane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (character.getHoldingItem() instanceof ItemStock.Weapon && !character.getHoldingItem().getName().contains("pickaxe")) {
                    character.attack();
                } else if (character.getHoldingItem().getName().contains("pickaxe")) {
                    if (Math.abs(character.getX() - (int) event.getX()) < (64 * 3) && Math.abs(character.getY() - (int) event.getY()) < (64 * 3)) {
                        int xView = field.getXView((int) event.getX());
                        int yView = field.getYView((int) event.getY());
                        if (field.block(xView, yView) != 1) {
                            character.addToInventory(ItemStock.Tile.fromId(field.block(xView, yView)));
                            field.setBlock(xView, yView, 1);
                            ImageView tileImage = (ImageView) tilepane.getChildren().get(xView + yView * field.getWidth());
                            tileImage.setImage(skyImage);
                        }
                    }
                }
                if (character.getHoldingItem().getName().contains("Bow")) {
                    Arrow arrow = new Arrow(character.getX(), character.getY() - 32, environment);
                    arrows.add(arrow);
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (character.getHoldingItem() instanceof ItemStock.Tile) {
                    if (Math.abs(character.getX() - (int) event.getX()) < (64 * 3) && Math.abs(character.getY() - (int) event.getY()) < (64 * 3)) {
                        int xView = field.getXView((int) event.getX());
                        int yView = field.getYView((int) event.getY());
                        if (field.block(xView, yView) == 1) {
                            field.setBlock(xView, yView, character.getHoldingItem().getId());
                            ImageView placedBlock = (ImageView) tilepane.getChildren().get(xView + yView * field.getWidth());
                            placedBlock.setImage(character.getHoldingItem().getImage());
                            character.removeFromInventory(character.getHoldingItem());
                        }
                    }
                }
            }
            updateInventory();
        });
    }

    /**
     * Updates the inventory UI based on the character's current inventory.
     * Each item is shown as an ImageView, and can be clicked to hold/select the item.
     */
    public void updateInventory() {
        inventory.getChildren().clear();
        for (int i = 0; i < 48; i++) {
            int index = i;
            ImageView imageView = new ImageView(getItemImageAt(i));
            imageView.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Map<ItemInterface, Integer> inventoryMap = character.getInventory();
                    if (inventoryMap != null) {
                        List<ItemInterface> items = new ArrayList<>(inventoryMap.keySet());
                        if (index < items.size()) {
                            ItemInterface item = items.get(index);
                            if (item != null) {
                                character.setHoldingItem(item);
                                int quantity = inventoryMap.get(item);
                                System.out.println(item.getName() + " x" + quantity);
                            }
                        }
                    }
                }
            });
            inventory.getChildren().add(imageView);
        }
    }

    /**
     * Initializes the camera to follow the character with bounds.
     * The camera translation is bound to character coordinates with constraints to not go beyond field edges.
     * @param field current game field
     */
    public void initializeCamera(Field field) {
        IntegerBinding bindX = Bindings.createIntegerBinding(() -> {
            int x = character.getX();
            if (x < limitLeftCam) {
                return -limitLeftCam + (1920 / 2) - 300;
            } else if (x > (((field.getWidth() * 32) * 2) - 38) - 864) {
                return -((((field.getWidth() * 32) * 2) - 38) - 864) + (1920 / 2) - 300;
            } else {
                return (-x - 300) + (1920 / 2);
            }
        }, character.getXProperty());

        IntegerBinding bindY = Bindings.createIntegerBinding(() -> {
            int y = character.getY();
            if (y > 928) {
                return -928 + (1080 / 2);
            } else {
                return -y + (1080 / 2);
            }
        }, character.getYProperty());

        camera.translateXProperty().bind(bindX);
        camera.translateYProperty().bind(bindY);
    }

    /**
     * Initializes health and experience bars and level label.
     * Bars width dynamically reflect current health/exp relative to max.
     */
    public void initializeHealthBar() {
        double maxBarWidth = 200.0;
        healthBar.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> (character.getHealth() / (double) character.getMaxHealth()) * maxBarWidth,
                        character.healthProperty()
                )
        );
        expBar.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> (character.getExp() / (double) character.getExpToNextLevel()) * maxBarWidth,
                        character.expProperty(), character.expToNextLevelProperty()
                )
        );
        lvlLabel.textProperty().bind(
                Bindings.createStringBinding(() -> String.valueOf(character.getLevel()), character.levelProperty())
        );
    }

    /**
     * Sets up a listener to show the death menu and stop the game loop when character health reaches zero or less.
     */
    public void setupDeathMenu() {
        character.healthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() <= 0) {
                try {
                    if (timeline != null) timeline.stop();

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

    /**
     * Adds a listener on the arrow list to update the UI when arrows are added or removed.
     * Arrows are represented by ImageView nodes bound to arrow coordinates.
     */
    public void addArrowListener() {
        arrows.addListener((ListChangeListener<Arrow>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Arrow arrow : change.getAddedSubList()) {
                        ImageView arrowImage = new ImageView(new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/arrow.png").toExternalForm()));
                        arrowImage.translateXProperty().bind(arrow.xProperty());
                        arrowImage.translateYProperty().bind(arrow.yProperty());
                        arrow.setArrowImage(arrowImage);
                        paneperso.getChildren().add(arrowImage);
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

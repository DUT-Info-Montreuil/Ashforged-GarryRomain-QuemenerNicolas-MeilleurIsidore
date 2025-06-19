package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Field;

// Class to manage the graphical representation of a game field using a TilePane
public class FieldView {

    private Field field; // The game field model containing block data
    private TilePane tilePane; // The TilePane to display the field's tiles
    private static final double TILE_SIZE = 64; // Fixed size of each tile in pixels

    /**
     * Constructor for FieldView, initializes the view with a field model and a TilePane.
     * Creates the field view by calling createField.
     *
     * @param tilePane The TilePane where the field's tiles are displayed.
     * @param field The Field model containing the block data for the game map.
     */
    public FieldView(TilePane tilePane, Field field) {
        this.field = field; // Store the field model reference
        this.tilePane = tilePane; // Store the TilePane reference
        createField(tilePane); // Initialize the field view
    }

    /**
     * Creates the game field by rendering tiles based on the block data from the Field model.
     * Configures the TilePane and adds ImageView objects for each tile, using appropriate images
     * based on block types.
     *
     * @param tilePane The TilePane where the tiles are displayed.
     */
    public void createField(TilePane tilePane) {
        // Retrieve field dimensions
        int rows = field.getHeight(); // Number of rows (height)
        int cols = field.getWidth();  // Number of columns (width)

        // Validate field dimensions
        if (rows <= 0 || cols <= 0) {
            System.err.println("Invalid map dimensions: rows=" + rows + ", cols=" + cols);
            throw new IllegalStateException("Field dimensions must be positive");
        }

        // Clear existing tiles and configure TilePane
        tilePane.getChildren().clear(); // Remove any previous tiles
        tilePane.setPrefColumns(cols); // Set number of columns
        tilePane.setPrefRows(rows); // Set number of rows
        tilePane.setPrefSize(cols * TILE_SIZE, rows * TILE_SIZE); // Set TilePane size
        tilePane.setHgap(0); // No horizontal gap between tiles
        tilePane.setVgap(0); // No vertical gap between tiles
        tilePane.setAlignment(javafx.geometry.Pos.TOP_LEFT); // Align tiles to top-left

        // Load tile images from resources
        Image sky = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png").toExternalForm());
        Image grass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/grass.png").toExternalForm());
        Image leftGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftGrass.png").toExternalForm());
        Image rightGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightGrass.png").toExternalForm());
        Image rightSideGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightSideGrass.png").toExternalForm());
        Image leftSideGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftSideGrass.png").toExternalForm());
        Image stone = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png").toExternalForm());
        Image ground = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png").toExternalForm());
        Image building = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/building.png").toExternalForm());
        Image glass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/glass.png").toExternalForm());
        Image coal = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/coal.png").toExternalForm());
        Image enchantedMineral = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/enchanted_mineral.png").toExternalForm());
        Image gold = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/gold.png").toExternalForm());
        Image iron = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png").toExternalForm());
        Image leaf = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leaf.png").toExternalForm());
        Image wood = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/wood.png").toExternalForm());

        // Iterate through rows and columns to create tiles
        for (int y = 0; y < rows; y++) { // Loop through height
            for (int x = 0; x < cols; x++) { // Loop through width
                ImageView imageView;
                int blockType = field.block(x, y); // Get block type at position (x, y)

                // Assign image and ID based on block type
                switch (blockType) {
                    case 0:
                        imageView = new ImageView(iron);
                        imageView.setId("iron");
                        break;
                    case 1:
                        imageView = new ImageView(sky);
                        imageView.setId("sky");
                        break;
                    case 2:
                        imageView = new ImageView(ground);
                        imageView.setId("ground");
                        break;
                    case 3:
                        imageView = new ImageView(rightGrass);
                        imageView.setId("rightGrass");
                        break;
                    case 4:
                        imageView = new ImageView(leftGrass);
                        imageView.setId("leftGrass");
                        break;
                    case 5:
                        imageView = new ImageView(rightSideGrass);
                        imageView.setId("rightSideGrass");
                        break;
                    case 6:
                        imageView = new ImageView(leftSideGrass);
                        imageView.setId("leftSideGrass");
                        break;
                    case 7:
                        imageView = new ImageView(stone);
                        imageView.setId("stone");
                        break;
                    case 8:
                        imageView = new ImageView(grass);
                        imageView.setId("grass");
                        break;
                    case 9:
                        imageView = new ImageView(wood);
                        imageView.setId("wood");
                        break;
                    case 10:
                        imageView = new ImageView(leaf);
                        imageView.setId("leaf");
                        break;
                    case 11:
                        imageView = new ImageView(gold);
                        imageView.setId("gold");
                        break;
                    case 12:
                        imageView = new ImageView(enchantedMineral);
                        imageView.setId("enchantedMineral");
                        break;
                    case 13:
                        imageView = new ImageView(coal);
                        imageView.setId("coal");
                        break;
                    case 15:
                        imageView = new ImageView(building);
                        imageView.setId("building");
                        break;
                    case 16:
                        imageView = new ImageView(glass);
                        imageView.setId("glass");
                        break;
                    default:
                        imageView = new ImageView(sky); // Default to sky for unknown block types
                        imageView.setId("unknown");
                        break;
                }

                // Set tile dimensions
                imageView.setFitWidth(TILE_SIZE); // Set width to tile size
                imageView.setFitHeight(TILE_SIZE); // Set height to tile size
                tilePane.getChildren().add(imageView); // Add tile to TilePane
            }
        }
    }
}
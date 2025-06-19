package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Field;

/**
 * FieldView is responsible for rendering the game field (tiles) visually
 * using JavaFX's TilePane. It maps the logical field structure (from the model)
 * to graphical components based on block types.
 */
public class FieldView {

    private Field field; // Logical field representation from the model
    private TilePane tilepane; // JavaFX TilePane to visually display tiles
    private static final double TILE_SIZE = 64; // Fixed size for each tile in pixels

    /**
     * Constructor initializes the field view and immediately builds the tile map.
     *
     * @param tilepane the JavaFX TilePane where tiles are rendered
     * @param field    the logical field model containing tile types
     */
    public FieldView(TilePane tilepane, Field field) {
        this.field = field;
        this.tilepane = tilepane;
        this.createField(tilepane);
    }

    /**
     * Builds and populates the TilePane based on the field's 2D block matrix.
     * Each block type is translated into a specific image tile.
     *
     * @param tilepane the pane to populate with tile images
     */
    public void createField(TilePane tilepane) {
        int rows = field.getHeight(); // Number of rows (vertical tiles)
        int cols = field.getWidth();  // Number of columns (horizontal tiles)

        // Basic validation of field dimensions
        if (rows <= 0 || cols <= 0) {
            System.err.println("Invalid map dimensions: rows=" + rows + ", cols=" + cols);
            return;
        }

        // Prepare TilePane layout and size
        tilepane.getChildren().clear();
        tilepane.setPrefColumns(cols);
        tilepane.setPrefRows(rows);
        tilepane.setPrefSize(cols * TILE_SIZE, rows * TILE_SIZE);
        tilepane.setHgap(0);
        tilepane.setVgap(0);
        tilepane.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        // Load all required tile images
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

        // Populate the field using nested loops over coordinates
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                ImageView imageView;
                int blockType = field.block(x, y); // Retrieve block type for current tile

                // Assign image and ID based on block type
                if (blockType == 0) {
                    imageView = new ImageView(iron);
                    imageView.setId("iron");
                } else if (blockType == 8) {
                    imageView = new ImageView(grass);
                    imageView.setId("grass");
                } else if (blockType == 2) {
                    imageView = new ImageView(ground);
                    imageView.setId("ground");
                } else if (blockType == 3) {
                    imageView = new ImageView(rightGrass);
                    imageView.setId("rightGrass");
                } else if (blockType == 4) {
                    imageView = new ImageView(leftGrass);
                    imageView.setId("leftGrass");
                } else if (blockType == 5) {
                    imageView = new ImageView(rightSideGrass);
                    imageView.setId("rightSideGrass");
                } else if (blockType == 6) {
                    imageView = new ImageView(leftSideGrass);
                    imageView.setId("leftSideGrass");
                } else if (blockType == 7) {
                    imageView = new ImageView(stone);
                    imageView.setId("stone");
                } else if (blockType == 1) {
                    imageView = new ImageView(sky);
                    imageView.setId("sky");
                } else if (blockType == 9) {
                    imageView = new ImageView(wood);
                    imageView.setId("wood");
                } else if (blockType == 10) {
                    imageView = new ImageView(leaf);
                    imageView.setId("leaf");
                } else if (blockType == 11) {
                    imageView = new ImageView(gold);
                    imageView.setId("gold");
                } else if (blockType == 12) {
                    imageView = new ImageView(enchantedMineral);
                    imageView.setId("enchantedMineral");
                } else if (blockType == 13) {
                    imageView = new ImageView(coal);
                    imageView.setId("coal");
                } else if (blockType == 15) {
                    imageView = new ImageView(building);
                    imageView.setId("building");
                } else if (blockType == 16) {
                    imageView = new ImageView(glass);
                    imageView.setId("glass");
                } else {
                    imageView = new ImageView(sky); // Default case: sky
                    imageView.setId("unknown");
                }

                // Set tile size and add to TilePane
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                tilepane.getChildren().add(imageView);
            }
        }
    }
}

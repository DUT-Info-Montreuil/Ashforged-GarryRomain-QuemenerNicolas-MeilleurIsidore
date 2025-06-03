package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Field;

public class FieldView {

    private Field field;
    private TilePane tilepane;
    private static final double TILE_SIZE = 64; // Taille fixe d'une tuile

    public FieldView(TilePane tilepane, Field field) {
        this.field = field;
        this.tilepane = tilepane;
        this.createField(tilepane);
    }

    /***
     * Permet de créer le terrain en fonction du tableau regroupant les tuiles à poser
     * présent dans la classe FieldView en plaçant les bons tuiles au bon endroit.
     *
     * @param tilepane
     */
    public void createField(TilePane tilepane) {
        int rows = field.getHeight();
        int cols = field.getWidth();

        tilepane.getChildren().clear(); // Clear previous tiles
        tilepane.setPrefColumns(cols);
        tilepane.setPrefRows(rows);
        tilepane.setPrefSize(cols * TILE_SIZE, rows * TILE_SIZE);
        tilepane.setHgap(0);
        tilepane.setVgap(0);
        tilepane.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Ciel.png").toExternalForm());
        Image terre = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Terre.png").toExternalForm());
        Image pierre = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Pierre.png").toExternalForm());

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                ImageView imageView;
                if (field.block(x, y) == 1) {
                    imageView = new ImageView(ciel);
                    imageView.setId("ciel");

                } else if (field.block(x, y) == 2) {
                    imageView = new ImageView(terre);
                    imageView.setId("terre");
                } else {
                    imageView = new ImageView(pierre);
                    imageView.setId("pierre");
                }
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                tilepane.getChildren().add(imageView);
            }
        }
    }
}
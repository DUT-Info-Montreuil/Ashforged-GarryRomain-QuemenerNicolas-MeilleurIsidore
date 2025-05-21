package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Field;

public class FieldView {

    private Field field;
    private TilePane tilepane;

    public FieldView(TilePane tilepane,Field field) {
        this.field = field;
        this.tilepane = tilepane;
        this.createField(tilepane, 1920, 1080);
    }

    public void createField(TilePane tilepane, double screenWidth, double screenHeight) {
        int rows = field.hauteur();
        int cols = field.longueur();

        // Calculate tile size to fit the screen
        double tileWidth = screenWidth / cols;
        double tileHeight = screenHeight / rows;

        tilepane.setPrefColumns(cols);
        tilepane.setPrefRows(rows);
        tilepane.setPrefSize(screenWidth, screenHeight);
        tilepane.setHgap(-1);
        tilepane.setVgap(-1);

        Image ciel = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Ciel.png").toExternalForm());
        Image terre = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Terre.png").toExternalForm());
        Image pierre = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/Pierre.png").toExternalForm());

        for (int y = 0; y < field.hauteur(); y++) {
            for (int x = 0; x < field.longueur(); x++) {
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
                imageView.setFitWidth(tileWidth);
                imageView.setFitHeight(tileHeight);
                tilepane.getChildren().add(imageView);
            }
        }
    }


}

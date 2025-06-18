package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        int rows = field.getHeight(); // Nombre de lignes (hauteur)
        int cols = field.getWidth();  // Nombre de colonnes (largeur)

        if (rows <= 0 || cols <= 0) {
            System.err.println("Dimensions de la carte invalides : rows=" + rows + ", cols=" + cols);
            return;
        }

        tilepane.getChildren().clear();
        tilepane.setPrefColumns(cols);
        tilepane.setPrefRows(rows);
        tilepane.setPrefSize(cols * TILE_SIZE, rows * TILE_SIZE);
        tilepane.setHgap(0);
        tilepane.setVgap(0);
        tilepane.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        // Chargement des images
        Image sky = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png").toExternalForm());
        Image grass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/grass.png").toExternalForm());
        Image leftGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftGrass.png").toExternalForm());
        Image rightGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightGrass.png").toExternalForm());
        Image rightSideGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightSideGrass.png").toExternalForm());
        Image leftSideGrass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftSideGrass.png").toExternalForm());
        Image stone = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png").toExternalForm());
        Image ground = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png").toExternalForm());
        Image bat = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/bat.png").toExternalForm());
        Image glass = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/glass.png").toExternalForm());
        Image coal = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/coal.png").toExternalForm());
        Image enchantedMineral = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/enchanted_mineral.png").toExternalForm());
        Image gold = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/gold.png").toExternalForm());
        Image iron = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png").toExternalForm());
        Image leaf = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leaf.png").toExternalForm());
        Image wood = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/wood.png").toExternalForm());

        // Parcourir les lignes (hauteur) et les colonnes (largeur)
        for (int y = 0; y < rows; y++) { // Parcourir la hauteur
            for (int x = 0; x < cols; x++) { // Parcourir la largeur
                ImageView imageView;
                int blockType = field.block(x, y);

                    if (blockType == 0) {
                        imageView = new ImageView(iron);
                        imageView.setId("fer");
                    } else if (blockType == 8) {
                        imageView = new ImageView(grass);
                        imageView.setId("herbe");
                    } else if (blockType == 2) {
                        imageView = new ImageView(ground);
                        imageView.setId("sol");
                    } else if (blockType == 3) {
                        imageView = new ImageView(rightGrass);
                        imageView.setId("herbeDroite");
                    } else if (blockType == 4) {
                        imageView = new ImageView(leftGrass);
                        imageView.setId("herbeGauche");
                    } else if (blockType == 5) {
                        imageView = new ImageView(rightSideGrass);
                        imageView.setId("coteDroitHerbe");
                    } else if (blockType == 6) {
                        imageView = new ImageView(leftSideGrass);
                        imageView.setId("coteGaucheHerbe");
                    } else if (blockType == 7) {
                        imageView = new ImageView(stone);
                        imageView.setId("pierre");
                    } else if (blockType == 1) {
                        imageView = new ImageView(sky);
                        imageView.setId("ciel");
                    } else if (blockType == 9) {
                        imageView = new ImageView(wood);
                        imageView.setId("bois");
                    } else if (blockType == 10) {
                        imageView = new ImageView(leaf);
                        imageView.setId("feuille");
                    } else if (blockType == 11) {
                        imageView = new ImageView(gold);
                        imageView.setId("or");
                    } else if (blockType == 12) {
                        imageView = new ImageView(enchantedMineral);
                        imageView.setId("mineraiEnchante");
                    } else if (blockType == 13) {
                        imageView = new ImageView(coal);
                        imageView.setId("charbon");
                    } else if (blockType == 15) {
                        imageView = new ImageView(bat);
                        imageView.setId("chauve-souris");
                    } else if (blockType == 16) {
                        imageView = new ImageView(glass);
                        imageView.setId("verre");
                    } else {
                        imageView = new ImageView(sky); // Valeur par défaut
                        imageView.setId("inconnu");
                    }

                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                tilepane.getChildren().add(imageView);
            }
        }
    }
}

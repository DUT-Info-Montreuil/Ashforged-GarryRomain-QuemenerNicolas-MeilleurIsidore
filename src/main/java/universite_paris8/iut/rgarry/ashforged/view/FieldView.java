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
        int rows = field.getHeight();
        int cols = field.getWidth();

        tilepane.getChildren().clear(); // Clear previous tiles
        tilepane.setPrefColumns(cols);
        tilepane.setPrefRows(rows);
        tilepane.setPrefSize(cols * TILE_SIZE, rows * TILE_SIZE);
        tilepane.setHgap(0);
        tilepane.setVgap(0);
        tilepane.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        // Chargement des images
        Image sky = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png");
        Image grass = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/grass.png");
        Image leftGrass = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftGrass.png");
        Image rightGrass = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightGrass.png");
        Image rightSideGrass = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightSideGrass.png");
        Image leftSideGrass = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftSideGrass.png");
        Image stone = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png");
        Image ground = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png");
        Image bat = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/bat.png");
        Image glass = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/glass.png");
        Image coal = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/coal.png");
        Image enchantedMineral = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/enchanted_mineral.png");
        Image gold = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/gold.png");
        Image iron = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png");
        Image leaf = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/leaf.png");
        Image wood = loadImage("/universite_paris8/iut/rgarry/ashforged/Image/tiles/wood.png");

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                ImageView imageView;
                int blockType = field.block(x, y);

                switch (blockType) {
                    case 0:
                        imageView = new ImageView(iron);
                        imageView.setId("fer");
                        break;
                    case 8:
                        imageView = new ImageView(grass);
                        imageView.setId("herbe");
                        break;
                    case 2:
                        imageView = new ImageView(ground);
                        imageView.setId("sol");
                        break;
                    case 3:
                        imageView = new ImageView(rightGrass);
                        imageView.setId("herbeDroite");
                        break;
                    case 4:
                        imageView = new ImageView(leftGrass);
                        imageView.setId("herbeGauche");
                        break;
                    case 5:
                        imageView = new ImageView(rightSideGrass);
                        imageView.setId("coteDroitHerbe");
                        break;
                    case 6:
                        imageView = new ImageView(leftSideGrass);
                        imageView.setId("coteGaucheHerbe");
                        break;
                    case 7:
                        imageView = new ImageView(stone);
                        imageView.setId("pierre");
                        break;
                    case 1:
                        imageView = new ImageView(sky);
                        imageView.setId("ciel");
                        break;
                    case 9:
                        imageView = new ImageView(wood);
                        imageView.setId("bois");
                        break;
                    case 10:
                        imageView = new ImageView(leaf);
                        imageView.setId("feuille");
                        break;
                    case 11:
                        imageView = new ImageView(gold);
                        imageView.setId("or");
                        break;
                    case 12:
                        imageView = new ImageView(enchantedMineral);
                        imageView.setId("mineraiEnchante");
                        break;
                    case 13:
                        imageView = new ImageView(coal);
                        imageView.setId("charbon");
                        break;
                    case 15:
                        imageView = new ImageView(bat);
                        imageView.setId("chauve-souris");
                        break;
                    case 16:
                        imageView = new ImageView(glass);
                        imageView.setId("verre");
                        break;
                    default:
                        imageView = new ImageView(leaf); // fallback
                        imageView.setId("inconnu");
                        break;
                }

                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                tilepane.getChildren().add(imageView);
            }
        }
    }

    private Image loadImage(String path) {
        var url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Image non trouvée: " + path);
        }
        return new Image(url.toExternalForm());
    }
}

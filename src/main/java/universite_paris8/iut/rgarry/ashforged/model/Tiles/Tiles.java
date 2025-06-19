package universite_paris8.iut.rgarry.ashforged.model.Tiles;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashMap;

public class Tiles {
    /***
     * Cette énumération permet de créer en avance les nombreux objets qui seront utilisé lors de tout le jeu
     * par les mobs, les PNG et le joueur.
     */
    public enum Tile {
        building("building", 15, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/building.png"),
        coal("coal", 13, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/coal.png"),
        enchanted_mineral("enchanted_mineral", 12, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/enchanted_mineral.png"),
        glass("glass", 16, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/glass.png"),
        gold("gold", 11, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/gold.png"),
        grass("grass", 8, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/grass.png"),
        ground("ground", 2, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
        iron("iron", 0, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png"),
        leaf("leaf", 10, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leaf.png"),
        leftGrass("leftGrass", 4, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftGrass.png"),
        leftSideGrass("leftSideGrass", 6, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftSideGrass.png"),
        rightGrass("rightGrass", 3, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightGrass.png"),
        rightSideGrass("rightSideGrass", 5, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightSideGrass.png"),
        sky("sky", 1, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png"),
        stone("stone", 7, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
        wood("wood", 9, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/wood.png");


        private final String name;
        private final int value;
        private final String imagePath;


        Tile(String name, int value, String imagePath) {
            this.name = name;
            this.value = value;
            this.imagePath = imagePath;
        }


        public String getName() {
            return name;
        }

        public int getValue() {return  value;}


        public String getImagePath() {
            return imagePath;
        }


        public Image getImage() {
            URL url = getClass().getResource(getImagePath());
            if (url == null) {
                System.err.println("Image introuvable : " + getImagePath());
                return null;
            }
            return new Image(url.toExternalForm());
        }
    }
}




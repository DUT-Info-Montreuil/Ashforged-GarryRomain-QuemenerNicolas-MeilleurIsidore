package universite_paris8.iut.rgarry.ashforged.model.Item;

import javafx.scene.image.Image;

import java.net.URL;

public enum Tile implements ItemInterface{
    grass("grass",8,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/grass.png"),
    building("building", 15,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/building.png"),
    coal("coal",13,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/coal.png"),
    enchanted_mineral("enchanted_mineral",12,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/enchanted_mineral.png"),
    glass("glass",16,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/glass.png"),
    gold("gold",11,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/gold.png"),
    ground("ground",2,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
    iron("iron",0,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png"),
    leaf("leaf",10,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leaf.png"),
    left_grass("leftGrass",4,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftGrass.png"),
    left_side_grass("leftSideGrass",6,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftSideGrass.png"),
    right_grass("rightGrass",3,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightGrass.png"),
    right_side_grass("rightSideGrass",5,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightSideGrass.png"),
    sky("sky",1,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png"),
    stone("stone",7,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
    wood("wood",9,  "/universite_paris8/iut/rgarry/ashforged/Image/tiles/wood.png");



    private final String name;
    private final int id;
    private final String imagePath;

    Tile(String name, int id, String imagePath) {
        this.name = name;
        this.id = id;
        this.imagePath = imagePath;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return 0; // Pas pertinent pour Tile
    }

    @Override
    public int getDamage() {
        return 0; // Pas pertinent pour Tile
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Image getImage() {
        URL url = getClass().getResource(getImagePath());
        return url != null ? new Image(url.toExternalForm()) : null;
    }

    public static Tile fromId(int id) {
        for (Tile tile : values()) {
            if (tile.getId() == id) {
                return tile;
            }
        }
        return null; // Aucun Tile avec cet id
    }
}


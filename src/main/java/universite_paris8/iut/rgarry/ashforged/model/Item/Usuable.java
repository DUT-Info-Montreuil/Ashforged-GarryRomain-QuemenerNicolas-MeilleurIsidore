package universite_paris8.iut.rgarry.ashforged.model.Item;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashMap;

public enum Usuable implements ItemInterface{
    ground("ground", 1, 0, "ground", true, 25, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
    wood("wood", 2, 0, "wood", true, 26, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/wooden.png"),
    stone("stone", 3, 0, "stone", true, 27, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
    iron("iron", 5, 0, "iron", true, 28, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png"),
    alluminium("alluminium", 4, 0, "iron", true, 29, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/alluminium.png"),
    canon_powder("canon_powder", 1, 0, "powder for firearm", false, 30, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/canon_powder.png"),
    perlimpinpin_powder("perlimpinpin_powder", 1, 0, "powder for magical manipulation", false, 31, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/perlimpinpin_powder.png"),
    feather("feather", 0, 0, "feather", false, 32, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/feather.png"),
    ball("ball", 2, 0, "ball for gun", false, 34, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/ball.png" ),
    string("string", 1, 0, "string for make bow", false, 33, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/string.png"),
    coal("coal", 3, 0, "available under the ground by mining", true, 35, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/coal.png"),
    enchanted_mineral("enchanted_mineral", 2, 0, "for magical manipulation", false, 36, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/enchanted_mineral.png"),
    golden_piece("golden_piece", 1, 0, "for buy some object", false, 37, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/goldenPiece.png"),
    craft_table("craft_table", 5, 0, "for make weapon and usuable", true, 38, "");


    static {
        // Enchanted Mineral
        enchanted_mineral.getComponents().put(Usuable.stone, 3); enchanted_mineral.getComponents().put(Usuable.iron,  1); enchanted_mineral.getComponents().put(Usuable.alluminium, 2);

        // Ball
        ball.getComponents().put(Usuable.alluminium, 2); ball.getComponents().put(Usuable.canon_powder, 2);
    }

    private final String name;
    private final int weight;
    private final int value;
    private final String description;
    private final boolean breakable;
    private final int id;
    private final String imagePath;
    private HashMap<ItemInterface, Integer> components = new HashMap();

    Usuable(String name, int weight, int value, String description, boolean breakable, int id, String imagePath) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.description = description;
        this.breakable = breakable;
        this.id = id;
        this.imagePath = imagePath;
        this.components.put(this, value);
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
        return weight;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Image getImage() {
        URL url = getClass().getResource(getImagePath());
        if (url == null) {
            System.err.println("Image introuvable : " + getImagePath());
            return null;
        }
        return new Image(url.toExternalForm());
    }

    @Override
    public int getDamage() {
        return 0;
    }

    public HashMap<ItemInterface, Integer> getComponents() {
        return components;
    }
}

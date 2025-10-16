package universite_paris8.iut.rgarry.ashforged.model.Item.Enum;

import javafx.scene.image.Image;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

import java.net.URL;
import java.util.HashMap;

public enum Usuable {
    /// Weapons

    bow("Bow", 3, 0, 16, 1, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bow.png"),
    stick("Stick", 3, 3, 16, 2, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/stick.png"),

    wooden_knife("Wooden knife", 2, 5, 32, 3, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/woodenKnife.png"),
    stone_knife("Stone knife", 3, 10, 64, 4, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/stoneKnife.png"),
    iron_knife("Iron knife", 4, 15, 128, 5, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/ironKnife.png"),


    wooden_sword("Wooden sword", 2, 5, 32, 7, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/woodenSword.png"),
    stone_sword("Stone sword", 3, 10, 64, 8, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/stoneSword.png"),
    iron_sword("Iron sword", 4, 15, 128, 9, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/ironSword.png"),


    wooden_sabre("Wooden sabre", 2, 5, 32, 11, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/woodenSabre.png"),
    stone_sabre("Stone sabre", 3, 10, 64, 12, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/stoneSabre.png"),
    iron_sabre("Iron sabre", 4, 15, 128, 13, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/ironSabre.png"),


    wooden_axe("Wooden axe", 2, 5, 32, 15, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/woodenAxe.png"),
    stone_axe("Stone axe", 3, 10, 64, 16, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/stoneAxe.png"),
    iron_axe("Iron axe", 4, 15, 128, 17, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/ironAxe.png"),


    wooden_pickaxe("Wooden pickaxe", 2, 5, 32, 19, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/woodenPickaxe.png"),
    stone_pickaxe("Stone pickaxe", 3, 10, 64, 20, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/stonePickaxe.png"),
    iron_pickaxe("Iron pickaxe", 4, 15, 128, 21, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/ironPickaxe.png"),


    firearm("Shotgun", 5, 25, 256, 23, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/firearm.png"),

    bomb("Bomb", 4, 10, 1, 24, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bomb.png"),

    enma("Enma", 10, 30, 300, 22, false, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/enma.png"),


    ///Consomables

    ground("ground", 1, 0, 0, 25, true, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
    wood("wood", 2, 0, 0, 26, true, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
    stone("stone", 3, 0, 0, 27, true, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
    iron("iron", 5, 0, 0, 28, true, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png"),
    alluminium("alluminium", 4, 0, 0, 29, true, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/alluminium.png"),
    canon_powder("canon_powder", 1, 0, 0, 30, false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/canon_powder.png"),
    perlimpinpin_powder("perlimpinpin_powder", 1, 0, 0, 31, false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/perlimpinpin_powder.png"),
    feather("feather", 0, 0, 0, 32, false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/feather.png"),
    ball("ball", 2, 0, 0, 33, false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/ball.png"),
    string("string", 1, 0, 0, 34, false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/string.png"),
    coal("coal", 3, 0, 0, 35, true, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/coal.png"),
    enchanted_mineral("enchanted_mineral", 2, 0, 0, 35,false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/enchanted_mineral.png"),
    golden_piece("golden_piece", 1, 0, 0, 36, false, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/goldenPiece.png"),
    craft_table("craft_table", 5, 0, 0, 37,true, " ");

    static {
        // Bâton
        stick.getComponents().put(Usuable.wood, 2);

        // Arcs
        bow.getComponents().put(Usuable.string, 1);
        bow.getComponents().put(Usuable.wood, 2);

        // Couteaux
        wooden_knife.getComponents().put(Usuable.wood, 4);

        stone_knife.getComponents().put(Consomable.wood, 2);
        stone_knife.getComponents().put(Consomable.stone, 2);

        iron_knife.getComponents().put(Consomable.wood, 2);
        iron_knife.getComponents().put(Consomable.iron, 3);


        // Épées
        wooden_sword.getComponents().put(Consomable.wood, 4);

        stone_sword.getComponents().put(Consomable.wood, 2);
        stone_sword.getComponents().put(Consomable.stone, 2);

        iron_sword.getComponents().put(Consomable.wood, 2);
        iron_sword.getComponents().put(Consomable.iron, 3);


        // Sabres
        wooden_sabre.getComponents().put(Consomable.wood, 4);

        stone_sabre.getComponents().put(Consomable.wood, 2);
        stone_sabre.getComponents().put(Consomable.stone, 2);

        iron_sabre.getComponents().put(Consomable.wood, 2);
        iron_sabre.getComponents().put(Consomable.iron, 3);


        // Haches
        wooden_axe.getComponents().put(Consomable.wood, 4);

        stone_axe.getComponents().put(Consomable.wood, 2);
        stone_axe.getComponents().put(Consomable.stone, 2);

        iron_axe.getComponents().put(Consomable.wood, 2);
        iron_axe.getComponents().put(Consomable.iron, 3);


        // Pioches
        wooden_pickaxe.getComponents().put(Consomable.wood, 4);

        stone_pickaxe.getComponents().put(Consomable.wood, 2);
        stone_pickaxe.getComponents().put(Consomable.stone, 2);

        iron_pickaxe.getComponents().put(Consomable.wood, 2);
        iron_pickaxe.getComponents().put(Consomable.iron, 3);

        // Arme à feu
        firearm.getComponents().put(Consomable.iron, 4);
        firearm.getComponents().put(Consomable.wood, 1);
        firearm.getComponents().put(Consomable.canon_powder, 2); // si gunpowder existe

        // Bombe
        bomb.getComponents().put(Consomable.canon_powder, 3); // à adapter selon les ressources disponibles
        bomb.getComponents().put(Consomable.iron, 1);
    }

    private final String name;
    private final int weight;
    private final int power;
    private final int durability;
    private final boolean breakable;
    private final int id;
    private final String imagePath;
    private HashMap<Usuable, Integer> components;

    Usuable(String name, int weight, int power, int durability, int id, boolean breakable ,String imagePath) {
        this.name = name;
        this.weight = weight;
        this.power = power;
        this.durability = durability;
        this.id = id;
        this.breakable = breakable;
        this.imagePath = imagePath;
        this.components = new HashMap<>();
    }

    public int getDamage() {
        return power;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

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



    public HashMap<Usuable, Integer> getComponents() {
        return components;
    }
}



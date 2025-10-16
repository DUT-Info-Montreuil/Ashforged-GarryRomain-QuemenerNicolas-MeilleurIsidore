package universite_paris8.iut.rgarry.ashforged.model.Item;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashMap;

public enum Weapon implements ItemInterface {
    bow("Bow", 3, 0, 16, 1, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bow.png"),
    stick("Stick", 3, 3, 16, 2, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/stick.png"),

    wooden_knife("Wooden knife", 2, 5, 32, 3, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/woodenKnife.png"),
    stone_knife("Stone knife", 3, 10, 64, 4, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/stoneKnife.png"),
    iron_knife("Iron knife", 4, 15, 128, 5, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/ironKnife.png"),


    wooden_sword("Wooden sword", 2, 5, 32, 7, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/woodenSword.png"),
    stone_sword("Stone sword", 3, 10, 64, 8, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/stoneSword.png"),
    iron_sword("Iron sword", 4, 15, 128, 9, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/ironSword.png"),


    wooden_sabre("Wooden sabre", 2, 5, 32, 11, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/woodenSabre.png"),
    stone_sabre("Stone sabre", 3, 10, 64, 12, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/stoneSabre.png"),
    iron_sabre("Iron sabre", 4, 15, 128, 13, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/ironSabre.png"),


    wooden_axe("Wooden axe", 2, 5, 32, 15, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/woodenAxe.png"),
    stone_axe("Stone axe", 3, 10, 64, 16, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/stoneAxe.png"),
    iron_axe("Iron axe", 4, 15, 128, 17, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/ironAxe.png"),


    wooden_pickaxe("Wooden pickaxe", 2, 5, 32, 19, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/woodenPickaxe.png"),
    stone_pickaxe("Stone pickaxe", 3, 10, 64, 20, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/stonePickaxe.png"),
    iron_pickaxe("Iron pickaxe", 4, 15, 128, 21, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/ironPickaxe.png"),


    firearm("Shotgun", 5, 25, 256, 23, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/firearm.png"),

    bomb("Bomb", 4, 10, 1, 24, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bomb.png"),

    enma("Enma", 10, 30, 300, 22, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/enma.png");

    static {
        // Bâton
        stick.getComponents().put(Usuable.wood, 2);

        // Arcs
        bow.getComponents().put(Usuable.string, 1);
        bow.getComponents().put(Usuable.wood, 2);

        // Couteaux
        wooden_knife.getComponents().put(Usuable.wood, 4);

        stone_knife.getComponents().put(Usuable.wood, 2);
        stone_knife.getComponents().put(Usuable.stone, 2);

        iron_knife.getComponents().put(Usuable.wood, 2);
        iron_knife.getComponents().put(Usuable.iron, 3);


        // Épées
        wooden_sword.getComponents().put(Usuable.wood, 4);

        stone_sword.getComponents().put(Usuable.wood, 2);
        stone_sword.getComponents().put(Usuable.stone, 2);

        iron_sword.getComponents().put(Usuable.wood, 2);
        iron_sword.getComponents().put(Usuable.iron, 3);


        // Sabres
        wooden_sabre.getComponents().put(Usuable.wood, 4);

        stone_sabre.getComponents().put(Usuable.wood, 2);
        stone_sabre.getComponents().put(Usuable.stone, 2);

        iron_sabre.getComponents().put(Usuable.wood, 2);
        iron_sabre.getComponents().put(Usuable.iron, 3);


        // Haches
        wooden_axe.getComponents().put(Usuable.wood, 4);

        stone_axe.getComponents().put(Usuable.wood, 2);
        stone_axe.getComponents().put(Usuable.stone, 2);

        iron_axe.getComponents().put(Usuable.wood, 2);
        iron_axe.getComponents().put(Usuable.iron, 3);


        // Pioches
        wooden_pickaxe.getComponents().put(Usuable.wood, 4);

        stone_pickaxe.getComponents().put(Usuable.wood, 2);
        stone_pickaxe.getComponents().put(Usuable.stone, 2);

        iron_pickaxe.getComponents().put(Usuable.wood, 2);
        iron_pickaxe.getComponents().put(Usuable.iron, 3);

        // Arme à feu
        firearm.getComponents().put(Usuable.iron, 4);
        firearm.getComponents().put(Usuable.wood, 1);
        firearm.getComponents().put(Usuable.canon_powder, 2); // si gunpowder existe

        // Bombe
        bomb.getComponents().put(Usuable.canon_powder, 3); // à adapter selon les ressources disponibles
        bomb.getComponents().put(Usuable.iron, 1);
    }

    private final String name;
    private final int weight;
    private final int power;
    private final int durability;
    private final int id;
    private final String imagePath;
    private HashMap<ItemInterface, Integer> components;

    Weapon(String name, int weight, int power, int durability, int id, String imagePath) {
        this.name = name;
        this.weight = weight;
        this.power = power;
        this.durability = durability;
        this.id = id;
        this.imagePath = imagePath;
        this.components = new HashMap<>();
    }

    public int getDamage() {
        return power;
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



    public HashMap<ItemInterface, Integer> getComponents() {
        return components;
    }
}



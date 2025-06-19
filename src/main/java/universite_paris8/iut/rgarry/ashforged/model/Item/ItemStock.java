package universite_paris8.iut.rgarry.ashforged.model.Item;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.HashMap;

public class ItemStock {
    /***
     * Cette énumération permet de créer en avance les nombreux objets qui seront utilisé lors de tout le jeu
     * par les mobs, les PNG et le joueur.
     */
    public enum Usuable implements ItemInterface {
        ground("ground", 1, 0, "ground", true, 25, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
        wood("wood", 2, 0, "wood", true, 26, "/universite_paris8/iut/rgarry/ashforged/Image/wooden"),
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

    public enum Weapon implements ItemInterface {
        bow("Bow", 3, 0, 16, 1, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bow.png"),
        stick("Stick", 3, 3, 16, 2, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/stick.png"),

        wooden_knife("Wooden knife", 2, 5, 32, 3, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/woodenKnife.png"),
        stone_knife("Stone knife", 3, 10, 64, 4, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/stoneKnife.png"),
        iron_knife("Iron knife", 4, 15, 128, 5, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/ironKnife.png"),
        aluminium_knife("Aluminium knife", 3, 20, 256, 6, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/alluminiumKnife.png"),

        wooden_sword("Wooden sword", 2, 5, 32, 7, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/woodenSword.png"),
        stone_sword("Stone sword", 3, 10, 64, 8, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/stoneSword.png"),
        iron_sword("Iron sword", 4, 15, 128, 9, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/ironSword.png"),
        aluminium_sword("Aluminium sword", 3, 20, 256, 10, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/alluminiumSword.png"),

        wooden_sabre("Wooden sabre", 2, 5, 32, 11, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/woodenSabre.png"),
        stone_sabre("Stone sabre", 3, 10, 64, 12, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/stoneSabre.png"),
        iron_sabre("Iron sabre", 4, 15, 128, 13, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/ironSabre.png"),
        aluminium_sabre("Aluminium sabre", 3, 20, 256, 14, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/alluminiumSabre.png"),

        wooden_axe("Wooden axe", 2, 5, 32, 15, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/woodenAxe.png"),
        stone_axe("Stone axe", 3, 10, 64, 16, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/stoneAxe.png"),
        iron_axe("Iron axe", 4, 15, 128, 17, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/ironAxe.png"),
        aluminium_axe("Aluminium axe", 3, 20, 256, 18, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/alluminiumAxe.png"),

        wooden_pickaxe("Wooden pickaxe", 2, 5, 32, 19, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/woodenPickaxe.png"),
        stone_pickaxe("Stone pickaxe", 3, 10, 64, 20, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/stonePickaxe.png"),
        iron_pickaxe("Iron pickaxe", 4, 15, 128, 21, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/ironPickaxe.png"),
        aluminium_pickaxe("Aluminium pickaxe", 3, 20, 256, 22, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/alluminiumPickaxe.png"),

        firearm("Shotgun", 5, 25, 256, 23, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/firearm.png"),

        bomb("Bomb", 4, 10, 1, 24, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bomb.png"),

        enma("Enma", 10, 30, 300, 22, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/enma.png");

        static {
            // Bâton
            stick.getComponents().put(Usuable.wood, 2);

            // Arcs
            bow.getComponents().put(Usuable.feather, 1);
            bow.getComponents().put(Usuable.wood, 2);

            // Couteaux
            wooden_knife.getComponents().put(Usuable.wood, 4);

            stone_knife.getComponents().put(Usuable.wood, 2);
            stone_knife.getComponents().put(Usuable.stone, 2);

            iron_knife.getComponents().put(Usuable.wood, 2);
            iron_knife.getComponents().put(Usuable.iron, 3);

            aluminium_knife.getComponents().put(Usuable.wood, 2);
            aluminium_knife.getComponents().put(Usuable.alluminium, 3);

            // Épées
            wooden_sword.getComponents().put(Usuable.wood, 4);

            stone_sword.getComponents().put(Usuable.wood, 2);
            stone_sword.getComponents().put(Usuable.stone, 2);

            iron_sword.getComponents().put(Usuable.wood, 2);
            iron_sword.getComponents().put(Usuable.iron, 3);

            aluminium_sword.getComponents().put(Usuable.wood, 2);
            aluminium_sword.getComponents().put(Usuable.alluminium, 3);

            // Sabres
            wooden_sabre.getComponents().put(Usuable.wood, 4);

            stone_sabre.getComponents().put(Usuable.wood, 2);
            stone_sabre.getComponents().put(Usuable.stone, 2);

            iron_sabre.getComponents().put(Usuable.wood, 2);
            iron_sabre.getComponents().put(Usuable.iron, 3);

            aluminium_sabre.getComponents().put(Usuable.wood, 2);
            aluminium_sabre.getComponents().put(Usuable.alluminium, 3);

            // Haches
            wooden_axe.getComponents().put(Usuable.wood, 4);

            stone_axe.getComponents().put(Usuable.wood, 2);
            stone_axe.getComponents().put(Usuable.stone, 2);

            iron_axe.getComponents().put(Usuable.wood, 2);
            iron_axe.getComponents().put(Usuable.iron, 3);

            aluminium_axe.getComponents().put(Usuable.wood, 2);
            aluminium_axe.getComponents().put(Usuable.alluminium, 3);

            // Pioches
            wooden_pickaxe.getComponents().put(Usuable.wood, 4);

            stone_pickaxe.getComponents().put(Usuable.wood, 2);
            stone_pickaxe.getComponents().put(Usuable.stone, 2);

            iron_pickaxe.getComponents().put(Usuable.wood, 2);
            iron_pickaxe.getComponents().put(Usuable.iron, 3);

            aluminium_pickaxe.getComponents().put(Usuable.wood, 2);
            aluminium_pickaxe.getComponents().put(Usuable.alluminium, 3);

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
        private HashMap<ItemInterface, Integer> components = new HashMap();

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

    public enum Tile implements ItemInterface {
        GRASS("grass", "/path/to/grass.png");

        private final String name;
        private final String imagePath;

        Tile(String name, String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
        }

        @Override
        public int getId() {
            return -1; // Pas utilisé
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
    }

}

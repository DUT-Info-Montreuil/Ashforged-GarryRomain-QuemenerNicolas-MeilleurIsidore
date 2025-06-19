package universite_paris8.iut.rgarry.ashforged.model.Item;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.HashMap;

// Class to define a stock of predefined items, weapons, and tiles used throughout the game
public class ItemStock {

    /**
     * Enum to define usable items that can be collected or crafted by mobs, NPCs, and the player.
     * Each item has properties like name, weight, value, description, breakability, and crafting components.
     */
    public enum Usuable implements ItemInterface {
        ground("Ground", 1, 0, "Ground material", true, 25, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
        wood("Wood", 2, 0, "Wood material", true, 26, "/universite_paris8/iut/rgarry/ashforged/Image/wooden.png"),
        stone("Stone", 3, 0, "Stone material", true, 27, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
        iron("Iron", 5, 0, "Iron material", true, 28, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png"),
        aluminum("Aluminum", 4, 0, "Aluminum material", true, 29, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/aluminum.png"),
        cannon_powder("Cannon Powder", 1, 0, "Powder for firearms", false, 30, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/cannon_powder.png"),
        perlimpinpin_powder("Perlimpinpin Powder", 1, 0, "Powder for magical manipulation", false, 31, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/perlimpinpin_powder.png"),
        feather("Feather", 0, 0, "Feather material", false, 32, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/feather.png"),
        ball("Ball", 2, 0, "Ball for gun ammunition", false, 34, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/ball.png"),
        string("String", 1, 0, "String for making bows", false, 33, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/string.png"),
        coal("Coal", 3, 0, "Available underground by mining", true, 35, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/coal.png"),
        enchanted_mineral("Enchanted Mineral", 2, 0, "For magical manipulation", false, 36, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/enchanted_mineral.png"),
        golden_piece("Golden Piece", 1, 0, "For buying items", false, 37, "/universite_paris8/iut/rgarry/ashforged/Image/Usuable/goldenPiece.png"),
        craft_table("Craft Table", 5, 0, "For crafting weapons and items", true, 38, "");

        // Static block to define crafting recipes for specific items
        static {
            // Enchanted Mineral recipe
            enchanted_mineral.getComponents().put(stone, 3);
            enchanted_mineral.getComponents().put(iron, 1);
            enchanted_mineral.getComponents().put(aluminum, 2);

            // Ball recipe
            ball.getComponents().put(aluminum, 2);
            ball.getComponents().put(cannon_powder, 2);
        }

        private final String name; // Item name
        private final int weight; // Item weight
        private final int value; // Item value (e.g., for trading)
        private final String description; // Item description
        private final boolean breakable; // Whether the item can break
        private final int id; // Unique item identifier
        private final String imagePath; // Path to item image
        private final HashMap<ItemInterface, Integer> components = new HashMap<>(); // Crafting components

        /**
         * Constructor for Usuable items.
         *
         * @param name The item name.
         * @param weight The item weight.
         * @param value The item value.
         * @param description The item description.
         * @param breakable Whether the item can break.
         * @param id The unique item identifier.
         * @param imagePath The path to the item’s image.
         */
        Usuable(String name, int weight, int value, String description, boolean breakable, int id, String imagePath) {
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.description = description;
            this.breakable = breakable;
            this.id = id;
            this.imagePath = imagePath;
            this.components.put(this, value); // Initialize with self-reference
        }

        @Override
        public int getId() {
            return id; // Return item ID
        }

        @Override
        public String getName() {
            return name; // Return item name
        }

        @Override
        public int getWeight() {
            return weight; // Return item weight
        }

        @Override
        public String getImagePath() {
            return imagePath; // Return image path
        }

        @Override
        public Image getImage() {
            URL url = getClass().getResource(imagePath);
            if (url == null) {
                System.err.println("Image not found: " + imagePath);
                return null;
            }
            return new Image(url.toExternalForm()); // Load and return image
        }

        @Override
        public int getDamage() {
            return 0; // Usuable items have no damage
        }

        /**
         * Retrieves the crafting components required for this item.
         *
         * @return A HashMap mapping ItemInterface components to their required quantities.
         */
        public HashMap<ItemInterface, Integer> getComponents() {
            return components; // Return crafting components
        }
    }

    /**
     * Enum to define weapons that can be equipped by entities, with properties like name, weight, power, durability, and crafting components.
     */
    public enum Weapon implements ItemInterface {
        bow("Bow", 3, 0, 16, 1, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bow.png"),
        stick("Stick", 3, 3, 16, 2, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/stick.png"),
        wooden_knife("Wooden Knife", 2, 5, 32, 3, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/woodenKnife.png"),
        stone_knife("Stone Knife", 3, 10, 64, 4, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/stoneKnife.png"),
        iron_knife("Iron Knife", 4, 15, 128, 5, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/ironKnife.png"),
        wooden_sword("Wooden Sword", 2, 5, 32, 7, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/woodenSword.png"),
        stone_sword("Stone Sword", 3, 10, 64, 8, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/stoneSword.png"),
        iron_sword("Iron Sword", 4, 15, 128, 9, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/ironSword.png"),
        wooden_sabre("Wooden Sabre", 2, 5, 32, 11, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/woodenSabre.png"),
        stone_sabre("Stone Sabre", 3, 10, 64, 12, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/stoneSabre.png"),
        iron_sabre("Iron Sabre", 4, 15, 128, 13, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/ironSabre.png"),
        wooden_axe("Wooden Axe", 2, 5, 32, 15, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/woodenAxe.png"),
        stone_axe("Stone Axe", 3, 10, 64, 16, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/stoneAxe.png"),
        iron_axe("Iron Axe", 4, 15, 128, 17, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/ironAxe.png"),
        wooden_pickaxe("Wooden Pickaxe", 2, 5, 32, 19, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/woodenPickaxe.png"),
        stone_pickaxe("Stone Pickaxe", 3, 10, 64, 20, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/stonePickaxe.png"),
        iron_pickaxe("Iron Pickaxe", 4, 15, 128, 21, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/ironPickaxe.png"),
        firearm("Shotgun", 5, 25, 256, 23, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/firearm.png"),
        bomb("Bomb", 4, 10, 1, 24, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/bomb.png"),
        enma("Enma", 10, 30, 300, 22, "/universite_paris8/iut/rgarry/ashforged/Image/Weapons/enma.png");

        // Static block to define crafting recipes for weapons
        static {
            // Stick
            stick.getComponents().put(Usuable.wood, 2);

            // Bow
            bow.getComponents().put(Usuable.string, 1);
            bow.getComponents().put(Usuable.wood, 2);

            // Knives
            wooden_knife.getComponents().put(Usuable.wood, 4);
            stone_knife.getComponents().put(Usuable.wood, 2);
            stone_knife.getComponents().put(Usuable.stone, 2);
            iron_knife.getComponents().put(Usuable.wood, 2);
            iron_knife.getComponents().put(Usuable.iron, 3);

            // Swords
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

            // Axes
            wooden_axe.getComponents().put(Usuable.wood, 4);
            stone_axe.getComponents().put(Usuable.wood, 2);
            stone_axe.getComponents().put(Usuable.stone, 2);
            iron_axe.getComponents().put(Usuable.wood, 2);
            iron_axe.getComponents().put(Usuable.iron, 3);

            // Pickaxes
            wooden_pickaxe.getComponents().put(Usuable.wood, 4);
            stone_pickaxe.getComponents().put(Usuable.wood, 2);
            stone_pickaxe.getComponents().put(Usuable.stone, 2);
            iron_pickaxe.getComponents().put(Usuable.wood, 2);
            iron_pickaxe.getComponents().put(Usuable.iron, 3);

            // Firearm
            firearm.getComponents().put(Usuable.iron, 4);
            firearm.getComponents().put(Usuable.wood, 1);
            firearm.getComponents().put(Usuable.cannon_powder, 2);

            // Bomb
            bomb.getComponents().put(Usuable.cannon_powder, 3);
            bomb.getComponents().put(Usuable.iron, 1);
        }

        private final String name; // Weapon name
        private final int weight; // Weapon weight
        private final int power; // Weapon damage power
        private final int durability; // Weapon durability
        private final int id; // Unique weapon identifier
        private final String imagePath; // Path to weapon image
        private final HashMap<ItemInterface, Integer> components; // Crafting components

        /**
         * Constructor for Weapon items.
         *
         * @param name The weapon name.
         * @param weight The weapon weight.
         * @param power The weapon damage power.
         * @param durability The weapon durability.
         * @param id The unique weapon identifier.
         * @param imagePath The path to the weapon’s image.
         */
        Weapon(String name, int weight, int power, int durability, int id, String imagePath) {
            this.name = name;
            this.weight = weight;
            this.power = power;
            this.durability = durability;
            this.id = id;
            this.imagePath = imagePath;
            this.components = new HashMap<>();
        }

        @Override
        public int getDamage() {
            return power; // Return weapon damage
        }

        @Override
        public int getId() {
            return id; // Return weapon ID
        }

        @Override
        public String getName() {
            return name; // Return weapon name
        }

        @Override
        public int getWeight() {
            return weight; // Return weapon weight
        }

        @Override
        public String getImagePath() {
            return imagePath; // Return image path
        }

        @Override
        public Image getImage() {
            URL url = getClass().getResource(imagePath);
            if (url == null) {
                System.err.println("Image not found: " + imagePath);
                return null;
            }
            return new Image(url.toExternalForm()); // Load and return image
        }

        /**
         * Retrieves the crafting components required for this weapon.
         *
         * @return A HashMap mapping ItemInterface components to their required quantities.
         */
        public HashMap<ItemInterface, Integer> getComponents() {
            return components; // Return crafting components
        }
    }

    /**
     * Enum to define tile types used in the game map, with properties like name, ID, and image path.
     */
    public enum Tile implements ItemInterface {
        grass("Grass", 8, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/grass.png"),
        building("Building", 15, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/building.png"),
        coal("Coal", 13, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/coal.png"),
        enchanted_mineral("Enchanted Mineral", 12, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/enchanted_mineral.png"),
        glass("Glass", 16, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/glass.png"),
        gold("Gold", 11, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/gold.png"),
        ground("Ground", 2, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
        iron("Iron", 0, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/iron.png"),
        leaf("Leaf", 10, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leaf.png"),
        left_grass("Left Grass", 4, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftGrass.png"),
        left_side_grass("Left Side Grass", 6, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/leftSideGrass.png"),
        right_grass("Right Grass", 3, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightGrass.png"),
        right_side_grass("Right Side Grass", 5, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/rightSideGrass.png"),
        sky("Sky", 1, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/sky.png"),
        stone("Stone", 7, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
        wood("Wood", 9, "/universite_paris8/iut/rgarry/ashforged/Image/tiles/wood.png");

        private final String name; // Tile name
        private final int id; // Unique tile identifier
        private final String imagePath; // Path to tile image

        /**
         * Constructor for Tile items.
         *
         * @param name The tile name.
         * @param id The unique tile identifier.
         * @param imagePath The path to the tile’s image.
         */
        Tile(String name, int id, String imagePath) {
            this.name = name;
            this.id = id;
            this.imagePath = imagePath;
        }

        @Override
        public int getId() {
            return id; // Return tile ID
        }

        @Override
        public String getName() {
            return name; // Return tile name
        }

        @Override
        public int getWeight() {
            return 0; // Tiles have no weight
        }

        @Override
        public int getDamage() {
            return 0; // Tiles have no damage
        }

        @Override
        public String getImagePath() {
            return imagePath; // Return image path
        }

        @Override
        public Image getImage() {
            URL url = getClass().getResource(imagePath);
            return url != null ? new Image(url.toExternalForm()) : null; // Load and return image
        }

        /**
         * Retrieves a Tile by its ID.
         *
         * @param id The tile ID to look up.
         * @return The corresponding Tile, or null if no tile matches the ID.
         */
        public static Tile fromId(int id) {
            for (Tile tile : values()) {
                if (tile.getId() == id) {
                    return tile; // Return matching tile
                }
            }
            return null; // No tile found
        }
    }
}
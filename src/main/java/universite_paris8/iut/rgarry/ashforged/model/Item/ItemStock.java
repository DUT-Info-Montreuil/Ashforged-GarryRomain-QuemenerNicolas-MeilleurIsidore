package universite_paris8.iut.rgarry.ashforged.model.Item;

public class ItemStock {

    /***
     * Cette énumération permet de créer en avance les nombreuses armes qui seront utilisé lors de tout le jeu
     * par les mobs, les PNG et le joueur.
     */
    public enum Weapon implements ItemInterface{
        //Creation of bow
        // Creation of bow
        bow("Bow", 3, 0, 16, "#1", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/bow.png"),

        // Creation of stick
        stick("Stick", 3, 3, 16, "#2", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/stick.png"),

        // Creation of knife
        wooden_knife("Wooden knife", 2, 5, 32, "#3", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/woodenKnife.png"),
        stone_knife("Stone knife", 3, 10, 64, "#4", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/stoneKnife.png"),
        steel_knife("Steel knife", 4, 15, 128, "#5", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/steelKnife.png"),
        aluminium_knife("Aluminium knife", 3, 20, 256, "#6", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Knife/alluminiumKnife.png"),

        // Creation of sword
        wooden_sword("Wooden sword", 2, 5, 32, "#7", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/woodenSword.png"),
        stone_sword("Stone sword", 3, 10, 64, "#8", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/stoneSword.png"),
        steel_sword("Steel sword", 4, 15, 128, "#9", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/steelSword.png"),
        aluminium_sword("Aluminium sword", 3, 20, 256, "#10", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sword/alluminiumSword.png"),

        // Creation of sabre
        wooden_sabre("Wooden sabre", 2, 5, 32, "#11", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/woodenSabre.png"),
        stone_sabre("Stone sabre", 3, 10, 64, "#12", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/stoneSabre.png"),
        steel_sabre("Steel sabre", 4, 15, 128, "#13", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/steelSabre.png"),
        aluminium_sabre("Aluminium sabre", 3, 20, 256, "#14", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Sabre/alluminiumSabre.png"),

        // Creation of axe
        wooden_axe("Wooden axe", 2, 5, 32, "#15", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/woodenAxe.png"),
        stone_axe("Stone axe", 3, 10, 64, "#16", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/stoneAxe.png"),
        steel_axe("Steel axe", 4, 15, 128, "#17", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/steelAxe.png"),
        aluminium_axe("Aluminium axe", 3, 20, 256, "#18", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Axe/alluminiumAxe.png"),

        // Creation of pickaxe
        wooden_pickaxe("Wooden pickaxe", 2, 5, 32, "#19", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/woodenPickaxe.png"),
        stone_pickaxe("Stone pickaxe", 3, 10, 64, "#20", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/stonePickaxe.png"),
        steel_pickaxe("Steel pickaxe", 4, 15, 128, "#21", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/steelPickaxe.png"),
        aluminium_pickaxe("Aluminium pickaxe", 3, 20, 256, "#22", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/Pickaxe/alluminiumPickaxe.png"),

        // Creation of firearm
        firearm("Shotgun", 5, 25, 256, "#23", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/firearm.png"),

        bomb("Bomb", 4, 10, 1, "#24", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/bomb.png"),

        enma("Enma", 10, 30, 300, "#2202", "universite_paris8/iut/rgarry/ashforged/Image/Weapons/enma.png");



        private final String name;
        private final int weight;
        private final int power;
        private final int durability;
        private final String id;
        private final String imagePath;

        Weapon(String name, int weight, int power, int durability, String id, String imagePath) {
            this.name = name;
            this.weight = weight;
            this.power = power;
            this.durability = durability;
            this.id = id;
            this.imagePath = imagePath;
        }

        /***
         * Retourne l'identifiant de l'arme sélectionner
         *
         * @return
         */
        @Override
        public String getId() {
            return id;
        }

        /***
         * Retourne le nom de l'arme sélectionner
         *
         * @return
         */
        @Override
        public String getName() {
            return name;
        }

        /***
         * Retourne le poids de l'arme sélectionner
         *
         * @return
         */
        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public String getImagePath(){
            return imagePath;
        }


    }

    /***
     * Cette énumération permet de créer en avance les nombreux objets qui seront utilisé lors de tout le jeu
     * par les mobs, les PNG et le joueur.
     */
    public enum Usuable  implements ItemInterface{
        ground("ground", 1, 0, "ground", true, "#25", "universite_paris8/iut/rgarry/ashforged/Image/tiles/ground.png"),
        wood("wood", 2, 0, "wood", true, "#26", "universite_paris8/iut/rgarry/ashforged/Image/wooden"),
        stone("stone", 3, 0, "stone", true, "#27", "universite_paris8/iut/rgarry/ashforged/Image/tiles/stone.png"),
        steel("steel", 5, 0, "steel", true, "#28", "universite_paris8/iut/rgarry/ashforged/Image/tiles/steel.png"),
        alluminium("alluminium", 4, 0, "steel", true, "#29", "universite_paris8/iut/rgarry/ashforged/Image/tiles/alluminium.png"),
        canon_powder("canon_powder", 1, 0, "powder for firearm", false, "#30", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/canon_powder.png"),
        perlimpinpin_powder("perlimpinpin_powder", 1, 0, "powder for magical manipulation", false, "#31", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/perlimpinpin_powder.png"),
        feather("feather", 0, 0, "feather", false, "#32", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/feather.png"),
        string("string", 1, 0, "string for make bow", false, "#33", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/string.png"),
        coal("coal", 3, 0, "available under the ground by mining", true, "#34", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/coal.png"),
        enchanted_mineral("enchanted_mineral", 2, 0, "for magical manipulation", false, "#35", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/enchanted_mineral.png"),
        golden_piece("golden_piece", 1, 0, "for buy some object", false, "#36", "universite_paris8/iut/rgarry/ashforged/Image/Usuable/goldenPiece.png");




        private final String name;
        private final int weight;
        private final int value;
        private final String description;
        private final boolean breakable;
        private final String id;
        private final String imagePath;

        Usuable(String name, int weight,  int value, String description, boolean breakable, String id, String imagePath) {
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.description = description;
            this.breakable = breakable;
            this.id = id;
            this.imagePath = imagePath;
        }

        /***
         * Retourne l'identifiant de l'objet sélectionné
         *
         * @return
         */
        @Override
        public String getId() {
            return id;
        }

        /***
         * Retourne le nom de l'objet sélectionné
         *
         * @return
         */
        @Override
        public String getName() {
            return name;
        }

        /***
         * Retourne le poids de l'objet sélectionné
         *
         * @return
         */

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public String getImagePath(){
            return imagePath;
        }

    }
}

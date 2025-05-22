package universite_paris8.iut.rgarry.ashforged.model.Item;

public class ItemStock {

    /***
     * Cette énumération permet de créer en avance les nombreuses armes qui seront utilisé lors de tout le jeu
     * par les mobs, les PNG et le joueur.
     */
    public enum Weapon implements ItemInterface{
        //Creation of bow
        // Creation of bow
        bow("Bow", 3, 0, 16, "#1"),

        // Creation of stick
        stick("Stick", 3, 3, 16, "#2"),

        // Creation of knife
        wooden_knife("Wooden knife", 2, 5, 32, "#3"),
        stone_knife("Stone knife", 3, 10, 64, "#4"),
        steel_knife("Steel knife", 4, 15, 128, "#5"),
        aluminium_knife("Aluminium knife", 3, 20, 256, "#6"),

        // Creation of sword
        wooden_sword("Wooden sword", 2, 5, 32, "#7"),
        stone_sword("Stone sword", 3, 10, 64, "#8"),
        steel_sword("Steel sword", 4, 15, 128, "#9"),
        aluminium_sword("Aluminium sword", 3, 20, 256, "#10"),

        // Creation of sabre
        wooden_sabre("Wooden sabre", 2, 5, 32, "#11"),
        stone_sabre("Stone sabre", 3, 10, 64, "#12"),
        steel_sabre("Steel sabre", 4, 15, 128, "#13"),
        aluminium_sabre("Aluminium sabre", 3, 20, 256, "#14"),

        // Creation of axe
        wooden_axe("Wooden axe", 2, 5, 32, "#15"),
        stone_axe("Stone axe", 3, 10, 64, "#16"),
        steel_axe("Steel axe", 4, 15, 128, "#17"),
        aluminium_axe("Aluminium axe", 3, 20, 256, "#18"),

        // Creation of pickaxe
        wooden_pickaxe("Wooden pickaxe", 2, 5, 32, "#19"),
        stone_pickaxe("Stone pickaxe", 3, 10, 64, "#20"),
        steel_pickaxe("Steel pickaxe", 4, 15, 128, "#21"),
        aluminium_pickaxe("Aluminium pickaxe", 3, 20, 256, "#22"),

        // Creation of firearm
        firearm("Shotgun", 5, 25, 256, "#23"),

        bomb("Bomb", 4, 10, 1, "#24"),

        enma("Enma", 10, 30, 300, "#2202");



        private final String name;
        private final int weight;
        private final int power;
        private final int durability;
        private final String id;

        Weapon(String name, int weight, int power, int durability, String id) {
            this.name = name;
            this.weight = weight;
            this.power = power;
            this.durability = durability;
            this.id = id;
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


    }

    /***
     * Cette énumération permet de créer en avance les nombreux objets qui seront utilisé lors de tout le jeu
     * par les mobs, les PNG et le joueur.
     */
    public enum Usuable  implements ItemInterface{
        ground("ground", 1, 0, "ground", true, "#25"),
        wood("wood", 2, 0, "wood", true, "#26"),
        stone("stone", 3, 0, "stone", true, "#27"),
        steel("steel", 5, 0, "steel", true, "#28"),
        alluminium("alluminium", 4, 0, "steel", true, "#29"),
        canon_powder("canon_powder", 1, 0, "powder for firearm", false, "#30"),
        perlimpinpin_powder("perlimpinpin_powder", 1, 0, "powder for magical manipulation", false, "#31"),
        feather("feather", 0, 0, "feather", false, "#32"),
        string("string", 1, 0, "string for make bow", false, "#33"),
        coal("coal", 3, 0, "available under the ground by mining", true, "#34"),
        enchanted_mineral("enchanted_mineral", 2, 0, "for magical manipulation", false, "#35"),
        golden_piece("golden_piece", 1, 0, "for buy some object", false, "#36");




        private final String name;
        private final int weight;
        private final int value;
        private final String description;
        private final boolean breakable;
        private final String id;

        Usuable(String name, int weight,  int value, String description, boolean breakable, String id) {
            this.name = name;
            this.weight = weight;
            this.value = value;
            this.description = description;
            this.breakable = breakable;
            this.id = id;
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

    }
}

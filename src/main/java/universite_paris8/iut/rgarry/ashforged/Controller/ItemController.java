package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Item;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;


public class ItemController {

    // Weapon
    private static Item bow;
    private static Item stick;
    private static Item sword;
    private static Item knife;
    private static Item sabre;
    private static Item axe;
    private static Item pickaxe;
    private static Item firearm;

    // Consumable
    private static Consumable ground;
    private static Consumable wood;
    private static Consumable stone;
    private static Consumable steel;
    private static Consumable alluminium;
    private static Consumable canon_powder;
    private static Consumable perlimpinpin_powder;
    private static Consumable feather;
    private static Consumable string;
    private static Consumable coal;
    private static Consumable enchanted_mineral;
    private static Consumable golden_piece;






    public ItemController() {
        // Creation of bow
        this.bow = new Item("Bow", 3);

        //Creation of stick
        this.stick = new Item("Stick", 3);

        // Creation of Knife
        this.knife = new Item("Wooden knife", 2);
        this.knife = new Item("Stone knife", 2);
        this.knife = new Item("Steel knife", 2);
        this.knife = new Item("Alluminium knife", 2);


        // Creation of Sword
        this.sword = new Item("Wooden sword", 2);
        this.sword = new Item("Stone sword", 2);
        this.sword = new Item("Steel sword", 2);
        this.sword = new Item("Alluminium sword", 2);

        // Creation of Sabre
        this.sabre = new Item("Wooden sabre", 2);
        this.sabre = new Item("Stone sabre", 2);
        this.sabre = new Item("Steel sabre", 2);
        this.sabre = new Item("Alluminium sabre", 2);

        // Creation of Axe
        this.axe = new Item("Wooden axe", 2);
        this.axe = new Item("Stone axe", 2);
        this.axe = new Item("Steel axe", 2);
        this.axe = new Item("Alluminium axe", 2);

        // Creation of Pickaxe
        this.pickaxe = new Item("Wooden pickaxe", 2);
        this.pickaxe = new Item("Stone pickaxe", 2);
        this.pickaxe = new Item("Steel pickaxe", 2);
        this.pickaxe = new Item("Alluminium pickaxe", 2);


        //Creation of consumable
        this.ground = new Consumable(0, "ground",true );
        this.wood = new Consumable(0, "wood", true);
        this.stone = new Consumable(0, "stone", true);
        this.steel = new Consumable(0, "steel", true);
        this.alluminium = new Consumable(0, "steel", true);
        this.canon_powder = new Consumable(0, "powder for firearm",false);
        this.perlimpinpin_powder = new Consumable(0, "powder for magical manipulation", false);
        this.feather = new Consumable(0, "feather", false);
        this.string = new Consumable(0, "string for make bow", false);
        this.coal = new Consumable(0, "avaible under the ground by mining", true);
        this.enchanted_mineral = new Consumable(0, "for magical manipulation", false);
        this.golden_piece = new Consumable(0, "for buy some object", false);



    }
}

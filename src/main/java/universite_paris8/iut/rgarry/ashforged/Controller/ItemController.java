package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.model.Item.Item;
import universite_paris8.iut.rgarry.ashforged.model.character.Personnage;


public class ItemController {

    private static Item bow;
    private static Item sword;
    private static Item knife;
    private static Item sabre;
    private static Item axe;
    private static Item pickaxe;
    private static Item firearm;


    public ItemController() {
        // Creation of bow
        this.bow = new Item("Bow", 3);

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

    }
}

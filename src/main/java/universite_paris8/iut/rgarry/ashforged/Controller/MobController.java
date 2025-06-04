package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;

public class MobController {
    private final TilePane tilePane;
    private final Pane mobPane;

    private static Mobs mongolfière;
    private static Mobs soldat;
    private static Mobs zombie;
    private static Mobs bandit;
    private static Mobs boss;
    private static Mobs kozuki;

    private MobController(TilePane tilePane, Pane mobPane) {
        this.tilePane = tilePane;
        this.mobPane = mobPane;

//        this.mongolfière = new Mobs("Mongolfière", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.bomb, 600, 250);
//        this.soldat = new Mobs("Soldat", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.stone_sword, 600, 250);
//        this.zombie = new Mobs("Zombie", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.stick, 600, 250);
//        this.bandit = new Mobs("Bandit", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.firearm, 600, 250);
//        this.boss = new Mobs("Boss", 20, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.steel_sabre, 600, 250);
//        this.kozuki = new Mobs("Kozuki", 30, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.enma, 600, 250);
    }

}
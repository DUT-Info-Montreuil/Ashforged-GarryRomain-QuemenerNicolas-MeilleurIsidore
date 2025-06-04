package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

public class Mobs extends Character {
    private int stats_multiplier;
    private ItemInterface item;

    public Mobs(String name, int level, int[] stats, int stats_multiplier, ItemInterface item, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.stats_multiplier = stats_multiplier;
        this.item = item;
    }
}
package universite_paris8.iut.rgarry.ashforged.character;

import universite_paris8.iut.rgarry.ashforged.Item.Item;

public class Mobs extends Personnage {
    private int stats_multiplier;

    public Mobs(String name, int level, int health, int[] stats, int stats_multiplier, int x, int y) {
        super(name, level, stats, x, y);
        this.stats_multiplier = stats_multiplier;
    }
}
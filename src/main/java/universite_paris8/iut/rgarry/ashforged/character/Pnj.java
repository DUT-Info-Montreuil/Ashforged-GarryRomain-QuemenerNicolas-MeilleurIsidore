package universite_paris8.iut.rgarry.ashforged.character;

import universite_paris8.iut.rgarry.ashforged.Item.Consomable;
import universite_paris8.iut.rgarry.ashforged.Item.Item;

public class Pnj extends Personnage {
    private String dialogue;
    private Item[] reward;

    public Pnj(String name, int level, int health, int[] stats, String dialogue, Item[] reward,int x,int y) {
        super(name, level, stats,x,y);
        this.dialogue = dialogue;
        this.reward = reward;
    }
}

package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

public class Npc extends Personnage {
    private String dialogue;
    private ItemInterface[] reward;

    public Npc(String name, int level, int health, int[] stats, String dialogue, ItemInterface[] reward, int x, int y) {
        super(name, level, stats,x,y);
        this.dialogue = dialogue;
        this.reward = reward;
    }
}

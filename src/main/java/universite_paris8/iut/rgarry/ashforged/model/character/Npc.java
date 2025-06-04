package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

public class Npc extends Character {
    private String dialogue;
    private ItemInterface[] reward;



    public Npc(String name, int level, int[] stats, String dialogue, ItemInterface[] reward, int x, int y, Environment env) {
        super(name, level, stats,x,y, env);
        this.dialogue = dialogue;
        this.reward = reward;
    }
}

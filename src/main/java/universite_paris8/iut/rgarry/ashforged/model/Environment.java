package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

import java.util.List;

public class Environment {

    private Field field;
    private Character hero; // Liste des personnages (joueurs)
    private List<Mobs> mobs;          // Liste des mobs
    private List<Npc> npcs;          // Liste des NPCs

    public enum Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    public Environment(Field field, List<Mobs> mobs, List<Npc> npcs) {
        this.field = field;
        this.hero = new Character("Hero", 1, new int[]{1, 1, 5, 1}, 250, 300, this);
        this.mobs = mobs;
        this.npcs = npcs;
    }

    public Field getField() {
        return field;
    }

    public Character getHero() {
        return hero;
    }

    public List<Mobs> getMobs() {
        return mobs;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public boolean checkCollision(int x, int y) {
        // TODO: Implement collision detection logic based on the direction and entity position
        // This method should check if the entity collides with any obstacles or boundaries in the environment
        // For now, we can return false as a placeholder

        return this.field.checkCollision(x, y);



    }
}
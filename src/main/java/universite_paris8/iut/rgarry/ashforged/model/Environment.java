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

    public Environment(Field field, Character character, List<Mobs> mobs, List<Npc> npcs) {
        this.field = field;
        this.hero = character;
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

    public boolean checkCollision(Direction direction, Entity entity) {
        // TODO: Implement collision detection logic based on the direction and entity position
        // This method should check if the entity collides with any obstacles or boundaries in the environment
        // For now, we can return false as a placeholder
        return false;
    }
}
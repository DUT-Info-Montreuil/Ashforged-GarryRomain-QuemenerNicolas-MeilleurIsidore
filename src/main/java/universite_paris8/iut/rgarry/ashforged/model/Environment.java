package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private Field field;
    private Character hero;
    private List<Mobs> mobs;          // Liste des mobs
    private List<Npc> npcs;          // Liste des NPCs

    public enum Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    public Environment(Field field) {
        this.field = field;
        this.mobs = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.hero = new Character("Hero", 1, new int[]{1, 1, 5, 1}, 250, 300, this);

        this.mobs.add(new Mobs("Mongolfière", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Usuable.golden_piece, 600, 250, this));
        mobs.add(new Mobs("Soldat", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.stone_sword, 600, 250, this));
        mobs.add(new Mobs("Zombie", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.stick, 600, 250, this));
        mobs.add(new Mobs("Bandit", 15, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.firearm, 600, 250,  this));
        mobs.add(new Mobs("Boss", 20, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.steel_sabre, 600, 250,  this));
        mobs.add(new Mobs("Kozuki", 30, new int[]{1, 1, 10, 1}, 5, ItemStock.Weapon.enma, 600, 250,  this));

        npcs.add(new Npc("Paolo", 15,  new int[]{1, 1, 10, 1}, 2500,400, this));
        npcs.add(new Npc("Branda",  15,  new int[]{1, 1, 10, 1}, 2500,400, this));;
        npcs.add(new Npc("Terry", 15,  new int[]{1, 1, 10, 1}, 2500,400, this));
        npcs.add(new Npc("Salome", 15,  new int[]{1, 1, 10, 1}, 2500,400, this));

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
        return this.field.checkCollision(x, y);
    }
}
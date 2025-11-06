package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.Item.Weapon;
import universite_paris8.iut.rgarry.ashforged.model.character.*;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.factory.MobFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {
    private static Environment uniqueInstance=null;

    private Field field;
    private Character hero;
    private List<Ennemis> mobs = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private int maxMobs = 5;

    public enum Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    public Environment() {
        this.field = new Field();
        this.hero = Character.getInstance();

        // Création via la fabrique
        MobFactory factory = new MobFactory();
        Ennemis mob1 = factory.create(MobType.MONGOLFIERE, this);
        Ennemis mob2 = factory.create(MobType.KOZUKI, this);

        addEntity(mob1);
        addEntity(mob2);

        this.npcs.add(new NPC("Paolo", 15, 20, 2500, 400, 'i', 75));
        this.npcs.add(new NPC("Branda", 15, 20, 2500, 400, 'i', 75));
        this.npcs.add(new NPC("Terry", 15, 20, 2500, 400, 'i', 75));
        this.npcs.add(new NPC("Salome", 15, 20, 2500, 400, 'i', 75));

        this.entities.addAll(npcs);
        this.entities.add(hero);
    }

    public Field getField() { return field; }
    public Character getHero() { return hero; }
    public List<Entity> getEntities() { return entities; }
    public List<Ennemis> getMobs() { return mobs; }
    public List<NPC> getNpcs() { return npcs; }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Ennemis) {
            mobs.add((Ennemis) entity);
        } else if (entity instanceof NPC) {
            npcs.add((NPC) entity);
        }
    }

    public void removeEntity(Entity entity) {
        if (entity instanceof Ennemis) {
            mobs.remove(entity);
        } else if (entity instanceof NPC) {
            npcs.remove(entity);
        }
        entities.remove(entity);
    }

    public boolean checkCollision(int x, int y) {
        return field.checkCollision(x, y);
    }

    // Génération simplifiée via la fabrique
    public void generateRandomMob() {
        MobFactory factory = new MobFactory();
        while (this.mobs.size() < maxMobs) {
            Ennemis mob = factory.createRandom(this);
            addEntity(mob);
        }
    }

    public static Environment getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Environment();
        }
        return uniqueInstance;
    }
}

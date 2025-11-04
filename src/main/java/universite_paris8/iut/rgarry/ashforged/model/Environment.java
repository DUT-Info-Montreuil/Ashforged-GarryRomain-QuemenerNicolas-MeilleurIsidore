package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Pickaxe.IronPickaxe;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sword.IronSword;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sword.WoodenSword;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;
import universite_paris8.iut.rgarry.ashforged.model.character.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the main game environment containing the map (Field),
 * player character (Hero), mobs (enemies), and NPCs.
 */
public class Environment {
    private static Environment uniqueInstance=null;

    private Field field;
    private Character hero;
    private List<Ennemis> mobs = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private int maxMobs = 5;


    /**
     * Possible movement directions for entities.
     */
    public enum Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    /**
     * Creates the environment with a given field.
     * Initializes the hero, predefined mobs and NPCs, and adds them to the entity list.
     *
     *
     */
    public Environment() {
        this.field = new Field();
        this.hero = new Character("Hero", 1, 3,300, 13, 5, 25, 'd', 5, 3, 16, 75);

        this.mobs.add(new Ennemis("Mongolfière", 15, 4, 5,13,28, 'g' , new WoodenSword(), 1664));
        this.mobs.add(new Ennemis("Kozuki", 30, 15, 4, 5,13,'g', new IronSword(), 1664));

        this.npcs.add(new NPC("Paolo", 15, 20, 2500, 400, 'i', 75));
        this.npcs.add(new NPC("Branda", 15, 20, 2500, 400, 'i', 75));
        this.npcs.add(new NPC("Terry", 15, 20, 2500, 400, 'i', 75));
        this.npcs.add(new NPC("Salome", 15, 20, 2500, 400, 'i', 75));

        this.entities.addAll(mobs);
        this.entities.addAll(npcs);
        this.entities.add(hero);


    }

    /** @return the game field (map). */
    public Field getField() {
        return field;
    }

    /** @return the player's character. */
    public Character getHero() {
        return hero;
    }

    /** @return list of all entities (hero, mobs, npcs). */
    public List<Entity> getEntities() {
        return entities;
    }

    /** @return list of mobs (enemies). */
    public List<Ennemis> getMobs() {
        return mobs;
    }

    /** @return list of non-playable characters (npcs). */
    public List<NPC> getNpcs() {
        return npcs;
    }

    /**
     * Adds an entity to the environment.
     * Automatically adds it to the appropriate list (mobs or npcs).
     *
     * @param entity the entity to add
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Ennemis) {
            mobs.add((Ennemis) entity);
        } else if (entity instanceof NPC) {
            npcs.add((NPC) entity);
        }
    }

    /**
     * Removes an entity from the environment.
     * Automatically removes it from the appropriate list (mobs or npcs).
     *
     * @param entity the entity to remove
     */
    public void removeEntity(Entity entity) {
        if (entity instanceof Ennemis) {
            mobs.remove(entity);
        } else if (entity instanceof NPC) {
            npcs.remove(entity);
        }
        entities.remove(entity);
    }

    /**
     * Checks if the given pixel coordinates correspond to a collision in the field.
     *
     * @param x x-coordinate in pixels
     * @param y y-coordinate in pixels
     * @return true if collision is detected
     */
    public boolean checkCollision(int x, int y) {
        return field.checkCollision(x, y);
    }

    /**
     * Generates random mobs until the total number reaches the maxMobs limit.
     * Each mob is given a randomized name, stats, weapon, level, and position.
     * Mob stats are influenced by the hero’s stats and level.
     */
    public void generateRandomMob() {
        System.out.println("Generating random mobs...");
        Random rand = new Random();
        String[] mobNames = {"Mongolfière", "Soldat", "Zombie", "Bandit", "Boss", "Kozuki"};
                Usuables[] mobWeapons = {
                Usuables.wooden_sabre,
                Usuables.stone_sword,
                Usuables.stick,
                Usuables.firearm,
                Usuables.iron_sabre,
                Usuables.enma
        };

        int heroLevel = hero.getLevel();
        int heroStatSum = 0;


        for (int i = 0; i + this.mobs.size() < maxMobs; i++) {
            int idx = rand.nextInt(mobNames.length);
            int mobLevel = Math.max(1, rand.nextInt(heroLevel + 5 - Math.max(1, heroLevel - 5) + 1) + Math.max(1, heroLevel - 5));
            int statPointPool = heroStatSum * Math.max(1, Math.abs(1 + (mobLevel - heroLevel) / 10));

            // Randomly distribute stat points
            int[] mobStats = new int[4];
            int pointsLeft = statPointPool;
            for (int s = 0; s < 3; s++) {
                mobStats[s] = rand.nextInt(pointsLeft + 1);
                pointsLeft -= mobStats[s];
            }
            mobStats[3] = pointsLeft;

            int x, y;
            do {
                x = rand.nextInt(this.field.getWidth() * 64);
                y = 300;
                System.out.println("Attempt failed for mob: " + mobNames[idx] + " at (" + x + ", " + y + ")");
            } while (checkCollision(x, y));

            if (!checkCollision(x, y)) {
                System.out.println("Generating mob: " + mobNames[idx] + " (lvl " + mobLevel + ") at (" + x + ", " + y + ")");
                Ennemis mob = new Ennemis(mobNames[idx], x, y, 13, 45, 56, 'g', new IronPickaxe(), 13);
                this.mobs.add(mob);
                this.addEntity(mob);
            }
        }
    }

    public static Environment getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Environment();
        }
        return uniqueInstance;
    }
}


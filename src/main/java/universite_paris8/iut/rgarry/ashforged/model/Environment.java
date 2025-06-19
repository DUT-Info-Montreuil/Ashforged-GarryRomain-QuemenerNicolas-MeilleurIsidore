package universite_paris8.iut.rgarry.ashforged.model;

import javafx.scene.Node;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;
import universite_paris8.iut.rgarry.ashforged.view.MobView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the main game environment containing the map (Field),
 * player character (Hero), mobs (enemies), and NPCs.
 */
public class Environment {

    private Field field;
    private Character hero;
    private List<Mobs> mobs = new ArrayList<>();
    private List<Npc> npcs = new ArrayList<>();
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
     * @param field the game field (map)
     */
    public Environment(Field field) {
        this.field = field;
        this.hero = new Character("Hero", 1, new int[]{999, 99999, 5, 1}, 250, 300, this);

        this.mobs.add(new Mobs("Mongolfière", 15, new int[]{10, 1, 3, 1}, 5, ItemStock.Weapon.wooden_sabre, 1664, 300, this));
        this.mobs.add(new Mobs("Kozuki", 30, new int[]{1, 1, 3, 1}, 5, ItemStock.Weapon.enma, 600, 250, this));

        this.npcs.add(new Npc("Paolo", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));
        this.npcs.add(new Npc("Branda", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));
        this.npcs.add(new Npc("Terry", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));
        this.npcs.add(new Npc("Salome", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));

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
    public List<Mobs> getMobs() {
        return mobs;
    }

    /** @return list of non-playable characters (npcs). */
    public List<Npc> getNpcs() {
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
        if (entity instanceof Mobs) {
            mobs.add((Mobs) entity);
        } else if (entity instanceof Npc) {
            npcs.add((Npc) entity);
        }
    }

    /**
     * Removes an entity from the environment.
     * Automatically removes it from the appropriate list (mobs or npcs).
     *
     * @param entity the entity to remove
     */
    public void removeEntity(Entity entity) {
        if (entity instanceof Mobs) {
            mobs.remove(entity);
        } else if (entity instanceof Npc) {
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
        Random rand = new Random();
        String[] mobNames = {"Mongolfière", "Soldat", "Zombie", "Bandit", "Boss", "Kozuki"};
        ItemStock.Weapon[] mobWeapons = {
                ItemStock.Weapon.wooden_sabre,
                ItemStock.Weapon.stone_sword,
                ItemStock.Weapon.stick,
                ItemStock.Weapon.firearm,
                ItemStock.Weapon.iron_sabre,
                ItemStock.Weapon.enma
        };

        int heroLevel = hero.getLevel();
        int[] heroStats = hero.getStats();
        int heroStatSum = 0;
        for (int s : heroStats) heroStatSum += s;

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
                y = rand.nextInt(this.field.getHeight() * 64);
                System.out.println("Attempt failed for mob: " + mobNames[idx] + " at (" + x + ", " + y + ")");
            } while (checkCollision(x, y));

            if (!checkCollision(x, y)) {
                System.out.println("Generating mob: " + mobNames[idx] + " (lvl " + mobLevel + ") at (" + x + ", " + y + ")");
                Mobs mob = new Mobs(
                        mobNames[idx],
                        mobLevel,
                        mobStats,
                        5,
                        mobWeapons[idx],
                        x,
                        y,
                        this
                );
                this.mobs.add(mob);
                this.addEntity(mob);
            }
        }
    }
}


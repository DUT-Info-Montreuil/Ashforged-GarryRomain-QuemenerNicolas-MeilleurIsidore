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

// Class to manage the game environment, including the field, hero, mobs, NPCs, and entities
public class Environment {
    private Field field; // The game map defining tile structure
    private Character hero; // The player-controlled character
    private List<Mobs> mobs = new ArrayList<>(); // List of hostile mobs
    private List<Npc> npcs = new ArrayList<>(); // List of non-player characters
    private List<Entity> entities = new ArrayList<>(); // List of all entities (hero, mobs, NPCs)
    private int maxMobCount = 5; // Maximum number of mobs to generate

    // Enum to represent movement directions
    public enum Direction {
        LEFT, RIGHT, TOP, BOTTOM
    }

    /**
     * Constructor for Environment, initializes the game environment with a field and default entities.
     *
     * @param field The game map defining the tile structure.
     */
    public Environment(Field field) {
        this.field = field; // Store the field reference
        this.mobs = new ArrayList<>(); // Initialize mob list
        this.npcs = new ArrayList<>(); // Initialize NPC list
        // Initialize the hero with default attributes
        this.hero = new Character("Hero", 1, new int[]{10, 1, 5, 1}, 250, 300, this);

        // Add predefined mobs
        this.mobs.add(new Mobs("Mongolfière", 15, new int[]{10, 1, 3, 1}, 5, ItemStock.Weapon.wooden_sabre, 1664, 300, this));
        mobs.add(new Mobs("Kozuki", 30, new int[]{1, 1, 3, 1}, 5, ItemStock.Weapon.enma, 600, 250, this));

        // Add predefined NPCs
        this.npcs.add(new Npc("Paolo", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));
        this.npcs.add(new Npc("Branda", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));
        this.npcs.add(new Npc("Terry", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));
        this.npcs.add(new Npc("Salome", 15, new int[]{1, 1, 10, 1}, 2500, 400, this));

        // Add all entities to the entities list
        this.entities.addAll(mobs);
        this.entities.addAll(npcs);
        this.entities.add(hero);
    }

    /**
     * Retrieves the game field.
     *
     * @return The Field object representing the game map.
     */
    public Field getField() {
        return field; // Return the field
    }

    /**
     * Retrieves the hero character.
     *
     * @return The Character object representing the hero.
     */
    public Character getHero() {
        return hero; // Return the hero
    }

    /**
     * Retrieves the list of all entities in the environment.
     *
     * @return A List of Entity objects (hero, mobs, NPCs).
     */
    public List<Entity> getEntities() {
        return entities; // Return the entities list
    }

    /**
     * Retrieves the list of mobs in the environment.
     *
     * @return A List of Mobs objects.
     */
    public List<Mobs> getMobs() {
        return mobs; // Return the mobs list
    }

    /**
     * Retrieves the list of NPCs in the environment.
     *
     * @return A List of Npc objects.
     */
    public List<Npc> getNpcs() {
        return npcs; // Return the NPCs list
    }

    /**
     * Adds an entity to the environment, updating the appropriate lists.
     *
     * @param entity The Entity to add (can be Mobs, Npc, or Character).
     */
    public void addEntity(Entity entity) {
        entities.add(entity); // Add to entities list
        // Add to specific list based on entity type
        if (entity instanceof Mobs) {
            mobs.add((Mobs) entity);
        } else if (entity instanceof Npc) {
            npcs.add((Npc) entity);
        }
    }

    /**
     * Removes an entity from the environment, updating the appropriate lists.
     *
     * @param entity The Entity to remove (can be Mobs, Npc, or Character).
     */
    public void removeEntity(Entity entity) {
        // Remove from specific list based on entity type
        if (entity instanceof Mobs) {
            mobs.remove(entity);
        } else if (entity instanceof Npc) {
            npcs.remove(entity);
        }
        entities.remove(entity); // Remove from entities list
    }

    /**
     * Checks for a collision at the specified coordinates.
     *
     * @param x The x-coordinate in pixels.
     * @param y The y-coordinate in pixels.
     * @return true if there is a collision (non-sky tile), false otherwise.
     */
    public boolean checkCollision(int x, int y) {
        return field.checkCollision(x, y); // Delegate collision check to field
    }

    /**
     * Generates random mobs up to the maximum mob count, with attributes scaled to the hero's level.
     * Mobs are placed at random non-collidable positions on the map.
     */
    public void generateRandomMob() {
        Random rand = new Random();
        // Possible mob names and corresponding weapons
        String[] mobNames = {"Mongolfière", "Soldat", "Zombie", "Bandit", "Boss", "Kozuki"};
        ItemStock.Weapon[] mobWeapons = {
                ItemStock.Weapon.wooden_sabre,
                ItemStock.Weapon.stone_sword,
                ItemStock.Weapon.stick,
                ItemStock.Weapon.firearm,
                ItemStock.Weapon.iron_sabre,
                ItemStock.Weapon.enma
        };

        // Get hero's level and stats sum
        int heroLevel = hero.getLevel();
        int[] heroStats = hero.getStats();
        int heroStatSum = 0;
        for (int stat : heroStats) {
            heroStatSum += stat;
        }

        // Generate mobs until reaching maxMobCount
        for (int i = 0; i + mobs.size() < maxMobCount; i++) {
            int idx = rand.nextInt(mobNames.length); // Random mob type
            // Calculate mob level within a range around hero's level
            int mobLevel = Math.max(1, rand.nextInt(heroLevel + 5 - Math.max(1, heroLevel - 5) + 1) + Math.max(1, heroLevel - 5));
            // Scale stat points based on hero's stats and level difference
            int statPointPool = heroStatSum * Math.max(1, Math.abs(1 + (mobLevel - heroLevel) / 10));

            // Randomly distribute stat points across 4 stats
            int[] mobStats = new int[4];
            int pointsLeft = statPointPool;
            for (int s = 0; s < 3; s++) {
                mobStats[s] = rand.nextInt(pointsLeft + 1);
                pointsLeft -= mobStats[s];
            }
            mobStats[3] = pointsLeft;

            // Find a non-collidable position
            int x, y, attempts = 0;
            do {
                x = rand.nextInt(field.getWidth() * 64); // Random x-coordinate
                y = rand.nextInt(field.getHeight() * 64); // Random y-coordinate
                attempts++;
                System.out.println("Attempt failed for mob: " + mobNames[idx] + " at (" + x + ", " + y + ")");
            } while (checkCollision(x, y));

            // Create and add mob if position is valid
            if (!checkCollision(x, y)) {
                System.out.println("Generating mob: " + mobNames[idx] + " (lvl " + mobLevel + ") at (" + x + ", " + y + ")");
                Mobs mob = new Mobs(
                        mobNames[idx],
                        mobLevel,
                        mobStats,
                        5, // Fixed multiplier
                        mobWeapons[idx],
                        x,
                        y,
                        this
                );
                mobs.add(mob); // Add to mobs list
                addEntity(mob); // Add to entities list
            }
        }
    }
}
package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.BFS;
import universite_paris8.iut.rgarry.ashforged.model.Position;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.List;
import java.util.Random;

/**
 * The Mobs class represents an enemy character in the game, extending the Character class.
 * Mobs can move randomly or follow a path to pursue the player using BFS pathfinding.
 * They can attack non-mob entities within a close range and interact with the environment,
 * including jumping over obstacles and applying gravity.
 */
public class Mobs extends Character {
    private int statsMultiplier; // Multiplier for enhancing mob stats
    private ItemInterface item; // Item held by the mob
    private final int initialX; // Initial X position of the mob
    private final Random random = new Random(); // Random number generator for movement decisions
    private final int JUMP_STRENGTH = -12; // Jump velocity for the mob
    private boolean hasSeenPlayer = false; // Flag indicating if the mob has seen the player

    // Movement boundaries in tiles
    private int minX; // Minimum X position for movement
    private int maxX; // Maximum X position for movement

    private char currentDirection = 'i'; // Current direction: 'l' = left, 'r' = right, 'i' = idle

    private List<Position> currentPath = null; // Current path for pathfinding
    private int pathIndex = 1; // Index of the next position in the path

    private int lastPlayerTileX = -1; // Last known X tile position of the player
    private int lastPlayerTileY = -1; // Last known Y tile position of the player

    /**
     * Constructor for the Mobs class.
     * Initializes the mob with a name, level, stats, stats multiplier, item, position, and environment.
     * Sets the mob's initial position and held item.
     *
     * @param name           The name of the mob
     * @param level          The level of the mob
     * @param stats          Array of stats (e.g., strength, health)
     * @param statsMultiplier Multiplier for enhancing stats
     * @param item           Item held by the mob
     * @param x              Initial X position
     * @param y              Initial Y position
     * @param env            The environment in which the mob exists
     */
    public Mobs(String name, int level, int[] stats, int statsMultiplier, ItemInterface item, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.statsMultiplier = statsMultiplier;
        this.item = item;
        this.initialX = x;
        this.setHoldingItem(item);
    }

    /**
     * Randomly selects a movement direction for the mob.
     * The mob can move left ('l'), right ('r'), or remain idle ('i').
     */
    public void chooseRandomDirection() {
        int r = random.nextInt(3);
        if (r == 0) currentDirection = 'l';
        else if (r == 1) currentDirection = 'r';
        else currentDirection = 'i';
    }

    /**
     * Moves the mob randomly based on its current direction.
     * Calls moveLeftRandom() or moveRightRandom() if the direction is 'l' or 'r', respectively.
     */
    public void moveRandom() {
        if (currentDirection == 'l') {
            moveLeftRandom();
        } else if (currentDirection == 'r') {
            moveRightRandom();
        }
    }

    /**
     * Moves the mob to the left, respecting collision checks and map boundaries.
     */
    @Override
    public void moveLeft() {
        int newX = getX() - getSpeed();
        if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        }
    }

    /**
     * Moves the mob to the right, respecting collision checks and map boundaries.
     */
    @Override
    public void moveRight() {
        int newX = getX() + getSpeed();
        if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        }
    }

    /**
     * Moves the mob to the right with random behavior, including jumping if grounded and blocked.
     * Checks for collisions and map boundaries, and initiates a jump if an obstacle is encountered.
     */
    public void moveRightRandom() {
        int newX = getX() + getSpeed();
        if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGTH);
            }
        }
    }

    /**
     * Moves the mob to the left with random behavior, including jumping if grounded and blocked.
     * Checks for collisions and map boundaries, and initiates a jump if an obstacle is encountered.
     */
    public void moveLeftRandom() {
        int newX = getX() - getSpeed();
        if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31) && isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGTH);
            }
        }
    }

    /**
     * Moves the mob either toward the player using BFS pathfinding or randomly if the player is not seen.
     * If the player is within 5 tiles, the mob uses pathfinding to pursue them, including jumping if necessary.
     * Otherwise, it moves randomly.
     */
    @Override
    public void move() {
        // Retrieve the player's position
        Character hero = env.getHero();
        int mobX = getX() / 64;
        int mobY = getY() / 64;
        int heroX = hero.getX() / 64;
        int heroY = hero.getY() / 64;

        // Use BFS to find a path to the player
        BFS bfs = new BFS(env.getField());
        Position start = new Position(mobX, mobY);
        Position goal = new Position(heroX, heroY);

        List<Position> path = bfs.findPath(start, goal);

        if (Math.abs(heroX - mobX) <= 5) {
            hasSeenPlayer = true;
        }

        // If the player is seen and a valid path exists
        if (hasSeenPlayer) {
            if (path.size() > 1) {
                Position next = path.get(1);

                if (next.x < mobX) {
                    moveLeft();
                } else if (next.x > mobX) {
                    moveRight();
                }

                // Jump if the next position is above
                if (next.y < mobY) {
                    if (env.checkCollision(getX(), getY() + 32) && env.checkCollision(getX() + 31, getY() + 32)) {
                        setVelocityY(JUMP_STRENGTH);
                    }
                }
            }
        } else {
            moveRandom();
        }
    }

    /**
     * Performs an attack on non-mob entities within a 2-tile radius.
     * If the mob is holding a weapon, it calculates damage based on the weapon's damage
     * and the mob's strength stat, then applies it to the target entity's health.
     */
    @Override
    public void attack() {
        System.out.println(this.getName() + " Health:" + this.getHealth());
        if (getHoldingItem() != null && this.getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if (!(entity instanceof Mobs)) {
                    int entityX = entity.getX() / 64;
                    int entityY = entity.getY() / 64;
                    int mobX = getX() / 64;
                    int mobY = getY() / 64;

                    if (Math.abs(entityX - mobX) < 2 && Math.abs(entityY - mobY) < 2) {
                        int damage;
                        if (stats[1] > 1)
                            damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        else
                            damage = getHoldingItem().getDamage() / 2;
                        entity.setHealth(entity.getHealth() - damage);
                    }
                }
            }
        }
    }

    /**
     * Executes the mob's main actions: applying gravity and moving.
     * This method is called to update the mob's state in each game cycle.
     */
    public void performAction() {
        this.applyGravity(env);
        this.move();
    }
}
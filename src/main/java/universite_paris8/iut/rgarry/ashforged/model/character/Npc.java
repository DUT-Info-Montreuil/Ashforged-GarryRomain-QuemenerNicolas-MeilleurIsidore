package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.Random;

/**
 * The Npc class represents a non-player character in the game, extending the Entity class.
 * NPCs can move randomly within a defined range, attack nearby mobs, and interact with the environment.
 * They have a constrained movement range based on their initial position and can perform actions
 * like moving left, right, or attacking based on their current state.
 */
public class Npc extends Entity {
    private final int initialX; // Initial X position of the NPC
    private final Random random = new Random(); // Random number generator for movement decisions
    private int minX; // Minimum X position the NPC can move to
    private int newX; // Temporary X position for movement calculations
    private int maxX; // Maximum X position the NPC can move to
    private char currentDirection = 'i'; // Current direction: 'l' = left, 'r' = right, 'i' = idle

    /**
     * Constructor for the Npc class.
     * Initializes the NPC with a name, level, stats, position, and environment.
     * Sets movement boundaries based on the initial X position (±640 units).
     *
     * @param name   The name of the NPC
     * @param level  The level of the NPC
     * @param stats  Array of stats (e.g., strength, health)
     * @param x      Initial X position
     * @param y      Initial Y position
     * @param env    The environment in which the NPC exists
     */
    public Npc(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.initialX = x;
        this.minX = initialX - 640;
        this.maxX = initialX + 640;
    }

    /**
     * Randomly selects a movement direction for the NPC.
     * The NPC can move left ('l'), right ('r'), or remain idle ('i').
     */
    public void chooseRandomDirection() {
        int r = random.nextInt(3);
        if (r == 0) currentDirection = 'l';
        else if (r == 1) currentDirection = 'r';
        else currentDirection = 'i';
    }

    /**
     * Moves the NPC to the left, respecting movement boundaries and collision checks.
     * If a collision occurs or the NPC reaches the minimum X position, movement is restricted.
     * If grounded (colliding below), the NPC can jump.
     */
    @Override
    public void moveLeft() {
        newX = getX() - getSpeed();
        if (newX < minX) newX = minX;
        boolean collision = env.checkCollision(newX, getY()) || env.checkCollision(newX, getY() + 31);
        if (!collision && isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (env != null
                    && env.checkCollision(getX(), getY() + 32)
                    && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(-12);
            }
        }
    }

    /**
     * Moves the NPC to the right, respecting movement boundaries and collision checks.
     * If a collision occurs or the NPC reaches the maximum X position, movement is restricted.
     * If grounded (colliding below), the NPC can jump.
     */
    @Override
    public void moveRight() {
        newX = getX() + getSpeed();
        if (newX > maxX) newX = maxX;
        boolean collision = env.checkCollision(newX + 31, getY()) || env.checkCollision(newX + 31, getY() + 31);
        if (!collision && isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (env != null
                    && env.checkCollision(getX(), getY() + 32)
                    && env.checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(-12);
            }
        }
    }

    /**
     * Moves the NPC based on its current direction.
     * Calls moveLeft() or moveRight() if the direction is 'l' or 'r', respectively.
     * No action is taken if the direction is 'i' (idle).
     */
    public void move() {
        if (currentDirection == 'l') {
            moveLeft();
        } else if (currentDirection == 'r') {
            moveRight();
        }
    }

    /**
     * Performs an attack on nearby mobs within a 2-tile radius.
     * If the NPC is holding a weapon, it calculates damage based on the weapon's damage
     * and the NPC's strength stat, then applies it to the target mob's health.
     */
    public void attack() {
        if (getHoldingItem() != null && this.getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if (entity instanceof Mobs) {
                    int entityX = entity.getX() / 64;
                    int entityY = entity.getY() / 64;
                    int npcX = getX() / 64;
                    int npcY = getY() / 64;

                    if (Math.abs(entityX - npcX) < 2 && Math.abs(entityY - npcY) < 2) {
                        System.out.println("Attack");
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
}
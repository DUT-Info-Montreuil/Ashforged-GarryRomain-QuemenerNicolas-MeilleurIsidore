package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

/**
 * The Character class represents a playable character in the game, extending the Entity class.
 * It defines movement behavior with collision detection and attack mechanics targeting mobs.
 * The character can move left or right, stop at walls, and attack nearby mobs with a held weapon.
 */
public class Character extends Entity {

    /**
     * Constructor for the Character class.
     * Initializes the character with a name, level, stats, position, and environment.
     * Sets the initial held item to null.
     *
     * @param name  The name of the character
     * @param level The level of the character
     * @param stats Array of stats (health, strength, speed, strength)
     * @param x     Initial X position
     * @param y     Initial Y position
     * @param env   The environment in which the character exists
     */
    public Character(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        setHoldingItem(null);
    }

    /**
     * Moves the character based on its current direction ('l' for left, 'r' for right).
     * Checks for collisions with the environment and ensures the character stays within map boundaries.
     * If a collision occurs, the character is positioned just before or after the wall to prevent overlap.
     */
    @Override
    public void move() {
        int newX = getX();
        if (direction == 'r') {
            newX += getSpeed();
            if (!env.checkCollision(newX + 31, getY()) && !env.checkCollision(newX + 31, getY() + 31) && isWithinMapX(newX)) {
                setX(newX);
                test = false;
            } else {
                if (!test) {
                    // Place the character just before the wall (1 pixel away)
                    int blockX = ((newX + 31) / 64) * 64;
                    setX(blockX - 31 - 1);
                    test = true;
                }
            }
        } else if (direction == 'l') {
            newX -= getSpeed();
            if (!env.checkCollision(newX, getY()) && !env.checkCollision(newX, getY() + 31) && isWithinMapX(newX)) {
                setX(newX);
                test = false;
            } else {
                if (!test) {
                    // Place the character just after the wall (1 pixel away)
                    int blockX = (newX / 64) * 64;
                    setX(blockX + 64 + 1);
                    test = true;
                }
            }
        }
    }

    /**
     * Performs an attack on nearby mobs within a 2-tile radius.
     * If the character is holding a weapon, it calculates damage based on the weapon's damage
     * and the character's strength stat, applies it to the mob's health, and awards experience
     * if the mob is defeated.
     */
    @Override
    public void attack() {
        if (getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if (entity instanceof Mobs) {
                    int dx = Math.abs(entity.getX() / 64 - this.getX() / 64);
                    int dy = Math.abs(entity.getY() / 64 - this.getY() / 64);
                    if (dx < 2 && dy < 2) {
                        System.out.println("HUSSSSS !");
                        int damage;
                        if (stats[1] > 1) {
                            damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        } else {
                            damage = getHoldingItem().getDamage() / 2;
                        }
                        if (entity.getHealth() - damage <= 0) {
                            entity.setHealth(0);
                            System.out.println("You have killed " + entity.getName() + " !");
                            System.out.println(entity.getLevel());
                            this.gainExp(entity.getLevel());
                        } else {
                            entity.setHealth(entity.getHealth() - damage);
                            System.out.println("You have dealt " + damage + " damage to " + entity.getName() + " !");
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if the given X coordinate is within the map's width boundaries.
     *
     * @param x The X coordinate to check
     * @return True if the X coordinate is within the map, false otherwise
     */
    private boolean isWithinMapX(int x) {
        return x >= 0 && x + 31 < env.getField().getWidth() * 64; // 31: entity width in pixels
    }

    /**
     * Checks if the given Y coordinate is within the map's height boundaries.
     *
     * @param y The Y coordinate to check
     * @return True if the Y coordinate is within the map, false otherwise
     */
    private boolean isWithinMapY(int y) {
        return y >= 0 && y + 31 < env.getField().getHeight() * 64; // 31: entity height in pixels
    }
}
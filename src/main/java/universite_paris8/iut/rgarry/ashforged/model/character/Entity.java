package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

import java.util.LinkedHashMap;

/**
 * The Entity class is an abstract base class representing a game entity, such as a character or NPC.
 * It manages properties like position, health, inventory, and experience, and provides methods for
 * movement, gravity application, and inventory management. Subclasses must implement movement and
 * attack behaviors.
 */
public abstract class Entity {
    private Node node; // JavaFX node for rendering the entity
    private ItemInterface holdingItem; // Item currently held by the entity
    private static final double GRAVITY = 0.5; // Gravity constant applied to the entity
    protected double velocityY; // Vertical velocity for movement (e.g., jumping/falling)
    protected String id; // Unique identifier for the entity
    protected String name; // Name of the entity
    protected IntegerProperty level; // Current level of the entity
    protected int[] stats; // Array of stats: [0] = health, [1] = strength, [2] = speed, [3] = strength
    protected LinkedHashMap<ItemInterface, Integer> inventory; // Inventory mapping items to quantities
    protected int pods; // Current weight carried by the entity
    protected int maxPods; // Maximum weight the entity can carry
    protected IntegerProperty health = new SimpleIntegerProperty(); // Current health of the entity
    protected int maxHealth; // Maximum health of the entity
    protected IntegerProperty exp = new SimpleIntegerProperty(0); // Current experience points
    protected IntegerProperty expToNextLevel = new SimpleIntegerProperty(5); // Experience needed for next level
    protected Environment env; // Game environment the entity exists in
    protected int statPoint; // Available stat points for upgrading stats
    protected IntegerProperty x, y; // X and Y coordinates of the entity
    protected char direction; // Current direction: 'r' = right, 'l' = left, 'i' = idle
    protected static int counter = 0; // Counter for generating unique IDs
    protected boolean test = false; // Test flag (purpose unclear, possibly for debugging)

    /**
     * Constructor for the Entity class.
     * Initializes the entity with a name, level, stats, position, and environment.
     * Sets up health, inventory, and stat points based on the provided stats.
     *
     * @param name  The name of the entity
     * @param level The level of the entity
     * @param stats Array of stats (health, strength, speed, strength)
     * @param x     Initial X position
     * @param y     Initial Y position
     * @param env   The environment in which the entity exists
     */
    public Entity(String name, int level, int[] stats, int x, int y, Environment env) {
        this.velocityY = 0;
        this.id = "#" + counter++;
        this.name = name;
        this.level = new SimpleIntegerProperty(level);
        this.stats = stats;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.statPoint += 5 * (level + 1);
        this.inventory = new LinkedHashMap<>();
        this.pods = 0;
        this.maxPods = 10 * stats[1];
        this.maxHealth = 3 * stats[0];
        this.health.set(this.maxHealth);
        this.direction = 'i';
    }

    /**
     * Applies gravity to the entity, causing it to fall or move upward based on velocity.
     * Checks for collisions with the environment to stop movement when hitting the ground or ceiling.
     *
     * @param environment The game environment to check for collisions
     */
    public void applyGravity(Environment environment) {
        velocityY += GRAVITY;
        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            int nextY = getY() + direction;
            // Check if the next position is within the map
            if (!isWithinMap(getX(), nextY)) {
                velocityY = 0;
                break;
            }
            if (direction > 0) { // Moving down
                if (!environment.getField().checkCollision(getX(), getY() + 32) &&
                        !environment.getField().checkCollision(getX() + 31, getY() + 32)) {
                    setY(getY() + 1);
                } else {
                    velocityY = 0;
                    break;
                }
            } else if (direction < 0) { // Moving up
                if (!environment.getField().checkCollision(getX(), getY() - 1) &&
                        !environment.getField().checkCollision(getX() + 31, getY() - 1)) {
                    setY(getY() - 1);
                } else {
                    velocityY = 0;
                    break;
                }
            }
        }
    }

    /**
     * Checks if the given position is within the boundaries of the game map.
     *
     * @param x X coordinate to check
     * @param y Y coordinate to check
     * @return True if the position is within the map, false otherwise
     */
    protected boolean isWithinMap(int x, int y) {
        int width = env.getField().getWidth() * 64;
        int height = env.getField().getHeight() * 64;
        return x >= 0 && x + 31 < width && y >= 0 && y + 31 < height;
    }

    /**
     * Abstract method for moving the entity.
     * Must be implemented by subclasses to define specific movement behavior.
     */
    public abstract void move();

    /**
     * Abstract method for attacking.
     * Must be implemented by subclasses to define specific attack behavior.
     */
    public abstract void attack();

    /**
     * Sets the entity's direction to right ('r').
     */
    public void moveRight() { this.direction = 'r'; }

    /**
     * Sets the entity's direction to left ('l').
     */
    public void moveLeft() { this.direction = 'l'; }

    /**
     * Sets the entity's direction to idle ('i').
     */
    public void stayIdle() { this.direction = 'i'; }

    /**
     * Gets the available stat points for upgrading stats.
     *
     * @return The number of stat points
     */
    public int getStatPoint() { return statPoint; }

    /**
     * Gets the JavaFX node representing the entity.
     *
     * @return The JavaFX node
     */
    public Node getNode() { return node; }

    /**
     * Sets the JavaFX node representing the entity.
     *
     * @param node The JavaFX node to set
     */
    public void setNode(Node node) { this.node = node; }

    /**
     * Gets the vertical velocity of the entity.
     *
     * @return The vertical velocity
     */
    public double getVelocityY() { return velocityY; }

    /**
     * Sets the vertical velocity of the entity.
     *
     * @param velocityY The vertical velocity to set
     */
    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }

    /**
     * Gets the unique identifier of the entity.
     *
     * @return The entity's ID
     */
    public String getId() { return id; }

    /**
     * Gets the name of the entity.
     *
     * @return The entity's name
     */
    public String getName() { return name; }

    /**
     * Gets the current level of the entity.
     *
     * @return The entity's level
     */
    public int getLevel() { return level.get(); }

    /**
     * Gets the array of stats for the entity.
     *
     * @return The stats array
     */
    public int[] getStats() { return stats; }

    /**
     * Gets the X coordinate property of the entity.
     *
     * @return The X coordinate property
     */
    public IntegerProperty getXProperty() { return x; }

    /**
     * Gets the current X coordinate of the entity.
     *
     * @return The X coordinate
     */
    public int getX() { return x.getValue(); }

    /**
     * Gets the Y coordinate property of the entity.
     *
     * @return The Y coordinate property
     */
    public IntegerProperty getYProperty() { return y; }

    /**
     * Gets the current Y coordinate of the entity.
     *
     * @return The Y coordinate
     */
    public int getY() { return y.getValue(); }

    /**
     * Sets the X coordinate of the entity.
     *
     * @param pos The X coordinate to set
     */
    public void setX(int pos) { x.setValue(pos); }

    /**
     * Sets the Y coordinate of the entity.
     *
     * @param pos The Y coordinate to set
     */
    public void setY(int pos) { y.setValue(pos); }

    /**
     * Sets the level of the entity.
     *
     * @param level The level to set
     */
    public void setLevel(int level) { this.level.set(level); }

    /**
     * Gets the level property of the entity.
     *
     * @return The level property
     */
    public IntegerProperty levelProperty() { return level; }

    /**
     * Gets the speed of the entity based on its stats.
     *
     * @return The speed value
     */
    public int getSpeed() { return this.stats[2]; }

    /**
     * Sets the stats array for the entity.
     *
     * @param stats The stats array to set
     */
    public void setStats(int[] stats) { this.stats = stats; }

    /**
     * Sets the available stat points for the entity.
     *
     * @param statPoint The number of stat points to set
     */
    public void setStatPoint(int statPoint) { this.statPoint = statPoint; }

    /**
     * Gets the current health of the entity.
     *
     * @return The current health
     */
    public int getHealth() { return health.get(); }

    /**
     * Gets the maximum health of the entity.
     *
     * @return The maximum health
     */
    public int getMaxHealth() { return maxHealth; }

    /**
     * Gets the health property of the entity.
     *
     * @return The health property
     */
    public IntegerProperty healthProperty() { return health; }

    /**
     * Sets the current health of the entity.
     *
     * @param value The health value to set
     */
    public void setHealth(int value) { this.health.set(value); }

    /**
     * Gets the experience property of the entity.
     *
     * @return The experience property
     */
    public IntegerProperty expProperty() { return exp; }

    /**
     * Gets the experience needed for the next level property.
     *
     * @return The experience to next level property
     */
    public IntegerProperty expToNextLevelProperty() { return expToNextLevel; }

    /**
     * Gets the current experience points of the entity.
     *
     * @return The current experience
     */
    public int getExp() { return exp.get(); }

    /**
     * Gets the experience needed for the next level.
     *
     * @return The experience needed for the next level
     */
    public int getExpToNextLevel() { return expToNextLevel.get(); }

    /**
     * Gets the environment in which the entity exists.
     *
     * @return The environment
     */
    public Environment getEnv() { return env; }

    /**
     * Sets the maximum health of the entity based on a stat value.
     *
     * @param statHealth The health stat to base max health on
     */
    public void setMaxHealth(int statHealth) { this.maxHealth = statHealth * 3; }

    /**
     * Gets the item currently held by the entity.
     *
     * @return The held item
     */
    public ItemInterface getHoldingItem() { return holdingItem; }

    /**
     * Sets the item currently held by the entity.
     *
     * @param holdingItem The item to hold
     */
    public void setHoldingItem(ItemInterface holdingItem) { this.holdingItem = holdingItem; }

    /**
     * Adds experience points to the entity and handles level-ups if enough experience is gained.
     *
     * @param amount The amount of experience to gain
     */
    public void gainExp(int amount) {
        exp.set(exp.get() + amount);
        while (exp.get() >= expToNextLevel.get()) {
            exp.set(exp.get() - expToNextLevel.get());
            levelUp();
        }
    }

    /**
     * Levels up the entity, increasing its level and stat points, and updating the experience needed for the next level.
     */
    private void levelUp() {
        setLevel(getLevel() + 1);
        expToNextLevel.set(expToNextLevel.get() + 5);
        statPoint += 5;
    }

    /**
     * Gets the entity's inventory.
     *
     * @return The inventory as a LinkedHashMap
     */
    public LinkedHashMap<ItemInterface, Integer> getInventory() { return inventory; }

    /**
     * Adds an item to the entity's inventory, respecting weight limits.
     * If the item is already in the inventory, its quantity is incremented.
     */
    public void addToInventory(ItemInterface item) {
        System.out.println("------ Add to Inventory ------");
        if (!inventory.containsKey(item)) {
            if (item.getWeight() + this.pods <= maxPods) {
                inventory.put(item, 1);
            } else {
                System.out.println("The inventory is full or too much pods");
            }
        } else {
            inventory.put(item, inventory.get(item) + 1); // Increment the quantity of the item
        }
    }

    /**
     * Removes an item from the entity's inventory.
     * If the item has a quantity greater than 1, its quantity is decremented; otherwise, it is removed.
     *
     * @param item The item to remove
     */
    public void removeFromInventory(ItemInterface item) {
        System.out.println("------ Remove from Inventory ------");
        boolean removed = false;

        if (inventory.containsKey(item)) {
            if (inventory.get(item) > 1) {
                inventory.put(item, inventory.get(item) - 1);
            } else {
                inventory.remove(item);
            }
            System.out.println("Item " + item.getName() + " removed from inventory\n");
            removed = true;
        }

        if (!removed) {
            System.out.println("Item not found in inventory\n");
        }
    }

    /**
     * Finds an item in the inventory by its index.
     *
     * @param inventory The inventory to search
     * @param index     The index of the item to find
     * @return The item at the specified index, or null if the index is invalid
     */
    public ItemInterface findKey(LinkedHashMap<ItemInterface, Integer> inventory, int index) {
        if (index < 0 || index >= inventory.size()) {
            return null; // Index out of range
        }
        int i = 0;
        for (ItemInterface key : inventory.keySet()) {
            if (i == index) {
                return key; // Found the key at 'index'
            }
            i++;
        }
        return null; // If index is invalid (shouldn't happen due to check)
    }
}
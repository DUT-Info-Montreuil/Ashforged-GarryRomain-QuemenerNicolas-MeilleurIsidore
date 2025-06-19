package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

import java.util.LinkedHashMap;

public abstract class Entity {
    private Node node;
    private ItemInterface holdingItem;
    private static final double GRAVITY = 0.5;
    protected double velocityY;
    protected String id;
    protected String name;
    protected IntegerProperty level;
    protected int[] stats;
    // stats[0] = vie, stats[1] = force, stats[2] = vitesse, stats[3] = force (à vérifier).
    protected LinkedHashMap<ItemInterface, Integer> inventory;
    protected int pods;
    protected int maxPods;
    protected IntegerProperty health = new SimpleIntegerProperty();
    protected int maxHealth;
    protected IntegerProperty exp = new SimpleIntegerProperty(0);
    protected IntegerProperty expToNextLevel = new SimpleIntegerProperty(5);
    protected Environment env;
    protected int stat_point;
    protected IntegerProperty x, y;
    protected char direction;
    protected static int compter = 0;
    protected boolean test = false;

    public Entity(String name, int level, int[] stats, int x, int y, Environment env) {
        this.velocityY = 0;
        this.id = "#" + compter++;
        this.name = name;
        this.level = new SimpleIntegerProperty(level);
        this.stats = stats;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.stat_point = 5 * (level + 1);
        this.inventory = new LinkedHashMap<>();
        this.pods = 0;
        this.maxPods = 10 * stats[1];
        this.maxHealth = 3 * stats[0];
        this.health.set(this.maxHealth);
        this.direction = 'i';
    }

    /**
     * Applique la gravité sur l'entité.
     * La gravité affecte velocityY, et déplace l'entité vers le bas ou le haut
     * en vérifiant les collisions et limites de la map.
     */
    public void applyGravity(Environment environment) {
        velocityY += GRAVITY;
        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            int nextY = getY() + direction;
            if (!isWithinMap(getX(), nextY)) {
                velocityY = 0;
                break;
            }
            if (direction > 0) { // Descente
                if (!environment.getField().checkCollision(getX(), getY() + 32) &&
                        !environment.getField().checkCollision(getX() + 31, getY() + 32)) {
                    setY(getY() + 1);
                } else {
                    velocityY = 0;
                    break;
                }
            } else { // Montée
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

    /** Vérifie que la position (x,y) est dans les limites de la carte */
    protected boolean isWithinMap(int x, int y) {
        int width = env.getField().getWidth() * 64;
        int height = env.getField().getHeight() * 64;
        return x >= 0 && x + 31 < width && y >= 0 && y + 31 < height;
    }

    // Méthodes abstraites à implémenter par les classes filles
    public abstract void seDeplacer();
    public abstract void attack();

    public int getStatPoint() { return stat_point; }

    public void vaADroite() { this.direction = 'd'; }
    public void vaAGauche() { this.direction = 'g'; }
    public void resteImmobile() { this.direction = 'i'; }

    // Getters et setters usuels

    public Node getNode() { return node; }
    public void setNode(Node node) { this.node = node; }

    public double getVelocityY() { return velocityY; }
    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
    public String getId() { return id; }
    public String getName() { return name; }
    public int getLevel() { return level.get(); }
    public int[] getStats() { return stats; }
    public IntegerProperty getXProperty() { return x; }
    public int getX() { return x.getValue(); }
    public IntegerProperty getYProperty() { return y; }
    public int getY() { return y.getValue(); }
    public void setX(int pos) { x.setValue(pos); }
    public void setY(int pos) { y.setValue(pos); }
    public void setLevel(int level) { this.level.set(level); }
    public IntegerProperty levelProperty() { return level; }
    public int getVitesse() { return this.stats[2]; }
    public void setStats(int[] stats) { this.stats = stats; }
    public void setStatPoint(int stat_point) { this.stat_point = stat_point; }
    public int getHealth() { return health.get(); }
    public int getMaxHealth() { return maxHealth; }
    public IntegerProperty healthProperty() { return health; }
    public void setHealth(int value) { this.health.set(value); }
    public IntegerProperty expProperty() { return exp; }
    public IntegerProperty expToNextLevelProperty() { return expToNextLevel; }
    public int getExp() { return exp.get(); }
    public int getExpToNextLevel() { return expToNextLevel.get(); }
    public Environment getEnv() { return env; }
    public void setMaxHealth(int statHealth) { this.maxHealth = statHealth * 3; }

    public ItemInterface getHoldingItem() { return holdingItem; }
    public void setHoldingItem(ItemInterface holdingItem) { this.holdingItem = holdingItem; }

    /** Ajoute de l'expérience et gère le niveau */
    public void gainExp(int amount) {
        exp.set(exp.get() + amount);
        while (exp.get() >= expToNextLevel.get()) {
            exp.set(exp.get() - expToNextLevel.get());
            levelUp();
        }
    }

    private void levelUp() {
        setLevel(getLevel() + 1);
        expToNextLevel.set(expToNextLevel.get() + 5);
        stat_point += 5;
    }

    public LinkedHashMap<ItemInterface, Integer> getInventory() {
        return inventory;
    }

    /** Ajoute un item à l'inventaire */
    public void addToInventory(ItemInterface item) {
        System.out.println("------ Add to Inventory ------");
        if (!inventory.containsKey(item)) {
            if (item.getWeight() + this.pods <= maxPods) {
                inventory.put(item, 1);
            } else {
                System.out.println("The inventory is full or too much pods");
            }
        } else {
            inventory.put(item, inventory.get(item) + 1);
        }
    }

    /** Enlève un item de l'inventaire */
    public void removeFromInventory(ItemInterface item) {
        System.out.println("------ Remove from Inventory ------");
        if (inventory.containsKey(item)) {
            if (inventory.get(item) > 1) {
                inventory.put(item, inventory.get(item) - 1);
            } else {
                inventory.remove(item);
            }
            System.out.println("Item " + item.getName() + " removed from inventory\n");
        } else {
            System.out.println("Item not found in inventory\n");
        }
    }

    /** Retourne la clé (item) à l'index donné dans l'inventaire */
    public ItemInterface findKey(LinkedHashMap<ItemInterface, Integer> inventory, int index) {
        if (index < 0 || index >= inventory.size()) return null;
        int i = 0;
        for (ItemInterface key : inventory.keySet()) {
            if (i == index) return key;
            i++;
        }
        return null;
    }
}

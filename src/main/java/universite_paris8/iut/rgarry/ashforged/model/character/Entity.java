// src/main/java/universite_paris8/iut/rgarry/ashforged/model/character/Entity.java
package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;

public abstract class Entity {
    private static final double GRAVITY = 0.5;
    protected double velocityY;
    protected String id;
    protected String name;
    protected IntegerProperty level;
    protected int[] stats;
    protected ItemInterface[] items;
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
        this.stat_point += 5 * (level + 1);
        this.items = new ItemInterface[48];
        this.pods = 0;
        this.maxPods = 10 * stats[1];
        this.maxHealth = 3 * stats[0];
        this.health.set(this.maxHealth);
        this.direction = 'i';
    }

    public void applyGravity(Environment environment) {
        velocityY += GRAVITY;
        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            if (direction > 0) { // Vers le bas
                if (!environment.getField().checkCollision(getX(), getY() + 1) &&
                        !environment.getField().checkCollision(getX() + 31, getY() + 1) &&
                        !environment.getField().checkCollision(getX(), getY() + 32) &&
                        !environment.getField().checkCollision(getX() + 31, getY() + 32)) {
                    setY(getY() + 1);
                } else {
                    velocityY = 0;
                    break;
                }
            } else if (direction < 0) { // Vers le haut
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
    public abstract void seDeplacer();

    public void vaADroite() { this.direction = 'd'; }
    public void vaAGauche() { this.direction = 'g'; }
    public void resteImobile() { this.direction = 'i'; }

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
    }
    public ItemInterface[] getInventory() {
        System.out.println("------ Inventory ------");
        int i = 0;
        boolean empty = true;
        while (i < items.length) {
            if (items[i] != null) {
                System.out.println(items[i].getName());
                empty = false;
            }
            i++;
        }
        if (empty) {
            System.out.println("The inventory is empty\n");
        }
        System.out.println("------ End of Inventory ------");
        return items;
    }
    public void addToInventory(ItemInterface item) {
        System.out.println("------ Add to Inventory ------");
        int i = 0;
        boolean add = false;
        if (item.getWeight() + this.pods <= maxPods) {
            while (i < items.length && !add) {
                if (items[i] == null) {
                    items[i] = item;
                    add = true;
                }
                i++;
            }
        } else {
            System.out.println("The inventory is full or too much pods");
        }
    }
    public void removeFromInventory(ItemInterface item) {
        System.out.println("------ Remove from Inventory ------");
        boolean removed = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(item.getId())) {
                System.out.println("Item " + items[i].getName() + " removed from inventory\n");
                items[i] = null;
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Item not found in inventory\n");
        }
    }
}
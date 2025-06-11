package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.*;

public class Character implements Entity {
    private double velocityY;
    private String id;
    private String name;
    private IntegerProperty level;
    private int[] stats;
    private LinkedHashMap<ItemInterface, Integer> inventory = new LinkedHashMap<>();
    private int pods;
    private int maxPods;
    private IntegerProperty health = new SimpleIntegerProperty();
    private int maxHealth;
    private IntegerProperty exp = new SimpleIntegerProperty(0);
    private IntegerProperty expToNextLevel = new SimpleIntegerProperty(5);

    private Environment env;

    // Exemple de statistiques: {hp: 5, str: 5, spd: 5, ...}

    private int stat_point;
    private IntegerProperty x, y;

    private char direction;

    private static int compter = 0;

    public Character(String name, int level, int[] stats, int x, int y, Environment env) {
        this.velocityY = 0;
        this.id = "#" + compter++;
        this.name = name;
        this.level = new SimpleIntegerProperty(level);
        this.stats = stats;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.stat_point += 5 * (level + 1);

        this.inventory = new LinkedHashMap<ItemInterface, Integer>();
        inventory.put(ItemStock.Weapon.bomb, 1);
        inventory.put(ItemStock.Usuable.string, 4);
        inventory.put(ItemStock.Weapon.stone_axe, 1);
        inventory.put(ItemStock.Weapon.steel_pickaxe, 1);
        inventory.put(ItemStock.Usuable.golden_piece, 15);

        this.pods = 0;
        this.maxPods = 10 * stats[1];
        this.maxHealth = 3 * stats[0];
        this.health.set(this.maxHealth);
        this.direction = 'i';
    }

    public void vaADroite() {
        this.direction = 'd';
    }

    public void vaAGauche() {
        this.direction = 'g';
    }

    public void seDeplacer() {
        int newX = getX();
        if (direction == 'd') {
            newX += getVitesse();
            if (!env.checkCollision(newX+31,getY()) && !env.checkCollision(newX+31,getY()+31)) { //TODO vérif collision
                setX(newX);
            } else {
                //TODO : calculer la position qui le colle au bloc
            }
        } else if (direction == 'g') {
            newX -= getVitesse();
            if (!env.checkCollision(newX,getY()) && !env.checkCollision(newX,getY()+31)) { //TODO vérif collision
                setX(newX);
            }
        }

    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /***
     * Permet de retourner l'identifiant du personnage jouable ou des mobs et des NPC.
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /***
     * Permet de retourner le nom du personnage jouable ou des mobs et des NPC.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /***
     * Permet de retourner le niveau du joueur et des personnages.
     *
     * @return
     */
    public int getLevel() {
        return level.get();
    }

    /***
     * Permet de retourner l'ensemble des stats du joueur et des personnages.
     *
     * @return
     */
    public int[] getStats() {
        return stats;
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    /***
     * Permet d'afficher la position en "x" du joueur et des personnages.
     *
     * @return
     */
    public int getX() {
        return x.getValue();
    }


    public IntegerProperty getYProperty() {
        return y;
    }

    /***
     * Permet de retourner la position en "y" du joueur et des personnages.
     *
     * @return
     */
    public int getY() {
        return y.getValue();
    }

    /***
     * Permet de modifier ma position en "x" du joueur et des personnages.
     *
     * @param pos
     */
    @Override
    public void setX(int pos) {
        x.setValue(pos);
    }

    /***
     * Permet de modifier ma position en "y" du joueur et des personnages.
     *
     * @param pos
     */
    @Override
    public void setY(int pos) {
        y.setValue(pos);
    }

    /***
     * Permet de modifier le niveau du joueur et des personnages.
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level.set(level);
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    /***
     * Permet de retourner la vitesse du joueur et des personages.
     *
     * @return
     */
    public int getVitesse() {
        return this.stats[2];
    }

    /***
     * Permet de modifier les statistiques actuelles du joueur et des personnages.
     * Cette fonction est appelé lorque le joueur monte de niveau.
     *
     * @param stats
     */
    public void setStats(int[] stats) {
        this.stats = stats;
    }

    /***
     * Permet de modifier les statistiques stocker qui pourront être attribuer au joueur et aux personnages.
     *
     * @param stat_point
     */
    public void setStatPoint(int stat_point) {
        this.stat_point = stat_point;
    }

    public int getHealth() {
        return health.get();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public IntegerProperty healthProperty() {
        return health;
    }

    public void setHealth(int value) {
        this.health.set(value);
    }

    public IntegerProperty expProperty() {
        return exp;
    }

    public IntegerProperty expToNextLevelProperty() {
        return expToNextLevel;
    }

    public int getExp() {
        return exp.get();
    }

    public int getExpToNextLevel() {
        return expToNextLevel.get();
    }

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


    /***
     *  Permet d'afficher l'ensemble des items présents dans l'inventaire du joueur.
     */
    public LinkedHashMap<ItemInterface, Integer> getInventory() {return inventory;}


    /***
     * Permet d'ajouter l'item voulu à l'inventaire s'il y a une place de libre
     * ou que le poids max de la charge du personnage n'est pas dépassé.
     *
     * @param item
     */
    public void addToInventory(ItemInterface item) {
        System.out.println("------ Add to Inventory ------");
        int i = 0;
        boolean add = false;

        if (!inventory.containsKey(item)) {
            if (item.getWeight() + this.pods <= maxPods) {
                inventory.put(item, 0);
            } else {
                System.out.println("The inventory is full or too much pods");
            }
        }
        else inventory.put(item, inventory.get(item) + 1); // Incrémente la valeur associée à "item" de 1


    }


    /***
     * Permet d'enlever un item présent dans l'inventaire.
     *
     * @param item
     */
    public void removeFromInventory(ItemInterface item) {
        System.out.println("------ Remove from Inventory ------");
        boolean removed = false;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.containsKey(item)) {
                System.out.println("Item " + inventory.get(item) + " removed from inventory\n");
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("Item not found in inventory\n");
        }
    }

    public ItemInterface findKey(LinkedHashMap<ItemInterface, Integer> inventory, int index) {
        if (index < 0 || index >= inventory.size()) {
            return null;  // index out of range
        }
        int i = 0;
        for (ItemInterface key : inventory.keySet()) {
            if (i == index) {
                return key;  // found the key at 'index'
            }
            i++;
        }
        return null;  // if index is invalid (shouldn't happen because of check)
    }

}

package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

public class Personnage {
    private int velocity = 1;
    private String id;
    private String name;
    private int level;
    private int[] stats;
    private ItemInterface[] items;
    private int pods;
    private int maxPods;

    // Exemple de statistiques: {hp: 5, str: 5, spd: 5, ...}

    private int stat_point;
    private IntegerProperty x, y;
    private static int compter = 0;

    public Personnage(String name, int level, int[] stats, int x, int y) {
        this.id = "#" + compter++;
        this.name = name;
        this.level = level;
        this.stats = stats;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.stat_point += 5*(level+1);
        this.items = new ItemInterface[30];
        this.pods = pods;
        this.maxPods =10*stats[1];
    }

    /***
     * Permet de gérer la direction du déplacement du joueur.
     *
     * @param direction
     * @param maxX
     * @param maxY
     */
    public void deplacer(char direction, int maxX, int maxY) {
        int newX = getX();
        int newY = getY();
        int vitesse = getVitesse();

        switch (direction) {
            case 'u':
                newY = Math.max(0, getY() - vitesse);
                break;
            case 'd':
                newY = Math.min(maxY, getY() + vitesse);
                break;
            case 'l':
                newX = Math.max(0, getX() - vitesse);
                break;
            case 'r':
                newX = Math.min(maxX, getX() + vitesse);
                break;
        }
        setX(newX);
        setY(newY);
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
        return level;
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
    public void setX(int pos) {
        x.setValue(pos);
    }

    /***
     * Permet de modifier ma position en "y" du joueur et des personnages.
     *
     * @param pos
     */
    public void setY(int pos) {
        y.setValue(pos);
    }

    /***
     * Permet de modifier le niveau du joueur et des personnages.
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
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


    /***
     *  Permet d'afficher l'ensemble des items présents dans l'inventaire du joueur.
     */
    public void getInventory() {
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
    }

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

        if(item.getWeight() + this.pods <= maxPods) {
            while (i < items.length && !add) {
                if (items[i] == null) {
                    items[i] = item;
                    add = true;
                }
                i++;
            }
        }
        else {
            System.out.println("The inventory is full or too much pods");
        }
    }


    /***
     * Permet d'enlever un item présent dans l'inventaire.
     *
     * @param item
     */
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
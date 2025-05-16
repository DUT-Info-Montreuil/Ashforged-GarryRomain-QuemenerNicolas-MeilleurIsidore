package universite_paris8.iut.rgarry.ashforged.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.Field;
import universite_paris8.iut.rgarry.ashforged.Item.Item;

import java.util.Objects;

public class Personnage {
    private int velocity = 1;
    private String id;
    private String name;
    private int level;
    private int[] stats;
    private Item[] items;
    private int pods;
    private int maxPods;

    // Exemple de statistiques: {hp: 5, str: 5, spd: 5,

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
        this.items = new Item[30];
        this.pods = pods;
        this.maxPods =10*stats[1];
    }

//    public void deplacement(char direction) {
//        switch (direction) {
//            case 'l':
//                break;
//            case 'r':
//                break;
//            case 'u':
//                break;
//            case 'd':
//                break;
//        }
//    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int[] getStats() {
        return stats;
    }

    public int getStatPoint() {
        return stat_point;
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public int getX() {
        return x.getValue();
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public int getY() {
        return y.getValue();
    }

    public void setX(int pos) {
        x.setValue(pos);
    }

    public void setY(int pos) {
        y.setValue(pos);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public void setStatPoint(int stat_point) {
        this.stat_point = stat_point;
    }

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

    public void addToInventory(Item item) {
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

    public void removeFromInventory(Item item) {
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
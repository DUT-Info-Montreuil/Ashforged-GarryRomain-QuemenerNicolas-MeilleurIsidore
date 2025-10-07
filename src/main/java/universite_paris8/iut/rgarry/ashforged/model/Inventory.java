package universite_paris8.iut.rgarry.ashforged.model;

import javafx.scene.image.Image;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.Usuable;

import java.util.LinkedHashMap;

public class Inventory {
    private LinkedHashMap<ItemInterface, Integer> inventory;
    private int pods;
    private int maxPods;


    public Inventory(){
        inventory = new LinkedHashMap<ItemInterface, Integer>();
        maxPods = 0;
        pods = 0;
    }

    public LinkedHashMap<ItemInterface, Integer> getInventory() {
        return inventory;
    }

    /** Ajoute un item à l'inventaire */
    public void addToInventory(ItemInterface item) {
        System.out.println("------ Add to Inventory ------");
        if (!inventory.containsKey(item)) {
            if (item.getWeight() + pods <= maxPods) {
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
    public ItemInterface findKey(int index) {
        if (index < 0 || index >= this.getInventory().size()) return null;
        int i = 0;
        for (ItemInterface key : this.getInventory().keySet()) {
            if (i == index) return key;
            i++;
        }
        return null;
    }

    public void setMaxPods(int maxPods) {maxPods = maxPods;}

}

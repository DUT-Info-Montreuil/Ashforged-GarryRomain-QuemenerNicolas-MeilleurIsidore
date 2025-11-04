package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

import java.util.HashMap;

public abstract class Item {
    private int id;
    private String name;
    private String imagePath;

    public Item(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getImagePath(){
        return this.imagePath;
    }

    public abstract int getWeight();

    public abstract int getPower();

    public abstract int getDurability();

    public abstract boolean isBreakable();

    public abstract HashMap<Usuables, Integer> getComponents();

}

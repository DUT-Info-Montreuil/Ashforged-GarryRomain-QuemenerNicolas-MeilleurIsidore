package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Item;

import java.util.HashMap;

public class Consumables extends Item {
    private int weight;
    private int power;
    private int durability;
    private boolean breakable;
    private HashMap<Usuable, Integer> components;



    public Consumables(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath);
        this.weight = weight;
        this.power = power;
        this.durability = durability;
        this.breakable = breakable;
        this.components = new HashMap<Usuable, Integer>();
    }


    public int getWeight() {
        return weight;
    }

    public int getPower() {
        return power;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public HashMap<Usuable, Integer> getComponents() {
        return components;
    }

}

package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

import java.util.HashMap;

public class Consumable extends Item {
    private int weight;
    private int power;
    private int durability;
    private boolean breakable;
    private HashMap<Usuables, Integer> components;



    public Consumable(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuables, Integer> components) {
        super(id, name, imagePath);
        this.weight = weight;
        this.power = power;
        this.durability = durability;
        this.breakable = breakable;
        this.components = new HashMap<Usuables, Integer>();
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

    public HashMap<Usuables, Integer> getComponents() {
        return components;
    }

}
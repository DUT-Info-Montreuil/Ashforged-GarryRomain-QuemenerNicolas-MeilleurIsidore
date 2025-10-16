package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import java.util.HashMap;

public class Weapons extends Item {
    private String name;
    private int weight;
    private int power;
    private int durability;
    private boolean breakable;
    private int id;
    private String imagePath;
    private HashMap<Usuable, Integer> components;

    public Weapons(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath);
        this.weight = weight;
        this.power = power;
        this.durability = durability;
        this.breakable = breakable;
        this.components = new HashMap<Usuable, Integer>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImagePath() {
        return this.imagePath;
    }
}

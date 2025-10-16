package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import java.util.HashMap;

public class HandWeapons extends Weapons {
    public HandWeapons(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath, weight, power, durability, breakable, components);
    }
}


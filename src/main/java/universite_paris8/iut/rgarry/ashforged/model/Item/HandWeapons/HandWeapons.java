package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Weapons;

import java.util.HashMap;

public abstract class HandWeapons extends Weapons {
    public HandWeapons(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuables, Integer> components) {
        super(id, name, imagePath, weight, power, durability, breakable, components);
    }

    @Override
    public abstract void agir();
}


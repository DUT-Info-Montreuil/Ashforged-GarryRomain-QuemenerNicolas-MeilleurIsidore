package universite_paris8.iut.rgarry.ashforged.model.Item.RangedWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Weapons;

import java.util.HashMap;

public abstract class RangedWeapons extends Weapons {
    public RangedWeapons(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath, weight, power, durability, breakable, components);
    }

    @Override
    public abstract void agir();
}

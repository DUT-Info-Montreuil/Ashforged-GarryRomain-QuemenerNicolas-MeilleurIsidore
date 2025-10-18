package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import java.util.HashMap;

public class RangedWeapons extends Weapons{
    public RangedWeapons(int id, String name, String imagePath, int weight, int power, int durability, boolean breakable, HashMap<Usuable, Integer> components) {
        super(id, name, imagePath, weight, power, durability, breakable, components);
    }

    public void createBow(){
        HandWeapons bow = new HandWeapons(Usuable.bow.getId(), Usuable.bow.getName(),
                Usuable.bow.getImagePath(), Usuable.bow.getWeight(), Usuable.bow.getPower(),
                Usuable.bow.getDurability(), Usuable.bow.isBreakable(), Usuable.bow.getComponents());
    }
}

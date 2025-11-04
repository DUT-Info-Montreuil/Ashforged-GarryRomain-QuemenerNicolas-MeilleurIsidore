package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Pickaxe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenPickaxe extends HandWeapons {
    public WoodenPickaxe() {
        super(Usuables.wooden_pickaxe.getId(),
              Usuables.wooden_pickaxe.getName(),
              Usuables.wooden_pickaxe.getImagePath(),
              Usuables.wooden_pickaxe.getWeight(),
              Usuables.wooden_pickaxe.getPower(),
              Usuables.wooden_pickaxe.getDurability(),
              Usuables.wooden_pickaxe.isBreakable(),
              Usuables.wooden_pickaxe.getComponents());
    }

    @Override
    public void agir() {

    }
}

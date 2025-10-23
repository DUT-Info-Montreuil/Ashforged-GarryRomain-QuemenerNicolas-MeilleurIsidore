package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Pickaxe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenPickaxe extends HandWeapons {
    public WoodenPickaxe() {
        super(Usuable.wooden_pickaxe.getId(),
              Usuable.wooden_pickaxe.getName(),
              Usuable.wooden_pickaxe.getImagePath(),
              Usuable.wooden_pickaxe.getWeight(),
              Usuable.wooden_pickaxe.getPower(),
              Usuable.wooden_pickaxe.getDurability(),
              Usuable.wooden_pickaxe.isBreakable(),
              Usuable.wooden_pickaxe.getComponents());
    }

    @Override
    public void agir() {

    }
}

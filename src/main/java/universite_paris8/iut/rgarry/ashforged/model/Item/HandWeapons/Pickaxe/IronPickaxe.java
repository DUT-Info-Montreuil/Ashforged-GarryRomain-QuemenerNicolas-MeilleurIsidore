package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Pickaxe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronPickaxe extends HandWeapons {
    public IronPickaxe() {
        super(Usuables.iron_pickaxe.getId(),
              Usuables.iron_pickaxe.getName(),
              Usuables.iron_pickaxe.getImagePath(),
              Usuables.iron_pickaxe.getWeight(),
              Usuables.iron_pickaxe.getPower(),
              Usuables.iron_pickaxe.getDurability(),
              Usuables.iron_pickaxe.isBreakable(),
              Usuables.iron_pickaxe.getComponents());
    }

    public void  agir(){}
}

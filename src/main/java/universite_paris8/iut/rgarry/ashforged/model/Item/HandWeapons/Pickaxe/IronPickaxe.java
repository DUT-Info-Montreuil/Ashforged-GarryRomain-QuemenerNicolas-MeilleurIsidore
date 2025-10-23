package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Pickaxe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronPickaxe extends HandWeapons {
    public IronPickaxe() {
        super(Usuable.iron_pickaxe.getId(),
              Usuable.iron_pickaxe.getName(),
              Usuable.iron_pickaxe.getImagePath(),
              Usuable.iron_pickaxe.getWeight(),
              Usuable.iron_pickaxe.getPower(),
              Usuable.iron_pickaxe.getDurability(),
              Usuable.iron_pickaxe.isBreakable(),
              Usuable.iron_pickaxe.getComponents());
    }

    public void  agir(){}
}

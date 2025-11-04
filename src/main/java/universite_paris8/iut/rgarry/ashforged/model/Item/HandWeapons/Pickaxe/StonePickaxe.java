package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Pickaxe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StonePickaxe extends HandWeapons {
    public StonePickaxe() {
        super(Usuables.stone_pickaxe.getId(),
              Usuables.stone_pickaxe.getName(),
              Usuables.stone_pickaxe.getImagePath(),
              Usuables.stone_pickaxe.getWeight(),
              Usuables.stone_pickaxe.getPower(),
              Usuables.stone_pickaxe.getDurability(),
              Usuables.stone_pickaxe.isBreakable(),
              Usuables.stone_pickaxe.getComponents());
    }

    @Override
    public void agir() {

    }
}

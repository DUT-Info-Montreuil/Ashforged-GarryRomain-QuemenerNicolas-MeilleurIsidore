package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneSword extends HandWeapons {
    public StoneSword() {
        super(Usuables.stone_sword.getId(),
              Usuables.stone_sword.getName(),
              Usuables.stone_sword.getImagePath(),
              Usuables.stone_sword.getWeight(),
              Usuables.stone_sword.getPower(),
              Usuables.stone_sword.getDurability(),
              Usuables.stone_sword.isBreakable(),
              Usuables.stone_sword.getComponents());
    }

    @Override
    public void agir() {

    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneSword extends HandWeapons {
    public StoneSword() {
        super(Usuable.stone_sword.getId(),
              Usuable.stone_sword.getName(),
              Usuable.stone_sword.getImagePath(),
              Usuable.stone_sword.getWeight(),
              Usuable.stone_sword.getPower(),
              Usuable.stone_sword.getDurability(),
              Usuable.stone_sword.isBreakable(),
              Usuable.stone_sword.getComponents());
    }

    @Override
    public void agir() {

    }
}

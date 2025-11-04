package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Knife;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneKnife extends HandWeapons {
    public StoneKnife() {
        super(Usuables.stone_knife.getId(),
              Usuables.stone_knife.getName(),
              Usuables.stone_knife.getImagePath(),
              Usuables.stone_knife.getWeight(),
              Usuables.stone_knife.getPower(),
              Usuables.stone_knife.getDurability(),
              Usuables.stone_knife.isBreakable(),
              Usuables.stone_knife.getComponents());
    }

    @Override
    public void agir() {

    }
}

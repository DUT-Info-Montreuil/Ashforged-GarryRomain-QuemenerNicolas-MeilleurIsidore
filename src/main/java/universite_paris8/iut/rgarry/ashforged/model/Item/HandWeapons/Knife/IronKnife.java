package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Knife;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronKnife extends HandWeapons {
    public IronKnife() {
        super(Usuables.iron_knife.getId(),
              Usuables.iron_knife.getName(),
              Usuables.iron_knife.getImagePath(),
              Usuables.iron_knife.getWeight(),
              Usuables.iron_knife.getPower(),
              Usuables.iron_knife.getDurability(),
              Usuables.iron_knife.isBreakable(),
              Usuables.iron_knife.getComponents());
    }

    @Override
    public void agir() {

    }
}

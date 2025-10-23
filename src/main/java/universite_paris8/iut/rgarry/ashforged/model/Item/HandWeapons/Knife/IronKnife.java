package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Knife;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronKnife extends HandWeapons {
    public IronKnife() {
        super(Usuable.iron_knife.getId(),
              Usuable.iron_knife.getName(),
              Usuable.iron_knife.getImagePath(),
              Usuable.iron_knife.getWeight(),
              Usuable.iron_knife.getPower(),
              Usuable.iron_knife.getDurability(),
              Usuable.iron_knife.isBreakable(),
              Usuable.iron_knife.getComponents());
    }

    @Override
    public void agir() {

    }
}

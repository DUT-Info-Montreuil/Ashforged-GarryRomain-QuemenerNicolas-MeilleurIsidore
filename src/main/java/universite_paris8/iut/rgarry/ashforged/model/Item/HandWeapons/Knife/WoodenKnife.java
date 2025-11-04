package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Knife;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenKnife extends HandWeapons {
    public WoodenKnife() {
        super(Usuables.wooden_knife.getId(),
              Usuables.wooden_knife.getName(),
              Usuables.wooden_knife.getImagePath(),
              Usuables.wooden_knife.getWeight(),
              Usuables.wooden_knife.getPower(),
              Usuables.wooden_knife.getDurability(),
              Usuables.wooden_knife.isBreakable(),
              Usuables.wooden_knife.getComponents());
    }

    @Override
    public void agir() {

    }
}

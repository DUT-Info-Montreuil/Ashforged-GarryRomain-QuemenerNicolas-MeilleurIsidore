package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sword;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenSword extends HandWeapons {
    public WoodenSword() {
        super(Usuables.wooden_sword.getId(),
              Usuables.wooden_sword.getName(),
              Usuables.wooden_sword.getImagePath(),
              Usuables.wooden_sword.getWeight(),
              Usuables.wooden_sword.getPower(),
              Usuables.wooden_sword.getDurability(),
              Usuables.wooden_sword.isBreakable(),
              Usuables.wooden_sword.getComponents());
    }

    @Override
    public void agir() {

    }
}

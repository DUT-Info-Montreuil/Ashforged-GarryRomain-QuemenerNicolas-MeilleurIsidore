package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sword;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenSword extends HandWeapons {
    public WoodenSword() {
        super(Usuable.wooden_sword.getId(),
              Usuable.wooden_sword.getName(),
              Usuable.wooden_sword.getImagePath(),
              Usuable.wooden_sword.getWeight(),
              Usuable.wooden_sword.getPower(),
              Usuable.wooden_sword.getDurability(),
              Usuable.wooden_sword.isBreakable(),
              Usuable.wooden_sword.getComponents());
    }

    @Override
    public void agir() {

    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sword;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronSword extends HandWeapons {
    public IronSword() {
        super(Usuables.iron_sword.getId(),
              Usuables.iron_sword.getName(),
              Usuables.iron_sword.getImagePath(),
              Usuables.iron_sword.getWeight(),
              Usuables.iron_sword.getPower(),
              Usuables.iron_sword.getDurability(),
              Usuables.iron_sword.isBreakable(),
              Usuables.iron_sword.getComponents());
    }

    @Override
    public void agir() {

    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sword;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronSword extends HandWeapons {
    public IronSword() {
        super(Usuable.iron_sword.getId(),
              Usuable.iron_sword.getName(),
              Usuable.iron_sword.getImagePath(),
              Usuable.iron_sword.getWeight(),
              Usuable.iron_sword.getPower(),
              Usuable.iron_sword.getDurability(),
              Usuable.iron_sword.isBreakable(),
              Usuable.iron_sword.getComponents());
    }

    @Override
    public void agir() {

    }
}

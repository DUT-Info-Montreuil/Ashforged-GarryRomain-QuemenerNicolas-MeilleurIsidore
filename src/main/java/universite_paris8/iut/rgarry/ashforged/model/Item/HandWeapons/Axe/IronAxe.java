package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Axe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronAxe extends HandWeapons {
    public IronAxe() {
        super(Usuable.iron_axe.getId(),
              Usuable.iron_axe.getName(),
              Usuable.iron_axe.getImagePath(),
              Usuable.iron_axe.getWeight(),
              Usuable.iron_axe.getPower(),
              Usuable.iron_axe.getDurability(),
              Usuable.iron_axe.isBreakable(),
              Usuable.iron_axe.getComponents());
    }

    @Override
    public void agir() {

    }
}

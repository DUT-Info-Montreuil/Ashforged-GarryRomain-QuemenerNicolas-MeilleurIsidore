package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Axe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronAxe extends HandWeapons {
    public IronAxe() {
        super(Usuables.iron_axe.getId(),
              Usuables.iron_axe.getName(),
              Usuables.iron_axe.getImagePath(),
              Usuables.iron_axe.getWeight(),
              Usuables.iron_axe.getPower(),
              Usuables.iron_axe.getDurability(),
              Usuables.iron_axe.isBreakable(),
              Usuables.iron_axe.getComponents());
    }

    @Override
    public void agir() {

    }
}

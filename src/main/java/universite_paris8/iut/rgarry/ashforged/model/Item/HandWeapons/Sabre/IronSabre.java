package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sabre;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronSabre extends HandWeapons {
    public IronSabre() {
        super(Usuables.iron_sabre.getId(),
              Usuables.iron_sabre.getName(),
              Usuables.iron_sabre.getImagePath(),
              Usuables.iron_sabre.getWeight(),
              Usuables.iron_sabre.getPower(),
              Usuables.iron_sabre.getDurability(),
              Usuables.iron_sabre.isBreakable(),
              Usuables.iron_sabre.getComponents());
    }

    @Override
    public void agir() {

    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sabre;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class IronSabre extends HandWeapons {
    public IronSabre() {
        super(Usuable.iron_sabre.getId(),
              Usuable.iron_sabre.getName(),
              Usuable.iron_sabre.getImagePath(),
              Usuable.iron_sabre.getWeight(),
              Usuable.iron_sabre.getPower(),
              Usuable.iron_sabre.getDurability(),
              Usuable.iron_sabre.isBreakable(),
              Usuable.iron_sabre.getComponents());
    }

    @Override
    public void agir() {

    }
}

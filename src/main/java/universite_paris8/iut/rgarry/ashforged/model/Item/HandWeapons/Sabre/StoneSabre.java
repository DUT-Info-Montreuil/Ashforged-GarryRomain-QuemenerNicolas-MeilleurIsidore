package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sabre;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneSabre extends HandWeapons {
    public StoneSabre() {
        super(Usuable.stone_sabre.getId(),
              Usuable.stone_sabre.getName(),
              Usuable.stone_sabre.getImagePath(),
              Usuable.stone_sabre.getWeight(),
              Usuable.stone_sabre.getPower(),
              Usuable.stone_sabre.getDurability(),
              Usuable.stone_sabre.isBreakable(),
              Usuable.stone_sabre.getComponents());
    }

    @Override
    public void agir() {

    }
}

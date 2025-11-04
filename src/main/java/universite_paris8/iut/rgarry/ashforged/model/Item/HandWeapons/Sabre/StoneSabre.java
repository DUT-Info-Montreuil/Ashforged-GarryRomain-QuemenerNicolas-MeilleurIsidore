package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sabre;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneSabre extends HandWeapons {
    public StoneSabre() {
        super(Usuables.stone_sabre.getId(),
              Usuables.stone_sabre.getName(),
              Usuables.stone_sabre.getImagePath(),
              Usuables.stone_sabre.getWeight(),
              Usuables.stone_sabre.getPower(),
              Usuables.stone_sabre.getDurability(),
              Usuables.stone_sabre.isBreakable(),
              Usuables.stone_sabre.getComponents());
    }

    @Override
    public void agir() {

    }
}

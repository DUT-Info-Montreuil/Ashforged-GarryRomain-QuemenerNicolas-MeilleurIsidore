package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Sabre;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenSabre extends HandWeapons {
    public WoodenSabre() {
        super(Usuables.wooden_sabre.getId(),
              Usuables.wooden_sabre.getName(),
              Usuables.wooden_sabre.getImagePath(),
              Usuables.wooden_sabre.getWeight(),
              Usuables.wooden_sabre.getPower(),
              Usuables.wooden_sabre.getDurability(),
              Usuables.wooden_sabre.isBreakable(),
              Usuables.wooden_sabre.getComponents());
    }

    @Override
    public void agir() {

    }
}

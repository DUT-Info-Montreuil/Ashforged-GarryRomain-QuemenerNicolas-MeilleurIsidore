package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Axe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenAxe extends HandWeapons {
    public WoodenAxe() {
        super(Usuables.wooden_axe.getId(),
              Usuables.wooden_axe.getName(),
              Usuables.wooden_axe.getImagePath(),
              Usuables.wooden_axe.getWeight(),
              Usuables.wooden_axe.getPower(),
              Usuables.wooden_axe.getDurability(),
              Usuables.wooden_axe.isBreakable(),
              Usuables.wooden_axe.getComponents());
    }

    @Override
    public void agir() {

    }
}

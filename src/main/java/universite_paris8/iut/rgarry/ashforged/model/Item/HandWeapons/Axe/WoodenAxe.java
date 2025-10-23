package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Axe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class WoodenAxe extends HandWeapons {
    public WoodenAxe() {
        super(Usuable.wooden_axe.getId(),
              Usuable.wooden_axe.getName(),
              Usuable.wooden_axe.getImagePath(),
              Usuable.wooden_axe.getWeight(),
              Usuable.wooden_axe.getPower(),
              Usuable.wooden_axe.getDurability(),
              Usuable.wooden_axe.isBreakable(),
              Usuable.wooden_axe.getComponents());
    }

    @Override
    public void agir() {

    }
}

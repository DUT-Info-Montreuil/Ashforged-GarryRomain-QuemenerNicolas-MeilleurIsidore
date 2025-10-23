package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Axe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneAxe extends HandWeapons {
    public StoneAxe() {
        super(Usuable.stone_axe.getId(),
              Usuable.stone_axe.getName(),
              Usuable.stone_axe.getImagePath(),
              Usuable.stone_axe.getWeight(),
              Usuable.stone_axe.getPower(),
              Usuable.stone_axe.getDurability(),
              Usuable.stone_axe.isBreakable(),
              Usuable.stone_axe.getComponents());
    }

    @Override
    public void agir() {

    }
}

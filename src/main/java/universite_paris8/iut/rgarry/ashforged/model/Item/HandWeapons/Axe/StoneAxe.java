package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.Axe;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;
import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons.HandWeapons;

public class StoneAxe extends HandWeapons {
    public StoneAxe() {
        super(Usuables.stone_axe.getId(),
              Usuables.stone_axe.getName(),
              Usuables.stone_axe.getImagePath(),
              Usuables.stone_axe.getWeight(),
              Usuables.stone_axe.getPower(),
              Usuables.stone_axe.getDurability(),
              Usuables.stone_axe.isBreakable(),
              Usuables.stone_axe.getComponents());
    }

    @Override
    public void agir() {

    }
}

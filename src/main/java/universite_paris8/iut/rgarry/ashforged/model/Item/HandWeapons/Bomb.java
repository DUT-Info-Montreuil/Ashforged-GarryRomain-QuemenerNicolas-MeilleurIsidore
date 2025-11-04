package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;


public class Bomb extends HandWeapons {
    public Bomb() {
        super(Usuables.bomb.getId(),
              Usuables.bomb.getName(),
              Usuables.bomb.getImagePath(),
              Usuables.bomb.getWeight(),
              Usuables.bomb.getPower(),
              Usuables.bomb.getDurability(),
              Usuables.bomb.isBreakable(),
              Usuables.bomb.getComponents());
    }

    @Override
    public void agir() {

    }
}

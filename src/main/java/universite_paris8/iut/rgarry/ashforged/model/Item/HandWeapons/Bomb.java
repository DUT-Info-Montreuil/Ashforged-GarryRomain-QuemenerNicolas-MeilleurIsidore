package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

public class Bomb extends HandWeapons {
    public Bomb() {
        super(Usuable.bomb.getId(),
              Usuable.bomb.getName(),
              Usuable.bomb.getImagePath(),
              Usuable.bomb.getWeight(),
              Usuable.bomb.getPower(),
              Usuable.bomb.getDurability(),
              Usuable.bomb.isBreakable(),
              Usuable.bomb.getComponents());
    }
}

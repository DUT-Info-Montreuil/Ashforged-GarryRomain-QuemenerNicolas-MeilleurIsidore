package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;


public class Enma extends HandWeapons {
    public Enma() {
        super(Usuable.enma.getId(),
              Usuable.enma.getName(),
              Usuable.enma.getImagePath(),
              Usuable.enma.getWeight(),
              Usuable.enma.getPower(),
              Usuable.enma.getDurability(),
              Usuable.enma.isBreakable(),
              Usuable.enma.getComponents());
    }

    @Override
    public void agir() {

    }
}

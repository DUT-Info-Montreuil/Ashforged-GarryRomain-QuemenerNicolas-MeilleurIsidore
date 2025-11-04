package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;


public class Enma extends HandWeapons {
    public Enma() {
        super(Usuables.enma.getId(),
              Usuables.enma.getName(),
              Usuables.enma.getImagePath(),
              Usuables.enma.getWeight(),
              Usuables.enma.getPower(),
              Usuables.enma.getDurability(),
              Usuables.enma.isBreakable(),
              Usuables.enma.getComponents());
    }

    @Override
    public void agir() {

    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Ground extends Consumables {
    public Ground() {
        super(Usuable.ground.getId(),
              Usuable.ground.getName(),
              Usuable.ground.getImagePath(),
              Usuable.ground.getWeight(),
              Usuable.ground.getPower(),
              Usuable.ground.getDurability(),
              Usuable.ground.isBreakable(),
              Usuable.ground.getComponents());
    }
}

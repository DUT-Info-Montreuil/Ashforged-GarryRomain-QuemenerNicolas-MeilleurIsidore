package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Ground extends Consumable {
    public Ground() {
        super(Usuables.ground.getId(),
              Usuables.ground.getName(),
              Usuables.ground.getImagePath(),
              Usuables.ground.getWeight(),
              Usuables.ground.getPower(),
              Usuables.ground.getDurability(),
              Usuables.ground.isBreakable(),
              Usuables.ground.getComponents());
    }
}

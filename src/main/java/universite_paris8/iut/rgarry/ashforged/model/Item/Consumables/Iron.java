package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumables.Consumables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Iron extends Consumables {
    public Iron() {
        super(Usuable.iron.getId(),
              Usuable.iron.getName(),
              Usuable.iron.getImagePath(),
              Usuable.iron.getWeight(),
              Usuable.iron.getPower(),
              Usuable.iron.getDurability(),
              Usuable.iron.isBreakable(),
              Usuable.iron.getComponents());
    }
}

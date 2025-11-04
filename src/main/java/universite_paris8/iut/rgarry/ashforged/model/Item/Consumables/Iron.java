package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Iron extends Consumable {
    public Iron() {
        super(Usuables.iron.getId(),
              Usuables.iron.getName(),
              Usuables.iron.getImagePath(),
              Usuables.iron.getWeight(),
              Usuables.iron.getPower(),
              Usuables.iron.getDurability(),
              Usuables.iron.isBreakable(),
              Usuables.iron.getComponents());
    }
}

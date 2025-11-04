package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Wood extends Consumable {
    public Wood() {
        super(Usuables.wood.getId(),
              Usuables.wood.getName(),
              Usuables.wood.getImagePath(),
              Usuables.wood.getWeight(),
              Usuables.wood.getPower(),
              Usuables.wood.getDurability(),
              Usuables.wood.isBreakable(),
              Usuables.wood.getComponents());
    }
}

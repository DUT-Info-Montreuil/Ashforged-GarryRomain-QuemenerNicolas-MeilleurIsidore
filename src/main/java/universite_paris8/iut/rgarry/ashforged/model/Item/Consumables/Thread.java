package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Thread extends Consumable {
    public Thread() {
        super(Usuables.string.getId(),
              Usuables.string.getName(),
              Usuables.string.getImagePath(),
              Usuables.string.getWeight(),
              Usuables.string.getPower(),
              Usuables.string.getDurability(),
              Usuables.string.isBreakable(),
              Usuables.string.getComponents());
    }
}

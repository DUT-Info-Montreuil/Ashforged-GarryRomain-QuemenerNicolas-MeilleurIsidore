package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Stone extends Consumable {
    public Stone() {
        super(Usuables.stone.getId(),
              Usuables.stone.getName(),
              Usuables.stone.getImagePath(),
              Usuables.stone.getWeight(),
              Usuables.stone.getPower(),
              Usuables.stone.getDurability(),
              Usuables.stone.isBreakable(),
              Usuables.stone.getComponents());
    }
}

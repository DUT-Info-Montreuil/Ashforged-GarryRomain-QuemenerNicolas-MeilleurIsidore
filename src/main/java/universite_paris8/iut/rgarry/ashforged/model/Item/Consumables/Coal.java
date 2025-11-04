package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Coal extends Consumable {
    public Coal() {
        super(Usuables.coal.getId(),
              Usuables.coal.getName(),
              Usuables.coal.getImagePath(),
              Usuables.coal.getWeight(),
              Usuables.coal.getPower(),
              Usuables.coal.getDurability(),
              Usuables.coal.isBreakable(),
              Usuables.coal.getComponents());
    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumables.Consumables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Coal extends Consumables {
    public Coal() {
        super(Usuable.coal.getId(),
              Usuable.coal.getName(),
              Usuable.coal.getImagePath(),
              Usuable.coal.getWeight(),
              Usuable.coal.getPower(),
              Usuable.coal.getDurability(),
              Usuable.coal.isBreakable(),
              Usuable.coal.getComponents());
    }
}

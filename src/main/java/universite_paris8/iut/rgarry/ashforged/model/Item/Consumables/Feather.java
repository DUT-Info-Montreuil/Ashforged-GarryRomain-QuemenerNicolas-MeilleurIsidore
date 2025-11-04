package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Feather extends Consumable {
    public Feather() {
        super(Usuables.feather.getId(),
              Usuables.feather.getName(),
              Usuables.feather.getImagePath(),
              Usuables.feather.getWeight(),
              Usuables.feather.getPower(),
              Usuables.feather.getDurability(),
              Usuables.feather.isBreakable(),
              Usuables.feather.getComponents());
    }
}

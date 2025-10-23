package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumables.Consumables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Alluminium extends Consumables {
    public Alluminium() {
        super(Usuable.alluminium.getId(),
              Usuable.alluminium.getName(),
              Usuable.alluminium.getImagePath(),
              Usuable.alluminium.getWeight(),
              Usuable.alluminium.getPower(),
              Usuable.alluminium.getDurability(),
              Usuable.alluminium.isBreakable(),
              Usuable.alluminium.getComponents());
    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Alluminium extends Consumable {
    public Alluminium() {
        super(Usuables.alluminium.getId(),
              Usuables.alluminium.getName(),
              Usuables.alluminium.getImagePath(),
              Usuables.alluminium.getWeight(),
              Usuables.alluminium.getPower(),
              Usuables.alluminium.getDurability(),
              Usuables.alluminium.isBreakable(),
              Usuables.alluminium.getComponents());
    }
}

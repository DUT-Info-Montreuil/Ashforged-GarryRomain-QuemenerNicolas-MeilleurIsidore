package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class CraftTable extends Consumable {
    public CraftTable() {
        super(Usuables.craft_table.getId(),
              Usuables.craft_table.getName(),
              Usuables.craft_table.getImagePath(),
              Usuables.craft_table.getWeight(),
              Usuables.craft_table.getPower(),
              Usuables.craft_table.getDurability(),
              Usuables.craft_table.isBreakable(),
              Usuables.craft_table.getComponents());
    }
}

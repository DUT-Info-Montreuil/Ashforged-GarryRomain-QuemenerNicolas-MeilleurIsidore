package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class CraftTable extends Consumables {
    public CraftTable() {
        super(Usuable.craft_table.getId(),
              Usuable.craft_table.getName(),
              Usuable.craft_table.getImagePath(),
              Usuable.craft_table.getWeight(),
              Usuable.craft_table.getPower(),
              Usuable.craft_table.getDurability(),
              Usuable.craft_table.isBreakable(),
              Usuable.craft_table.getComponents());
    }
}

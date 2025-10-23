package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Stone extends Consumables {
    public Stone() {
        super(Usuable.stone.getId(),
              Usuable.stone.getName(),
              Usuable.stone.getImagePath(),
              Usuable.stone.getWeight(),
              Usuable.stone.getPower(),
              Usuable.stone.getDurability(),
              Usuable.stone.isBreakable(),
              Usuable.stone.getComponents());
    }
}

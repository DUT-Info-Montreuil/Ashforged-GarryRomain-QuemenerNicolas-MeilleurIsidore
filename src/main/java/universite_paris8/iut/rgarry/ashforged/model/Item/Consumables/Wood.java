package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Wood extends Consumables {
    public Wood() {
        super(Usuable.wood.getId(),
              Usuable.wood.getName(),
              Usuable.wood.getImagePath(),
              Usuable.wood.getWeight(),
              Usuable.wood.getPower(),
              Usuable.wood.getDurability(),
              Usuable.wood.isBreakable(),
              Usuable.wood.getComponents());
    }
}

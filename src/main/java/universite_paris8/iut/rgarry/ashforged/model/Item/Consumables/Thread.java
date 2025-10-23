package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Thread extends Consumables {
    public Thread() {
        super(Usuable.string.getId(),
              Usuable.string.getName(),
              Usuable.string.getImagePath(),
              Usuable.string.getWeight(),
              Usuable.string.getPower(),
              Usuable.string.getDurability(),
              Usuable.string.isBreakable(),
              Usuable.string.getComponents());
    }
}

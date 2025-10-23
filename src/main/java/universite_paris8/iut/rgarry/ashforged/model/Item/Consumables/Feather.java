package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Feather extends Consumables {
    public Feather() {
        super(Usuable.feather.getId(),
              Usuable.feather.getName(),
              Usuable.feather.getImagePath(),
              Usuable.feather.getWeight(),
              Usuable.feather.getPower(),
              Usuable.feather.getDurability(),
              Usuable.feather.isBreakable(),
              Usuable.feather.getComponents());
    }
}

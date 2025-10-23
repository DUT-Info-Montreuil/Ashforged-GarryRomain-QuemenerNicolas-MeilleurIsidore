package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumables.Consumables;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class CanonPowder extends Consumables {
    public CanonPowder() {
        super(Usuable.canon_powder.getId(),
              Usuable.canon_powder.getName(),
              Usuable.canon_powder.getImagePath(),
              Usuable.canon_powder.getWeight(),
              Usuable.canon_powder.getPower(),
              Usuable.canon_powder.getDurability(),
              Usuable.canon_powder.isBreakable(),
              Usuable.canon_powder.getComponents());
    }
}

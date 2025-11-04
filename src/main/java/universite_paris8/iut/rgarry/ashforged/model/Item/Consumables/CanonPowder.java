package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class CanonPowder extends Consumable {
    public CanonPowder() {
        super(Usuables.canon_powder.getId(),
              Usuables.canon_powder.getName(),
              Usuables.canon_powder.getImagePath(),
              Usuables.canon_powder.getWeight(),
              Usuables.canon_powder.getPower(),
              Usuables.canon_powder.getDurability(),
              Usuables.canon_powder.isBreakable(),
              Usuables.canon_powder.getComponents());
    }
}

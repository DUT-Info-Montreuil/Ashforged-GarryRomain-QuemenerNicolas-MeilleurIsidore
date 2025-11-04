package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class PerlimpinpinPowder extends Consumable {
    public PerlimpinpinPowder() {
        super(Usuables.perlimpinpin_powder.getId(),
              Usuables.perlimpinpin_powder.getName(),
              Usuables.perlimpinpin_powder.getImagePath(),
              Usuables.perlimpinpin_powder.getWeight(),
              Usuables.perlimpinpin_powder.getPower(),
              Usuables.perlimpinpin_powder.getDurability(),
              Usuables.perlimpinpin_powder.isBreakable(),
              Usuables.perlimpinpin_powder.getComponents());
    }
}

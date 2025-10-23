package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class PerlimpinpinPowder extends Consumables {
    public PerlimpinpinPowder() {
        super(Usuable.perlimpinpin_powder.getId(),
              Usuable.perlimpinpin_powder.getName(),
              Usuable.perlimpinpin_powder.getImagePath(),
              Usuable.perlimpinpin_powder.getWeight(),
              Usuable.perlimpinpin_powder.getPower(),
              Usuable.perlimpinpin_powder.getDurability(),
              Usuable.perlimpinpin_powder.isBreakable(),
              Usuable.perlimpinpin_powder.getComponents());
    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.RangedWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;


public class Bow extends RangedWeapons {
    public Bow(){
        super(Usuables.bow.getId(), Usuables.bow.getName(),
                Usuables.bow.getImagePath(), Usuables.bow.getWeight(), Usuables.bow.getPower(),
                Usuables.bow.getDurability(), Usuables.bow.isBreakable(), Usuables.bow.getComponents());
    }

    @Override
    public void agir() {

    }
}

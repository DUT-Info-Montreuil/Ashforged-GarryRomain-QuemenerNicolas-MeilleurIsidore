package universite_paris8.iut.rgarry.ashforged.model.Item.RangedWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

import java.util.HashMap;


public class Bow extends RangedWeapons {
    public Bow(){
        super(Usuable.bow.getId(), Usuable.bow.getName(),
                Usuable.bow.getImagePath(), Usuable.bow.getWeight(), Usuable.bow.getPower(),
                Usuable.bow.getDurability(), Usuable.bow.isBreakable(), Usuable.bow.getComponents());
    }

    @Override
    public void agir() {

    }
}

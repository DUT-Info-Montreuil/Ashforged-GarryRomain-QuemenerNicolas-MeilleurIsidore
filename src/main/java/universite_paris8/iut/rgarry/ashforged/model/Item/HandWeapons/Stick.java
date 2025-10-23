package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Stick extends HandWeapons{
    public Stick() {
        super(Usuable.stick.getId(), Usuable.stick.getName(),
                Usuable.stick.getImagePath(), Usuable.stick.getWeight(), Usuable.stick.getPower(),
                Usuable.stick.getDurability(), Usuable.stick.isBreakable(), Usuable.stick.getComponents());
    }


    public void agir() {

    }
}

package universite_paris8.iut.rgarry.ashforged.model.Item.HandWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Stick extends HandWeapons{
    public Stick() {
        super(Usuables.stick.getId(), Usuables.stick.getName(),
                Usuables.stick.getImagePath(), Usuables.stick.getWeight(), Usuables.stick.getPower(),
                Usuables.stick.getDurability(), Usuables.stick.isBreakable(), Usuables.stick.getComponents());
    }


    public void agir() {

    }
}

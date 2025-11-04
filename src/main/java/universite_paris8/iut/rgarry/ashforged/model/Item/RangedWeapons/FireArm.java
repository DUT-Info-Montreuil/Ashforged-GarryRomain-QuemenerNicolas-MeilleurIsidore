package universite_paris8.iut.rgarry.ashforged.model.Item.RangedWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class FireArm extends RangedWeapons{
    public FireArm() {
        super(Usuables.firearm.getId(), Usuables.firearm.getName(),
                Usuables.firearm.getImagePath(), Usuables.firearm.getWeight(), Usuables.firearm.getPower(),
                Usuables.firearm.getDurability(), Usuables.firearm.isBreakable(), Usuables.firearm.getComponents());
    }

    @Override
    public void agir() {
    }

}

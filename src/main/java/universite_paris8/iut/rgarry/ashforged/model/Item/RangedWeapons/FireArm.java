package universite_paris8.iut.rgarry.ashforged.model.Item.RangedWeapons;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class FireArm extends RangedWeapons{
    public FireArm() {
        super(Usuable.firearm.getId(), Usuable.firearm.getName(),
                Usuable.firearm.getImagePath(), Usuable.firearm.getWeight(), Usuable.firearm.getPower(),
                Usuable.firearm.getDurability(), Usuable.firearm.isBreakable(), Usuable.firearm.getComponents());
    }

    @Override
    public void agir() {

    }
}

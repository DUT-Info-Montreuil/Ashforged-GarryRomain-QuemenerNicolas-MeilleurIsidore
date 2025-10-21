package universite_paris8.iut.rgarry.ashforged.model.Item;

public class IronKnife extends HandWeapons {
    public IronKnife() {
        super(Usuable.iron_knife.getId(),
              Usuable.iron_knife.getName(),
              Usuable.iron_knife.getImagePath(),
              Usuable.iron_knife.getWeight(),
              Usuable.iron_knife.getPower(),
              Usuable.iron_knife.getDurability(),
              Usuable.iron_knife.isBreakable(),
              Usuable.iron_knife.getComponents());
    }
}

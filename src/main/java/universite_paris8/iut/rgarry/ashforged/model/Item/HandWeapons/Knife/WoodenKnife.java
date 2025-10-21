package universite_paris8.iut.rgarry.ashforged.model.Item;

public class WoodenKnife extends HandWeapons {
    public WoodenKnife() {
        super(Usuable.wooden_knife.getId(),
              Usuable.wooden_knife.getName(),
              Usuable.wooden_knife.getImagePath(),
              Usuable.wooden_knife.getWeight(),
              Usuable.wooden_knife.getPower(),
              Usuable.wooden_knife.getDurability(),
              Usuable.wooden_knife.isBreakable(),
              Usuable.wooden_knife.getComponents());
    }
}

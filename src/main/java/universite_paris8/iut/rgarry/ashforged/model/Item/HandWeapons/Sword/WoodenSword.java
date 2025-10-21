package universite_paris8.iut.rgarry.ashforged.model.Item;

public class WoodenSword extends HandWeapons {
    public WoodenSword() {
        super(Usuable.wooden_sword.getId(),
              Usuable.wooden_sword.getName(),
              Usuable.wooden_sword.getImagePath(),
              Usuable.wooden_sword.getWeight(),
              Usuable.wooden_sword.getPower(),
              Usuable.wooden_sword.getDurability(),
              Usuable.wooden_sword.isBreakable(),
              Usuable.wooden_sword.getComponents());
    }
}

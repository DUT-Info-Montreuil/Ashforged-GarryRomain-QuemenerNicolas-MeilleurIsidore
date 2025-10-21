package universite_paris8.iut.rgarry.ashforged.model.Item;

public class WoodenPickaxe extends HandWeapons {
    public WoodenPickaxe() {
        super(Usuable.wooden_pickaxe.getId(),
              Usuable.wooden_pickaxe.getName(),
              Usuable.wooden_pickaxe.getImagePath(),
              Usuable.wooden_pickaxe.getWeight(),
              Usuable.wooden_pickaxe.getPower(),
              Usuable.wooden_pickaxe.getDurability(),
              Usuable.wooden_pickaxe.isBreakable(),
              Usuable.wooden_pickaxe.getComponents());
    }
}

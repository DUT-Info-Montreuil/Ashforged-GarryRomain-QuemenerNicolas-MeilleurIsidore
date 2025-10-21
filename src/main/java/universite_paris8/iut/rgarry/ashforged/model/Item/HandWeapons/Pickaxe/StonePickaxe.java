package universite_paris8.iut.rgarry.ashforged.model.Item;

public class StonePickaxe extends HandWeapons {
    public StonePickaxe() {
        super(Usuable.stone_pickaxe.getId(),
              Usuable.stone_pickaxe.getName(),
              Usuable.stone_pickaxe.getImagePath(),
              Usuable.stone_pickaxe.getWeight(),
              Usuable.stone_pickaxe.getPower(),
              Usuable.stone_pickaxe.getDurability(),
              Usuable.stone_pickaxe.isBreakable(),
              Usuable.stone_pickaxe.getComponents());
    }
}

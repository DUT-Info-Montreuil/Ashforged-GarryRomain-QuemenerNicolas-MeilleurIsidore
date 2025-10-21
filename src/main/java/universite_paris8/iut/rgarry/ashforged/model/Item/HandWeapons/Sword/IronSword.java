package universite_paris8.iut.rgarry.ashforged.model.Item;

public class IronSword extends HandWeapons {
    public IronSword() {
        super(Usuable.iron_sword.getId(),
              Usuable.iron_sword.getName(),
              Usuable.iron_sword.getImagePath(),
              Usuable.iron_sword.getWeight(),
              Usuable.iron_sword.getPower(),
              Usuable.iron_sword.getDurability(),
              Usuable.iron_sword.isBreakable(),
              Usuable.iron_sword.getComponents());
    }
}

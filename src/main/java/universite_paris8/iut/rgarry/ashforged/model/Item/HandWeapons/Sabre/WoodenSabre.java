package universite_paris8.iut.rgarry.ashforged.model.Item;

public class WoodenSabre extends HandWeapons {
    public WoodenSabre() {
        super(Usuable.wooden_sabre.getId(),
              Usuable.wooden_sabre.getName(),
              Usuable.wooden_sabre.getImagePath(),
              Usuable.wooden_sabre.getWeight(),
              Usuable.wooden_sabre.getPower(),
              Usuable.wooden_sabre.getDurability(),
              Usuable.wooden_sabre.isBreakable(),
              Usuable.wooden_sabre.getComponents());
    }
}

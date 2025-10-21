package universite_paris8.iut.rgarry.ashforged.model.Item;

public class StoneKnife extends HandWeapons {
    public StoneKnife() {
        super(Usuable.stone_knife.getId(),
              Usuable.stone_knife.getName(),
              Usuable.stone_knife.getImagePath(),
              Usuable.stone_knife.getWeight(),
              Usuable.stone_knife.getPower(),
              Usuable.stone_knife.getDurability(),
              Usuable.stone_knife.isBreakable(),
              Usuable.stone_knife.getComponents());
    }
}

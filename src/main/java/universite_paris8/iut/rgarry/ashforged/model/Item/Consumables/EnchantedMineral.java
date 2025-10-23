package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class EnchantedMineral extends Consumables {
    public EnchantedMineral() {
        super(Usuable.enchanted_mineral.getId(),
              Usuable.enchanted_mineral.getName(),
              Usuable.enchanted_mineral.getImagePath(),
              Usuable.enchanted_mineral.getWeight(),
              Usuable.enchanted_mineral.getPower(),
              Usuable.enchanted_mineral.getDurability(),
              Usuable.enchanted_mineral.isBreakable(),
              Usuable.enchanted_mineral.getComponents());
    }
}

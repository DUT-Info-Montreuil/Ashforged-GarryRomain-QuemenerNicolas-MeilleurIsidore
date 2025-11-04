package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class EnchantedMineral extends Consumable {
    public EnchantedMineral() {
        super(Usuables.enchanted_mineral.getId(),
              Usuables.enchanted_mineral.getName(),
              Usuables.enchanted_mineral.getImagePath(),
              Usuables.enchanted_mineral.getWeight(),
              Usuables.enchanted_mineral.getPower(),
              Usuables.enchanted_mineral.getDurability(),
              Usuables.enchanted_mineral.isBreakable(),
              Usuables.enchanted_mineral.getComponents());
    }
}

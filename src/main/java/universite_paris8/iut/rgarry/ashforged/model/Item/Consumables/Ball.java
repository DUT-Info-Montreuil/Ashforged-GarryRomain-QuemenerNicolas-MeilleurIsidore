package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class Ball extends Consumable {
    public Ball() {
        super(Usuables.ball.getId(),
              Usuables.ball.getName(),
              Usuables.ball.getImagePath(),
              Usuables.ball.getWeight(),
              Usuables.ball.getPower(),
              Usuables.ball.getDurability(),
              Usuables.ball.isBreakable(),
              Usuables.ball.getComponents());
    }
}

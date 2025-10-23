package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;

import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class Ball extends Consumables {
    public Ball() {
        super(Usuable.ball.getId(),
              Usuable.ball.getName(),
              Usuable.ball.getImagePath(),
              Usuable.ball.getWeight(),
              Usuable.ball.getPower(),
              Usuable.ball.getDurability(),
              Usuable.ball.isBreakable(),
              Usuable.ball.getComponents());
    }
}

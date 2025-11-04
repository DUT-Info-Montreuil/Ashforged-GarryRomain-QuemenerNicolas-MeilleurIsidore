package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;


import universite_paris8.iut.rgarry.ashforged.model.Item.Consumable;
import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuables;

public class GoldenPiece extends Consumable {
    public GoldenPiece() {
        super(Usuables.golden_piece.getId(),
              Usuables.golden_piece.getName(),
              Usuables.golden_piece.getImagePath(),
              Usuables.golden_piece.getWeight(),
              Usuables.golden_piece.getPower(),
              Usuables.golden_piece.getDurability(),
              Usuables.golden_piece.isBreakable(),
              Usuables.golden_piece.getComponents());
    }
}

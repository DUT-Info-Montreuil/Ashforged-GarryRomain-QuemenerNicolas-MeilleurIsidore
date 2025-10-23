package universite_paris8.iut.rgarry.ashforged.model.Item.Consumables;


import universite_paris8.iut.rgarry.ashforged.model.Item.Enum.Usuable;

public class GoldenPiece extends Consumables {
    public GoldenPiece() {
        super(Usuable.golden_piece.getId(),
              Usuable.golden_piece.getName(),
              Usuable.golden_piece.getImagePath(),
              Usuable.golden_piece.getWeight(),
              Usuable.golden_piece.getPower(),
              Usuable.golden_piece.getDurability(),
              Usuable.golden_piece.isBreakable(),
              Usuable.golden_piece.getComponents());
    }
}

package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

public class NpcController {

    private final TilePane tilePane;
    private final Pane paneNpc;

    private static Npc paolo;
    private static Npc branda;
    private static Npc terry;
    private static Npc salome;

    public NpcController(TilePane tilePane, Pane paneNpc) {
        this.tilePane = tilePane;
        this.paneNpc = paneNpc;
    }

}

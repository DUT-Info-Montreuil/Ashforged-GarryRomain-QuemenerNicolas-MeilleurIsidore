package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

public class NpcController {

    private final TilePane tilePane;
    private final Pane paneNpc;

    private static Npc paolo;
    private static Npc branda;
    private static Npc terry;
    private static Npc salome;

    /**
     * Constructeur du contrôleur de NPC.
     * @param tilePane TilePane pour afficher la liste ou grille des NPCs
     * @param paneNpc Pane dédié à l'affichage des NPCs dans la scène
     */
    public NpcController(TilePane tilePane, Pane paneNpc) {
        this.tilePane = tilePane;
        this.paneNpc = paneNpc;
    }

    // TODO : ajouter méthodes pour initialiser, afficher et gérer les NPCs
}

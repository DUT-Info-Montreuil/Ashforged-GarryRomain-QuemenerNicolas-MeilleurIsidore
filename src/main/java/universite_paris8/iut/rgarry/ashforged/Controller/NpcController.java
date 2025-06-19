package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

/**
 * Controller for managing NPC (Non-Playable Characters) and their UI-related behaviors.
 *
 * This class is responsible for displaying NPCs and interacting with their associated
 * items or dialogues within the game interface.
 */
public class NpcController {

    /** The UI container that displays NPC items or components. */
    private final TilePane tilePane;

    /** The pane representing the NPC's dedicated interaction area. */
    private final Pane paneNpc;

    // Static references to NPCs used in the game. These could represent fixed roles.
    private static Npc paolo;
    private static Npc branda;
    private static Npc terry;
    private static Npc salome;

    /**
     * Constructs the NPC controller with specified UI containers.
     *
     * @param tilePane the tile pane used to display NPC-related UI elements.
     * @param paneNpc the pane used for focused NPC interactions.
     */
    public NpcController(TilePane tilePane, Pane paneNpc) {
        this.tilePane = tilePane;
        this.paneNpc = paneNpc;
    }

    // Future functionality may include:
    // - Initializing NPCs
    // - Displaying NPC inventories or dialogues
    // - Handling interaction events with specific NPCs
}

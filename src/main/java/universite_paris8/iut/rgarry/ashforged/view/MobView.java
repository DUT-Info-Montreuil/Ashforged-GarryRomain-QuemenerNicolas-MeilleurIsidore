package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

import java.util.ArrayList;
import java.util.List;

// Class to manage the graphical representation of mobs and NPCs in the game environment
public class MobView {
    private Pane characterPane; // Pane to hold character and mob image views
    private ImageView mob; // ImageView for a mob (not used in current implementation)
    private List<Npc> npcs = new ArrayList<>(); // List of NPCs in the environment
    private List<Mobs> mobs = new ArrayList<>(); // List of mobs in the environment
    private Environment environment; // Game environment containing mobs and NPCs

    // Image for mobs loaded from resources
    private Image mobImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/mob.png").toExternalForm());

    /**
     * Constructor for MobView, initializes the view with the game environment and pane.
     * Loads NPC images and binds their positions to the pane.
     *
     * @param environment The game environment containing mobs and NPCs.
     * @param characterPane The pane where NPC and mob images are displayed.
     * @throws NullPointerException if environment or characterPane is null, or if image resources are not found.
     */
    public MobView(Environment environment, Pane characterPane) {
        this.environment = environment; // Store the environment reference
        this.characterPane = characterPane; // Store the pane reference

        // Retrieve mobs and NPCs from the environment
        mobs = environment.getMobs();
        npcs = environment.getNpcs();

        // Load images for NPCs and inventory
        Image inventoryImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/caseInventaire.png").toExternalForm());
        Image paoloImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftPaolo.png").toExternalForm());
        Image salomeImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftSalome.png").toExternalForm());
        Image terryImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftTerry.png").toExternalForm());
        Image brandaImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftTerry.png").toExternalForm());

        // Iterate through NPCs to create and bind their image views
        for (Npc npc : npcs) {
            ImageView npcView;
            // Assign specific images and positions based on NPC name
            if (npc.getName().equals("Paolo")) {
                npcView = new ImageView(paoloImage);
            } else if (npc.getName().equals("Branda")) {
                npcView = new ImageView(brandaImage);
            } else if (npc.getName().equals("Terry")) {
                npcView = new ImageView(terryImage);
            } else if (npc.getName().equals("Salome")) {
                npcView = new ImageView(salomeImage);
            } else {
                npcView = new ImageView(inventoryImage); // Default image for unrecognized NPCs
            }
            // Bind NPC image position to its properties
            npcView.layoutXProperty().bind(npc.getXProperty().asObject());
            npcView.layoutYProperty().bind(npc.getYProperty().asObject());
            characterPane.getChildren().add(npcView); // Add NPC image to the pane
        }
    }

    /**
     * Sets up the view for mobs by creating and binding their image views to the pane.
     * Only creates an ImageView for mobs that do not already have an associated node.
     *
     */
    public void setMobsView() {
        mobs = environment.getMobs(); // Retrieve the latest mob list from the environment
        // Iterate through mobs to create and bind their image views
        for (Mobs m : mobs) {
            if (m.getNode() == null) { // Check if the mob lacks a graphical node
                ImageView mobView = new ImageView(mobImage); // Create new ImageView for the mob
                mobView.layoutXProperty().bind(m.getXProperty().asObject()); // Bind X position
                mobView.layoutYProperty().bind(m.getYProperty().asObject()); // Bind Y position
                characterPane.getChildren().add(mobView); // Add mob image to the pane
                m.setNode(mobView); // Associate the ImageView with the mob
            }
        }
    }

    /**
     * Retrieves the list of NPCs in the environment.
     *
     * @return A list of Npc objects.
     */
    public List<Npc> getNpcs() {
        return npcs; // Return the list of NPCs
    }

    /**
     * Retrieves the list of mobs in the environment.
     *
     * @return A list of Mobs objects.
     */
    public List<Mobs> getMobs() {
        return mobs; // Return the list of mobs
    }
}
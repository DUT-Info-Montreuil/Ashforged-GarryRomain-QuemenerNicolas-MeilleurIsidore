package universite_paris8.iut.rgarry.ashforged.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;
import universite_paris8.iut.rgarry.ashforged.model.character.Npc;

import java.util.ArrayList;
import java.util.List;

/**
 * The MobView class is responsible for visualizing NPCs and Mobs
 * within the game environment using JavaFX components.
 * It binds character positions to the GUI and displays corresponding images.
 */
public class MobView {
    private Pane paneperso; // JavaFX Pane where characters are displayed
    private ImageView mob; // Placeholder for mob ImageView (not used in current implementation)
    private List<Npc> npcs = new ArrayList<>(); // List of NPCs to render
    private List<Mobs> mobs = new ArrayList<>(); // List of mobs to render
    private Environment environment; // Game environment containing mobs and NPCs

    // Default image used for mobs
    public Image mobImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/mob.png").toExternalForm());

    /**
     * Constructor initializes the MobView with NPCs and places their corresponding images on the pane.
     * Specific characters (Paolo, Branda, Terry, Salome) are assigned unique images and initial positions.
     *
     * @param environment the game environment containing characters
     * @param paneperso   the pane on which characters are rendered
     */
    public MobView(Environment environment, Pane paneperso){
        this.environment = environment;
        this.paneperso = paneperso;

        mobs = environment.getMobs();
        npcs = environment.getNpcs();

        // Load images for NPCs
        Image inventoryImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/tiles/caseInventaire.png").toExternalForm());
        Image paoloImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftPaolo.png").toExternalForm());
        Image salomeImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftSalome.png").toExternalForm());
        Image terryImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftTerry.png").toExternalForm());
        Image brandaImage = new Image(getClass().getResource("/universite_paris8/iut/rgarry/ashforged/Image/PNJ/leftTerry.png").toExternalForm());

        // Assign image and position to each NPC based on name
        for (Npc npc : npcs) {
            ImageView npcView;
            if (npc.getName().equals("Paolo")) {
                npcView = new ImageView(paoloImage);
            } else {
                npcView = new ImageView(inventoryImage); // Default image
                if (npc.getName().equals("Branda")) {
                    npcView = new ImageView(brandaImage);
                    npc.setX(200);
                    npc.setY(200);
                } else if (npc.getName().equals("Terry")) {
                    npcView = new ImageView(terryImage);
                    npc.setX(300);
                    npc.setY(300);
                } else if (npc.getName().equals("Salome")) {
                    npcView = new ImageView(salomeImage);
                    npc.setX(400);
                    npc.setY(400);
                }
            }

            // Bind the NPC's position to the ImageView position in the pane
            npcView.layoutXProperty().bind(npc.getXProperty().asObject());
            npcView.layoutYProperty().bind(npc.getYProperty().asObject());

            // Add the NPC view to the pane
            paneperso.getChildren().add(npcView);
        }
    }

    /**
     * Displays mobs in the pane by creating ImageViews for each mob if not already displayed.
     * Binds each mob's coordinates to their respective ImageView.
     */
    public void setMobsView() {
        mobs = environment.getMobs();
        for (Mobs m : mobs) {
            if (m.getNode() == null) { // Only add if not already added
                ImageView mobView = new ImageView(mobImage);
                mobView.layoutXProperty().bind(m.getXProperty().asObject());
                mobView.layoutYProperty().bind(m.getYProperty().asObject());
                paneperso.getChildren().add(mobView);
                m.setNode(mobView); // Save reference to the node
            }
        }
    }

    /**
     * Returns the list of NPCs managed by this view.
     *
     * @return list of Npc objects
     */
    public List<Npc> getNpcs(){
        return npcs;
    }

    /**
     * Returns the list of mobs managed by this view.
     *
     * @return list of Mobs objects
     */
    public List<Mobs> getMobs(){
        return mobs;
    }
}

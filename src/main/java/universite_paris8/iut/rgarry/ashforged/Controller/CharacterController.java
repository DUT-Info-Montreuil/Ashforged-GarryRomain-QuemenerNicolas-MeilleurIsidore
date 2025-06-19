package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.KeyMapping;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class CharacterController {

    // Enumération simple pour représenter les directions (non utilisée dans ce code, mais prête pour extension)
    enum Direction {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private Character personnage;

    // Propriétés pour savoir si certaines touches sont pressées
    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private final BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    // Constante pour la force du saut (valeur négative vers le haut)
    private static final double JUMP_STRENGTH = -12;

    private final TilePane tilePane;
    private final Pane panePerso;

    // Constructeur : récupère les références aux conteneurs et au personnage contrôlé
    public CharacterController(TilePane tilePane, Pane panePerso, Character personnage) {
        this.tilePane = tilePane;
        this.panePerso = panePerso;
        this.personnage = personnage;
    }

    /**
     * Configure les événements clavier pour gérer les pressions/relais des touches.
     * @param pane Le panneau recevant les événements clavier
     */
    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyMapping.getKey("jump")) spacePressed.set(true);
            if (event.getCode() == KeyMapping.getKey("moveLeft")) qPressed.set(true);
            if (event.getCode() == KeyMapping.getKey("moveRight")) dPressed.set(true);
            if (event.getCode() == KeyMapping.getKey("down")) sPressed.set(true);

            changerDirectionPersonnage();
        });

        pane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyMapping.getKey("jump")) spacePressed.set(false);
            if (event.getCode() == KeyMapping.getKey("moveLeft")) qPressed.set(false);
            if (event.getCode() == KeyMapping.getKey("moveRight")) dPressed.set(false);
            if (event.getCode() == KeyMapping.getKey("down")) sPressed.set(false);

            changerDirectionPersonnage();
        });
    }

    /**
     * Modifie la direction du personnage en fonction des touches pressées.
     * Gère également le saut si la touche jump est pressée et que le personnage est au sol.
     */
    public void changerDirectionPersonnage() {
        if (this.isQPressed()) {
            personnage.vaAGauche();
        }
        if (this.isDPressed()) {
            personnage.vaADroite();
        }
        if (!this.isQPressed() && !this.isDPressed()) {
            personnage.resteImmobile();
        }

        // Vérifie si le personnage est "au sol" (collision en bas à gauche et à droite ou limite basse du terrain)
        int bottomY = personnage.getEnv().getField().getHeight() * 64 - 32;
        boolean onGround = (personnage.getEnv().checkCollision(personnage.getX(), personnage.getY() + 32) &&
                personnage.getEnv().checkCollision(personnage.getX() + 31, personnage.getY() + 32)) ||
                (personnage.getY() == bottomY);

        // Saut uniquement si la touche jump est pressée et que le personnage est au sol
        if (this.isSpacePressed() && onGround) {
            personnage.setVelocityY(JUMP_STRENGTH);
        }
    }

    // Getters pour l'état des touches
    public boolean isSpacePressed() {
        return spacePressed.get();
    }

    public boolean isQPressed() {
        return qPressed.get();
    }

    public boolean isSPressed() {
        return sPressed.get();
    }

    public boolean isDPressed() {
        return dPressed.get();
    }
}

package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import universite_paris8.iut.rgarry.ashforged.model.KeyMapping;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;

public class CharacterController {

    private static final double JUMP_STRENGTH = -12;

    private final Character personnage;

    private final BooleanProperty spacePressed = new SimpleBooleanProperty(false);
    private final BooleanProperty qPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty sPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty dPressed = new SimpleBooleanProperty(false);

    public CharacterController(Pane pane, Character personnage) {
        this.personnage = personnage;
        setupKeyHandlers(pane);
    }

    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyMapping.getKey("jump")) spacePressed.set(true);
            if (code == KeyMapping.getKey("moveLeft")) qPressed.set(true);
            if (code == KeyMapping.getKey("moveRight")) dPressed.set(true);
            if (code == KeyMapping.getKey("down")) sPressed.set(true);
            changerDirectionPersonnage();
        });

        pane.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            if (code == KeyMapping.getKey("jump")) spacePressed.set(false);
            if (code == KeyMapping.getKey("moveLeft")) qPressed.set(false);
            if (code == KeyMapping.getKey("moveRight")) dPressed.set(false);
            if (code == KeyMapping.getKey("down")) sPressed.set(false);
            changerDirectionPersonnage();
        });
    }

    /**
     * Met à jour la direction du personnage en fonction des touches pressées.
     * Gère aussi le saut si le personnage est au sol.
     */
    public void changerDirectionPersonnage() {
        if (isQPressed()) {
            personnage.moveLeft();
        }
        if (isDPressed()) {
            personnage.moveRight();
        }
        if (!isQPressed() && !isDPressed()) {
            personnage.stayIdle();
        }

        boolean onGround = isOnGround();
        if (isSpacePressed() && onGround) {
            personnage.setVelocityY(JUMP_STRENGTH);
        }
    }

    private boolean isOnGround() {
        int x = personnage.getX();
        int y = personnage.getY();
        int bottomY = personnage.getEnv().getField().getHeight() * 64 - 32;

        boolean collisionLeft = personnage.getEnv().checkCollision(x, y + 32);
        boolean collisionRight = personnage.getEnv().checkCollision(x + 31, y + 32);

        return (collisionLeft && collisionRight) || (y == bottomY);
    }

    // Getters pour les propriétés des touches
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

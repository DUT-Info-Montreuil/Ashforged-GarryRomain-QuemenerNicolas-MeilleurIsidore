package universite_paris8.iut.rgarry.ashforged.Controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Character;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;

import java.util.ArrayList;
import java.util.List;

public class CharacterController {
    enum Direction {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private static Character personnage;

    private final BooleanProperty spacePressed = new SimpleBooleanProperty();
    private final BooleanProperty qPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private double velocityY;
    private final double GRAVITY = 0.5;
    private final double JUMP_STRENGHT = -15;

    private final TilePane tilePane;
    private final Pane panePerso;

    static {
        personnage = new Character("Hero", 1, new int[]{1, 1, 10, 1}, 250, 300);
    }

    public CharacterController(TilePane tilePane, Pane panePerso) {
        this.tilePane = tilePane;
        this.panePerso = panePerso;
    }

    public static Character getPersonnage() {
        return personnage;
    }

    public void setupKeyHandlers(Pane pane) {
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(true);
                case Q -> qPressed.set(true);
                case S -> sPressed.set(true);
                case D -> dPressed.set(true);
            }
        });

        pane.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case SPACE -> spacePressed.set(false);
                case Q -> qPressed.set(false);
                case S -> sPressed.set(false);
                case D -> dPressed.set(false);
            }
        });
    }

    public boolean checkCollision(Entity entity, Environment environment, Environment.Direction direction) {
        int entityLeft = entity.getX() - 1;
        int entityRight = entity.getX() + 32 + 1;
        int entityTop = entity.getY() - 1;
        int entityBottom = entity.getY() + 32 + 1; // Décalage de 1 pixel vers le haut
        int entityBottomAdjusted = entityBottom - 1; // Ajustement pour gauche et droite

        int tileSize = 64;

        // Calcul des indices des tuiles dans la grille
        int leftTile = entityLeft / tileSize;
        int rightTile = (entityRight - 1) / tileSize;
        int topTile = entityTop / tileSize;
        int bottomTile = (entityBottom - 1) / tileSize;

        switch (direction) {
            case TOP -> {
                return topTile >= 0 && (environment.getField().block(leftTile, topTile) != 1 ||
                        environment.getField().block(rightTile, topTile) != 1);
            }
            case BOTTOM -> {
                return bottomTile < environment.getField().getHeight() &&
                        (environment.getField().block(leftTile, bottomTile) != 1 ||
                                environment.getField().block(rightTile, bottomTile) != 1);
            }
            case LEFT -> {
                return leftTile >= 0 && (environment.getField().block(leftTile, topTile) != 1 ||
                        environment.getField().block(leftTile, bottomTile-1) != 1);
            }
            case RIGHT -> {
                return rightTile < environment.getField().getWidth() &&
                        (environment.getField().block(rightTile, topTile) != 1 ||
                                environment.getField().block(rightTile, bottomTile-1) != 1);
            }
        }

        return false;
    }

    public void applyGravity(Environment environment) {
        velocityY += GRAVITY;
        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            if (direction > 0 && !checkCollision(personnage, environment, Environment.Direction.BOTTOM)) {
                personnage.setY(personnage.getY() + 2);
            } else if (direction < 0 && !checkCollision(personnage, environment, Environment.Direction.TOP)) {
                personnage.setY(personnage.getY() - 1);
            } else {
                velocityY = 0;
                break;
            }
        }
    }

    public void moveCharacter(Character personnage, Environment environment, char direction) {
        int step = personnage.getVitesse(); // Distance de déplacement par mouvement
        int newX = personnage.getX();
        int newY = personnage.getY();

        switch (direction) {
            case 'u': // Haut
                if (checkCollision(personnage, environment, Environment.Direction.BOTTOM)) {
                    velocityY = JUMP_STRENGHT; // Appliquer la force de saut
                }
                break;
            case 'd': // Bas
                if (!checkCollision(personnage, environment, Environment.Direction.BOTTOM)) {
                    newY += step;
                }
                break;
            case 'l': // Gauche
                if (!checkCollision(personnage, environment, Environment.Direction.LEFT)) {
                    newX -= step;
                }
                break;
            case 'r': // Droite
                if (!checkCollision(personnage, environment, Environment.Direction.RIGHT)) {
                    newX += step;
                }
                break;
        }

        personnage.setX(newX);
        personnage.setY(newY);
    }
    public boolean isSpacePressed() { return spacePressed.get(); }
    public boolean isQPressed()     { return qPressed.get(); }
    public boolean isSPressed()     { return sPressed.get(); }
    public boolean isDPressed()     { return dPressed.get(); }
}
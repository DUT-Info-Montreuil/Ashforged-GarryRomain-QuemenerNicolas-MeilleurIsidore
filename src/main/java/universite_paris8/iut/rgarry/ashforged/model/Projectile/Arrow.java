package universite_paris8.iut.rgarry.ashforged.model.Projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.NonePlayer;
import universite_paris8.iut.rgarry.ashforged.model.character.Ennemis;

/**
 * Represents an arrow projectile in the game.
 * Handles movement, gravity, collision detection and damage application.
 */
public class Arrow {
    private static final double GRAVITY = 0.1; // Gravity applied to the arrow's vertical speed
    private Environment environment;
    private int damageFleche = 10; // Base damage of the arrow

    // Position properties (for JavaFX binding)
    private IntegerProperty x;
    private IntegerProperty y;

    // Velocity components
    private double velocityX;
    private double velocityY;

    // State flags
    private boolean isActive;
    private boolean touchee = false; // Unused? Can be removed if unnecessary

    private ImageView arrowImage;

    /**
     * Constructs an arrow at the specified start coordinates within the given environment.
     *
     * @param startX starting X coordinate
     * @param startY starting Y coordinate
     * @param environment the game environment
     */
    public Arrow(double startX, double startY, Environment environment) {
        this.x = new SimpleIntegerProperty((int) startX);
        this.y = new SimpleIntegerProperty((int) startY);
        this.velocityX = 3;    // Initial horizontal speed
        this.velocityY = -5;   // Initial vertical speed (upwards)
        this.isActive = true;
        this.environment = environment;
    }

    /**
     * Updates the arrow state per frame: moves horizontally, applies gravity, and checks for attacks.
     */
    public void update() {
        if (!isActive) return;

        moveHorizontal();
        applyGravity();
        attackFleche();
    }

    /**
     * Moves the arrow horizontally if no collision.
     */
    private void moveHorizontal() {
        double newX = x.get() + velocityX;

        if (!environment.getField().checkCollision((int) newX, y.get())) {
            x.set((int) newX);
        } else {
            velocityX = 0;
            isActive = false; // Arrow stops moving on collision
        }
    }

    /**
     * Applies gravity to the arrow's vertical velocity and updates its vertical position accordingly.
     * Stops the arrow if it hits an obstacle.
     */
    private void applyGravity() {
        velocityY += GRAVITY;

        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            int nextY = y.get() + direction;
            if (!environment.getField().checkCollision(x.get(), nextY)) {
                y.set(nextY);
            } else {
                velocityY = 0;
                isActive = false;
                break;
            }
        }
    }

    /**
     * Checks if the arrow hits any Ennemis, applies damage, and deactivates the arrow on hit.
     */
    public void attackFleche() {
        for (NonePlayer NonePlayer : environment.getNonePlayer()) {
            if(NonePlayer instanceof Ennemis) {
                int dx = Math.abs(NonePlayer.getX() / 64 - this.getX() / 64);
                int dy = Math.abs(NonePlayer.getY() / 64 - this.getY() / 64);
                if (dx == 0 && dy == 0) {
                    System.out.println("Hit!");
                    int damage;
                    if (environment.getHero().getHoldingItem().getDamage() > 1)
                        damage = (int) (environment.getHero().getHoldingItem().getDamage() * 0.5 + ((double) damageFleche / 2));
                    else
                        damage = damageFleche / 2;

                    if(NonePlayer.health().getHealth() - damage <= 0) {
                        NonePlayer.setHealth(0);
                        System.out.println("You killed " + NonePlayer.getName() + " with an arrow!");
                        environment.getHero().gainExp(NonePlayer.getLevel());
                    } else {
                        NonePlayer.setHealth(NonePlayer.health().getHealth() - damage);
                        System.out.println("You dealt " + damage + " damage to " + NonePlayer.getName() + "!");
                    }
                    isActive = false;
                    break; // Arrow hits only one target
                }
            }
        }
    }

    // Getters and setters for position and active state

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }

    public boolean isActive() {
        return isActive;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setArrowImage(ImageView arrowImage) {
        this.arrowImage = arrowImage;
    }

    public ImageView getArrowImage() {
        return arrowImage;
    }
}

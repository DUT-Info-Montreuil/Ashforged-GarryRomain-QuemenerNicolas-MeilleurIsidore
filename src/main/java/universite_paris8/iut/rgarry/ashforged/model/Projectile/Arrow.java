package universite_paris8.iut.rgarry.ashforged.model.Projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rgarry.ashforged.model.Environment;

public class Arrow {
    // Constantes
    private static final double GRAVITY = 0.1;

    // Position
    private IntegerProperty x;
    private IntegerProperty y;

    // Vitesse
    private double velocityX;
    private double velocityY;

    // État
    private boolean isActive;

    private ImageView arrowImage;

    // Constructeur
    public Arrow(double startX, double startY) {
        this.x = new SimpleIntegerProperty((int) startX);
        this.y = new SimpleIntegerProperty((int) startY);
        this.velocityX = 3;
        this.velocityY = -5;
        this.isActive = true;
    }

    // Mise à jour par frame
    public void update(Environment environment) {
        if (!isActive) return;

        moveHorizontal(environment);
        applyGravity(environment);
    }

    private void moveHorizontal(Environment environment) {
        double newX = x.get() + velocityX;

        if (!environment.getField().checkCollision((int) newX, y.get())) {
            x.set((int) newX);
        } else {
            velocityX = 0;
            isActive = false;
        }
    }

    private void applyGravity(Environment environment) {
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

    // Getters
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

    // Setters
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
        this.arrowImage=arrowImage;
    }

    public ImageView getArrowImage() {
        return arrowImage;
    }
}

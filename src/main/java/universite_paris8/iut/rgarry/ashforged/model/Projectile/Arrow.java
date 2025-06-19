package universite_paris8.iut.rgarry.ashforged.model.Projectile;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.character.Entity;
import universite_paris8.iut.rgarry.ashforged.model.character.Mobs;

public class Arrow {
    // Constantes
    private static final double GRAVITY = 0.1;
    private Environment environment;
    private int damageFleche = 10;

    // Position
    private IntegerProperty x;
    private IntegerProperty y;

    // Vitesse
    private double velocityX;
    private double velocityY;

    // État
    private boolean isActive;
    private boolean touchee = false;

    private ImageView arrowImage;

    // Constructeur
    public Arrow(double startX, double startY, Environment environment) {
        this.x = new SimpleIntegerProperty((int) startX);
        this.y = new SimpleIntegerProperty((int) startY);
        this.velocityX = 3;
        this.velocityY = -5;
        this.isActive = true;
        this.environment = environment;
    }

    // Mise à jour par frame
    public void update(Environment environment) {
        if (!isActive) return;

        moveHorizontal(environment);
        applyGravity(environment);
        attackFleche();
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


    public void attackFleche() {
        if (touchee) {
            for (Entity entity : environment.getEntities()) {
                if(entity instanceof Mobs) {
                    int dx = Math.abs(entity.getX() / 64 - this.getX() / 64);
                    int dy = Math.abs(entity.getY() / 64 - this.getY() / 64);
                    if (dx == 2 && dy == 2) {
                        System.out.println("touche !");
                        int damage;
                        if (environment.getHero().getStats()[1] > 1) damage = (int) (environment.getHero().getStats()[1] * 0.5 + ((double) damageFleche / 2));
                        else damage = damageFleche / 2;
                        if(entity.getHealth() - damage <= 0) {
                            entity.setHealth(0);
                            System.out.println("Vous avez tué " + entity.getName() + " avec une fleche !");
                            System.out.println(entity.getLevel());
                            environment.getHero().gainExp(entity.getLevel());
                        } else {
                            entity.setHealth(entity.getHealth() - damage);
                            System.out.println("Vous avez infligé " + damage + " points de dégâts à " + entity.getName() + " !");
                        }
                        touchee = true;
                    }
                }
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

package universite_paris8.iut.rgarry.ashforged.model;

import universite_paris8.iut.rgarry.ashforged.model.character.Entity;

public class Gravity {

    private static final double GRAVITY = 0.5;
    private static double velocityY = 15;


    public static void applyGravityCharacter(Entity entity) {
        velocityY += GRAVITY;
        int steps = (int) Math.abs(velocityY);
        int direction = velocityY > 0 ? 1 : -1;

        for (int i = 0; i < steps; i++) {
            int nextY = entity.getY() + direction;
            if (!Environment.getInstance().getField().isWithinMap(entity.getX(), nextY)) {
                velocityY = 0;
                break;
            }
            if (direction > 0) { // Descente
                if (!Environment.getInstance().getField().checkCollision(entity.getX(), entity.getY() + 32) &&
                        !Environment.getInstance().getField().checkCollision(entity.getX() + 31, entity.getY() + 32)) {
                    entity.setY(entity.getY() + 1);
                } else {
                    velocityY = 0;
                    break;
                }
            } else { // Montée
                if (!Environment.getInstance().getField().checkCollision(entity.getX(),entity.getY() - 1) &&
                        !Environment.getInstance().getField().checkCollision(entity.getX() + 31, entity.getY() - 1)) {
                    entity.setY(entity.getY() - 1);
                } else {
                    velocityY = 0;
                    break;
                }
            }
        }
    }
}

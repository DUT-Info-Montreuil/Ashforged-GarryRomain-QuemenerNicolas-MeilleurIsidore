package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;

import java.util.Random;

public class NPC extends NonePlayer {
    private final int initialX;
    private final Random random = new Random();

    private int minX; // Limite gauche de déplacement
    private int maxX; // Limite droite de déplacement
    private int newX; // Position calculée temporaire

    // Direction courante : 'g' = gauche, 'd' = droite, 'i' = immobile
    private char directionCourante = 'i';

    public NPC(String name, int x, int y, int speed, int health, char direction, double velocityY) {
        super(name, x, y,  speed, health, direction, velocityY);
        this.initialX = x;
        this.minX = initialX - 640;
        this.maxX = initialX + 640;
    }

    /** Choisit une direction aléatoire parmi gauche, droite, immobile */
    @Override
    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        switch (r) {
            case 0 -> directionCourante = 'g';
            case 1 -> directionCourante = 'd';
            default -> directionCourante = 'i';
        }
    }




    public void vaAGauche() {
        newX = getX() - getSpeed();
        if (newX < minX) newX = minX;

        boolean collision = Environment.getInstance().checkCollision(newX, getY()) || Environment.getInstance().checkCollision(newX, getY() + 31);

        if (!collision && Environment.getInstance().getField().isWithinMap(newX, getY())) {
            setX(newX);
        } else if (Environment.getInstance() != null
                && Environment.getInstance().checkCollision(getX(), getY() + 32)
                && Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
            setVelocityY(-12.0); // saut automatique si collision au sol
        }
    }


    public void vaADroite() {
        newX = getX() + getSpeed();
        if (newX > maxX) newX = maxX;

        boolean collision = Environment.getInstance().checkCollision(newX + 31, getY()) || Environment.getInstance().checkCollision(newX + 31, getY() + 31);

        if (!collision && Environment.getInstance().getField().isWithinMap(newX, getY())) {
            setX(newX);
        } else if (Environment.getInstance() != null
                && Environment.getInstance().checkCollision(getX(), getY() + 32)
                && Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
            setVelocityY(-12); // saut automatique si collision au sol
        }
    }

    /** Déplace le NPC selon la direction choisie */
    public void seDeplacer() {
        if (directionCourante == 'g') {
            vaAGauche();
        } else if (directionCourante == 'd') {
            vaADroite();
        }
        // Si 'i', ne fait rien (immobile)
    }

    public void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }


}

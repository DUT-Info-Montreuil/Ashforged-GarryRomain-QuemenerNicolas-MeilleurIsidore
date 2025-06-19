package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

import java.util.Random;

public class Npc extends Entity {
    private final int initialX;
    private final Random random = new Random();

    private int minX; // Limite gauche de déplacement
    private int maxX; // Limite droite de déplacement
    private int newX; // Position calculée temporaire

    // Direction courante : 'g' = gauche, 'd' = droite, 'i' = immobile
    private char directionCourante = 'i';

    public Npc(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        this.initialX = x;
        this.minX = initialX - 640;
        this.maxX = initialX + 640;
    }

    /** Choisit une direction aléatoire parmi gauche, droite, immobile */
    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        switch (r) {
            case 0 -> directionCourante = 'g';
            case 1 -> directionCourante = 'd';
            default -> directionCourante = 'i';
        }
    }

    @Override
    public void vaAGauche() {
        newX = getX() - getVitesse();
        if (newX < minX) newX = minX;

        boolean collision = env.checkCollision(newX, getY()) || env.checkCollision(newX, getY() + 31);

        if (!collision && isWithinMap(newX, getY())) {
            setX(newX);
        } else if (env != null
                && env.checkCollision(getX(), getY() + 32)
                && env.checkCollision(getX() + 31, getY() + 32)) {
            setVelocityY(-12); // saut automatique si collision au sol
        }
    }

    @Override
    public void vaADroite() {
        newX = getX() + getVitesse();
        if (newX > maxX) newX = maxX;

        boolean collision = env.checkCollision(newX + 31, getY()) || env.checkCollision(newX + 31, getY() + 31);

        if (!collision && isWithinMap(newX, getY())) {
            setX(newX);
        } else if (env != null
                && env.checkCollision(getX(), getY() + 32)
                && env.checkCollision(getX() + 31, getY() + 32)) {
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

    /** Attaque les entités de type Mobs proches si arme tenue */
    public void attack() {
        if (getHoldingItem() != null && getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if (entity instanceof Mobs) {
                    int entityX = entity.getX() / 64;
                    int entityY = entity.getY() / 64;
                    int npcX = getX() / 64;
                    int npcY = getY() / 64;

                    // Vérifie la proximité
                    if (Math.abs(entityX - npcX) < 2 && Math.abs(entityY - npcY) < 2) {
                        System.out.println("Attack");

                        int damage;
                        if (stats[1] > 1) {
                            damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        } else {
                            damage = getHoldingItem().getDamage() / 2;
                        }

                        entity.setHealth(entity.getHealth() - damage);
                    }
                }
            }
        }
    }
}

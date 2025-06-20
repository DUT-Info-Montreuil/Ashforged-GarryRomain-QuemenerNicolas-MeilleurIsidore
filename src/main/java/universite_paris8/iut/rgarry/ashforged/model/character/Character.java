package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemStock;

public class Character extends Entity {

    public Character(String name, int level, int[] stats, int x, int y, Environment env) {
        super(name, level, stats, x, y, env);
        setHoldingItem(null);
    }

    /**
     * Déplace le personnage horizontalement selon la direction
     * en vérifiant les collisions avec l'environnement et les limites.
     */
    @Override
    public void seDeplacer() {
        int newX = getX();

        if (direction == 'd') { // déplacement vers la droite
            newX += getVitesse();
            if (!env.checkCollision(newX + 31, getY()) &&
                    !env.checkCollision(newX + 31, getY() + 31) &&
                    isWithinMap(newX, getY())) {
                setX(newX);
                test = false;
            } else if (!test) {
                // Place juste avant le mur (1 pixel avant)
                int blockX = ((newX + 31) / 64) * 64;
                setX(blockX - 31 - 1);
                test = true;
            }

        } else if (direction == 'g') { // déplacement vers la gauche
            newX -= getVitesse();

            if (!env.checkCollision(newX, getY()) &&
                    !env.checkCollision(newX, getY() + 31) &&
                    isWithinMap(newX, getY())) {
                setX(newX);
                test = false;
            } else if (!test) {
                // Place juste après le mur (1 pixel après)
                int blockX = (newX / 64) * 64;
                setX(blockX + 64 + 1);
                test = true;
            }
        }
    }

    /**
     * Attaque les mobs proches si le personnage tient une arme.
     * Inflige des dégâts et gagne de l'expérience en cas de kill.
     */
    @Override
    public void attack() {
        if (getHoldingItem() instanceof ItemStock.Weapon) {
            for (Entity entity : env.getEntities()) {
                if (entity instanceof Mobs) {
                    int dx = Math.abs(entity.getX() / 64 - this.getX() / 64);
                    int dy = Math.abs(entity.getY() / 64 - this.getY() / 64);

                    if (dx < 2 && dy < 2) { // à portée d'attaque
                        System.out.println("HUSSSSS !");
                        int damage;
                        if (stats[1] > 1) {
                            damage = (int) (stats[1] * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        } else {
                            damage = getHoldingItem().getDamage() / 2;
                        }

                        if (entity.getHealth() - damage <= 0) {
                            entity.setHealth(0);
                            System.out.println("Vous avez tué " + entity.getName() + " !");
                            System.out.println("Niveau ennemi : " + entity.getLevel());
                            this.gainExp(entity.getLevel());
                        } else {
                            entity.setHealth(entity.getHealth() - damage);
                            System.out.println("Vous avez infligé " + damage + " points de dégâts à " + entity.getName() + " !");
                        }
                    }
                }
            }
        }
    }
}

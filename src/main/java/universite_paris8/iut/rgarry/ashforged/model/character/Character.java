package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Field;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.Weapon;

public class Character extends Entity {

    private int level;
//  private int[] stats;

    private int speed;
    private int health;
    private int power;

    private IntegerProperty exp = new SimpleIntegerProperty(0);
    private IntegerProperty expToNextLevel = new SimpleIntegerProperty(5);
    protected int stat_point;

    private boolean verifMurACote;

    private char direction;

    private ItemInterface holdingItem;
    

    public Character(String name, int x, int y, Environment environment, int speed, char direction, int level, int power, int health, int stat_point, IntegerProperty exp) {
        super(name,x, y,environment);
        this.level = level;
        this.stat_point = stat_point;
        this.exp.set(exp.get());
//      this.stats = stats;
        this.speed = speed;
        this.power = power;
        this.health = health;
        this.direction = direction;
        this.holdingItem = null;
    }

    /**
     * Déplace le personnage horizontalement selon la direction
     * en vérifiant les collisions avec l'getEnv()ironnement et les limites.
     */
    @Override
    public void seDeplacer() {
        int newX = getX();

        if (direction == 'd') { // déplacement vers la droite
            newX += this.speed;

            if (!getEnv().checkCollision(newX + 31, getY()) &&
                    !getEnv().checkCollision(newX + 31, getY() + 31) &&
                    getEnv().getField().isWithinMap(newX, getY())) {
                setX(newX);
                verifMurACote = false;
            } else if (!verifMurACote) {
                // Place juste avant le mur (1 pixel avant)
                int blockX = ((newX + 31) / 64) * 64;
                setX(blockX - 31 - 1);
                verifMurACote = true;
            }

        } else if (direction == 'g') { // déplacement vers la gauche
            newX -= this.speed;

            if (!getEnv().checkCollision(newX, getY()) &&
                    !getEnv().checkCollision(newX, getY() + 31) &&
                    getEnv().getField().isWithinMap(newX, getY())) {
                setX(newX);
                verifMurACote = false;
            } else if (!verifMurACote) {
                // Place juste après le mur (1 pixel après)
                int blockX = (newX / 64) * 64;
                setX(blockX + 64 + 1);
                verifMurACote = true;
            }
        }
    }

    /**
     * Attaque les mobs proches si le personnage tient une arme.
     * Inflige des dégâts et gagne de l'expérience en cas de kill.
     */
    @Override
    public void attack() {
        if (getHoldingItem() instanceof Weapon) {
            for (Entity entity : getEnv().getEntities()) {
                if (entity instanceof Ennemis) {
                    int dx = Math.abs(this.getX() / 64 - this.getX() / 64);
                    int dy = Math.abs(this.getY() / 64 - this.getY() / 64);

                    if (dx < 2 && dy < 2) { // à portée d'attaque
                        System.out.println("HUSSSSS !");
                        int damage;
                        if (this.power > 1) {
                            damage = (int) (this.power * 0.5 + ((double) getHoldingItem().getDamage() / 2));
                        } else {
                            damage = getHoldingItem().getDamage() / 2;
                        }

                        if (this.health - damage <= 0) {
                            this.health=0;
                            System.out.println("Vous avez tué " + this.getName() + " !");
                            System.out.println("Niveau ennemi : " + this.level);
                            this.gainExp(this.level);
                        } else {
                            this.health = this.health - damage;
                            System.out.println("Vous avez infligé " + damage + " points de dégâts à " + this.getName() + " !");
                        }
                    }
                }
            }
        }
    }


    private void levelUp() {
        this.level = this.level + 1;
        expToNextLevel.set(expToNextLevel.get() + 5);
        stat_point += 5;
    }


    public void gainExp(int amount) {
        exp.set(exp.get() + amount);
        while (exp.get() >= expToNextLevel.get()) {
            exp.set(exp.get() - expToNextLevel.get());
            levelUp();
        }
    }






    public ItemInterface getHoldingItem() { return holdingItem; }
    public void setHoldingItem(ItemInterface holdingItem) { this.holdingItem = holdingItem; }

    // Ces méthodes privées ne semblent pas utilisées, la vérification via isWithinMap() suffit
    /*
    private boolean isWithinMapX(int x) {
        return x >= 0 && x + 31 < getEnv().getField().getWidth();
    }

    private boolean isWithinMapY(int y) {
        return y >= 0 && y + 31 < getEnv().getField().getHeight();
    }
    */
}

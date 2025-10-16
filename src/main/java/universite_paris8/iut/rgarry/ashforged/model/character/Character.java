package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.model.Environment;
import universite_paris8.iut.rgarry.ashforged.model.Item.ItemInterface;
import universite_paris8.iut.rgarry.ashforged.model.Item.Weapon;

public class Character extends Entity {
    private static Character character;

    private final int JUMP_STRENGHT = -12;

    private int level;

    private int speed;
    private Health health;
    private int power;


    private IntegerProperty exp = new SimpleIntegerProperty(0);
    private IntegerProperty expToNextLevel = new SimpleIntegerProperty(5);
    protected int stat_point;

    private boolean verifMurACote;

    private char direction;
    private double velocityY;

    private ItemInterface holdingItem;

    protected Inventory inventory;


    public Character(String name, int x, int y, int speed, int power, int health, int maxHealth, char direction, int level, int stat_point, int exp, double velocityY) {
        super(name, x, y);
        this.level = level;
        this.stat_point = stat_point;
        this.exp.set(exp);
        this.speed = speed;
        this.power = power;
        this.health = new Health(health, maxHealth);
        this.direction = direction;
        this.velocityY = velocityY;
        this.holdingItem = null;
        this.inventory = null;
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

            if (!Environment.getInstance().checkCollision(newX + 31, getY()) &&
                    !Environment.getInstance().checkCollision(newX + 31, getY() + 31) &&
                    Environment.getInstance().getField().isWithinMap(newX, getY())) {
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

            if (!Environment.getInstance().checkCollision(newX, getY()) &&
                    !Environment.getInstance().checkCollision(newX, getY() + 31) &&
                    Environment.getInstance().getField().isWithinMap(newX, getY())) {
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
     * Version avec saut si collision au sol
     */
    public void vaADroite() {
        int newX = getX() + getSpeed();
        if (!Environment.getInstance().checkCollision(newX + 31, getY()) && !Environment.getInstance().checkCollision(newX + 31, getY() + 31) && Environment.getInstance().getField().isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (Environment.getInstance().checkCollision(getX(), getY() + 32) && Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGHT);
            }
        }
    }

    /**
     * Version avec saut si collision au sol
     */
    public void vaAGauche() {
        int newX = getX() - getSpeed();
        if (!Environment.getInstance().checkCollision(newX, getY()) && !Environment.getInstance().checkCollision(newX, getY() + 31) && Environment.getInstance().getField().isWithinMap(newX, getY())) {
            setX(newX);
        } else {
            if (Environment.getInstance().checkCollision(getX(), getY() + 32) && Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
                setVelocityY(JUMP_STRENGHT);
            }
        }
    }


    /**
     * Attaque les mobs proches si le personnage tient une arme.
     * Inflige des dégâts et gagne de l'expérience en cas de kill.
     */
    public void attack() {
        if (getHoldingItem() instanceof Weapon) {
            for (Entity entity : Environment.getInstance().getEntities()) {
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

                        if (health.getHealth() - damage <= 0) {
                            health.setHealthProperty(0);
                            System.out.println("Vous avez tué " + this.getName() + " !");
                            System.out.println("Niveau ennemi : " + this.level);
                            this.gainExp(this.level);
                        } else {
                            health.setHealthProperty(health.getHealth() - damage);
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

    public int getLevel() {
        return this.level;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }


    public int getExp() {
        return this.exp.get();
    }

    public IntegerProperty getExpProperty() {
        return this.exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel.get();
    }

    public IntegerProperty getExpToNextLevelProperty() {
        return expToNextLevel;
    }

    public void gainExp(int amount) {
        exp.set(exp.get() + amount);
        while (exp.get() >= expToNextLevel.get()) {
            exp.set(exp.get() - expToNextLevel.get());
            levelUp();
        }
    }

    public int getSpeed() {
        return this.speed;
    }

    public Health health() {
        return this.health;
    }

    public ItemInterface getHoldingItem() {
        return holdingItem;
    }

    public void setHoldingItem(ItemInterface holdingItem) {
        this.holdingItem = holdingItem;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static Character getInstance() {
        if (character == null) {
            character = new Character("Hero", 1, 3, 250, 300, 13, 5, 'd', 75, 5, 3, 16);
        }
        return character;
    }


}

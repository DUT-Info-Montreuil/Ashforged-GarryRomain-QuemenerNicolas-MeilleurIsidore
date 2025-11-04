package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.BFS;
import universite_paris8.iut.rgarry.ashforged.model.Item.Consumables.*;
import universite_paris8.iut.rgarry.ashforged.model.Item.Item;
import universite_paris8.iut.rgarry.ashforged.model.Item.Weapons;
import universite_paris8.iut.rgarry.ashforged.model.Position;
import universite_paris8.iut.rgarry.ashforged.model.Environment;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ennemis extends NonePlayer {
    private int stats_multiplier;
    private Item item;
    private final int initialX;
    private int force;
    private final Random random = new Random();
    private final int JUMP_STRENGHT = -12;
    private boolean aVuJoueur = false;

    private Weapons holdingItem;

    // Bornes de déplacement en tuiles (non initialisées dans le constructeur ici)
    private int minX;
    private int maxX;

    private char directionCourante = 'i';



    public Ennemis(String name, int x, int y,int speed, int health, int force, char direction,  Weapons item, double velocityY) {
        super(name, x, y, speed, health, direction, velocityY);
        this.item = item;
        this.force = force;
        this.initialX = x;
        this.setHoldingItem(item);
    }

    /** Choisit une direction aléatoire */
    @Override
    public void choisirDirectionAleatoire() {
        int r = random.nextInt(3);
        if (r == 0) directionCourante = 'g';
        else if (r == 1) directionCourante = 'd';
        else directionCourante = 'i';
    }

    /** Se déplace selon direction aléatoire */
    public void seDeplacerRandom() {
        if (directionCourante == 'g') {
            vaAGauche();
        } else if (directionCourante == 'd') {
            vaADroite();
        }
    }



    /** Version avec saut si collision au sol */
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

    /** Version avec saut si collision au sol */
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

    @Override
    public void seDeplacer() {
        // Position du joueur
        Character hero = Environment.getInstance().getHero();
        int mobX = getX() / 64;
        int mobY = getY() / 64;
        int heroX = hero.getX() / 64;
        int heroY = hero.getY() / 64;

        // Recherche de chemin BFS
        BFS bfs = new BFS(Environment.getInstance().getField());
        Position start = new Position(mobX, mobY);
        Position goal = new Position(heroX, heroY);

        List<Position> path = bfs.findPath(start, goal);

        if (Math.abs(heroX - mobX) <= 5) {
            aVuJoueur = true;
        }

        if (aVuJoueur) {
            if (path.size() > 1) {
                Position next = path.get(1);

                if (next.x < mobX) vaAGauche();
                else if (next.x > mobX) vaADroite();

                // Saut si case au-dessus
                if (next.y < mobY) {
                    if (Environment.getInstance().checkCollision(getX(), getY() + 32) && Environment.getInstance().checkCollision(getX() + 31, getY() + 32)) {
                        setVelocityY(JUMP_STRENGHT);
                    }
                }
            }
        } else {
            seDeplacerRandom();
        }
    }

    public void onDeath() {
        Random rand = new Random();

        // 5% chance to drop weapon
        if (getHoldingItem() != null && rand.nextInt(100) < 5) {
//            Environment.getInstance().getHero().addToInventory(getHoldingItem());
        }

        // List of possible resources
        List<Item> resources = Arrays.asList(
                new Iron(),
                new CanonPowder(),
                new PerlimpinpinPowder(),
                new Feather(),
                new Ball(),
                new Coal(),
                new EnchantedMineral(),
                new GoldenPiece()
        );

        // Randomly decide how many resources to drop (at least 1)
        int numDrops = 1 + rand.nextInt(resources.size());

        for (int i = 0; i < numDrops; i++) {
            Item resource = resources.get(rand.nextInt(resources.size()));
//            Environment.getInstance().getHero().addToInventory(resource);
        }
    }


    public void attack() {
        System.out.println(this.getName() + " Health:" + this.health());
        if (getHoldingItem() != null && getHoldingItem() instanceof Weapons) {
            for (Entity entity : Environment.getInstance().getEntities()) {
                if (!(entity instanceof Ennemis)) {
                    int entityX = entity.getX() / 64;
                    int entityY = entity.getY() / 64;
                    int mobX = getX() / 64;
                    int mobY = getY() / 64;

                    if (Math.abs(entityX - mobX) < 2 && Math.abs(entityY - mobY) < 2) {
                        int damage;
                        if (getForce() > 1)
                            damage = (int) (getForce() * 0.5 + ((double) getHoldingItem().getPower() / 2));
                        else
                            damage = getHoldingItem().getPower() / 2;
                        this.health().setHealthProperty(health().getHealth() - damage);
                    }
                }
            }
        }
    }


    public void setVelocityY(double velocityY) {
       this.velocityY = velocityY;
    }

    public int getForce(){return force;}

    public Weapons getHoldingItem() { return holdingItem; }
    public void setHoldingItem(Weapons holdingItem) { this.holdingItem = holdingItem; }

    public void action() {
//        this.applyGravity(Environment.getInstance());
        this.seDeplacer();
    }
}

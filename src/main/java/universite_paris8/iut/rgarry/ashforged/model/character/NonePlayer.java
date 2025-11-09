package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Gravity;


public abstract class NonePlayer extends Entity {
    private char direction;
    private static final double GRAVITY = 0.5;
    protected double velocityY;
    private int speed;
    private Health health;


    public NonePlayer(String name, int x, int y, int speed, int health, char direction, double velocityY) {
        super(name, x, y);
        this.direction = direction;
        this.speed = speed;
        this.health = new Health(health,health);
        this.velocityY = velocityY;
    }


    public abstract void choisirDirectionAleatoire();

    public int getSpeed(){
        return speed;
    }

    public abstract void setVelocityY(double velocityY);

    public Health health(){
        return health;
    }






}

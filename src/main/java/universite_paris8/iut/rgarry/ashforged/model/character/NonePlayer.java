package universite_paris8.iut.rgarry.ashforged.model.character;

import universite_paris8.iut.rgarry.ashforged.model.Environment;

public abstract class NonePlayer extends Entity {
    protected char direction;
    private static final double GRAVITY = 0.5;
    protected double velocityY;

    public NonePlayer(String name, int x, int y, Environment environment) {
        super(name, x, y, environment);
    }


    public abstract void choisirDirectionAleatoire();



}

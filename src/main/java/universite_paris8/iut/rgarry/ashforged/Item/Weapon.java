package universite_paris8.iut.rgarry.ashforged.Item;

public class Weapon extends Type{
    private int damage;

    public Weapon(int damage, String description, boolean breakable){
        super(description, breakable);
        this.damage = damage;
    }


}

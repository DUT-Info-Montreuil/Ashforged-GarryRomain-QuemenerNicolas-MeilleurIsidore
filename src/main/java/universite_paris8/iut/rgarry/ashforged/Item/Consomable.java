package universite_paris8.iut.rgarry.ashforged.Item;

public class Consomable extends Type{
    private int hpGive;

    public Consomable(int hpGive, String description, boolean breakable){
        super(description, breakable);
        this.hpGive = hpGive;
    }
}

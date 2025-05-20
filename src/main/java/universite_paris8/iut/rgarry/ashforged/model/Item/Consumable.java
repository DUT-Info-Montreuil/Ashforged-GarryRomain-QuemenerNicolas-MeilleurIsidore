package universite_paris8.iut.rgarry.ashforged.model.Item;

public class Consumable extends Type{
    private int value;

    public Consumable(int value , String description, boolean breakable){
        super(description, breakable);
        this.value = value;
    }
}

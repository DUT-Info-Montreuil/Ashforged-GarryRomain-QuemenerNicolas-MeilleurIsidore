package universite_paris8.iut.rgarry.ashforged.Item;

public class Item {
    private String  id;
    private String name;
    private int weight;
    private Type type;
    private static int counter = 0;

    public Item(String name, int weight){
        this.id = "#" + counter;
        this.name = name;
        this.weight = weight;
        counter++;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {return weight;}
}

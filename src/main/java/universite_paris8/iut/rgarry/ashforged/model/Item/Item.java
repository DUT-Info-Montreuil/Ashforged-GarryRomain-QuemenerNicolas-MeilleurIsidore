package universite_paris8.iut.rgarry.ashforged.model.Item;

public abstract class Item {
    private int id;
    private String name;
    private String description;

    public Item(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = imagePath;
    }

    public abstract int getId();

    public abstract String getName();

    public abstract String getImagePath();

}

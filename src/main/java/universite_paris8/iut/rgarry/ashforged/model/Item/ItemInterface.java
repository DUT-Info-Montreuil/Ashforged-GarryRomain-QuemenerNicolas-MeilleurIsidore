package universite_paris8.iut.rgarry.ashforged.model.Item;

import javafx.scene.image.Image;

public interface ItemInterface {
    String getId();
    String getName();
    int getWeight();
     String getImagePath();
    public Image getImage();
}

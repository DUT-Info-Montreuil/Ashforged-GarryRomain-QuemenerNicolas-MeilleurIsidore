package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.rgarry.ashforged.model.Environment;

public abstract class Entity {

    private IntegerProperty x, y;

    private String id;
    private String name;
    private static int compter = 0;


    public Entity(String name, int x, int y) {

        this.id = "#" + compter++;
        this.name = name;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }


//    /**
//     * Vérifie que la position (x,y) est dans les limites de la carte
//     */
//    protected boolean isWithinMap(int x, int y) {
//        int width = env.getField().getWidth() * 64;
//        int height = env.getField().getHeight() * 64;
//        return x >= 0 && x + 31 < width && y >= 0 && y + 31 < height;
//    }

    // Méthodes abstraites à implémenter par les classes filles
    public abstract void seDeplacer();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public int getX() {
        return x.getValue();
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public int getY() {
        return y.getValue();
    }

    public void setX(int pos) {
        x.setValue(pos);
    }

    public void setY(int pos) {
        y.setValue(pos);
    }


}
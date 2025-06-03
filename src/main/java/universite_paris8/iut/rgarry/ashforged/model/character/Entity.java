package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;

public interface Entity {
    int getX();
    int getY();
    double getVelocityY();

    void setX(int x);
    void setY(int y);
    void setVelocityY(double velocityY);
}

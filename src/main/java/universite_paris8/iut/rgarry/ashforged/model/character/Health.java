package universite_paris8.iut.rgarry.ashforged.model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Health {
    private IntegerProperty health;
    private int maxHealth;

    public Health(int health,int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = new SimpleIntegerProperty(health);
    }

    public int getHealth() {
        return health.get();
    }
    public IntegerProperty getHealthProperty() {
        return health;
    }
    public void setHealthProperty(int health) {
        this.health.set(health);
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}

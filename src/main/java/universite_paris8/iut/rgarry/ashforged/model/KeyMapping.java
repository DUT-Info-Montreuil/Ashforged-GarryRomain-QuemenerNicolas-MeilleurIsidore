package universite_paris8.iut.rgarry.ashforged.model;

import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;

public class KeyMapping {
    private static final Map<String, KeyCode> keyBindings = new HashMap<>();

    static {
        keyBindings.put("moveRight", KeyCode.D);
        keyBindings.put("moveLeft", KeyCode.Q);
        keyBindings.put("jump", KeyCode.SPACE);
    }

    public static KeyCode getKey(String action) {
        return keyBindings.get(action);
    }

    public static void setKey(String action, KeyCode key) {
        keyBindings.put(action, key);
    }

    public static Map<String, KeyCode> getAll() {
        return keyBindings;
    }
}
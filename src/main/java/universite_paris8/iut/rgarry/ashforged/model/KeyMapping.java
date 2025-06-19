package universite_paris8.iut.rgarry.ashforged.model;

import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages key bindings for player actions.
 * This class provides a static mapping between action names (e.g., "moveRight")
 * and the corresponding JavaFX {@link KeyCode} values.
 */
public class KeyMapping {

    /**
     * Stores the current key bindings.
     * The map uses action names as keys and {@link KeyCode} values.
     */
    private static final Map<String, KeyCode> keyBindings = new HashMap<>();

    // Static initializer: defines default key bindings
    static {
        keyBindings.put("moveRight", KeyCode.D);   // Default key for moving right
        keyBindings.put("moveLeft", KeyCode.Q);    // Default key for moving left
        keyBindings.put("jump", KeyCode.SPACE);    // Default key for jumping
    }

    /**
     * Returns the key currently bound to a given action.
     *
     * @param action the name of the action (e.g., "moveRight")
     * @return the {@link KeyCode} bound to that action, or null if none
     */
    public static KeyCode getKey(String action) {
        return keyBindings.get(action);
    }

    /**
     * Binds a new key to a specific action.
     *
     * @param action the action name to bind (e.g., "jump")
     * @param key the {@link KeyCode} to assign to the action
     */
    public static void setKey(String action, KeyCode key) {
        keyBindings.put(action, key);
    }

    /**
     * Returns all current key bindings.
     *
     * @return a map of all actions and their corresponding {@link KeyCode} values
     */
    public static Map<String, KeyCode> getAll() {
        return keyBindings;
    }
}

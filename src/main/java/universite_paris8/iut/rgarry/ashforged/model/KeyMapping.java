package universite_paris8.iut.rgarry.ashforged.model;

import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;

// Class to manage key bindings for game actions using a mapping of action names to KeyCode values
public class KeyMapping {
    // Static map to store action-to-key bindings
    private static final Map<String, KeyCode> keyBindings = new HashMap<>();

    // Static initialization block to define default key bindings
    static {
        keyBindings.put("moveRight", KeyCode.D); // Bind "moveRight" action to D key
        keyBindings.put("moveLeft", KeyCode.Q); // Bind "moveLeft" action to Q key
        keyBindings.put("jump", KeyCode.SPACE); // Bind "jump" action to SPACE key
    }

    /**
     * Retrieves the KeyCode associated with a given action.
     *
     * @param action The name of the action (e.g., "moveRight", "moveLeft", "jump").
     * @return The KeyCode mapped to the action, or null if the action is not found.
     */
    public static KeyCode getKey(String action) {
        return keyBindings.get(action); // Return the KeyCode for the specified action
    }

    /**
     * Sets or updates the KeyCode for a given action.
     *
     * @param action The name of the action to bind.
     * @param key The KeyCode to associate with the action.
     */
    public static void setKey(String action, KeyCode key) {
        keyBindings.put(action, key); // Update or add the action-to-key mapping
    }

    /**
     * Retrieves the entire map of action-to-key bindings.
     *
     * @return A Map containing all action-to-KeyCode mappings.
     */
    public static Map<String, KeyCode> getAll() {
        return keyBindings; // Return the complete key bindings map
    }
}
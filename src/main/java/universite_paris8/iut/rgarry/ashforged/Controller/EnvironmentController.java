package universite_paris8.iut.rgarry.ashforged.Controller;

import universite_paris8.iut.rgarry.ashforged.model.Environment;

/**
 * Controller for managing the game environment.
 *
 * This class handles interactions with the {@link Environment} model,
 * including access to terrain, objects, weather, or background logic.
 * Acts as a bridge between the UI and the environmental data.
 */
public class EnvironmentController {

    private final Environment environment;

    /**
     * Constructs an EnvironmentController with the specified environment instance.
     *
     * @param environment the game environment to manage.
     */
    public EnvironmentController(Environment environment) {
        this.environment = environment;
    }
}

package universite_paris8.iut.rgarry.ashforged.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Class to represent a grid-based graph for pathfinding, with adjacency lists and obstacle management
public class Grid {
    private Environment environment; // The game environment providing field data
    private int height; // Height of the grid (number of rows)
    private int width; // Width of the grid (number of columns)
    private Map<Position, Set<Position>> adjacencyList; // Adjacency list for grid positions
    private ObservableList<Position> obstacles; // List of positions marked as obstacles

    /**
     * Constructor for Grid, initializes the grid with dimensions and environment data.
     * Builds the adjacency graph and places obstacles based on the field.
     *
     * @param environment The game environment containing the field data.
     * @param height The height of the grid (number of rows).
     * @param width The width of the grid (number of columns).
     */
    public Grid(Environment environment, int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Grid dimensions must be positive: height=" + height + ", width=" + width);
        }
        this.environment = environment; // Store environment reference
        this.height = height; // Set grid height
        this.width = width; // Set grid width
        this.adjacencyList = new HashMap<>(); // Initialize adjacency list
        this.obstacles = FXCollections.observableArrayList(); // Initialize observable obstacle list
        buildGraph(); // Construct the adjacency graph
        placeObstacles(); // Place obstacles based on field data
    }

    /**
     * Builds the adjacency graph for the grid, connecting each position to its valid neighbors.
     */
    public void buildGraph() {
        // Initialize adjacency sets for all positions
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                adjacencyList.put(new Position(x, y), new HashSet<>()); // Create empty adjacency set
            }
        }

        // Connect each position to its valid neighbors (up, down, left, right)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Position current = getPosition(x, y); // Current position
                // Add left neighbor if within grid
                if (isWithinGrid(x - 1, y)) {
                    adjacencyList.get(current).add(getPosition(x - 1, y));
                }
                // Add right neighbor if within grid
                if (isWithinGrid(x + 1, y)) {
                    adjacencyList.get(current).add(getPosition(x + 1, y));
                }
                // Add down neighbor if within grid
                if (isWithinGrid(x, y + 1)) {
                    adjacencyList.get(current).add(getPosition(x, y + 1));
                }
                // Add up neighbor if within grid
                if (isWithinGrid(x, y - 1)) {
                    adjacencyList.get(current).add(getPosition(x, y - 1));
                }
            }
        }
    }

    /**
     * Retrieves the Position object for the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The Position object, or null if not found.
     */
    public Position getPosition(int x, int y) {
        // Search for position in adjacency list keys
        for (Position p : adjacencyList.keySet()) {
            if (p.getX() == x && p.getY() == y) {
                return p; // Return matching position
            }
        }
        return null; // Return null if position not found
    }

    /**
     * Checks if a position is marked as disconnected (an obstacle).
     *
     * @param position The position to check.
     * @return true if the position is an obstacle, false otherwise.
     */
    public boolean isDisconnected(Position position) {
        return obstacles.contains(position); // Check if position is in obstacle list
    }

    /**
     * Retrieves the set of adjacent positions for a given position.
     * Returns an empty set if the position is disconnected (an obstacle).
     *
     * @param position The position whose neighbors are requested.
     * @return A set of adjacent positions, or an empty set if the position is disconnected.
     */
    public Set<Position> getNeighbors(Position position) {
        // Return adjacency list if not disconnected, otherwise empty set
        return isDisconnected(position) ? new HashSet<>() : adjacencyList.get(position);
    }

    /**
     * Checks if the coordinates are within the grid boundaries.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return true if the coordinates are within the grid, false otherwise.
     */
    private boolean isWithinGrid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height; // Validate coordinates
    }

    /**
     * Places obstacles on the grid based on the environment's field data.
     * Marks positions as obstacles if their block type is not 1 (sky).
     */
    public void placeObstacles() {
        // Iterate through field dimensions
        for (int y = 0; y < environment.getField().getHeight(); y++) {
            for (int x = 0; x < environment.getField().getWidth(); x++) {
                // Add position as obstacle if block type is not sky (1)
                if (environment.getField().block(x, y) != 1) {
                    obstacles.add(new Position(x, y));
                }
            }
        }
    }

    /**
     * Reconnects a position by removing it from the obstacle list.
     *
     * @param position The position to reconnect.
     */
    public void reconnect(Position position) {
        obstacles.remove(position); // Remove position from obstacles
    }

    /**
     * Disconnects a position by adding it to the obstacle list.
     *
     * @param position The position to disconnect.
     */
    public void disconnect(Position position) {
        obstacles.add(position); // Add position to obstacles
    }
}
package universite_paris8.iut.rgarry.ashforged.model;

import java.util.*;

// Class to implement Breadth-First Search (BFS) for finding a path on a game field
public class BFS {
    private Field field; // The game field defining the map structure and collision rules
    private int minX = 0; // Minimum x-coordinate boundary for movement
    private int maxX = Integer.MAX_VALUE; // Maximum x-coordinate boundary for movement
    private static final int TILE_SIZE = 64; // Size of each tile in pixels

    /**
     * Constructor for BFS, initializes the pathfinding algorithm with a game field.
     *
     * @param field The game field used for collision checks and pathfinding.
     * @throws NullPointerException if field is null.
     */
    public BFS(Field field) {
        this.field = field; // Store the field reference
    }

    /**
     * Sets the minimum x-coordinate boundary for movement.
     *
     * @param minX The minimum x-coordinate allowed.
     */
    public void setMinX(int minX) {
        this.minX = minX; // Update minimum x boundary
    }

    /**
     * Sets the maximum x-coordinate boundary for movement.
     *
     * @param maxX The maximum x-coordinate allowed.
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX; // Update maximum x boundary
    }

    /**
     * Finds the shortest path from start to goal using Breadth-First Search.
     *
     * @param start The starting position.
     * @param goal The target position.
     * @return A List of Position objects representing the path from start to goal, or an empty list if no path exists.
     * @throws NullPointerException if start or goal is null.
     */
    public List<Position> findPath(Position start, Position goal) {
        Queue<Position> queue = new LinkedList<>(); // Queue for BFS exploration
        Map<Position, Position> predecessors = new HashMap<>(); // Tracks parent of each position
        Set<Position> visited = new HashSet<>(); // Tracks visited positions

        queue.add(start); // Start with the initial position
        visited.add(start); // Mark start as visited
        predecessors.put(start, null); // Start has no predecessor

        // Explore the graph using BFS
        while (!queue.isEmpty()) {
            Position current = queue.poll(); // Get next position
            if (current.equals(goal)) { // Goal reached
                break;
            }
            // Explore neighbors
            for (Position neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor); // Add unvisited neighbor to queue
                    visited.add(neighbor); // Mark neighbor as visited
                    predecessors.put(neighbor, current); // Record predecessor
                }
            }
        }

        // Reconstruct path from goal to start
        List<Position> path = new ArrayList<>();
        Position step = goal;
        if (!predecessors.containsKey(goal)) {
            return path; // Return empty path if goal is unreachable
        }
        while (step != null) {
            path.add(0, step); // Add position to front of path
            step = predecessors.get(step); // Move to predecessor
        }
        return path; // Return the constructed path
    }

    /**
     * Retrieves the valid neighboring positions for a given position.
     * Neighbors are positions in the four cardinal directions (up, down, left, right)
     * that are within bounds and not collidable.
     *
     * @param pos The position to find neighbors for.
     * @return A List of Position objects representing valid neighbors.
     */
    private List<Position> getNeighbors(Position pos) {
        List<Position> neighbors = new ArrayList<>();
        // Define four cardinal directions: right, left, down, up
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Check each direction
        for (int[] dir : directions) {
            int nx = pos.getX() + dir[0]; // New x-coordinate
            int ny = pos.getY() + dir[1]; // New y-coordinate
            // Skip if outside x boundaries
            if (nx < minX || nx > maxX) {
                continue;
            }
            // Check if the position is non-collidable
            if (!field.checkCollision(nx * TILE_SIZE, ny * TILE_SIZE)) {
                neighbors.add(new Position(nx, ny)); // Add valid neighbor
            }
        }
        return neighbors; // Return list of valid neighbors
    }
}
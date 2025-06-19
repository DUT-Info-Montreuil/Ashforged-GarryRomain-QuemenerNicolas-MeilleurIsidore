package universite_paris8.iut.rgarry.ashforged.model;

import java.util.*;

/**
 * Breadth-First Search (BFS) pathfinding algorithm implementation.
 * Used to find the shortest path on a grid-based field avoiding collisions.
 */
public class BFS {
    private Field field;
    private int minX = 0; // Minimum X coordinate allowed
    private int maxX = Integer.MAX_VALUE; // Maximum X coordinate allowed

    /**
     * Constructor for BFS.
     *
     * @param field The field (map) used to determine valid positions and collisions.
     */
    public BFS(Field field) {
        this.field = field;
    }

    /**
     * Sets the minimum X value (left limit) for the BFS search.
     * @param minX Minimum allowed X coordinate
     */
    public void setMinX(int minX) {
        this.minX = minX;
    }

    /**
     * Sets the maximum X value (right limit) for the BFS search.
     * @param maxX Maximum allowed X coordinate
     */
    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    /**
     * Finds the shortest path from a starting position to a goal position.
     * Uses BFS to explore the map while avoiding obstacles.
     *
     * @param start The starting position
     * @param goal  The goal position
     * @return List of positions representing the shortest path from start to goal,
     *         or an empty list if no path is found.
     */
    public List<Position> findPath(Position start, Position goal) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> pred = new HashMap<>();
        Set<Position> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        pred.put(start, null);

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            // Goal reached
            if (current.equals(goal)) {
                break;
            }

            for (Position neighbor : getAdjacents(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    pred.put(neighbor, current);
                }
            }
        }

        // Reconstruct the path by walking backwards from goal to start
        List<Position> path = new ArrayList<>();
        Position step = goal;
        if (!pred.containsKey(goal)) {
            return path; // No path found
        }
        while (step != null) {
            path.add(0, step); // Insert at beginning
            step = pred.get(step);
        }
        return path;
    }

    /**
     * Gets adjacent positions (up, down, left, right) from the current position,
     * filters out positions that are outside the allowed X range or that collide with obstacles.
     *
     * @param pos The current position
     * @return List of valid adjacent positions
     */
    private List<Position> getAdjacents(Position pos) {
        List<Position> adj = new ArrayList<>();
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // Right, Left, Down, Up

        for (int[] dir : directions) {
            int nx = pos.x + dir[0];
            int ny = pos.y + dir[1];

            // Enforce minX/maxX bounds
            if (nx < minX || nx > maxX) continue;

            // Multiply by 64 because field uses pixel coordinates
            if (!field.checkCollision(nx * 64, ny * 64)) {
                adj.add(new Position(nx, ny));
            }
        }

        return adj;
    }
}

package universite_paris8.iut.rgarry.ashforged.Controller;

import universite_paris8.iut.rgarry.ashforged.model.Field;
import java.util.*;

public class BFS {
    private Field field;
    private int minX = 0;
    private int maxX = Integer.MAX_VALUE;

    public BFS(Field field) {
        this.field = field;
    }

    public void setMinX(int minX) { this.minX = minX; }
    public void setMaxX(int maxX) { this.maxX = maxX; }

    public List<Position> findPath(Position start, Position goal) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> pred = new HashMap<>();
        Set<Position> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        pred.put(start, null);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
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

        List<Position> path = new ArrayList<>();
        Position step = goal;
        if (!pred.containsKey(goal)) {
            return path; // Pas de chemin trouvé
        }
        while (step != null) {
            path.add(0, step);
            step = pred.get(step);
        }
        return path;
    }

    private List<Position> getAdjacents(Position pos) {
        List<Position> adj = new ArrayList<>();
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] dir : directions) {
            int nx = pos.x + dir[0];
            int ny = pos.y + dir[1];
            // Limite les déplacements à [minX, maxX]
            if (nx < minX || nx > maxX) continue;
            if (!field.checkCollision(nx * 64, ny * 64)) {
                adj.add(new Position(nx, ny));
            }
        }
        return adj;
    }
}
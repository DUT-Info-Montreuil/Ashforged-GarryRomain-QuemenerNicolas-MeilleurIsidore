package universite_paris8.iut.rgarry.ashforged.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a grid-based graph used for navigation or pathfinding.
 * Each cell is a Position node and can have connections (edges) to adjacent walkable nodes,
 * unless it is an obstacle.
 */
public class Grille {
    private Environment environnement;
    private int hauteur;
    private int largeur;
    private java.util.Map<Position, Set<Position>> listeAdj;
    private ObservableList<Position> obstacles;

    /**
     * Constructs the grid graph based on the given environment and dimensions.
     * Initializes nodes, their adjacency relations, and obstacles from the environment's field.
     *
     * @param environnement the environment context (provides tile information)
     * @param hauteur       the grid height (in number of cells)
     * @param largeur       the grid width (in number of cells)
     */
    public Grille(Environment environnement, int hauteur, int largeur) {
        this.environnement = environnement;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        construireMap();
        poseObstacles();
    }

    /**
     * Constructs the graph by creating all grid positions and connecting them
     * to their 4 direct neighbors (up, down, left, right) if within bounds.
     */
    public void construireMap() {
        // Initialize all positions
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                this.listeAdj.put(new Position(x, y), new HashSet<>());
            }
        }

        // Connect adjacent positions
        for (int i = 0; i < this.largeur; ++i) {
            for (int j = 0; j < this.hauteur; ++j) {
                Position s = this.getPosition(i, j);
                if (this.dansGrille(i - 1, j)) {
                    this.listeAdj.get(s).add(this.getPosition(i - 1, j));
                }
                if (this.dansGrille(i + 1, j)) {
                    this.listeAdj.get(s).add(this.getPosition(i + 1, j));
                }
                if (this.dansGrille(i, j + 1)) {
                    this.listeAdj.get(s).add(this.getPosition(i, j + 1));
                }
                if (this.dansGrille(i, j - 1)) {
                    this.listeAdj.get(s).add(this.getPosition(i, j - 1));
                }
            }
        }
    }

    /**
     * Finds and returns the Position object matching the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the Position object if found, otherwise null
     */
    public Position getPosition(int x, int y) {
        for (Position p : this.listeAdj.keySet()) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }

    /**
     * Checks whether the given position is considered an obstacle (disconnected from the graph).
     *
     * @param s the position to check
     * @return true if the position is an obstacle, false otherwise
     */
    public boolean estDeconnecte(Position s) {
        return this.obstacles.contains(s);
    }

    /**
     * Returns the set of adjacent (neighbor) positions for a given position.
     * If the position is an obstacle, returns an empty set.
     *
     * @param s the position to query
     * @return a set of adjacent positions or an empty set if disconnected
     */
    public Set<Position> adjacents(Position s) {
        return !this.estDeconnecte(s) ? this.listeAdj.get(s) : new HashSet<>();
    }

    /**
     * Checks whether the (x, y) coordinate is inside the bounds of the grid.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if within bounds, false otherwise
     */
    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < this.largeur && y >= 0 && y < this.hauteur;
    }

    /**
     * Populates the list of obstacles based on the Environment's Field.
     * Any tile that is not of type 1 (sky/empty) is treated as an obstacle.
     */
    public void poseObstacles() {
        for (int l = 0; l < environnement.getField().getHeight(); l++) {
            for (int c = 0; c < environnement.getField().getWidth(); c++) {
                if (environnement.getField().block(c, l) != 1) {
                    this.obstacles.add(new Position(c, l));
                }
            }
        }
    }

    /**
     * Reconnects a position (removes it from the obstacle list).
     *
     * @param s the position to reconnect
     */
    public void reconnecte(Position s) {
        this.obstacles.remove(s);
    }

    /**
     * Disconnects a position (adds it to the obstacle list).
     *
     * @param s the position to disconnect
     */
    public void deconnecte(Position s) {
        this.obstacles.add(s);
    }
}

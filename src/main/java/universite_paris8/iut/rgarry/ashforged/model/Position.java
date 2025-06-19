package universite_paris8.iut.rgarry.ashforged.model;

import java.util.Objects;

// Class to represent a 2D position with x and y coordinates
public class Position {
    private int x; // X-coordinate of the position
    private int y; // Y-coordinate of the position

    /**
     * Constructor for Position, initializes the x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x; // Set the x-coordinate
        this.y = y; // Set the y-coordinate
    }

    /**
     * Computes the hash code for this position based on its x and y coordinates.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y); // Generate hash code using x and y
    }

    /**
     * Checks if this position is equal to another object.
     * Two positions are equal if they have the same x and y coordinates.
     *
     * @param obj The object to compare with this position.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check for self-comparison
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null or type mismatch
        Position other = (Position) obj; // Cast to Position
        return x == other.x && y == other.y; // Compare x and y coordinates
    }

    /**
     * Returns a string representation of this position.
     *
     * @return A string in the format "Position{x=X, y=Y}".
     */
    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}'; // Format position as string
    }

    /**
     * Gets the x-coordinate of this position.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return this.x; // Return the x-coordinate
    }

    /**
     * Gets the y-coordinate of this position.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return this.y; // Return the y-coordinate
    }

    /**
     * Sets the y-coordinate of this position.
     *
     * @param y The new y-coordinate.
     */
    public void setY(int y) {
        this.y = y; // Update the y-coordinate
    }

    /**
     * Sets the x-coordinate of this position.
     *
     * @param x The new x-coordinate.
     */
    public void setX(int x) {
        this.x = x; // Update the x-coordinate
    }
}
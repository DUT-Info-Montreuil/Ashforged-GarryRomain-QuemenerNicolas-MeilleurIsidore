package universite_paris8.iut.rgarry.ashforged.model;

import java.util.Objects;

/**
 * Represents a two-dimensional coordinate with integer values for x and y.
 * This class is useful for identifying positions in a grid or game world.
 */
public class Position {
    public int x, y;

    /**
     * Constructs a Position object with the given coordinates.
     *
     * @param x the horizontal coordinate
     * @param y the vertical coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a hash code value for the object based on x and y.
     *
     * @return hash code of the position
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Compares this position with another object for equality.
     * Two positions are equal if they have the same x and y values.
     *
     * @param obj the object to compare with
     * @return true if the objects represent the same position; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Returns a string representation of the position.
     *
     * @return a string in the format Position{x=..., y=...}
     */
    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * Gets the x-coordinate of the position.
     *
     * @return the x value
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of the position.
     *
     * @return the y value
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y the new y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x the new x value
     */
    public void setX(int x) {
        this.x = x;
    }
}

package universite_paris8.iut.rgarry.ashforged.Controller;

import java.util.Objects;

public class Position {
    public int x, y;
    public Position(int x, int y) { this.x = x; this.y = y; }

    @Override
    public int hashCode() { return Objects.hash(x, y); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position other = (Position) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
}

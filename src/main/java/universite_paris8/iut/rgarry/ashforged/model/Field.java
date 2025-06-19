package universite_paris8.iut.rgarry.ashforged.model;

/**
 * Represents the game field or tilemap, where each tile is represented by an integer value.
 * Example: 1 = sky block, 2 = earth block, etc.
 */
public class Field {
    private int[][] tiles;

    /**
     * Initializes the tilemap.
     * Each row represents a horizontal slice of the field, and each number corresponds to a specific tile type.
     */
    public Field() {
        this.tiles = new int[][] {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,15,1,1,15,1,1,15},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,15,15,15,15,15,15,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,15,15,15,15,15,15},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,15,16,15,16,15,16,15},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,15,15,15,1,15,15,1},
                {1,1,1,1,1,1,1,10,1,1,1,1,1,1,10,10,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,15,15,15,15,15,15},
                {6,1,1,1,1,1,10,10,10,1,1,1,1,10,10,10,10,10,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,8,7,7,7,7,7,7},
                {2,8,8,6,1,1,1,9,1,1,1,1,1,1,1,9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,2,2,2,7,7,7,7,7},
                {2,2,2,3,1,1,1,9,1,1,1,1,1,1,1,9,1,1,1,1,1,1,1,1,5,8,8,8,8,8,8,8,8,2,7,7,2,7,7,7,7,7},
                {2,2,2,2,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,2,2,2,2,2,2,2,2,2,2,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,2,2,2,2,7,7,7,7,7,7,7,7},
                {13,7,13,7,7,7,7,13,13,13,7,7,7,7,7,7,7,7,7,7,7,13,7,7,7,13,7,7,7,7,2,2,2,2,2,2,0,0,2,2,2,2},
                {7,13,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,13,7,7,7,7,7,7,7,7,13,13,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,0,7,7,7,7,7,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,13,13,7,0,7,7,7,7,7,7,7,7},
                {7,7,7,7,0,0,7,7,7,7,0,0,0,7,7,7,7,7,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,0,0,0,7,7,7,7,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,13,13,7,7,7,7},
                {7,7,11,11,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,11,11,7,7,7,7,7,7,7,7,7,13,13,7,7,7,7},
                {7,7,7,7,7,7,7,12,12,7,7,7,7,7,7,7,7,7,7,7,7,12,7,7,11,11,11,7,7,12,7,7,7,7,7,7,7,7,7,7,7,7}

                };
    }

    /**
     * Gets the tile value at a given (x, y) position in the tilemap.
     * Returns 1 (sky) if the position is out of bounds.
     *
     * @param x X coordinate (column index)
     * @param y Y coordinate (row index)
     * @return the tile value at the specified location
     */
    public int block(int x, int y) {
        if (y < 0 || y >= tiles.length || x < 0 || x >= tiles[0].length) {
            return 1; // Default to sky or safe value
        }
        return this.tiles[y][x];
    }

    /**
     * Sets the tile value at a specific (x, y) location in the tilemap.
     *
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param bloc  the new tile value
     */
    public void setBlock(int x, int y, int bloc) {
        this.tiles[y][x] = bloc;
    }

    /**
     * Gets the number of tiles horizontally (width of the map).
     *
     * @return the number of columns in the tilemap
     */
    public int getWidth() {
        return this.tiles[0].length;
    }

    /**
     * Gets the number of tiles vertically (height of the map).
     *
     * @return the number of rows in the tilemap
     */
    public int getHeight() {
        return this.tiles.length;
    }

    /**
     * Converts an absolute X coordinate (in pixels) to a tile index.
     *
     * @param x pixel-based X coordinate
     * @return corresponding column index
     */
    public int getXView(int x) {
        int index = x / 64;
        if (index < 0 || index >= getWidth()) {
            return 0;
        }
        return index;
    }

    /**
     * Converts an absolute Y coordinate (in pixels) to a tile index.
     *
     * @param y pixel-based Y coordinate
     * @return corresponding row index
     */
    public int getYView(int y) {
        int index = y / 64;
        if (index < 0 || index >= getHeight()) {
            return 0;
        }
        return index;
    }

    /**
     * Checks whether a given pixel position collides with a solid tile (i.e., non-sky).
     *
     * @param x X coordinate in pixels
     * @param y Y coordinate in pixels
     * @return true if there is a collision, false otherwise
     */
    public boolean checkCollision(int x, int y) {
        return block(getXView(x), getYView(y)) != 1;
    }

    /**
     * Internal method to validate that all tilemap rows have the same length.
     * Outputs a warning to stderr if inconsistency is found.
     */
    private void validateTiles() {
        int expectedWidth = tiles[0].length;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].length != expectedWidth) {
                System.err.println("Line " + i + " has incorrect width: " + tiles[i].length + ", expected: " + expectedWidth);
            }
        }
    }
}

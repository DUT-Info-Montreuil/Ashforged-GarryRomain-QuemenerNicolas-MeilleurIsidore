package universite_paris8.iut.rgarry.ashforged.model;

// Class to define the structure of the game map, specifying the type of tile at each position
public class Field {
    private int[][] tiles; // 2D array representing the map, where each value is a tile type
    private static final int TILE_SIZE = 64; // Size of each tile in pixels (used for view calculations)

    /**
     * Constructor for Field, initializes the map with a predefined tile layout.
     * Each integer in the tiles array represents a different tile type (e.g., 1 for sky, 2 for ground).
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
        validateTiles(); // Validate the tile array structure
    }

    /**
     * Retrieves the tile type at the specified coordinates.
     *
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @return The tile type at (x, y), or 1 (sky) if the coordinates are out of bounds.
     */
    public int block(int x, int y) {
        // Check if coordinates are out of bounds
        if (y < 0 || y >= tiles.length || x < 0 || x >= tiles[0].length) {
            return 1; // Default to sky for invalid coordinates
        }
        return tiles[y][x]; // Return tile type at (x, y)
    }

    /**
     * Sets the tile type at the specified coordinates.
     *
     * @param x The x-coordinate (column index).
     * @param y The y-coordinate (row index).
     * @param block The tile type to set.
     */
    public void setBlock(int x, int y, int block) {
        tiles[y][x] = block; // Update tile type at (x, y)
    }

    /**
     * Retrieves the width of the map (number of columns).
     *
     * @return The number of columns in the tile array.
     */
    public int getWidth() {
        return tiles[0].length; // Return width of first row
    }

    /**
     * Retrieves the height of the map (number of rows).
     *
     * @return The number of rows in the tile array.
     */
    public int getHeight() {
        return tiles.length; // Return number of rows
    }

    /**
     * Converts a pixel x-coordinate to a tile index.
     *
     * @param x The x-coordinate in pixels.
     * @return The corresponding tile index, or 0 if out of bounds.
     */
    public int getXView(int x) {
        int index = x / TILE_SIZE; // Convert pixel coordinate to tile index
        if (index < 0 || index >= getWidth()) {
            return 0; // Default to 0 for out-of-bounds indices
        }
        return index; // Return tile index
    }

    /**
     * Converts a pixel y-coordinate to a tile index.
     *
     * @param y The y-coordinate in pixels.
     * @return The corresponding tile index, or 0 if out of bounds.
     */
    public int getYView(int y) {
        int index = y / TILE_SIZE; // Convert pixel coordinate to tile index
        if (index < 0 || index >= getHeight()) {
            return 0; // Default to 0 for out-of-bounds indices
        }
        return index; // Return tile index
    }

    /**
     * Checks for collision at the specified pixel coordinates.
     * A collision occurs if the tile at the corresponding index is not sky (1).
     *
     * @param x The x-coordinate in pixels.
     * @param y The y-coordinate in pixels.
     * @return true if there is a collision (non-sky tile), false otherwise.
     */
    public boolean checkCollision(int x, int y) {
        return block(getXView(x), getYView(y)) != 1; // Check if tile is not sky
    }

    /**
     * Validates the tile array to ensure all rows have the same width.
     * Logs errors for inconsistent row lengths.
     */
    private void validateTiles() {
        int expectedWidth = tiles[0].length; // Expected width based on first row
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].length != expectedWidth) {
                System.err.println("Row " + i + " has incorrect length: " + tiles[i].length + ", expected: " + expectedWidth);
            }
        }
    }
}

package universite_paris8.iut.rgarry.ashforged.model;

/*
    Cette classe définit la structure du terrain, c'est-à-dire quel type de tuile est où.
 */
public class Field {
    private int[][] tiles;

    /***
     * Permet la création de la map. Chaque chiffre représente une tuile diférente.
     * Par exemple 1: bloque de ciel, 2: bloque de terre.
     */
    public Field() {
        this.tiles = new int[][] {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,5,3,2,2,2,2,2,4,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7}
        };


    }

    /***
     * Retourne le nombre de tuile en x et en y contenue dans le tableau de la map
     *
     * @param x
     * @param y
     * @return
     */
    public int block(int x, int y) {
        if (y < 0 || y >= tiles.length || x < 0 || x >= tiles[0].length) {
            System.err.println("Accès invalide au bloc : x=" + x + ", y=" + y);
            return 1; // Valeur par défaut (ciel ou autre valeur sûre)
        }
        return this.tiles[y][x];
    }



    public void setBlock(int x, int y,int bloc) {
        this.tiles[y][x]=bloc;
    }



    /***
     * Retourne la longueur du tableau, c'est-à-dire le nombre de tuile.
     * @return
     */
    public int getWidth() {
        return this.tiles[0].length;
    }

    /***
     * Retourne la hauteur du tableau, c'est-à-dire le nombre de tuile.
     *
     * @return
     */
    public int getHeight() {
        return this.tiles.length;
    }

    public int getXView(int x) {
        int index = x / 64;
        if (index < 0 || index >= getWidth()) {
            System.err.println("getXView hors limites : x=" + x + ", index=" + index);
            return 0; // Ou une valeur par défaut
        }
        return index;
    }

    public int getYView(int y) {
        int index = y / 64;
        if (index < 0 || index >= getHeight()) {
            System.err.println("getYView hors limites : y=" + y + ", index=" + index);
            return 0; // Ou une valeur par défaut
        }
        return index;
    }



    public boolean checkCollision(int x, int y) {
        return block(getXView(x), getYView(y)) != 1;
    }

    private void validateTiles() {
        int expectedWidth = tiles[0].length;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].length != expectedWidth) {
                System.err.println("Ligne " + i + " a une longueur incorrecte : " + tiles[i].length + ", attendu : " + expectedWidth);
            }
        }
    }
}

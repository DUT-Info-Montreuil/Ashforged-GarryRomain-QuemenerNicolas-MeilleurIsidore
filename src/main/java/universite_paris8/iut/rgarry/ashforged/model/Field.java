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

    /***
     * Retourne le nombre de tuile en x et en y contenue dans le tableau de la map
     *
     * @param x
     * @param y
     * @return
     */
    public int block(int x, int y) {
        if (y < 0 || y >= tiles.length || x < 0 || x >= tiles[0].length) {

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

            return 0; // Ou une valeur par défaut
        }
        return index;
    }

    public int getYView(int y) {
        int index = y / 64;
        if (index < 0 || index >= getHeight()) {

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
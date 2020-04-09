

public class Solveur {

    private int grille[][];
    private static int TAILLE = 9;
    private long t0;
    private long t1 ;
    /*
    private static int[][] tabBase =   
                    {
                        {0,0,0,0,9,5,0,0,4},              //Tableau initial
                        {5,3,0,4,0,8,7,0,2},
                        {0,0,0,7,0,0,6,0,3},
                        {9,0,0,0,3,4,0,8,0},
                        {0,4,0,0,1,0,0,7,0},
                        {0,2,0,5,7,0,0,0,6},
                        {4,0,9,0,0,2,0,0,0},
                        {6,0,7,9,0,3,0,2,1},
                        {2,0,0,6,5,0,0,0,0}
                    };*/

    /**
     *
     * @param tableau un tableau d'entier a deux dimensions de taille 9x9
     */
    public Solveur(int tableau[][]) {
        this.grille = new int[TAILLE][TAILLE];

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                this.grille[i][j] = tableau[i][j];
            }


        }
    }

    /**
     *
     * @param numligne
     * @param numcolonne
     * @param val
     * @return True si il il y a val sur le carré désignée et False si non
     */
    protected boolean verifValDansCarre(int numligne, int numcolonne, int val ) {

        int carrex = numligne-numligne%3;
        int carrey = numcolonne-numcolonne%3;

        for (int i = carrex; i < carrex + 3; i++) {
            for (int j = carrey; j < carrey + 3; j++) {

                if (this.grille[i][j] == val) {

                    return true;
                }
            }
        }

        return false;
    }


    /**
     *
     * @param numcolonne
     * @param val
     * @return True si il il y a val sur la colonne désignée et False si non
     */
    protected boolean verifValDansColonne(int numcolonne, int val) {

        for (int i = 0; i < TAILLE; i++) {

            if (this.grille[i][numcolonne] == val) {

                return true;
            }
        }


        return false;
    }


    /**
     *
     * @param numligne
     * @param val
     * @return True si il il y a val sur la ligne désignée et False si non
     */
    protected boolean verifValDansLigne(int numligne, int val) {

        for (int i = 0; i < TAILLE; i++) {

            if (this.grille[numligne][i] == val) {

                return true;
            }
        }

        return false;

    }

    /**
     *
     * @return True si la résolutions a bien eu lieu False si le sudoku n'a pas de solutions
     */
    public boolean resolution()
    {

        boolean x, y, z;
        this.t0 = System.nanoTime();


        for (int ligne = 0; ligne < TAILLE; ligne++)
        {

            for (int colonne = 0; colonne < TAILLE; colonne++)
            {

                if (this.grille[ligne][colonne] == 0)
                {

                    for (int val = 1; val <= TAILLE; val++)
                    {

                        x = verifValDansLigne(ligne, val);
                        z = verifValDansColonne(colonne, val);
                        y = verifValDansCarre(ligne, colonne, val);

                        if (!x && !y && !z)
                        {

                            this.grille[ligne][colonne] = val;

                            if (resolution())
                            {
                                return true;
                            } else
                                {

                                this.grille[ligne][colonne] = 0;
                                }

                        }

                    }
                    return false;

                }
            }

        }
        this.t1 = System.nanoTime();
        return true;
        
    }

    /**
     *
     * @return la grille résolue sous forme d'un int[][]
     */
        public int[][] getGrilleResolue()
        {
            return this.grille;
        }

        public void setGrille(int numligne, int numcolonne, int val){
            this.grille[numligne][numcolonne] = val;
        }

    /**
     *
     * @return le nombre de Nanosecondes nécéssaire a la résolution de la grille.
     */

    public long getTimer(){

        return this.t1 - this.t0;
    }

     public String getTimerToString(){

        return Long.toString(this.t1 - this.t0);
    }

/*
public static void main(String[] args) {
    int[][] tabres = new int[TAILLE][TAILLE];
    long temps;

    Solveur sudoku = new Solveur(tabBase);
    
    if (sudoku.resolution()) {
            tabres = sudoku.getGrilleResolue();

    for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                System.out.print(tabres[i][j]);

            }
            System.out.println("");

        }
        System.out.println("le sudoku a été résolu en " + sudoku.getTimer() + " NanoSecondes");
        
    }else{
        System.out.println("il n'y a pas de solutions");
    }



}
*/

}

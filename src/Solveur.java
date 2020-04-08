
public class Solveur {
    private int grille[][];
    private static int TAILLE = 9;

    public Solveur(int tableau[][]) {
        this.grille = new int[TAILLE][TAILLE];

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                this.grille[i][j] = tableau[i][j];
            }


        }
    }
    public int[][] getGrilleResolue(){
        return this.grille;
    }

    private boolean verifValDansCarre(int numligne, int numcolonne, int val) {

        int carrex = numligne - numligne % 3;
        int carrey = numcolonne - numcolonne % 3;
/*
            if (numligne <= 2){
                carrex = 0;
            }else if(numligne>2 && numligne <= 5){
                carrex = 2;
            }else if(numligne>5){
                carrex = 5;
            }
            if (numcolonne <= 2){
                carrey = 0;
            }else if(numcolonne>2 && numcolonne <= 5){
                carrey = 2;
            }else if (numcolonne>5){
                carrey = 5;
            }
*/
        for (int i = carrex; i < carrex + 3; i++) {
            for (int j = carrey; j < carrey + 3; j++) {

                if (this.grille[i][j] == val) {

                    return true;
                }
            }
        }

        return false;
    }

    private boolean verifValDansColonne(int numcolonne, int val) {


        for (int i = 0; i < TAILLE; i++) {


            if (this.grille[i][numcolonne] == val) {

                return true;
            }
        }


        return false;
    }

    private boolean verifValDansLigne(int numligne, int val) {
        for (int i = 0; i < TAILLE; i++) {

            if (this.grille[numligne][i] == val) {
                return true;
            }
        }

        return false;

    }

    public boolean resolution() {
        boolean x, y, z;
        for (int ligne = 0; ligne < TAILLE; ligne++) {
            for (int colonne = 0; colonne < TAILLE; colonne++) {
                if (this.grille[ligne][colonne] == 0) {
                    for (int val = 1; val <= TAILLE; val++) {
                        x = verifValDansLigne(ligne, val);
                        z = verifValDansColonne(colonne, val);
                        y = verifValDansCarre(ligne, colonne, val);
                        if (!x && !y && !z) {
                            this.grille[ligne][colonne] = val;
                            if (resolution()) {
                                return true;
                            } else {
                                this.grille[ligne][colonne] = 0;
                            }

                        }

                    }
                    return false;

                }
            }

        }
        return true;
    }
}
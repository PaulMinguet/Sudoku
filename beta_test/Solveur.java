
public class Solveur {

    public static int[][] GRID_TO_SOLVE = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };

private int grille[][];
private static int TAILLE  = 9;

        public Solveur(int tableau[][]) {
        this.grille = new int[TAILLE][TAILLE];

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                this.grille[i][j] = tableau[i][j];
            }


        }
    }

        private boolean verifValDansCarre ( int numligne, int numcolonne, int val){
            int carrex, carrey;

            if (numligne <= 3){
                carrex = 0;
            }else if(numligne>3 && numligne <= 6){
                carrex = 3;
            }else{
                carrex = 6;
            }
            if (numcolonne <= 3){
                carrey = 0;
            }else if(numcolonne>3 && numcolonne <= 6){
                carrey = 3;
            }else{
                carrey = 6;
            }
            for (int i = carrex; i < carrex + 3; i++){
                for (int j = carrey; j < carrey + 3; j++){
                    if (grille[i][j] == val){
                        return true;
                    }

                }
            }
            return false;
        }

        private boolean verifValDansColonne ( int numcolonne, int val ){
            for (int i = 0; i < TAILLE; i++)
            {
                if (board[i][numcolonne] == val)
                {
                    return true;
                }
            }
            return false;
        }

        private boolean verifValDansLigne ( int numligne, int val ){
            for (int i = 0; i < TAILLE; i++)
                if (board[numligne][i] == val)
                    return true;

            return false;

        }

        public boolean resolution(){
            for (int ligne = 0; ligne < TAILLE; ligne++) {
                for (int colonne = 0; colonne < TAILLE; colonne++) {
                    if(this.grille[ligne][colonne] == 0){
                        for (int val = 1; val <= TAILLE ; val++) {
                            if (verifValDansCarre(ligne,colonne,val) && verifValDansColonne(colonne,val) && verifValDansLigne(ligne,val))
                                grille[ligne][colonne] = val;
                            if (resolution()){
                                return true;

                            }else{
                                grille[ligne][colonne] = 0;
                            }
                        }

                    }
                }

            }
        }

        public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Solveur solveur = new Solveur(GRID_TO_SOLVE);
        System.out.println("Sudoku grid to solve");
        solveur.display();

        // we try resolution
        if (solveur.solve()) {
            System.out.println("Sudoku Grid solved with simple BT");
            solveur.display();
        } else {
            System.out.println("Unsolvable");
        }
    }

}


}
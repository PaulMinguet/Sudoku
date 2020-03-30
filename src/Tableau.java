import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;

public class Tableau{
	int[][][] tabBase = {{{0,0,0,0,9,5,0,0,4},				//Tableau initial
						{5,3,0,4,0,8,7,0,2},
						{0,0,0,7,0,0,6,0,3},
						{9,0,0,0,3,4,0,8,0},
						{0,4,0,0,1,0,0,7,0},
						{0,2,0,5,7,0,0,0,6},
						{4,0,9,0,0,2,0,0,0},
						{6,0,7,9,0,3,0,2,1},
						{2,0,0,6,5,0,0,0,0}},

						{{0,0,0,0,0,0,0,0,0},				//Tableau indices gauche
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}},

						{{0,0,0,0,0,0,0,0,0},				//Tableau indices droit
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}},

						{{0,0,0,0,0,0,0,0,0},				//Tableau exposants gauche
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}},

						{{0,0,0,0,0,0,0,0,0},				//Tableau exposants droit
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}},

						{{0,0,0,0,0,0,0,0,0},				//Tableau utilisateur
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
			 			{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}},

						{{1,2,3},
						{4,5,6},
						{7,8,9},
						{0,10,0}}};


	public int[][] getTab(int[][] tabRec){					//Méthode pour récupérer un tableau			
		return tabRec;
	}

	public void setValue(int type, int x, int y, int value){	//Méthode pour définir une valeur dans le tableau "type"
		tabBase[type][x][y]=value;
	}	

	public int getValeur(int type, int x, int y){				//Méthode pour récupérer la valeur d'un tableau
		return tabBase[type][x][y];
	}	

	public String getValeurString(int type, int x, int y){		//Méthode pour récupérer la valeur en String d'un tableau
		return Integer.toString(tabBase[type][x][y]);
	}
}
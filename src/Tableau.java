import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;

public class Tableau{
	int[][][] tabBase = {{{0,0,0,0,0,0,0,0,0},				//Tableau initial
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}},

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

	int[][] grille = 	{{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0}};

	/**
	 *
	 * @param tabRec
	 * @return
	 */
	public int[][] getTab(int[][] tabRec){					//Méthode pour récupérer un tableau			
		return tabRec;
	}

	/**
	 *
	 * @param type
	 * @param x
	 * @param y
	 * @param value
	 */
	public void setValue(int type, int x, int y, int value){	//Méthode pour définir une valeur dans le tableau "type"
		tabBase[type][x][y]=value;
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param value
	 */

	public void setValueGrille(int x, int y, int value){	//Méthode pour définir une valeur dans le tableau "type"
		grille[x][y]=value;
	}

	/**
	 *
	 * @param type
	 * @param x
	 * @param y
	 * @return
	 */

	public int getValeur(int type, int x, int y){				//Méthode pour récupérer la valeur d'un tableau
		return tabBase[type][x][y];
	}

	/**
	 * 
	 * @param type
	 * @param x
	 * @param y
	 * @return
	 */

	public String getValeurString(int type, int x, int y){		//Méthode pour récupérer la valeur en String d'un tableau
		return Integer.toString(tabBase[type][x][y]);
	}
}
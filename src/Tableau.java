import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Tableau{
	public Tableau(){}
	private int i, j;

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

						{{1,2,3},							//Tableau des valeurs (à gauche)
						{4,5,6},
						{7,8,9},
						{0,10,0}}};

	int[][] grille = 	{{0,0,0,0,0,0,0,0,0},				//tableau à deux dimensions avec toutes les valeurs de la grille (tableau initial + utilisateur)
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
	 * @param tabRec = tableau que l'on veut retourner
	 * @return retourne le tableau entré dans tabRec
	 */
	public int[][] getTab(){					//Méthode pour récupérer un tableau			
		return grille;
	}

	/**
	 *
	 * @param type : tableau que l'on veut modifier (initial, indices, exposants, ...)
	 * @param x	: ligne
	 * @param y : colonne
	 * @param value : valeur à mettre
	 */
	public void setValue(int type, int x, int y, int value){	//Méthode pour définir une valeur dans le tableau "type"
		tabBase[type][x][y]=value;
	}

	/**
	 *
	 * @param x : ligne
	 * @param y : colonne
	 * @param value : valeur à mettre
	 */

	public void setValueGrille(int x, int y, int value){	//Méthode pour définir une valeur dans le tableau "type"
		grille[x][y]=value;
	}

	/**
	 *
	 * @param x : ligne
	 * @param y : colonne
	 */
	public int getValeurGrille(int x, int y){
		return grille[x][y];
	}

	/**
	 *
	 * @param x : ligne
	 * @param y : colonne
	 */
	public String getValeurGrilleString(int x, int y){
		return Integer.toString(grille[x][y]);
	}

	/**
	 *
	 * @param type : tableau que l'on veut modifier (initial, indices, exposants, ...)
	 * @param x	: ligne
	 * @param y : colonne
	 * @return retourne la valeur que l'on veut sur la ligne x et la colonne y
	 */
	public int getValeur(int type, int x, int y){				//Méthode pour récupérer la valeur d'un tableau
		return tabBase[type][x][y];
	}

	/**
	 *
	 * @param type : tableau que l'on veut modifier (initial, indices, exposants, ...)
	 * @param x	: ligne
	 * @param y : colonne
	 * @return retourne la valeur que l'on veut sur la ligne x et la colonne y
	 */
	public String getValeurString(int type, int x, int y){		//Méthode pour récupérer la valeur en String d'un tableau
		return Integer.toString(tabBase[type][x][y]);
	}

	public boolean verifFin(){
		boolean fini = false;
		int f = 0;
		//System.out.println("\n**Verification**");
		for(j = 0; j < 9; j++){
			for(i = 0; i < 9; i++){
				//System.out.print(getValeurGrille(i,j));
				if(getValeurGrille(i,j) == 0){
					f = 1;
				}
			}
			//System.out.println("");
		}
		if(f == 0){
			fini = true;
		}

		return fini;
	}

	public void sauvegarder(){
		JFileChooser dialogue = new JFileChooser(new File("."));						//On ouvre une fenêtre de dialogue de sauvegarde
		File fichier;
		String buf = "";
		int[] buffer = new int[82];			//1 place de plus que ce qu'il faut par sécurité
		if(dialogue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			fichier = dialogue.getSelectedFile();
			String fichierChoisi = new String(fichier.toString());
			
			try{									//On écrit les valeurs de tous les tableaux dans le fichier choisi
				FileOutputStream fos = new FileOutputStream(fichier);
				DataOutputStream ecrire = new DataOutputStream(fos);
				for(i = 0; i < 9; i++){
					for(j = 0; j < 9; j++){
						buf += Integer.toHexString(getValeur(0,i, j));
					}
					buffer[i] = Integer.parseInt(buf);
					buf = "";
				}
				for(i = 0; i < 9; i++){
					ecrire.writeInt(buffer[i]);
				}
				fos.close();
				try{
					ecrire.close();
				}catch(IOException ioee){
					ioee.printStackTrace();
				}

			}catch(IOException ioec){
				ioec.printStackTrace();
			}
		}
	}

	public void importer(){
		JFileChooser dialogue = new JFileChooser(new File("."));
		File fichier;
		String buf = "";
		int[] buffer = new int[82];
		if(dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			fichier = dialogue.getSelectedFile();
			try{
				FileInputStream fis = new FileInputStream(fichier);										//On écrit les valeurs du fichier dans les tableaux et dans la grille.
				DataInputStream lire = new DataInputStream(fis);
				for(i = 0; i < 9; i++){
					buffer[i] = lire.readInt();
					//System.out.println(Integer.toString(buffer[i]));
				}
				try{
					lire.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
				fis.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			for(i = 0; i < 9; i++){
				buf = Integer.toString(buffer[i]);
				//System.out.println("buf length = " + buf.length());
				for(j = 0; j < 9; j++){	
					if(j-(9-buf.length()) >= 0){
						//System.out.println(buf);
						//System.out.print("; j = "+j);
						//System.out.println("; New buf = " + buf);
						setValue(0,i,j,Integer.parseInt(buf.substring(j-(9-buf.length()),j-(9-buf.length())+1)));
						setValueGrille(i,j,Integer.parseInt(buf.substring(j-(9-buf.length()),j-(9-buf.length())+1)));
					}else{
						//System.out.print("0");
						setValue(0,i,j,0);
						setValueGrille(i,j,0);
					}
				}
			}
		}
	}
}
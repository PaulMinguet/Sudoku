import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Main{
		//options de la première boite de dialogue
	public static final int CREER           = 1;
	public static final int OUVRIR          = 2;
	//choix de la resolution
	public static final int MANUEL          = 1;
	public static final int AUTOMATIQUE     = 2;
	//texte des boites de dialogue
	public static final String[] menu       = {"MANUEL","AUTOMATIQUE"};
	public static final String[] ouverture  = {"nouveau","ouvrir"};

	public static void main(String[] args) throws IOException{
				JFrame fenetre = new JFrame();
		String fichier;
		int choix;
		

		choix = JOptionPane.showOptionDialog(null,"sudoku",
			"menu principal", JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE, null, ouverture,ouverture[1])+1;

		switch (choix){
			case OUVRIR:

				choix = JOptionPane.showOptionDialog(null,"action",
					"menu principal", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, menu,menu[1])+1;

				switch (choix){ 
					case MANUEL:
					Main.resoManu();
					break;

					case AUTOMATIQUE:
					Main.resolutionAuto();
					break;

					default:
					System.exit(0);
					}
			
				break;

			case CREER:
			Main.creationGrille();
			break;
			default:
			System.exit(0);
			}

	}

	public static void creationGrille(){
		Tableau tableau = new Tableau();
		Creer creation = new Creer(tableau);
		JFrame fenetre = new JFrame("Sudoku");
		fenetre.addMouseListener(creation);
		fenetre.setSize(1000, 500);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    fenetre.setResizable(false);
		fenetre.add(creation, BorderLayout.CENTER);
		fenetre.setVisible(true);
	}

	public static void resoManu(){
		Tableau tableau = new Tableau();
		Grille grille = new Grille(tableau);
		JFrame fenetre = new JFrame("Sudoku");
		fenetre.addMouseListener(grille);
		fenetre.setSize(1000, 500);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    fenetre.setResizable(false);
		fenetre.add(grille, BorderLayout.CENTER);
		fenetre.setVisible(true);
	}
		public static void resolutionAuto(){
		Tableau tab = new Tableau();
		tab.importer();
		Solveur resolv = new Solveur(tab.getTab());
		resolv.resolution();
		Grilleauto auto = new Grilleauto(resolv.getGrilleResolue(),resolv.getTimerToString());
		JFrame fenetre = new JFrame("Sudoku");
		fenetre.setSize(1000, 500);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		fenetre.add(auto, BorderLayout.CENTER);
		fenetre.setVisible(true);
	}
}
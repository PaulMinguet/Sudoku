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

	public static void main(String[] args) throws IOException{
	}

	public void creationGrille(){
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

	public void resoManu(){
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
}
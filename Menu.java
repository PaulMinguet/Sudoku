import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Menu extends JComponent implements MouseListener{
	private int x = 0;											//Définition des variables
	private int y = 0;
	private int clicX;
	private int clicY;
	private int tailleCase = 0;
	Tableau tableau;

	public Menu(Tableau tabBase){
		this.tableau = tabBase;
	}

	/**
	 *
	 * @param pinceau
	 */
  	@Override
  	protected void paintComponent(Graphics pinceau) {
    	Graphics secondPinceau = pinceau.create();				//On crée une copie du pinceau
    	if (this.isOpaque()) {
      		secondPinceau.setColor(this.getBackground());
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    	}

    	//Dessin ici -------------------------------------------
    	if(this.getWidth()/this.getHeight() < 1){				//Définir la taille d'une case en fonction de celle de la fenêtre
    		this.tailleCase = this.getWidth()/9;				//Si format portrait : case = largeur totale / 9
    	}else{
    		this.tailleCase = this.getHeight()/9;				//Sinon case = longueur totale / 9
    	}

    	this.x = (this.getWidth()/2-(3/2)*this.tailleCase);						//Positionner le titre "Sudoku"
	    this.y = 2*((this.getHeight())/8);

	    secondPinceau.setColor(Color.black);
    	secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase));
    	secondPinceau.drawString("Sudoku", this.x-2*this.tailleCase/4, this.y+2*this.tailleCase/3);	

    	/*---------------------------------------------Créer grille---------------------------------------------*/
    	this.x = (this.getWidth()/2-(3/2)*this.tailleCase-2*this.tailleCase);						//Positionner le bouton Créer grille
	    this.y = 4*((this.getHeight())/8);

	    secondPinceau.setColor(new Color(128, 208, 255));					//Afficher le bouton "Créer grille"
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Créer grille", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);	

    	/*---------------------------------------------Résoudre grille---------------------------------------------*/
    	this.x = (this.getWidth()/2-(3/2)*this.tailleCase+2*this.tailleCase);						//Positionner le bouton Résoudre grille
	    this.y = 4*((this.getHeight())/8);

	    secondPinceau.setColor(new Color(128, 208, 255));					//Afficher le bouton "Résoudre grille"
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Résoudre grille", this.x+2*this.tailleCase/4, this.y+2*this.tailleCase/3);	

	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	/**
	 *
	 * @param e
	 */
	public void mouseClicked(MouseEvent e){
		this.clicX = e.getX();
		this.clicY = e.getY();
		if(this.clicX > (this.getWidth()/2-(3/2)*this.tailleCase+2*this.tailleCase) && this.clicX < (this.getWidth()/2-(3/2)*this.tailleCase+2*this.tailleCase)+this.tailleCase*3&&
			this.clicY > 4*((this.getHeight())/8)+30 && this.clicY < 4*((this.getHeight())/8)+this.tailleCase+30){
			System.out.println("Résoudre");
		}else if(this.clicX > (this.getWidth()/2-(3/2)*this.tailleCase-2*this.tailleCase) && this.clicX < (this.getWidth()/2-(3/2)*this.tailleCase-2*this.tailleCase)+this.tailleCase*3&&
			this.clicY > 4*((this.getHeight())/8)+30 && this.clicY < 4*((this.getHeight())/8)+this.tailleCase+30){
			System.out.println("Créer");
		}
	}
}
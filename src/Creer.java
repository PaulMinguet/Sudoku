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
import java.io.DataOutputStream;
import java.io.IOException;

public class Creer extends JComponent implements MouseListener{
	private int x = 0;											//Définition des variables
	private int y = 0;
	private int i = 0;
	private int j = 0;
	private int k = 0;
	private int l = 0;
	private int sourisX;
	private int sourisY;
	private int clicX;
	private int clicY;
	private int posX;
	private int posY;
	private int posAddX;
	private int posAddY;
	private int tailleCase;
	private int addEtat = 0;
	private int boutonClic;
	private int valAdd;
	private int incertitude = 0;
	private int incertnum = 1;
	private int erreur = 0;
	private Tableau tableau;
	private Solveur solv;

	/**
	 *
	 * @param grille représente le tableau que prend en argument le constructeur de Creer pour les interactions entre Creer et Tableau (via this.tableau)
	 */
	public Creer(Tableau grille){
		this.tableau = grille;
		this.solv = new Solveur(tableau.grille);
	}

	public boolean verif(int ligne, int colonne, int valeur, Solveur solvi){		//méthode pour vérifier si l'utilisateur a le droit de mettre une valeur à un certain endroit
		boolean x = solvi.verifValDansLigne(ligne, valeur);							//Vérifie s'il n'y a pas déjà la même valeur sur la même ligne
        boolean z = solvi.verifValDansColonne(colonne, valeur);						//Vérifie s'il n'y a pas déjà la même valeur sur la même colonne
		boolean y = solvi.verifValDansCarre(ligne, colonne, valeur);				//Vérifie s'il n'y a pas déjà la même valeur dans le même bloc
        if (!x && !y && !z)
        {
        	return true;															//Renvoie vrai si l'utilisateur peut poser sa valeur, faux sinon
        }else{
        	return false;
        }
	}

	/**
	 *
	 * @param pinceau pinceau pour le graphique
	 */
  	@Override
  	protected void paintComponent(Graphics pinceau) {
    	Graphics secondPinceau = pinceau.create();				//On crée une copie du pinceau
    	if (this.isOpaque()) {
      		secondPinceau.setColor(this.getBackground());
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    	}

    	//Dessin ici ------------------------------------------- 
    	secondPinceau.setColor(Color.lightGray);

    	if(this.getWidth()/this.getHeight() < 1){				//Définir la taille d'une case en fonction de celle de la fenêtre
    		this.tailleCase = this.getWidth()/9;				//Si format portrait : case = largeur totale / 9
    	}else{
    		this.tailleCase = this.getHeight()/9;				//Sinon case = longueur totale / 9
    	}

    	/*---------------------------------------------Grille---------------------------------------------*/

	    this.x = (this.getWidth()-this.tailleCase*9)/2;			//Centrer la grille
	    this.y = (this.getHeight()-this.tailleCase*9)/2;

	    
	    int modBloc=0;											//modulo pour la couleur de fond
	    for(i = 0; i < 3; i++){
    		for(j = 0; j < 3; j++){
    			if (modBloc++%2==0)								//modulo 2 : fond gris pour faire les blocs de 3x3
		    	secondPinceau.fillRect(this.x+i*this.tailleCase*3, this.y+j*this.tailleCase*3, this.tailleCase*3, this.tailleCase*3);
		    }
	    }

	    this.x = (this.getWidth()-this.tailleCase*9)/2;			//Réinitialliser le centrage de la grille
	    this.y = (this.getHeight()-this.tailleCase*9)/2;


    	for(i = 0; i < 9; i++){									//Boucle pour afficher la grille
    		for(j = 0; j < 9; j++){
    			secondPinceau.setColor(Color.black);
    			secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/2));
		    	secondPinceau.drawRect(this.x+i*tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);	//On dessine une case
		    	if (this.tableau.getValeur(0,j,i) != 0){															//Si la valeur dans le tableau de base est différente de 0, on affiche la valeur dans la case créée
		    		secondPinceau.drawString(this.tableau.getValeurString(0,j,i), this.x+i*tailleCase+(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
		    	}else{																								//Sinon, on affiche les valeurs dans le tableau de l'utilisateur
		    		secondPinceau.setColor(Color.blue);																//Afficher tableau utilisateur
		    		secondPinceau.setFont(new Font("default", Font.PLAIN, tailleCase/2));
		    		if (this.tableau.getValeur(5,j,i) != 0){														//Si il n'y a rien dans le tableau de base à la même place, on affiche, sinon rien
		    			secondPinceau.drawString(this.tableau.getValeurString(5,j,i), this.x+i*tailleCase+(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
		    		}
		    	}
		    }
	    }

	    /*---------------------------------------------Bouton Sauvegarder---------------------------------------------*/

	    this.x = (this.getWidth()-this.tailleCase*4);						//Positionner le bouton Sauvegarde
	    this.y = (this.getHeight()-this.tailleCase*3)/8;

	    secondPinceau.setColor(new Color(128, 208, 255));					//Afficher le bouton "Sauvegarder"
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Sauvegarder", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);	

    	/*---------------------------------------------Bouton Importer---------------------------------------------*/

	    this.x = (this.getWidth()-this.tailleCase*4);						//Positionner le bouton Importer
	    this.y = 3*((this.getHeight()-this.tailleCase*3)/8);

	    secondPinceau.setColor(new Color(128, 208, 255));					//Afficher le bouton "Importer"
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Importer", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);	

    	/*---------------------------------------------Tableau valeurs gauche---------------------------------------------*/

	    this.x = ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2;						//Positionner le tableau de valeurs à gauche
	    this.y = (this.getHeight()-this.tailleCase*3)/2-30;

	    if(this.addEtat == 1){																		//Si une case est cliquée (addEtat = 1) alors on affiche le tableau de valeurs
	    	for(i = 0; i < 3; i++){
	    		for(j = 0; j < 3; j++){
		    		secondPinceau.setColor(new Color(128, 208, 255));								//Créer le tableau de valeurs
		    		secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/2));
		    		secondPinceau.fillRect(this.x+i*tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
		    		secondPinceau.setColor(Color.black);
			    	secondPinceau.drawRect(this.x+i*tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
			    	secondPinceau.drawString(this.tableau.getValeurString(6,j,i), this.x+i*tailleCase+(3/2)*(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
			    }
	    	}
	    	this.x = (((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2);					//"X" pour supprimer
	    	secondPinceau.setColor(new Color(128, 208, 255));										//Affiche le "X"
    		secondPinceau.fillRect(this.x+tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
    		secondPinceau.setColor(Color.black);
	    	secondPinceau.drawRect(this.x+tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
	    	secondPinceau.drawString("X", this.x+tailleCase+(3/2)*(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
	    }
  	}

  	/*----------------------------------------------------------------------------------------------Interactions avec la souris----------------------------------------------------------------------------------------------*/

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	/**
	 *
	 * @param e représente l'évènement lorsque la souris est cliquée
	 */
	public void mouseClicked(MouseEvent e){
		
		this.clicX = e.getX();						//On récupère les coordonnées de la souris lors du clic
		this.clicY = e.getY();

		/*--------------------------------------------Clics dans la grille--------------------------------------------*/

		if(this.clicX > (this.getWidth()-this.tailleCase*9)/2 && this.clicX < (tailleCase*9+(this.getWidth()-this.tailleCase*9)/2) &&		//Si on clique dans la grille
			this.clicY > (this.getHeight()-this.tailleCase*9)/2 && this.clicY < (tailleCase*9+(this.getHeight()-this.tailleCase*9)/2)){
			this.clicY = e.getY()-30;
			this.posX = (clicX-(this.getWidth()-this.tailleCase*9)/2)/tailleCase;									//On récupère la place du clic dans le tableau (x et y)
			this.posY = (clicY-(this.getHeight()-this.tailleCase*9)/2)/tailleCase;

			if(this.addEtat == 0){																					//On affiche le tableau de valeurs à gauche s'il n'y a rien sur la case dans tabBase
				this.addEtat++;																						//et s'il n'est pas déjà affiché; Sinon on l'efface
				repaint();
			}else if(this.addEtat == 1){
				this.addEtat--;
				repaint();
			}
		}

		/*--------------------------------------------Clics dans le tableau de séléction--------------------------------------------*/

		if(this.addEtat == 1 && this.clicX > ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2 &&
		this.clicX < ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2+3*this.tailleCase && this.clicY > (this.getHeight()-this.tailleCase*3)/2-30 && this.clicY < (this.getHeight()-this.tailleCase*3)/2-30+4*this.tailleCase){
			this.clicY = e.getY()-4;																				//Si on clique dans le tableau de valeurs
			this.clicY += 30;

			this.posAddX = (clicX-((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2)/tailleCase;			//On récupère la place du clic dans le tableau (x et y)
			this.posAddY = ((clicY-((this.getHeight()-this.tailleCase*9)/2-this.tailleCase*3)/2)/tailleCase)-5;
			this.valAdd = tableau.getValeur(6,posAddY,posAddX);
			//System.out.println("posX = " + this.posX + "; posY = " + this.posY + "; posAddX = " + this.posAddX + "; posAddY = " + this.posAddY + "; valAdd = " + this.valAdd);

			if(this.valAdd == 10){						//Si on clique sur le "X" alors on remplace les valeurs à cette place par 0
				for (int z = 0; z <= 5 ; z++) {				
					this.tableau.setValue(z, posY, posX, 0);
					solv.setGrille(posX, posY, 0);
				}
				repaint();
				this.addEtat--;
			}else{										//Sinon on place la valeur choisie dans le tableau utilisateur si ce n'est pas contraire aux contraintes du Sudoku et on l'affiche sur la grille

				if(verif(posX, posY, valAdd, this.solv)){
					this.erreur = 0;
					solv.setGrille(posX, posY, valAdd);
				}else{
					this.erreur = 1;
				}
				if(this.erreur == 0){
					this.tableau.setValue(0, posY, posX, valAdd);
					//System.out.println("0 err");
				}else{}
				this.addEtat--;							//On efface le tableau de valeurs à gauche
				repaint();
			}
		}

		/*--------------------------------------------Clics Sauvegarde--------------------------------------------*/
		if(this.clicX > (this.getWidth()-this.tailleCase*4) && this.clicX < (this.getWidth()-this.tailleCase*4)+3*this.tailleCase &&				//Si on clique sur le bouton "Sauvergarder"
			this.clicY > (this.getHeight()-this.tailleCase*3)/8+30 && this.clicY < (this.getHeight()-this.tailleCase*3)/8+this.tailleCase+30){
			//System.out.println("Save");
			for(i = 0; i < 9; i++){					//Remplir le tableau grille avec les valeurs de tabBase(0)
					for(j = 0; j < 9; j++){
						if(this.tableau.getValeur(0, i, j) == 0 && this.tableau.getValeur(5, i, j) == 0){			//S'il n'y a de valeur dans aucun tableau à la case i,j, alors on met 0 dans la variable valAdd
							this.valAdd = 0;
							//System.out.println("0");
						}else if(this.tableau.getValeur(0, i, j) == 0 && this.tableau.getValeur(5, i, j) != 0){		//S'il n'y a pas de valeur dans le tableau de base mais une dans le tableau utilisateur,
							this.valAdd = this.tableau.getValeur(5, i, j);											//Alors on met la valeur du tableau utilisateur dans la variable valAdd
							//System.out.println(this.tableau.getValeur(5,i,j));
						}else if(this.tableau.getValeur(0, i, j) != 0 && this.tableau.getValeur(5, i, j) == 0){		//S'il y a une valeur dans le tableau de base mais pas dans le tableau utilisateur,
							this.valAdd = this.tableau.getValeur(0, i, j);											//Alors on met la valeur du tableau de base dans la variable valAdd
							//System.out.println(this.tableau.getValeur(0,i,j));
						}
						this.tableau.setValueGrille(i,j,valAdd);													//On met la variable valAdd dans la grille
					}
				}

			this.tableau.sauvegarder();				//Sauvegarde dans le fichier choisi (voir Tableau.java)
		}

		/*--------------------------------------------Clics Import--------------------------------------------*/
		if(this.clicX > (this.getWidth()-this.tailleCase*4) && this.clicX < (this.getWidth()-this.tailleCase*4)+3*this.tailleCase &&					//Si on clique sur le bouton "Importer"
			this.clicY > 3*((this.getHeight()-this.tailleCase*3)/8)+30 && this.clicY < 3*((this.getHeight()-this.tailleCase*3)/8)+this.tailleCase+30){
			//System.out.println("Import");
			this.tableau.importer();				//Importation depuis le fichier choisi (voir Tableau.java)
			repaint();								//"Refresh" de la fenêtre
		}
	}
}

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

public class Grilleauto extends JComponent{
	private int[][] grille;
	private static int TAILLE = 9;
		private int x = 0;											//Définition des variables
		private int y = 0;
		private int i = 0;
		private int j = 0;
		private int tailleCase;
		private String temps;


                    public Grilleauto(int[][] tableau,String timer){

                    	this.grille = new int[TAILLE][TAILLE];

                    	for (int i = 0; i < TAILLE; i++) {
                    		for (int j = 0; j < TAILLE; j++) {
                    			this.grille[i][j] = tableau[i][j];
                    		}
                    	}
                    	this.temps = timer;

                    }


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
		    	if (this.grille[j][i] != 0){		//Si la valeur dans le tableau de base est différente de 0, on affiche la valeur dans la case créée
		    		secondPinceau.drawString(Integer.toString(this.grille[j][i]), this.x+i*tailleCase+(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
		    	}

		    }																						
		}

		this.x = (this.getWidth()-this.tailleCase*4);						//Positionner le bouton Sauvegarde
	    this.y = (this.getHeight()-this.tailleCase*3)/8;

	   					//Afficher le bouton "Sauvegarder"
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawString(this.temps+" nanosecondes", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);			

	}


}
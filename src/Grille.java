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

public class Grille extends JComponent implements MouseListener{
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
	Tableau tableau;

	public Grille(Tableau tabBase){
		this.tableau = tabBase;
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

	    
	    int modBloc=0;
	    for(i = 0; i < 3; i++){
    		for(j = 0; j < 3; j++){
    			if (modBloc++%2==0)
		    	secondPinceau.fillRect(this.x+i*this.tailleCase*3, this.y+j*this.tailleCase*3, this.tailleCase*3, this.tailleCase*3);
		    }
	    }

	    this.x = (this.getWidth()-this.tailleCase*9)/2;			//Centrer la grille
	    this.y = (this.getHeight()-this.tailleCase*9)/2;


    	for(i = 0; i < 9; i++){
    		for(j = 0; j < 9; j++){
    			secondPinceau.setColor(Color.black);
    			secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/2));
		    	secondPinceau.drawRect(this.x+i*tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
		    	if (this.tableau.getValeur(0,j,i) != 0){
		    		secondPinceau.drawString(this.tableau.getValeurString(0,j,i), this.x+i*tailleCase+(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
		    	}else{
		    		secondPinceau.setColor(Color.blue);											//Afficher tableau utilisateur
		    		secondPinceau.setFont(new Font("default", Font.PLAIN, tailleCase/2));
		    		if (this.tableau.getValeur(5,j,i) != 0){									//Si il n'y a rien dans le tableau de base à la même place, on affiche, sinon rien
		    			secondPinceau.drawString(this.tableau.getValeurString(5,j,i), this.x+i*tailleCase+(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
		    		}
		    	}
		    	secondPinceau.setColor(Color.gray);
	    		secondPinceau.setFont(new Font("default", Font.PLAIN, tailleCase/4));
	    		for(k = 1; k <= 4; k++){
	    		if(this.tableau.getValeur(k,j,i) != 0)
	    		secondPinceau.drawString(this.tableau.getValeurString(k,j,i), this.x+i*tailleCase+(k==1||k==3?1:8)*(this.tailleCase/10), 
	    			this.y+j*tailleCase+(k==3||k==4?9:1)*this.tailleCase/(k==3||k==4?10:4));		//Afficher les indices/exposants (hésitation du joueur).
	    		}																					//Calcul : si tableau 1 (haut gauche) ou 3 (bas gauche) alors pas de décalage de la gauche
		    }																						//Sinon décalage de 8/10 d'une case; si tableau 3 ou 4 (bas droite) alors décalage de 9/x d'une case vers le bas
	    }																							//Sinon pas de décalage; si tableau 3 ou 4 alors décalage de 9/10 d'une case vers le bas sinon 4/10

	    /*---------------------------------------------Bouton incertitude---------------------------------------------*/

	    this.x = ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2;						//Positionner le bouton incertitude
	    this.y = (this.getHeight()-this.tailleCase*3)/8;

	    if(this.incertitude == 0){
		    secondPinceau.setColor(new Color(128, 208, 255));
		}else{
			secondPinceau.setColor(Color.green);
		}
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Incertitude", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);	

	    /*---------------------------------------------Bouton Sauvegarder---------------------------------------------*/

	    this.x = (this.getWidth()-this.tailleCase*4);						//Positionner le bouton Sauvegarde
	    this.y = (this.getHeight()-this.tailleCase*3)/8;

	    secondPinceau.setColor(new Color(128, 208, 255));
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Sauvegarder", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);	

    	/*---------------------------------------------Bouton Importer---------------------------------------------*/

	    this.x = (this.getWidth()-this.tailleCase*4);						//Positionner le bouton Sauvegarde
	    this.y = 3*((this.getHeight()-this.tailleCase*3)/8);

	    secondPinceau.setColor(new Color(128, 208, 255));
	    secondPinceau.setFont(new Font("default", Font.BOLD, tailleCase/4));
		secondPinceau.fillRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
		secondPinceau.setColor(Color.black);
    	secondPinceau.drawRoundRect(this.x, this.y, this.tailleCase*3, this.tailleCase, this.tailleCase, this.tailleCase);
    	secondPinceau.drawString("Importer", this.x+3*this.tailleCase/4, this.y+2*this.tailleCase/3);	

    	/*---------------------------------------------Tableau valeurs gauche---------------------------------------------*/

	    this.x = ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2;						//Positionner le tableau de valeurs à gauche
	    this.y = (this.getHeight()-this.tailleCase*3)/2;

	    if(this.addEtat == 1){
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
	    	secondPinceau.setColor(new Color(128, 208, 255));
    		secondPinceau.fillRect(this.x+tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
    		secondPinceau.setColor(Color.black);
	    	secondPinceau.drawRect(this.x+tailleCase, this.y+j*tailleCase, this.tailleCase, this.tailleCase);
	    	secondPinceau.drawString("X", this.x+tailleCase+(3/2)*(this.tailleCase/3), this.y+j*tailleCase+2*this.tailleCase/3);
	    }
  	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseClicked(MouseEvent e){
		this.clicX = e.getX();
		this.clicY = e.getY();

		/*--------------------------------------------Clics dans la grille--------------------------------------------*/

		if(this.clicX > (this.getWidth()-this.tailleCase*9)/2 && this.clicX < (tailleCase*9+(this.getWidth()-this.tailleCase*9)/2) &&
			this.clicY > (this.getHeight()-this.tailleCase*9)/2 && this.clicY < (tailleCase*9+(this.getHeight()-this.tailleCase*9)/2)){
			this.clicY = e.getY()-30;
			this.posX = (clicX-(this.getWidth()-this.tailleCase*9)/2)/tailleCase;				//Place dans le tableau (x et y)
			this.posY = (clicY-(this.getHeight()-this.tailleCase*9)/2)/tailleCase;

			if(this.addEtat == 0 && this.tableau.getValeur(0,posY,posX) == 0){					//On affiche le tableau de valeurs à gauche s'il n'y a rien sur la case dans tabBase
				this.addEtat++;
				repaint();
			}else if(this.addEtat == 1){
				this.addEtat--;
				repaint();
			}
		}else{

			if(this.clicX > ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2 && this.clicX < (((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2)+3*this.tailleCase &&
				this.clicY > (this.getHeight()-this.tailleCase*3)/8+30 && this.clicY < (this.getHeight()-this.tailleCase*3)/8+this.tailleCase+2*this.tailleCase/3){
				if(this.incertitude == 1) {
					this.incertitude = 0;
					repaint();
				}else{
					this.incertitude = 1;
					repaint();
				}
				//System.out.println("Incertitude" + this.incertitude);
			}

		/*--------------------------------------------Clics dans le tableau de séléction--------------------------------------------*/

			//System.out.println("En dehors !");
			if(this.addEtat == 1 && this.clicX > ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2 &&
			this.clicX < ((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2+3*this.tailleCase && this.clicY > (this.getHeight()-this.tailleCase*3)/2 && this.clicY < (this.getHeight()-this.tailleCase*3)/2+4*this.tailleCase){
				this.clicY = e.getY()-4;

				this.posAddX = (clicX-((this.getWidth()-this.tailleCase*9)/2-this.tailleCase*3)/2)/tailleCase;
				this.posAddY = ((clicY-((this.getHeight()-this.tailleCase*9)/2-this.tailleCase*3)/2)/tailleCase)-5;
				this.valAdd = tableau.getValeur(6,posAddY,posAddX);

				if(this.valAdd == 10){
					this.tableau.setValue(1, posY, posX, 0);
					this.tableau.setValue(2, posY, posX, 0);
					this.tableau.setValue(3, posY, posX, 0);
					this.tableau.setValue(4, posY, posX, 0);
					this.tableau.setValue(5, posY, posX, 0);
				}else{
					if(this.incertitude == 1 && this.tableau.getValeur(0, posY, posX) == 0){
						if(this.tableau.getValeur(1, posY, posX) == 0){
							this.tableau.setValue(1, posY, posX, this.valAdd);
						}else if(this.tableau.getValeur(2, posY, posX) == 0){
							this.tableau.setValue(2, posY, posX, this.valAdd);
						}else if(this.tableau.getValeur(3, posY, posX) == 0){
							this.tableau.setValue(3, posY, posX, this.valAdd);
						}else if(this.tableau.getValeur(4, posY, posX) == 0){
							this.tableau.setValue(4, posY, posX, this.valAdd);
						}else{
							this.tableau.setValue(this.incertnum, posY, posX, valAdd);
							if(this.incertnum <= 4){
								this.incertnum++;
							}else{
								this.incertnum = 1;
							}
						}
					}else{
						this.tableau.setValue(5, posY, posX, valAdd);
					}
				}
				this.addEtat--;
				repaint();
			}

		/*--------------------------------------------Clics Sauvegarde--------------------------------------------*/
			if(this.clicX > (this.getWidth()-this.tailleCase*4) && this.clicX < (this.getWidth()-this.tailleCase*4)+3*this.tailleCase &&
				this.clicY > (this.getHeight()-this.tailleCase*3)/8+30 && this.clicY < (this.getHeight()-this.tailleCase*3)/8+this.tailleCase+30){
				System.out.println("Save");
				JFileChooser dialogue = new JFileChooser(new File("."));
				File fichier;
				if(dialogue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					fichier = dialogue.getSelectedFile();
					FileOutputStream fos = null;
					try{
						fos = new FileOutputStream(fichier);
						byte[] buf = new byte[72];
						for(k = 0; k <= 5; k++){
							for(i = 0; i < 9; i++){
								for(j = 0; j < 9; j++){
									buf = this.tableau.getValeurString(k, i, j).getBytes();
									fos.write(buf);
									/*if((j+1)%3 == 0){
										fos.write(" ".getBytes());
									}*/
									if((i*9+(j+1))%9 == 0){
										fos.write("\n".getBytes());
									}
								}
							}
							fos.write("\n".getBytes());
						}
						fos.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
				}
			}

		/*--------------------------------------------Clics Import--------------------------------------------*/
			if(this.clicX > (this.getWidth()-this.tailleCase*4) && this.clicX < (this.getWidth()-this.tailleCase*4)+3*this.tailleCase &&
				this.clicY > 3*((this.getHeight()-this.tailleCase*3)/8)+30 && this.clicY < 3*((this.getHeight()-this.tailleCase*3)/8)+this.tailleCase+30){
				System.out.println("Import");
				JFileChooser dialogue = new JFileChooser(new File("."));
				File fichier;
				if(dialogue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					fichier = dialogue.getSelectedFile();
					FileInputStream fis = null;
					try{
						fis = new FileInputStream(fichier);
						int b;
						for(k = 0; k <= 5; k++){
							for(i = 0; i < 9; i++){
								for(j = 0; j < 9; j++){
									b = fis.read()-48;	
									this.tableau.setValue(k,i,j,b);
									/*if((j+1)%3 == 0){
										fos.write(" ".getBytes());
									}*/
									if((i*9+(j+1))%9 == 0){
										fis.read();
									}
								}
							}
							fis.read();
						}
						fis.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
					repaint();
				}
			}
		}
	}
}
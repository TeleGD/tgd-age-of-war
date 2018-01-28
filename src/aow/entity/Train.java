/* au fil du temps, ligne de chemin de fer se construit */
/*  */

package aow.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import aow.World1;

public class Train {
		
		private int LONG_RAIL=48;
		private int numJoueur; // la hauteur et le placement dependent de ceci 
		private int x; // où se situe le train sur la carte graphiquement
		private int numRail; // combien de rails on a place
		private int nbrail=1280/LONG_RAIL+1; // division entiere
		
		
		private boolean end; // vaut true quand on a atteint l'autre base
		private boolean proche; // vaut true si proches de base
		private Image picture;
		
		
		
		/* constructeur, il faut faire commencer en décalant de longueur une ligne de train*/
		public Train(int idOwner)
		{
			end=false;
			proche=false;
			numJoueur=idOwner;
			numRail=0;
			try {
				picture=new Image("images/game1/track.png"); // a faire le nom
			} catch (SlickException e) {
				// nous donne la trace de l'erreur si on ne peut charger l'image correctement
				e.printStackTrace();
			}
			if(numJoueur==1)x=0;
			else
			{
				x=1280-LONG_RAIL;
			}
			
		}
	
		
		/* bouge le chemin de fer*/
		public void move()
		{
			if(numJoueur==1)
			{
				x+=LONG_RAIL; // coordonnees graphiques
				
			}
			else
			{
				x-=LONG_RAIL;
			}
			numRail++;
		}
		
		
		/* lance le mouvement et verifie si finit */
		public void update()
		{
				
			if(numRail>nbrail-3) 
			{
				proche=true;
				
				if(numRail==nbrail) // arrives
				{
					end=true;
				}
			}
			
			if(!end)this.move(); // on bouge si on n'a pas tout fait
		}
		
		public void removeRail(int nbrem)
		{
			
			numRail-=nbrem;
			numRail=Math.max(numRail, 0); // pour ne pas avoir de valeur negative
			
		}
		
		/* affichage de rail */
		public void render(GameContainer container, StateBasedGame game, Graphics g) {
			if(numJoueur==1)
			{
				for(int x=0 ; x<numRail ; x+=1)// le fait numRail fois
				{
					g.drawImage(picture, (float)(x*LONG_RAIL), (float)(663));
				}
			}
			
			else
			{
				for(int x=0 ; x<numRail ; x++)
				{
					g.drawImage(picture, (float)(1280-x*LONG_RAIL-LONG_RAIL), (float)(600));
				}
				
			}
		
		}
	
}

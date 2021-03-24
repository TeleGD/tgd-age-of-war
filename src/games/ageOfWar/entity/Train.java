/* au fil du temps, ligne de chemin de fer se construit */
/*  */

package games.ageOfWar.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.ageOfWar.World;

public class Train {

		private World world;
		private int LONG_RAIL=48;
		private int numJoueur; // la hauteur et le placement dependent de ceci
		private int x; // où se situe le train sur la carte graphiquement
		private int numRail; // combien de rails on a place
		private int nbrail=1280/LONG_RAIL+1; // division entiere


		private boolean end; // vaut true quand on a atteint l'autre base
		private boolean proche; // vaut true si proches de base
		private Image picture;
		private Audio goodMusic;
		private boolean toMove; // dit s'il faut faire avancer le rail (un update sur deux


		/* constructeur, il faut faire commencer en décalant de longueur une ligne de train*/
		public Train(World world, int idOwner)
		{
			this.world = world;
			end=false;
			proche=false;
			numJoueur=idOwner;
			numRail=0;
			goodMusic = AppLoader.loadAudio("/musics/ageOfWar/I_LIKE_TRAINS.ogg");
			picture=AppLoader.loadPicture("/images/ageOfWar/track.png"); // a faire le nom
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
		    		this.world.etat = 2 + numJoueur;
		    		goodMusic.playAsMusic(1f, .2f, true);

				}
			} else {
				proche = false;
			}

			toMove = !toMove;

			if(toMove)this.move();
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
					g.drawImage(picture, x*LONG_RAIL, (663));
				}
			}

			else
			{
				for(int x=0 ; x<numRail ; x++)
				{
					g.drawImage(picture, 1280-x*LONG_RAIL-LONG_RAIL, (600));
				}

			}

		}

}

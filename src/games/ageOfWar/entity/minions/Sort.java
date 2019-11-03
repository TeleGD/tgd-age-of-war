/* on utilise le damier, le tableau de minions, la méthode takeDamage de minions*/
/* doit durer un certain temps : a la fin du temps explose et hit le tout*/


package games.ageOfWar.entity.minions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.ageOfWar.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sort {


	private int power; // puissance du sort
	private int numJoueur; // de quel cote est le sort
	private Image picture; // les images du sort associees à chaque epoque
	private double x,y; // ira jusqu'au point culminant centre
	private double pente=(30.0-350.0)/((World.board.getX(World.tailleBoard-1)-World.board.getX(0))/2); //difference de hauteur / longueur axe des x  ; 15 sera la hauteur max
	private int i=0; //compteur d explosion
	private double speed = 5;
	private int basePower = 20;

	/* constructeur : -initialise power en fonction de l'age */
	public Sort(int numplayer, int age)
	{
		numJoueur=numplayer;
		power = basePower * age;

		if(age==1)
		{
			try {
				picture=new Image("images/ageOfWar/rock.png"); // a faire le nom
			} catch (SlickException e) {
				// nous donne la trace de l'erreur si on ne peut charger l'image correctement
				e.printStackTrace();
			}
		}
		else
		{
			if(age==2)
			{
				try {
					picture=new Image("images/ageOfWar/bullet.png"); // a faire le nom
				} catch (SlickException e) {
					// nous donne la trace de l'erreur si on ne peut charger l'image correctement
					e.printStackTrace();
				}
			}
			else
				if(age==3)
				{
					try {
						picture=new Image("images/ageOfWar/rocket.png"); // a faire le nom
					} catch (SlickException e) {
						// nous donne la trace de l'erreur si on ne peut charger l'image correctement
						e.printStackTrace();
					}
					if (numJoueur==2) {
						picture = picture.getFlippedCopy(true, false);
					}
					picture = picture.getScaledCopy((float) 0.4);

				}
		}// initialise puissance

		if(numJoueur==1)
		{
			x=140;
			y=350;
		}
		else
		{
			x=World.board.getX(World.tailleBoard-1)+50; // n taille du Damier
			y=350;
		}// initialise les coordonnees

	}

	public void update(GameContainer container, StateBasedGame game,int delta )
	{


		if(y>30)
		{
			deplace();
		}
		else // arrives a destination, fin de l objet
		{
			i++;
			if (i==24) {
				this.hitEnemies();
			}
		}

	}


	/* se deplace a chaque fois que on l appelle : a la fin arrivera au centre a la hauteur 15 */
	public void deplace()
	{
		if(numJoueur==1)
		{
			x+=speed;
			y+=pente*speed;
		}
		else
		{
			x-=speed ;
			y+=pente*speed;
		}

	} /* ou sont faits les tests pour que a la hauteur 15 boum, hit ennemis puis fin ???? */

	/* dedie a l affichage de image */
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

			g.drawImage(picture, (float)(x-picture.getWidth()/2), (float)(y-picture.getHeight()/2));


			if(y<=30) // explosion declenchee et des images a afficher
			{
				try {
					picture=new Image("images/ageOfWar/boom"+((i/8)+1)+".png");
				} catch (SlickException e) {
					// nous donne la trace de l'erreur si on ne peut charger l'image correctement
					e.printStackTrace();
				}
			}
			}





	public int getI() {
		return i;
	}

	/* cherche les ennemis et lance l'attaque, le fera quand sera au zenith, apres avoir explose */
	public void hitEnemies()
	{
		int cible=1;
		if(numJoueur==1) {
			cible=2; // maj de cible
		}

		for(int k=0 ; k<World.tailleBoard ; k++ )
		{
			if(World.board.getCase(k)==cible) {
				World.minions[k].takeDamage(power);
			}
		}



	}




}
/* ATTENTION : IL FAUDRA AJOUTER 4 IMAGES ET METTRE LES 4 CHEMINS A JOUR !!! */

package games.ageOfWar;
import java.util.Arrays;
import java.util.Random;

import games.ageOfWar.World;
import games.ageOfWar.entity.minions.Minion;

public class Board {
/*
 * classe définissant le damier de positions des unités.
 * Elle permet de gérer les collisions, la position de chaque unité par son type
 * et les déplacements.
 * Chaque case du damier vaut 0 si inoccupée, 1 si occupée par unité du joueur 1,
 * 2 si occupée par unité du joueur 2.
 */
	private World world;
	private int[] damier;
	private int case_width;  //largeur d'une case
	private int left_border; //pixel de démarrage à gauche du tableau
	private int right_border;  //pixel de fin du tableau à droite
	private int damier_width;   //taille en pixels du tableau
	private int lead_1; //position du minion leader du joueur 1
	private int lead_2; //position du minion leader du joueur 2
	private Random r;


	/********* Getters *********/

	public int getCase(int index){
		return damier[index];
	}

	/********* constructeur ********/


	public Board(World world, int size, int left_border, int right_border){
		/*
		 * le nombre de cases (size) du damier est défini dans le Main.
		 * left_border : position du bord gauche.
		 * right_border : position du bord droit.
		 *
		 */
		this.world = world;
		damier = new int[size];
		Arrays.fill(damier, 0);


		this.left_border = left_border;
		this.right_border = right_border;
		this.damier_width = this.right_border-this.left_border;
		this.case_width = this.damier_width/size;
		this.lead_1 = 0;
		this.lead_2 = size-1;
		this.r = new Random();
	}


	/******* calculs de positions ******/


	public int getX(int index){
		/*
		 * retourne la position horizontale de la case d'indice index
		 */
		return index*this.case_width+this.left_border;

	}




	/********* modification du damier ******/

	public void setMinionToCase(int player, int index){
		/*
		 * méthode pour attribuer à une case l'ID d'un joueur
		 * en fonction du minion présent sur la case
		 */
		this.damier[index] = player;
	}

	public void setVoidToCase(int index){
		/*
		 *
		 * méthode pour rendre une case vide
		 *
		 */
		this.damier[index] = 0;
	}


	/****** fonctions de rafraichissement du damier ******/
	public void refreshLeads(){

		/*
		 * Permet de repérer la position des leaders de chaque player
		 */
		int n = this.damier.length;

		this.lead_1=-1;
		this.lead_2=n;
		int i = 0;
		while(this.damier[i]!=2 && i < n-1){
			if(this.damier[i] == 1){
				this.lead_1 = i;
			}
			i++;
		}
		if(this.damier[i]==2){
			this.lead_2 = i;
		}
	}

	public void refreshPositions(){

		/* calcule le nouveau damier à partir de l'ancien */
		for(int j=0;j<damier.length;j++){
			damier[j]=this.world.minions[j].getIdOwner();

		}

		int n = this.damier.length;

		/* On recalcule au préalable les positions des deux leaders,
		 * au cas où des World.minions ont disparus ou sont apparus.
		 */

		this.refreshLeads();

		/*
		 *  on traite d'abord le cas particulier où les leaders
		 *  se départagent une même case.
		 *  Cas sur les bords : priorité aux World.minions déjà dans leurs bases
		 *  Cas général : aléatoire
		 */

		if(this.lead_2 == this.lead_1 + 2){
			if(this.lead_1 == 0){ //Cas où le lead 1 est juste à côté de sa base
				this.damier[0] = 0;
				this.damier[1] = 1;
				this.world.minions[0].move();

			}else if (this.lead_2 == n-1){ //Cas où le lead 2 est juste à côté de sa base
				this.damier[n-1] = 0;
				this.damier[n-2] = 2;
				this.world.minions[n-1].move();
			}else{ //Si on est quelque part au milieu, un des leaders au hasard avance
				int c = r.nextInt(2)+1;
				this.damier[this.lead_1+1]=c;
				if(c==1){
					this.damier[this.lead_1] = 0;
					this.world.minions[this.lead_1].move();
				}else{
					this.damier[this.lead_2] = 0;
					this.world.minions[this.lead_2].move();
				}
			}

		}


		/* On part ensuite de chaque leader et on remonte jusqu'à la base,
		 * afin d'éviter que les unités leaders bloquent leur prédécesseurs
		 */

		/* on commence en mettant à jour les World.minions du joueur 1 */
		for(int j = this.lead_1;j>=0;j--){
			if(this.damier[j] == 1 && j < n-3 && this.damier[j+1] == 0){
				this.damier[j] = 0;
				this.damier[j+1] = 1;
				this.world.minions[j].move();
			}
		}

		/* on met ensuite à jour les World.minions du joueur 2 */
		for(int j = this.lead_2;j<=n-1;j++){
			if(this.damier[j] == 2 && j > 2 && this.damier[j-1] == 0){
				this.damier[j] = 0;
				this.damier[j-1] = 2;
				this.world.minions[j].move();
			}
		}

		/*place aux attaques : seuls le leader et la base peuvent être attaqués*/

		this.refreshLeads();

		/*on commence par les bases ennemies*/

		if(this.damier[0] == 0 && this.damier[1] == 0 && this.damier[2] == 2){

			this.attackBase(this.world.minions[2]);
			if(this.damier[3] == 2 && this.world.minions[3].getType()==2){
				this.attackBase(this.world.minions[3]);

			}

		}else if(this.damier[n-1] == 0 && this.damier[n-2] == 0 && this.damier[n-3] == 1){

			this.attackBase(this.world.minions[n-3]);
			if(this.damier[n-4] == 1 && this.world.minions[n-4].getType()==2){
				this.attackBase(this.world.minions[n-4]);
			}


		/* Cas général : les leaders s'attaquent entre eux ;
		 * en renfort peut intervenir un allié derrière chacun.
		 */

		}else if(this.lead_2==this.lead_1+1){
			// Copie des minions leaders pour conserver les dégats qu'ils doivent se donner en même temps
			// Evite de favoriser le premier à attaquer
			Minion m1 = this.world.minions[this.lead_1];
			Minion m2 = this.world.minions[this.lead_2];
			this.attack(m1,this.world.minions[this.lead_2]);
			this.attack(m2,this.world.minions[this.lead_1]);
			if(this.damier[this.lead_1-1] == 1 && this.world.minions[this.lead_1-1].getType()==2){
				this.attack(this.world.minions[this.lead_1-1],this.world.minions[this.lead_2]);
			}
			if(this.damier[this.lead_2+1] == 2 && this.world.minions[this.lead_2+1].getType()==2){
				this.attack(this.world.minions[this.lead_2+1],this.world.minions[this.lead_1]);
			}

		}



	}

/******* Méthodes d'attaque ******/


	public void attackBase(Minion m){
		if(m.getIdOwner()==1){
			this.world.p2.takeDamage(m.getDamage());
		}else{
			this.world.p1.takeDamage(m.getDamage());
		}
	}


	public void attack(Minion m1, Minion m2){
		m2.takeDamage(m1.getDamage());
	}

}

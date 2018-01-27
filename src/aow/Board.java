package aow;
import java.util.Arrays;
import java.util.Random;
import aow.World1;
import aow.entity.minions.Minion;
import aow.entity.Player;

public class Board {
/*
 * classe dï¿½finissant le damier de positions des unitï¿½s.
 * Elle permet de gï¿½rer les collisions, la position de chaque unitï¿½ par son type
 * et les dï¿½placements.
 * Chaque case du damier vaut 0 si inoccupï¿½e, 1 si occupï¿½e par unitï¿½ du joueur 1,
 * 2 si occupï¿½e par unitï¿½ du joueur 2.
 */
	private int[] damier;
	private int case_width;  //largeur d'une case  
	private int left_border; //pixel de dï¿½marrage ï¿½ gauche du tableau
	private int right_border;  //pixel de fin du tableau ï¿½ droite
	private int damier_width;   //taille en pixels du tableau
	private int lead_1; //position du minion leader du joueur 1
	private int lead_2; //position du minion leader du joueur 2
	private Random r;
	
	
	/********* Getters *********/
	
	public int getCase(int index){
		return damier[index];
	}
	
	/********* constructeur ********/
	
	
	public Board(int size, int left_border, int right_border){
		/*
		 * le nombre de cases (size) du damier est dï¿½fini dans le Main.
		 * left_border : position du bord gauche.
		 * right_border : position du bord droit.
		 * 
		 */
		damier = new int[size];
		Arrays.fill(damier, 0);
		
		
		this.left_border = left_border;
		this.right_border = right_border;
		this.damier_width = this.right_border-this.left_border;
		this.case_width = (int) this.damier_width/size;
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
		 * mï¿½thode pour attribuer ï¿½ une case l'ID d'un joueur 
		 * en fonction du minion prï¿½sent sur la case
		 */
		this.damier[index] = player;
	}
	
	public void setVoidToCase(int index){
		/* 
		 * 
		 * mï¿½thode pour rendre une case vide
		 * 
		 */
		this.damier[index] = 0;
	}
	
	
	/****** fonctions de rafraichissement du damier ******/
	public void refreshLeads(){
		
		/*
		 * Permet de repï¿½rer la position des leaders de chaque player
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
		
		/* calcule le nouveau damier ï¿½ partir de l'ancien */
		for(int j=0;j<damier.length;j++){
			damier[j]=World1.minions[j].getIdOwner();
			
		}

		int n = this.damier.length;
				
		/* On recalcule au prï¿½alable les positions des deux leaders,
		 * au cas oï¿½ des World1.minions ont disparus ou sont apparus.
		 */
		
		this.refreshLeads();
		
		/*
		 *  on traite d'abord le cas particulier oï¿½ les leaders
		 *  se dï¿½partagent une mï¿½me case. 
		 *  Cas sur les bords : prioritï¿½ aux World1.minions dï¿½jï¿½ dans leurs bases
		 *  Cas gï¿½nï¿½ral : alï¿½atoire
		 */
		
		if(this.lead_2 == this.lead_1 + 2){
			if(this.lead_1 == 0){ //Cas oï¿½ le lead 1 est juste ï¿½ cï¿½tï¿½ de sa base
				this.damier[0] = 0;
				this.damier[1] = 1;
				World1.minions[0].move();
				
			}else if (this.lead_2 == n-1){ //Cas oï¿½ le lead 2 est juste ï¿½ cï¿½tï¿½ de sa base
				this.damier[n-1] = 0;
				this.damier[n-2] = 2;
				World1.minions[n-1].move();
			}else{ //Si on est quelque part au milieu, un des leaders au hasard avance
				int c = r.nextInt(2)+1;
				this.damier[this.lead_1+1]=c;
				if(c==1){
					this.damier[this.lead_1] = 0;
					World1.minions[this.lead_1].move();
				}else{
					this.damier[this.lead_2] = 0;
					World1.minions[this.lead_2].move();
				}
			}
			
		}
		
		
		/* On part ensuite de chaque leader et on remonte jusqu'ï¿½ la base, 
		 * afin d'ï¿½viter que les unitï¿½s leaders bloquent leur prï¿½dï¿½cesseurs 
		 */
		
		/* on commence en mettant ï¿½ jour les World1.minions du joueur 1 */
		for(int j = this.lead_1;j>=0;j--){
			if(this.damier[j] == 1 && j < n-3 && this.damier[j+1] == 0){
				this.damier[j] = 0;
				this.damier[j+1] = 1;
				World1.minions[j].move();
			}
		}
		
		/* on met ensuite ï¿½ jour les World1.minions du joueur 2 */
		for(int j = this.lead_2;j<=n-1;j++){
			if(this.damier[j] == 2 && j > 2 && this.damier[j-1] == 0){
				this.damier[j] = 0;
				this.damier[j-1] = 2;
				World1.minions[j].move();
			}
		}
		
		/*place aux attaques : seuls le leader et la base peuvent ï¿½tre attaquï¿½s*/
		
		this.refreshLeads();
		
		/*on commence par les bases ennemies*/
		
		if(this.damier[0] == 0 && this.damier[1] == 0 && this.damier[2] == 2){
			
			this.attackBase(World1.minions[2]);
			if(this.damier[3] == 2 && World1.minions[3].getType()==2){
				this.attackBase(World1.minions[3]);
				
			}
			
		}else if(this.damier[n-1] == 0 && this.damier[n-2] == 0 && this.damier[n-3] == 1){
			
			this.attackBase(World1.minions[n-3]);
			if(this.damier[n-4] == 1 && World1.minions[n-4].getType()==2){
				this.attackBase(World1.minions[n-4]);
			}
			
			
		/* Cas gï¿½nï¿½ral : les leaders s'attaquent entre eux ; 
		 * en renfort peut intervenir un alliï¿½ derriï¿½re chacun.
		 */
			
		}else if(this.lead_2==this.lead_1+1){
			// Copie des minions leaders pour conserver les dégats qu'ils doivent se donner en même temps
			// Evite de favoriser le premier à attaquer
			Minion m1 = World1.minions[this.lead_1];
			Minion m2 = World1.minions[this.lead_2];
			this.attack(m1,World1.minions[this.lead_2]);
			this.attack(m2,World1.minions[this.lead_1]);
			if(this.damier[this.lead_1-1] == 1){
				this.attack(World1.minions[this.lead_1-1],World1.minions[this.lead_2]);
			}
			if(this.damier[this.lead_2+1] == 2){
				this.attack(World1.minions[this.lead_2+1],World1.minions[this.lead_1]);
			}
			
		}
		
		
		
	}
	
/******* Mï¿½thodes d'attaque ******/
	
	
	public void attackBase(Minion m){
		if(m.getIdOwner()==1){
			World1.p2.takeDamage(m.getDamage());
		}else{
			World1.p1.takeDamage(m.getDamage());
		}
	}
	
	
	public void attack(Minion m1, Minion m2){
		m2.takeDamage(m1.getDamage());
	}
	
}

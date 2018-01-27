package aow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import aow.World1;
import aow.entity.minions.Minion;

public class Board {
/*
 * classe définissant le damier de positions des unités.
 * Elle permet de gérer les collisions, la position de chaque unité par son type
 * et les déplacements.
 * Chaque case du damier vaut 0 si inoccupée, 1 si occupée par unité du joueur 1,
 * 2 si occupée par unité du joueur 2.
 */
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
	
	
	public Board(int size, int left_border, int right_border){
		/*
		 * le nombre de cases (size) du damier est défini dans le Main.
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
		int n = this.damier.length;
		
		this.lead_1=0;
		this.lead_2=n-1;
		int i = 0;
		while(this.damier[i]!=2){
			if(this.damier[i] == 1){
				this.lead_1 = i;
			}
			i++;
		}
		this.lead_2 = i;
	
	}
	
	public void refreshPositions(){
		
		/* calcule le nouveau damier à partir de l'ancien */
		
		
		int n = this.damier.length;
		
		/* On recalcule au préalable les positions des deux leaders,
		 * au cas où des World1.minions ont disparus ou sont apparus.
		 */
		
		this.refreshLeads();
		
		/*
		 *  on traite d'abord le cas particulier où les leaders
		 *  se départagent une même case. 
		 *  Cas sur les bords : priorité aux World1.minions déjà dans leurs bases
		 *  Cas général : aléatoire
		 */
		
		if(this.lead_2 == this.lead_1 + 2){
			if(this.lead_1 == 0){ //Cas où le lead 1 est juste à côté de sa base
				this.damier[0] = 0;
				this.damier[1] = 1;
				World1.minions[0].move();
				
			}else if (this.lead_2 == n-1){ //Cas où le lead 2 est juste à côté de sa base
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
		
		
		/* On part ensuite de chaque leader et on remonte jusqu'à la base, 
		 * afin d'éviter que les unités leaders bloquent leur prédécesseurs 
		 */
		
		/* on commence en mettant à jour les World1.minions du joueur 1 */
		for(int j = this.lead_1;j>0;j--){
			if(this.damier[j] == 1 && j != n-3 && this.damier[j+1] == 0){
				this.damier[j] = 0;
				this.damier[j+1] = 1;
				World1.minions[j].move();
			}
		}
		
		/* on met ensuite à jour les World1.minions du joueur 2 */
		for(int j = this.lead_2;j< n-1;j++){
			if(this.damier[j] == 2 && j != 2 && this.damier[j-1] == 0){
				this.damier[j] = 0;
				this.damier[j-1] = 2;
				World1.minions[j].move();
			}
		}
		
		/*place aux attaques : seuls le leader et la base peuvent être attaqués*/
		
		this.refreshLeads();
		
		if(this.damier[0] == 0 && this.damier[1] == 0 && this.damier[1] == 2){
			//World1.minions[2].
		}
		
	}
	
	
}

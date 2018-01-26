package aow;
import java.util.Arrays;

public class Board {
/*
 * classe définissant le damier de positions des unités.
 * Elle permet de gérer les collisions, la position de chaque unité par son type
 * et les déplacements.
 * Chaque case du damier vaut 0 si inoccupée, 1 si occupée par unité du joueur 1,
 * 2 sioccupée par unité du joueur 2.
 */
	private int[] damier;
	private int case_width;  //largeur d'une case  
	private int left_border; //pixel de démarrage à gauche du tableau
	

	
	
	/********* constructeur ********/
	
	
	public Board(int size, int case_width, int left_border, int right_border){
		/*
		 * le nombre de cases (size) du damier est défini dans le Main.
		 * left_border : position du bord gauche
		 * case_width : largeur d'une case
		 * 
		 */
		damier = new int[size];
		Arrays.fill(damier, 0);
		this.case_width = case_width;
		this.left_border = left_border;
		
	}
	
	
	/******* calculs de positions ******/
	
	public int getCorner(int index){
		/*
		 * retourne la position horizontale de la case d'indice index
		 */
		return index*this.case_width+left_border;
		
	}
	
	
	
	
	/********* modification du damier ******/
	
	public void setMinionToCase(int player, int index){
		/*
		 * méthode pour attribuer 
		 */
		this.damier[index] = player;
	}
	
	public void setVoidToCase(int index){
		this.damier[index] = 0;
	}
	
	
	
	
}

package aow;
import java.util.Arrays;

public class Board {
/*
 * classe d�finissant le damier de positions des unit�s.
 * Elle permet de g�rer les collisions, la position de chaque unit� par son type
 * et les d�placements.
 * Chaque case du damier vaut 0 si inoccup�e, 1 si occup�e par unit� du joueur 1,
 * 2 sioccup�e par unit� du joueur 2.
 */
	private int[] damier;
	private int case_width;  //largeur d'une case  
	private int left_border; //pixel de d�marrage � gauche du tableau
	

	
	
	/********* constructeur ********/
	
	
	public Board(int size, int case_width, int left_border, int right_border){
		/*
		 * le nombre de cases (size) du damier est d�fini dans le Main.
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
		 * m�thode pour attribuer 
		 */
		this.damier[index] = player;
	}
	
	public void setVoidToCase(int index){
		this.damier[index] = 0;
	}
	
	
	
	
}

package aow.entity.minions;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import aow.Board;
import aow.World1;
import aow.entity.Player;

public class Minion1 {
	
	private int x;
	private int y;
	private int nextPosX;
	private int currentPosX;
	private int HP;
	private int damage;
	private int direction; // haut=1 -> sens horaire +1
	private double speed;
	private Image sprite;
	private int id;
//	private Player player; //Joueur ennemi de cette unité
//	private Board board;
	
	public Minion1(int posX, int y, int id) {
		/*
		 * posX : numéro de case du minion dans le board en partant de la gauche
		 * y :  position en y dans la fênetre, est constant au cours du temps
		 */
		
//		this.board = board;
//		this.player = player;
		this.id = id;
		this.x = World1.board.getCorner(posX);
		this.y= y;
		this.currentPosX = posX;
		this.nextPosX = posX;
		this.direction = 0;
		World1.minions.add(this);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.green);
    	g.fillRect(x, y, 40, 40);
	}
	
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
    
    
    public void takeDamage(int inflictedDamage) {
    	// Inflige les dégats d'une attaque à ce minion
    	HP -= inflictedDamage;
		if (this.HP<=0) {
			World1.minions.remove(this);
		}
    	
    }
	
}

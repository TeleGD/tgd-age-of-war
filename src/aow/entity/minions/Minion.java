package aow.entity.minions;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import aow.World1;

public class Minion {
	
	private int x;
	private int y;
	private int nextPosX;
	private int currentPosX;
	private int HP;
	private int damage;
	private int direction; // haut=1 -> sens horaire +1
	private double speed;
	private int idOwner;
	private Image sprite;

	public Minion(int posX, int y, int idOwner) {
		/*
		 * posX : numéro de case du minion dans le board en partant de la gauche
		 * y :  position en y dans la fênetre, est constant au cours du temps
		 */

		this.idOwner = idOwner;
		this.x = World1.board.getX(posX);
		this.y= y;
		this.currentPosX = posX;
		this.nextPosX = posX;
		this.direction = -2 * idOwner + 3 ; // = 1 si joueur1, = -1 si joueur2
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
    
    public void move() {
    	x += direction;
    }

}

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
	private int posX;
	private int nextPosX;
	private int currentPosX;
	private int HP;
	private int damage;
	private int direction; // haut=1 -> sens horaire +1
	private double speed;
	private int idOwner;
	private Image sprite;
	private int range;
	private int type;
	private int price;
	private String nameSprite;
	private Image image;

	public Minion(int idOwner, int age, int type, String nameSprite ) {
		/*
		 * posX : numéro de case du minion dans le board en partant de la gauche
		 * y :  position en y dans la fênetre, est constant au cours du temps
		 */
		
		if (idOwner != 0) {
			posX =  (World1.tailleBoard -1) * (idOwner - 1);
			
			HP = (int) (World1.HPminion * age * 1.25);
			price = (int) (World1.priceMinion * age * 1.25);
			this.idOwner = idOwner;
			this.x = World1.board.getX(posX);
			this.y= World1.yMinion;
			this.currentPosX = posX;
			this.nextPosX = posX;
			this.direction = -2 * idOwner + 3 ; // = 1 si joueur1, = -1 si joueur2
			
			try {
				image=new Image(nameSprite);
				image=image.getScaledCopy((float) 1);
			} catch (SlickException e) {
				// nous donne la trace de l'erreur si on ne peut charger l'image correctement
				e.printStackTrace();
			}
			
			World1.minions[posX] = this;
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if (idOwner != 0) {
			g.drawImage(image, (float)(x), (float)(y));
		}
	}
	
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
    
    
    public void takeDamage(int inflictedDamage) {
    	// Inflige les dégats d'une attaque à ce minion
    	HP -= inflictedDamage;
		if (this.HP<=0) {
			if (idOwner == 1) {
				World1.p2.addGold((int) (price * 1.25) );
			}
			else World1.p1.addGold((int) (price * 1.25) );
			World1.minions[posX] = World1.fantom ;
		}
    }
    
    public void move() {
    	World1.minions[posX] = World1.fantom ;
    	posX += direction;
    	x = World1.board.getX(posX);
    	World1.minions[posX] = this; 	
    	
    }
    
    public int getDamage() {
    	return damage;
    }
    
    public int getIdOwner() {
    	return idOwner;
    }

	public int getHP() {
		return HP;
	}

	public int getRange() {
		return range;
	}

	public int getType() {
		return type;
	}
    
    

}

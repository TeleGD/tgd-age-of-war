package aow.entity.minions;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
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
	private int range;
	private int type;
	private int price;
	private Image image;
	private int xp;
	private Sound bruit;
	private Sound surprise;

	public Minion(int idOwner, int age, int type) {
		/*
		 * posX : num�ro de case du minion dans le board en partant de la gauche
		 * y :  position en y dans la f�netre, est constant au cours du temps
		 */
		
		if (idOwner != 0) {
			posX =  (World1.tailleBoard -1) * (idOwner - 1);
			
			xp = 50 * age * type;
			damage = (int) (World1.damageDefault * age * type);
			HP = (int) (World1.HPminion * age * type);
			price = (int) (World1.priceMinion * age * type);
			try {
				surprise=new Sound("musics/game1/surprise.ogg");
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.idOwner = idOwner;
			this.x = World1.board.getX(posX);
			this.y= World1.yMinion;
			this.currentPosX = posX;
			this.nextPosX = posX;
			this.direction = -2 * idOwner + 3 ; // = 1 si joueur1, = -1 si joueur2
			
			try {
				if (type <= 3) {
					image=new Image("images/game1/stick_"+type+"_a"+age+".png");
					this.bruit=new Sound("musics/game1/criWilhelm.ogg");
				}
				if (type >= 4 ) {
					image=new Image("images/game1/stick_"+ (type - 3) +"_a"+age+".png");
					image=image.getScaledCopy((float) 2);
					this.y -= 90;
					this.bruit=new Sound("musics/game1/headshot.ogg");
					surprise.play(1,(float) 0.5);
				}
				if (idOwner==2) {
					image=image.getFlippedCopy(true, false);
				}
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
    	// Inflige les d�gats d'une attaque � ce minion
    	if (idOwner != 0) {
        	HP -= inflictedDamage;
    		if (this.HP<=0) {
    			if (idOwner == 1) {
    				World1.p2.addGold((int) price );
    				World1.p2.augmenteXp((int) xp );
    				
    			}
    			else {
    				World1.p1.addGold((int) price );
    				World1.p1.augmenteXp((int) xp );
    			}
    			
    			World1.minions[posX] = World1.fantom ;
    			bruit.play(1,(float) 0.4);
    		}
    	}

    }
    
    public void move() {
    	World1.minions[posX] = World1.fantom ;
    	nextPosX = posX + direction;
    	x = World1.board.getX(posX);
    	World1.minions[nextPosX] = this; 
    }
    
    public void fluidMove() {
    	if (posX != nextPosX) {
    		x=x+direction;
    	}
    }
    
    public void setPosX() {
    	this.posX=nextPosX;
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

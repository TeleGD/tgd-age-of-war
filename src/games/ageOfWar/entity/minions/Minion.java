package games.ageOfWar.entity.minions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.ageOfWar.World;

public class Minion {

	private World world;
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
	private Audio bruit;
	private Audio surprise;

	public Minion(World world, int idOwner, int age, int type) {
		/*
		 * posX : numéro de case du minion dans le board en partant de la gauche
		 * y :  position en y dans la fenêtre, est constant au cours du temps
		 */
		this.world = world;
		if (idOwner != 0) {
			posX =  (World.tailleBoard -1) * (idOwner - 1);

			xp = 50 * age * type;
			damage = World.damageDefault * age * type;
			HP = World.HPminion * age * type;
			price = (int) (World.priceMinion * age * type * 1.05);
			surprise=AppLoader.loadAudio("/sounds/ageOfWar/surprise.ogg");
			this.idOwner = idOwner;
			this.x = this.world.board.getX(posX);
			this.y= World.yMinion;
			this.currentPosX = posX;
			this.nextPosX = posX;
			this.direction = -2 * idOwner + 3 ; // = 1 si joueur1, = -1 si joueur2

			if (type <= 3) {
				image=AppLoader.loadPicture("/images/ageOfWar/stick_"+type+"_a"+age+".png");
				this.bruit=AppLoader.loadAudio("/sounds/ageOfWar/criWilhelm.ogg");
			}
			if (type >= 4 ) {
				image=AppLoader.loadPicture("/images/ageOfWar/stick_"+ (type - 3) +"_a"+age+".png");
				image=image.getScaledCopy(2);
				this.y -= 90;
				this.bruit=AppLoader.loadAudio("/sounds/ageOfWar/headshot.ogg");
				surprise.playAsSoundEffect(1f, .5f, false);
			}
			if (idOwner==2) {
				image=image.getFlippedCopy(true, false);
			}

			this.world.minions[posX] = this;
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if (idOwner != 0) {
			g.drawImage(image, (x), (y));
		}
	}

    public void update(GameContainer container, StateBasedGame game, int delta) {
    }


    public void takeDamage(int inflictedDamage) {
    	// Inflige les dégats d'une attaque à ce minion
    	if (idOwner != 0) {
        	HP -= inflictedDamage;
    		if (this.HP<=0) {
    			if (idOwner == 1) {
    				this.world.p2.addGold(price );
    				this.world.p2.augmenteXp(xp );

    			}
    			else {
    				this.world.p1.addGold(price );
    				this.world.p1.augmenteXp(xp );
    			}

    			this.world.minions[posX] = this.world.fantom ;
    			bruit.playAsSoundEffect(1f, .4f, false);
    		}
    	}

    }

    public void move() {
    	this.world.minions[posX] = this.world.fantom ;
    	nextPosX = posX + direction;
    	x = this.world.board.getX(posX);
    	this.world.minions[nextPosX] = this;
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

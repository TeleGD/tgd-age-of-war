package game2;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import game2.World2;


public class Player {

	
	private double x ;
	private double y ;
	private boolean rightPress;
	private boolean leftPress;
	private boolean isInJump;
	private double jumpSpeed;
	private boolean jumpReleased;
	private double speedX;
	private double speedY;
	private double gravity;
	private ArrayList<Tetris> tetrisList;
	private Shape hitBoxChar ;
	private Image image;
	
	public Player (int x,  int y, Shape hitBoxChar ){
		this.x=x;
		this.y=y;
		this.hitBoxChar=hitBoxChar;
		try {
			image=new Image("images/TetrisPolyBridge/player.png");
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.green);
		arg2.drawImage(image, (float)(x-image.getWidth()/2), (float)(y-image.getHeight()/2));
	}
	
	
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		move(delta);
		speedY = speedY - gravity*delta;
		collapse();
	
	}
	
	public void collapse() {
		if (this.x-1 >= 500) {
			speedY = 0;
		}
		tetrisList = World2.getTetrisList();
		for (int i=0; i <= World2.getTetrisList().size(); i++){
			
			
		}
	}
	
	
	public void keyPressed (int key, char c) {

			if (key == Input.KEY_RIGHT && leftPress == false ) {
				rightPress = true;
			}
			
			if (key == Input.KEY_LEFT && rightPress == false ) {
				rightPress = true;
			}
			
			if (key == Input.KEY_SPACE && isInJump == false && jumpReleased == true ) {
				jumpSpeed = 10;
				isInJump = true;
			}
			
		}
	
	public void keyReleased(int key, char c) {
		
		if (key == Input.KEY_RIGHT) {
			rightPress = false;
	}
		if (key == Input.KEY_LEFT) {
			leftPress = false;
	}
		if (key == Input.KEY_SPACE){
			jumpReleased = true;
		}
		}
	
	public void move(double delta) {
			if (rightPress = true && x<1280) {
				this.x = this.x + speedX*delta;
			}
			if (leftPress = true && x>0 ) {
				this.x = this.x + speedX*delta;
			}
			if (isInJump = true && y<0 && y>720  ){
				this.y = this.y + speedY*delta;
		}
			this.hitBoxChar = new Rectangle((int)x+6,(int)y,20,32);
	}
		
	
		
	
	
	
	
	
	
	
	
	}
	
	
	


package game2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
	
	
	public Player (int x,  int y){
		this.x=x;
		this.y=y;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		move(delta);
		speedY = speedY - gravity*delta;
	}
	
	public void collapse() {
		
		
	
	
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
	}
		
	
		
	
	
	
	
	
	
	
	
	}
	
	
	


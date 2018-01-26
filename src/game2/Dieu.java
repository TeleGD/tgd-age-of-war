package game2;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Dieu {
	private static final double TETRIS_NUM = 35454110; //cf serie n°A000988 on the OEIS website 
	//Classe qui dépose les tetris.
	private Image sprite;
	private boolean left,right,rightLeft,down,up,upDown,rotLeft,rotRight,rotrot,space;
	private float x,y,controlledBlockX,controlledBlockY,speed,controlledBlockSpeed;
	private Tetris controlledBlock;
	
	public Dieu() throws SlickException {
		sprite = new Image("images/TetrisPolyBridge/dieu.png");
		x = 360;
		y = 0;
		speed = 1;
		controlledBlockX = 360;
		controlledBlockY = 32;
		left = false;
		right = false;
		rightLeft = false;
		down = false;
		up = false;
		upDown = false;
		rotLeft = false;
		rotRight = false;
		rotrot = false;
		space = false;
	}
	
	public void throwBlock() {
		nextBlock();
	}
	
	public void nextBlock() {
		double r = Math.random() * TETRIS_NUM;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//controlledBlock.update();
		move(delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,(float) x,(float) y);
		//controlledBlock.render(container,game,g);
	}
	
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_Z:
			up = true;
			upDown = false;
			break;
		case Input.KEY_S:
			down = true;
			upDown = true;
			break;
		case Input.KEY_Q:
			System.out.println("q");
			left = true;
			rightLeft = true;
			break;
		case Input.KEY_D:
			right = true;
			rightLeft = false;
			break;
		case Input.KEY_A:
			rotLeft = true;
			rotrot = false;
			break;
		case Input.KEY_E:
			rotRight = true;
			rotrot = true;
			break;
		case Input.KEY_SPACE:
			space = true;
			break;
		}

	}
	
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_Z:
			up = false;
			break;
		case Input.KEY_S:
			down = false;
			break;
		case Input.KEY_Q:
			left = false;
			rightLeft = false;
			break;
		case Input.KEY_D:
			right = false;
			break;
		case Input.KEY_A:
			rotLeft = false;
			break;
		case Input.KEY_E:
			rotRight = false;
			break;
		case Input.KEY_SPACE:
			space = false;
			break;
		}
	}
	
	public void move(int dt){
		if((left && !right) || rightLeft){
			x -= dt*speed;
			if(x < 0) x = 0;
		}else if(right){
			x += dt*speed;
			if(x > 1280-32) x = 1280 - 32;
		}
	}
	
	//Getters
	
	public Image getSprite(){
		return this.sprite;
	}

	public float getSpeed() {
		return speed;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isRightLeft() {
		return rightLeft;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isUpdown() {
		return upDown;
	}

	public boolean isRotLeft() {
		return rotLeft;
	}

	public boolean isRotRight() {
		return rotRight;
	}

	public boolean isRotrot() {
		return rotrot;
	}
	
	public boolean space() {
		return space;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getControlledBlockX() {
		return controlledBlockX;
	}

	public float getControlledBlockY() {
		return controlledBlockY;
	}

	public float getControlledBlockSpeed() {
		return controlledBlockSpeed;
	}
	
	//Setters

	public void setSprite(Image s) {
		this.sprite = s;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setRightLeft(boolean rightLeft) {
		this.rightLeft = rightLeft;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setUpdown(boolean updown) {
		this.upDown = updown;
	}

	public void setRotLeft(boolean rotLeft) {
		this.rotLeft = rotLeft;
	}

	public void setRotRight(boolean rotRight) {
		this.rotRight = rotRight;
	}

	public void setRotrot(boolean rotrot) {
		this.rotrot = rotrot;
	}
	
	public void setSpace(boolean space) {
		this.space = space;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setControlledBlockX(float controlledBlockX) {
		this.controlledBlockX = controlledBlockX;
	}

	public void setControlledBlockY(float controlledBlockY) {
		this.controlledBlockY = controlledBlockY;
	}

	public void setControlledBlockSpeed(float controlledBlockSpeed) {
		this.controlledBlockSpeed = controlledBlockSpeed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}

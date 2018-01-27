package game2;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Dieu {
//	private static final double TETRIS_NUM = 35454110; //cf serie n°A000988 on the OEIS website 
	//Classe qui dépose les tetris.
	private Image sprite;
	private boolean left,right,rightLeft,down,up,upDown,rotLeft,rotRight,rotrot,drop;
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
		drop = false;
		nextBlock();
		controlledBlock.setVy(0);
	}
	
	public boolean checkCollision(Tetris t) {
		for(Block[] rowT : t.getMatrice()){
			if (rowT == null) continue;
			for(Block bT : rowT){
				if (bT == null) continue;
				for(Block[] row : controlledBlock.getMatrice()){
					if (row == null) continue;
					for(Block b : row){
						if (b == null) continue;
						if(bT.getHitbox().intersects(b.getHitbox())) {
							//System.out.println("Collision at x: "+bT.getPosx()+"/"+b.getPosx()+"; y: "+bT.getPosy()+"/"+b.getPosy());
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public void dropBlock() {
		controlledBlock.setVy(0.5);
	}
	
	public void nextBlock() {
		boolean[][] mat = new boolean[4][4];
		String cat = "";
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++) mat[i][j] = Math.random() > 0.5;
		}
		switch((int)Math.floor(Math.random()*9)){
		case 0:
			cat = "White";
			break;
		case 1:
			cat = "Yellow";
			break;
		case 2:
			cat = "Red";
			break;
		case 3:
			cat = "Blue";
			break;
		case 4:
			cat = "Gray";
			break;
		case 5:
			cat = "Green";
			break;
		case 6:
			cat = "Orange";
			break;
		case 7:
			cat = "Purple";
			break;
		case 8:
			cat = "Flash";
			break;
		/*case 9:
			cat = "QRCode";
			break;*/
			
		}
		try {
			if(controlledBlock != null) World2.addTetrisList(controlledBlock);
			//controlledBlock = new Tetris(mat, "images/TetrisPolyBridge/Bloc1QRCode.png");
			/*System.out.println("--------");
			for(int i = 0; i < 4; i ++){
				for(int j = 0; j < 4; j  ++){
					System.out.print(mat[i][j]+";");
				}
				System.out.println();
			}
			System.out.println("-------- ");*/
			controlledBlock = new Tetris(mat, "images/TetrisPolyBridge/Bloc"+(int)Math.floor(1+6*Math.random())+cat+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
		
		if(!drop){
			controlledBlock.setXcentre((int) (x+16));
		}
		else{
			for(Tetris t : World2.getTetrisList()){
				if(World2.getTetrisList().size() < 1) break;
				if(checkCollision(t)){
					controlledBlock.setVy(0);
					drop = false;
					nextBlock();
					break;
				}
			}
			
		}
		controlledBlock.update(container, game, delta);
		
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(sprite,(float) x,(float) y);
		controlledBlock.render(container,game,g);
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
			drop = true;
			nextBlock();
			//dropBlock();
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
			rotrot = false;
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
		if((rotRight && !rotLeft)|| rotrot ){
			controlledBlock.rotate(-15);
		}else if(rotrot){
			controlledBlock.rotate(15);
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
		return drop;
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
		this.drop = space;
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

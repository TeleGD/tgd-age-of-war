package game2;

/**		CLASSE DIEU :
 * Classe qui sert à déposer les pièces et à gérer leur rotation
 * ainsi que les collisions entre les pièces lachées et le reste
 */


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Dieu {
	//Classe qui dépose les tetris.
	private Image sprite;
	private boolean left,right,rightLeft,down,up,upDown,rotLeft,rotRight,rotrot,drop;
	private float x,y,controlledBlockX,controlledBlockY,speed,controlledBlockSpeed;
	private Tetris controlledBlock,nextBlock;
	
	public Dieu() throws SlickException {
		
		sprite = new Image("images/TetrisPolyBridge/dieu.png");
		x = 360;
		y = 0;
		speed = (float) 0.5;
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
		boolean[][] mat = new boolean[4][4];
		
		try {
			//Génération de la pièce de départ et de la suivante
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++) mat[i][j] = Math.random() > 0.5;
			}
			controlledBlock = new Tetris(mat, "images/TetrisPolyBridge/Bloc"+(int)Math.floor(1+7*Math.random())+randomCat()+".png");
			controlledBlock.setVy(0);
			
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++) mat[i][j] = Math.random() > 0.5;
			}
			nextBlock = new Tetris(mat, "images/TetrisPolyBridge/Bloc"+(int)Math.floor(1+7*Math.random())+randomCat()+".png");
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		//Téléportation de la première pièce générée sous dieu.
		controlledBlock.setXcentre((int) (x+16));
		controlledBlock.rotate(0);

	}
	
	public boolean checkCollision(Tetris t) {
	//Vérifie les collisions entre la pièce courante et t
	//On vérifie les intersections bloc par bloc, mais on s'arrête dès qu'on en trouve une
		for(Block[] rowT : t.getMatrice()){
			if (rowT == null) continue;
			for(Block bT : rowT){
				if (bT == null) continue;
				for(Block[] row : controlledBlock.getMatrice()){
					if (row == null) continue;
					for(Block b : row){
						if (b == null) continue;
						if(bT.getHitbox().intersects(b.getHitbox())) return true;
					}
				}
			}
		}
		
		
		return false;
	}
	
	public void dropBlock() {
		//Déclenche la chute de la pièce courante
		controlledBlock.setVy(0.5);
	}
	
	public String randomCat(){
		//Renvoie une catégorie aléatoire pour la pièce en cours de construction
		String cat = "";
		switch((int)Math.floor(Math.random()*10)){
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
		case 9:
			cat = "QRCode";
			break;
		}
		return cat;
	}
	
	public void nextBlock() {
		//Gère le passage à la pièce suivante : génération de la nouvelle pièce, téléportation sous dieu, et changement de la pièce.
		boolean[][] mat = new boolean[4][4];
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++) mat[i][j] = Math.random() > 0.5;
		}
		
		try {
			if(controlledBlock.getYcentre()>100)World2.addTetrisList(controlledBlock);
			controlledBlock = nextBlock;
			nextBlock = new Tetris(mat, "images/TetrisPolyBridge/Bloc"+(int)Math.floor(1+7*Math.random())+randomCat()+".png");
			controlledBlock.setVy(0);
			controlledBlock.setXcentre((int) (x+16));
			controlledBlock.rotate(0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move(delta);
		
		if(drop){
		
			for(Block[] row : controlledBlock.getMatrice()){
				if(row == null) continue;
				for(Block b : row){
					if(b == null) continue;
					if(b.getHitbox().intersects(World2.getShape1())){
						controlledBlock.setVy(0);
						drop = false;
						nextBlock();
						break;
					}
					else if(b.getHitbox().intersects(World2.getShape2())){
						controlledBlock.setVy(0);
						drop = false;
						nextBlock();
						break;
					}
					else if(b.getHitbox().intersects(World2.getShape3())){
						controlledBlock.setVy(0);
						drop = false;
						nextBlock();
						break;
					}
				}
			}
			
			if(World2.getTetrisList().size() >= 1){
				for(Tetris t : World2.getTetrisList()){
					if(checkCollision(t)){
						controlledBlock.setVy(0);
						drop = false;
						nextBlock();
						break;
					}
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
		case Input.KEY_ENTER:
			drop = true;
			dropBlock();
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
		//Déplace dieu en accord avec les commandes du joueur
		int moveX = 0;
		float tmpX = x;
		if((left && !right) || rightLeft){
			x -= dt*speed;
			moveX = (int) (-dt*speed);
			if(x < 148){
				moveX = (int) (148-tmpX);
				x = 148;
			}
		}else if(right){
			x += dt*speed;
			moveX = (int) (dt*speed);
			if(x > 868){
				moveX = (int) (868 - tmpX);
				x = 868;
			}
		}
		if(!drop)controlledBlock.moveCentre(moveX,0);
		if((rotRight && !rotLeft)|| rotrot ){
			controlledBlock.rotate(0.1);
		}else if(rotLeft){
			controlledBlock.rotate(-0.1);
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

	public Tetris getNextBlock() {
		return nextBlock;
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

	public void setNextBlock(Tetris nextBlock) {
		this.nextBlock = nextBlock;
	}
	
}

package game2;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tetris {
	private Block[][] matrice = new Block[4][4];
	private int x,y,xcentre,ycentre;
	private double angle,vx,vy;
	private String sprite;
	private boolean[][] matBool;
	
	
	public Tetris(boolean[][] matBool,String adresse) throws SlickException {
		this.x=500;
		this.matBool=matBool;
		this.y=-100;
		sprite=adresse;
		this.angle=0;
		this.vx=0;
		this.vy=1;
		this.xcentre=0;
		this.ycentre=0;
		int n=0;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					xcentre+=j*32+16;
					ycentre+=i*32+16;
					n++;
					matrice[i][j]=new Block(sprite,i*32+16,j*32+16);
				}
			}
		}
		xcentre/=n;
		ycentre/=n;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
    }
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					matrice[i][j].render(container,game,g);
				}
			}
		}
		
	}
	
	public void rotate(double vangle) {
		angle+=vangle;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					matrice[i][j].setAngle(angle);
				}
			}
		} 
	}
}

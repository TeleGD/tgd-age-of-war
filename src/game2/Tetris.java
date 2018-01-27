package game2;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tetris {
	private Block[][] matrice = new Block[4][4];
	private double[][][] matDonnees = new double[4][4][2];// matrice qui a chaque bloque associe la distance et l'angle au barycentre
	private int x,y,xcentre,ycentre;
	private double angle,vx,vy;
	private String sprite;
	private boolean[][] matBool=new boolean[4][4];
	
	
	public Tetris(boolean[][] matBool,String adresse) throws SlickException {
		this.x=500;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				this.matBool[i][j]=matBool[i][j];
			}
		}
		this.y=-100;
		sprite=adresse;
		this.angle=0;
		this.vx=0;
		this.vy=0;
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
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					matDonnees[i][j][0]=Math.sqrt( Math.pow(32*j+16-xcentre,2) + Math.pow(32*i+16-ycentre,2) );
					if (matDonnees[i][j][0]!=0) {
						if (32*i+16<ycentre) {
							matDonnees[i][j][1]=Math.acos((32*j+16-xcentre)/matDonnees[i][j][0]);
						} else {
							matDonnees[i][j][1]=-Math.acos((32*j+16-xcentre)/matDonnees[i][j][0]);
						}
					}
				}
			}
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		ycentre+=delta*vy;
		xcentre+=delta*vx;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					matrice[i][j].setVity(vy);
					matrice[i][j].update(container,game,delta);
				}
			}
		}
		rotate(0);
    }
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					if(matrice[i][j]!=null)matrice[i][j].render(container,game,g);
				}
			}
		}
	}
	
	public void rotate(double vangle) {
		angle+=vangle;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					matrice[i][j].rotate(angle*180/Math.PI,vangle);
					matrice[i][j].teleport( (int) (xcentre+matDonnees[i][j][0]*Math.cos(angle+matDonnees[i][j][1])) , (int) (ycentre+matDonnees[i][j][0]*Math.sin(angle+matDonnees[i][j][1])) );
				}
			}
		}
	}
	
	public void moveCentre(int x, int y) {
		xcentre+=x;
		ycentre+=y;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matBool[i][j]) {
					matrice[i][j].move(x,y);
				}
			}
		}
	}
	
	public void teleport(int x, int y) {
		moveCentre(x-xcentre,y-ycentre);
	}

	public Block[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(Block[][] matrice) {
		this.matrice = matrice;
	}

	public int getXcentre() {
		return xcentre;
	}

	public void setXcentre(int xcentre) {
		this.xcentre = xcentre;
	}

	public int getYcentre() {
		return ycentre;
	}

	public void setYcentre(int ycentre) {
		this.ycentre = ycentre;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}
	
	
}

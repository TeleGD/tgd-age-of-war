package game2;

public class Tetris {
	private boolean[][] matrice;
	private int x,y,xcentre,ycentre;
	private double angle,vx,vy;
	
	
	public Tetris(boolean[][] matrice) {
		this.matrice=matrice;
		this.x=500;
		this.y=-100;
		this.angle=0;
		this.vx=0;
		this.vy=1;
		this.xcentre=0;
		this.ycentre=0;
		int n=0;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				if (matrice[i][j]) {
					xcentre+=j*32+16;
					ycentre+=i*32+16;
					n++;
				}
			}
		}
		xcentre/=n;
		ycentre/=n;
	}
	
	public void rotate(double vangle) {
		angle+=vangle;
	}
}

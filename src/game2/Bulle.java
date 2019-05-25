package game2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Bulle {
	private int posx;
	private int posy;

	private Image bulle;
	private String urlBulle = "images/TetrisPolyBridge/bulle.png";

	public Bulle(int x, int y) throws SlickException{
    	bulle = new Image(urlBulle);

    	posx = x;
    	posy = 720 - y;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		posy -= 1;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(bulle,(float) posx,(float) posy);
	}

	public int getPosX(){
		return posx;
	}

	public int getPosy(){
		return posy;
	}
}

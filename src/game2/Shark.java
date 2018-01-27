package game2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Shark {
	private int posx;
	private int posy;
	private int sens;
	
	private Image shark;
	private Image shark1;
	private Image shark2;
	private String urlShark1 = "images/TetrisPolyBridge/requin1.png";
	private String urlShark2 = "images/TetrisPolyBridge/requin2.png";
	
	public Shark(int x, int dir) throws SlickException{
		posx = x;
		posy = 652-32;
		sens = dir;
		
		shark1 = new Image(urlShark1);	
		shark2 = new Image(urlShark2);
		
		if(dir == 1){
			shark = shark1;
		}else{
			shark = shark2;
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(sens == 1){
			posx += 1;
			if(posx>980-32){
				sens = 1 - sens;
				shark = shark2;
			}
		}else{
			posx -= 1;
			if(posx < 100){
				sens = 1 - sens;
				shark = shark1;
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(shark,(float) posx,(float) posy);
	}
	
	public int getPosX(){
		return posx;
	}
	
	public int getPosy(){
		return posy;
	}
}

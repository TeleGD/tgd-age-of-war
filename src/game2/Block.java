package game2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Block {
	//au centre du block
	private int posx;
	private int posy;
	
	private int angle;
	private int vitesse;
	
	//sprite
	private Image sprite;
	
	public Block(String url,int x, int y) throws SlickException{
		posx = x;
		posy = y;
		sprite = new Image(url);
		angle = 0;
		vitesse = 0;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}
	
	public int getPosx(){
		return posx;
	}
	
	public int getPosy(){
		return posy;
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public int getAngle(){
		return angle;
	}
	
	public int getVitesse(){
		return vitesse;
	}
	
	public void setPosx(int x){
		posx = x;
	}
	
	public void setPosy(int y){
		posy = y;
	}
	
	public void setSprite(String url) throws SlickException{
		sprite = new Image(url);
	}
	
	public void setAngle(int ang){
		angle = ang;
	}
	
	public void setVitesse(int fast){
		vitesse = fast;
	}
}

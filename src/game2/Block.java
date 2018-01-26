package game2;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Block {
	private int posx;
	private int posy;
	private int angle;
	private int vitesse;
	private boolean hit;
	
	//sprite
	private Image sprite;
	
	public Block(String url) throws SlickException{
		posx = 0;
		posy = 0;
		sprite = new Image(url);
		angle = 0;
		vitesse = 0;
		hit = false;
	}
	
	public void testHit(){
		
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

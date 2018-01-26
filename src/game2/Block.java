package game2;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Block {
	private int posx;
	private int posy;
	
	//sprite
	private Image sprite;
	
	public Block(String url) throws SlickException{
		posx = 0;
		posy = 0;
		sprite = new Image(url);
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
	
	public void setPosx(int x){
		posx = x;
	}
	
	public void setPosy(int y){
		posy = y;
	}
	
	public void setSprite(String url){
		sprite = new Image(url);
	}
}

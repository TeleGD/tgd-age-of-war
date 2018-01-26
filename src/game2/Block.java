package game2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.StateBasedGame;

public class Block {
	//au centre du block
	private int posx;
	private int posy;
	
	private double angle;
	private double vitx,vity;
	private Shape hitbox;
	
	//sprite
	private Image sprite;
	
	public Block(String url,int x, int y) throws SlickException{
		posx = x;
		posy = y;
		hitbox = new Rectangle(posx-16,posy-16,32,32);
		sprite = new Image(url);
		angle = 0;
		vitx = 0;
		vity=0;
	}
	//hitbox = hitbox.transform(Transform.createRotateTransform((float) angle));
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Image spriteTemporaire = sprite;
		spriteTemporaire.rotate((float) angle);
		g.drawImage(spriteTemporaire,(float) posx,(float) posy);
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
	
	public double getAngle(){
		return angle;
	}
	
	public double getVitx(){
		return vitx;
	}
	
	public double getVity(){
		return vity;
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
	
	public void setAngle(double ang){
		angle = ang;
	}
	
	public void setVitx(double fast){
		vitx = fast;
	}
	
	public void setVity(double fast){
		vity = fast;
	}
}

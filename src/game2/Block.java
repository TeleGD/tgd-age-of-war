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
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		posy+=delta*vity;
		posx+=delta*vitx;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		sprite.rotate((float) angle);
		g.drawImage(sprite,(float) posx,(float) posy);
		sprite.rotate((float) -angle);
		g.draw(hitbox);
	}
	
	public void rotate(double angle, double vangle) {
		this.angle=angle;
		System.out.println("blaaa");
		hitbox.transform(Transform.createRotateTransform((float) vangle));
	}
	
	public void move(int x,int y) {
		posx+=x;
		posy+=y;
		hitbox.setX(hitbox.getX()+x);
		hitbox.setY(hitbox.getY()+y);
	}
	
	public void teleport(int x, int y) {
		posx=x;
		posy=y;
		hitbox.setX(x);
		hitbox.setY(y);
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

	public Shape getHitbox() {
		return hitbox;
	}

	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}
	
}

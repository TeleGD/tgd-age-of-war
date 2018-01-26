package game2;


public class Block {
	private int posx;
	private int posy;
	
	//sprite
	private Image sprite;
	
	public Block(String url){
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
	
	public void setPosx(int x){
		posx = x;
	}
	
	public void setPosy(int y){
		posy = y;
	}
}

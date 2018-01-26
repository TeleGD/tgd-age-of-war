package game2;

import general.ui.Button;
import general.ui.TGDComponent;
import general.utils.FontUtils;
import menus.MainMenu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import java.util.*;

public class World2 extends BasicGameState {
	
	public static int ID=77;
	public static String name = "Tetris PolyBridge";
	private Player player;
	private Tetris tetris;
	private Dieu dieu;
	
	//private Image fond;
	private String urlFond = "";

    @Override
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
    	//player = new Player(0,0);
    	tetris = new Tetris(0);
    	dieu = new Dieu();
    	//fond = new Image(urlFond);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	//ciel
    	g.setColor(Color.cyan);
    	g.fillRect(0,0, 1280, 720);
    	
    	//fond avec image
    	//g.drawImage(fond, 0, 0);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
    
    public static void reset() {

    }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}


}

package aow;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class BadEndAow extends BasicGameState{
	
	public static int ID = 1000;	// 1000 !!

	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
				
	}

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	g.setColor(Color.white);
    	g.drawString("IL N'Y A PAS DE GAGNANTS DANS UNE GUERRE...", 550, 350);
		g.drawRect(525, 330, 205, 50);
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

	@Override
	public int getID() {
		return ID;
	}
    
    
}

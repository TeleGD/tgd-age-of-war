package aow;

import org.newdawn.slick.TrueTypeFont;
import general.utils.FontUtils;

import general.ui.Button;
import general.ui.TGDComponent;
import general.utils.FontUtils;
import menus.MainMenu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import aow.entity.Player;
import aow.entity.minions.Minion;

import java.awt.Font;
import java.util.*;

public class World1 extends BasicGameState {
	
	public static int ID=7;
	public static String name = "Age of War";
	public static Minion[] minions;

	public static int damageDefault = 5;
	public static int HPminion = 15;
	public static int priceMinion = 10 ;
	public static int goldInit = 50;
	public static int HPInit = 100;
	public static int tailleBoard = 20;
	public static int yMinion = 410;
	
	public Image fond;
	public Image fondBadEnd;
	
	protected TrueTypeFont fontEnd;

	
	public static Board board = new Board(tailleBoard,130,1150);

	public static Player p1 = new Player(1,goldInit,HPInit);
	public static Player p2 = new Player(2,goldInit,HPInit);

	public static Minion fantom = new Minion(0, 1, 1);
	
	private int incr=0;
	public static int etat = 0;
	
    @Override
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		fontEnd = FontUtils.loadFont("Kalinga",Font.BOLD,18,true);
    	
    	minions = new Minion[tailleBoard];
    	Arrays.fill(minions, fantom);
    	
    	// Minion m1 = new Minion(1, 1, 1);
    	Minion m2 = new Minion(2, 1, 1);
    	fond = new Image("images/game1/fond.png");
    	fondBadEnd = new Image("images/game1/yoda.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	if (etat == 0) { // Si tout le monde est vivant
	    	g.drawImage(fond,0,0);
	    	g.setColor(Color.green);
	//    	g.fillRect(50, 50, 50, 50);
	    	p1.render(container,game,g);
	    	p2.render(container,game,g);
	    	
	    	for(Minion m : minions){
				m.render(container,game,g);
			}
    	} else if (etat == 1) { // Si un joueur est mort
    		g.drawImage(fondBadEnd,0,0);
    		
        	g.setColor(Color.white);
			g.setFont(fontEnd);
        	g.drawString("IL N'Y A PAS DE GAGNANTS DANS UNE GUERRE...", 550, 350);
    	} else {
    		g.drawImage(fondBadEnd,0,0);
    		
        	g.setColor(Color.white);
			g.setFont(fontEnd);
        	g.drawString("PEACE", 550, 350);
    	}
    }


    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	
    	if (p1.getHp() > 0 && p2.getHp() > 0) {
	    	incr++;
	    	if(incr>50){
	    		incr = 0;
	    		for(Minion m : minions){
	    			m.setPosX();
	    		}
	    		board.refreshPositions();
	    	}
	    	p1.update(container, game, delta);
	    	p2.update(container, game, delta);
	    	for(Minion m : minions){
				m.fluidMove();
			}
    	} else {
    		
    	}

    }
    
    public static void reset() {
       
    }
    
    public void keyPressed(int key, char c){
		p1.keyPressed(key, c);
	}
	
	public void keyReleased(int key, char c){
		p1.keyReleased(key, c);
	}
    
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
}

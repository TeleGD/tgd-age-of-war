package games.ageOfWar;

import general.utils.FontUtils;

import menus.MainMenu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.ageOfWar.entity.Player;
import games.ageOfWar.entity.Train;
import games.ageOfWar.entity.minions.Minion;
import games.ageOfWar.entity.minions.Sort;

import java.awt.Font;
import java.util.*;

public class World extends BasicGameState {

	public static int ID=7;
	public static final String GAME_NAME = "Age of War";
	public static Minion[] minions;

	public static int damageDefault = 5;
	public static int HPminion = 15;
	public static int priceMinion = 20 ;
	public static int goldInit = 50;
	public static int HPInit = 100;
	public static int tailleBoard = 20;
	public static int yMinion = 410;

	public Image fond;
	public Image fondBadEnd;
	public Image fondGoodEnd;
	public Music music1;
	public Sound pegi;
	public boolean debut;

	protected TrueTypeFont fontEnd;

	public static ArrayList<Sort> spells = new ArrayList<>(0);

	public static Board board = new Board(tailleBoard,130,1150);

	public static Player p1 = new Player(1,goldInit,HPInit);
	public static Player p2 = new Player(2,goldInit,HPInit);
	public static Train t1 = new Train(1);
	public static Train t2 = new Train(2);

	public static Minion fantom = new Minion(0, 1, 1);

	private int incr=0;
	private boolean backMenu = false;
	private boolean escapePress = false;
	public static int etat = 0;

    @Override
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		fontEnd = FontUtils.loadFont("Kalinga",Font.BOLD,18,true);
		debut=true;

    	minions = new Minion[tailleBoard];
    	Arrays.fill(minions, fantom);

    	// Minion m1 = new Minion(1, 1, 1);
    	// Minion m2 = new Minion(2, 1, 1);
    	fond = new Image("images/ageOfWar/fond.png");
    	fondBadEnd = new Image("images/ageOfWar/yoda.png");
    	fondGoodEnd = new Image("images/ageOfWar/goodEnd.png");
    	music1=new Music("musics/ageOfWar/age1.ogg");
    	pegi = new Sound("musics/ageOfWar/pegi18.ogg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	if (etat == 0) { // Si tout le monde est vivant
	    	g.drawImage(fond,0,0);
	    	g.setColor(Color.green);
	//    	g.fillRect(50, 50, 50, 50);
	    	p1.render(container,game,g);
	    	p2.render(container,game,g);
	    	t1.render(container, game, g);
	    	t2.render(container, game, g);

	    	for(Sort s : spells){
				s.render(container, game, g);
			}

	    	for(Minion m : minions){
				m.render(container,game,g);
			}
    	} else if (etat == 1) { // Si un joueur est mort
    		g.drawImage(fondBadEnd,0,0);

        	g.setColor(Color.white);
			g.setFont(fontEnd);
        	g.drawString("IL N'Y A PAS DE GAGNANT DANS UNE GUERRE...", 550, 350);
    	} else {

    		g.drawImage(fondGoodEnd,0,0);

        	g.setColor(Color.white);
			g.setFont(fontEnd);
        	g.drawString("La liaison ferroviaire à l'initiative du joueur " + (etat - 2) + " a instauré la paix", 400, 280);
    	}
    }


    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    	if (etat == 0) {
	    	incr++;

	    	if(incr>50){
	    		incr = 0;
	    		for(Minion m : minions){
	    			m.setPosX();
	    		}
	    		board.refreshPositions();
	    		t1.update();
	    		t2.update();
	    	}
	    	p1.update(container, game, delta);
	    	p2.update(container, game, delta);
	    	for(Minion m : minions){
				m.fluidMove();
			}
	    	for(Sort s : spells){
				s.update(container, game, delta);
	    	}
	    	for (int i=0;i<spells.size();i++){
				if(spells.get(i).getI()==63){
					spells.remove(i);
				}
			}
    	}
    	if (backMenu) {
    		game.enterState(MainMenu.ID, new FadeOutTransition(),new FadeInTransition());
    	}
    	if (debut) {
    		debut=false;
    		pegi.play();
    		try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		music1.loop(1,(float) 0.4);
    	}

    }

    public static void reset(){
		spells = new ArrayList<Sort>();
	}

    @Override
	public void keyPressed(int key, char c){
		p1.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			escapePress=true;
			backMenu=true;
		}
		if ((key == Input.KEY_ENTER || key == Input.KEY_ESCAPE ) && etat>=1 ) {
			escapePress = true;
			backMenu  = true;
		}
	}

	@Override
	public void keyReleased(int key, char c){
		if (key == Input.KEY_ENTER || key == Input.KEY_ESCAPE) {
			escapePress = false;
		}
		p1.keyReleased(key, c);
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}

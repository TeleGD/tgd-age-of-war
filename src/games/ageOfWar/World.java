package games.ageOfWar;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

import games.ageOfWar.entity.Player;
import games.ageOfWar.entity.Train;
import games.ageOfWar.entity.minions.Minion;
import games.ageOfWar.entity.minions.Sort;

public class World extends BasicGameState {

	public static int damageDefault = 5;
	public static int HPminion = 15;
	public static int priceMinion = 20 ;
	public static int goldInit = 50;
	public static int HPInit = 100;
	public static int tailleBoard = 20;
	public static int yMinion = 410;

	private int ID;
	private int state;

	public Image fond;
	public Image fondBadEnd;
	public Image fondGoodEnd;
	public Audio music1;
	public Audio pegi;

	protected TrueTypeFont fontEnd;

	public ArrayList<Sort> spells;

	public Board board;

	public Player p1;
	public Player p2;
	public Train t1;
	public Train t2;

	public Minion fantom;
	public Minion[] minions;

	private int incr;
	public int etat;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

    @Override
    public void init(final GameContainer container, final StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
    }

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
		/* Méthode exécutée environ 60 fois par seconde */
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
    public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
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
		} else  if (input.isKeyDown(Input.KEY_ENTER)) {
			this.setState(3);
			game.enterState(1 /* Choice */, new FadeOutTransition(), new FadeInTransition());
		}
    }

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		fontEnd = AppLoader.loadFont("/fonts/vt323.ttf",AppFont.BOLD,18);

		fond = AppLoader.loadPicture("/images/ageOfWar/fond.png");
		fondBadEnd = AppLoader.loadPicture("/images/ageOfWar/yoda.png");
		fondGoodEnd = AppLoader.loadPicture("/images/ageOfWar/goodEnd.png");
		music1=AppLoader.loadAudio("/musics/ageOfWar/age1.ogg");
		pegi = AppLoader.loadAudio("/sounds/ageOfWar/pegi18.ogg");

		spells = new ArrayList<>(0);

		board = new Board(this, tailleBoard,130,1150);

		p1 = new Player(this, 1,goldInit,HPInit);
		p2 = new Player(this, 2,goldInit,HPInit);
		t1 = new Train(this, 1);
		t2 = new Train(this, 2);

		fantom = new Minion(this, 0, 1, 1);
		// Minion m1 = new Minion(1, 1, 1);
		// Minion m2 = new Minion(2, 1, 1);
		minions = new Minion[tailleBoard];
		Arrays.fill(minions, fantom);

		incr=0;
		etat = 0;

		pegi.playAsSoundEffect(1f, .3f, false);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music1.playAsMusic(1f, .4f, true);
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_A:
			this.p1.achatMinion(1);
			break;
		case Input.KEY_Z:
			this.p1.achatMinion(2);
			break;
		case Input.KEY_E:
			this.p1.achatMinion(3);
			break;
		case Input.KEY_S:
			this.p1.castSpell();
			break;
		case Input.KEY_I:
			this.p2.achatMinion(1);
			break;
		case Input.KEY_O:
			this.p2.achatMinion(2);
			break;
		case Input.KEY_P:
			this.p2.achatMinion(3);
			break;
		case Input.KEY_L:
			this.p2.castSpell();
			break;
		}
	}

}

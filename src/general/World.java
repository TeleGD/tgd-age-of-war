package general;

import java.io.File;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World extends BasicGameState {

	public static int ID=2;


	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables

		//Il faudra voir s'il faut bouger ces inits dans enter(...) si ca prend trop de temps


	}

	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps

	}


	public void startGame(){
	}

	public void startAgain() throws SlickException{
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("oui", 200, 200);


	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	}

	public void keyReleased(int key, char c) {
	}


	public void keyPressed(int key, char c) {
		if(key==Input.KEY_SPACE)
			System.exit(0);
	}

	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}

}

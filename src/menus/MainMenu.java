package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class MainMenu extends Menu{

	public static int ID = -3;	
	
	public MainMenu(){
		super.setTitrePrincipal("INSERER TITRE ICI");
		super.setTitreSecondaire("SOUS TITRE");
		
		super.setItems(/*World1.GAME_NAME,World2.GAME_NAME,World3.GAME_NAME,*/"Scores", "Quitter");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}
	
	@Override
	public void onOptionItemFocusedChanged(int position) {
		time=System.currentTimeMillis();
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			//appeler le reset du world du jeu correspondant
			//puis faire un game.enterState(ID du world, transition de sortie comme new FadeOutTransition(), 
			//											 transition d'entree comme new FadeInTransition())
			break;
		case 1:
			//Pareil pour le deuxieme item, etc
			break;
		case 2:
			System.out.println("exit");
			System.exit(0);
			break;
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
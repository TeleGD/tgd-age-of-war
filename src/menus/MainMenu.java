package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends Menu{

	public static int ID = -3;

	public MainMenu(){
		super.setTitrePrincipal("TGD - CODING NIGHT DU MEILLEUR BUREAU");
		super.setTitreSecondaire("CECI EST LE SOUS TITRE SOUS LE TITRE");

		super.setItems(games.ageOfWar.World.GAME_NAME,"Scores", "Quitter");

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
			games.ageOfWar.World.reset();
			game.enterState(games.ageOfWar.World.ID, new FadeOutTransition(),new FadeInTransition());
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

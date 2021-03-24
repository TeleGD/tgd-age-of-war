package games.ageOfWar.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.ageOfWar.World;
import games.ageOfWar.entity.minions.Minion;
import games.ageOfWar.entity.minions.Sort;

public class Player {

	private World world;
	private int PV; //pts de vie de la base
	private int PVMax;
	private int gold;
	private int ID; //num joueur
	private int xp;
	private int xpMax = 3000;
	private int age = 1;
	private int boardLength = World.tailleBoard;
	private int temps = 0;
	private Image base1;
	private Image base2;
	private Audio sadMusic;
	private Audio goodMusic;
	private Audio musicAge;

	//constructeur
	public Player(World world, int num, int gold, int HP ) // init num joueur, or et pv
	{
		this.world = world;
		ID=num;
		sadMusic=AppLoader.loadAudio("/musics/ageOfWar/sadMusic.ogg");
		goodMusic = AppLoader.loadAudio("/musics/ageOfWar/I_LIKE_TRAINS.ogg");
		this.gold=gold;
		PV=HP;
		PVMax=HP;
		base1=AppLoader.loadPicture("/images/ageOfWar/base_1_a1.png");
		base2=AppLoader.loadPicture("/images/ageOfWar/base_2_a1.png");
	}

	public void takeDamage(int degat) {
		PV=PV-degat;
		if (PV<=0) {
			System.out.println("Défaite du joueur "+ID);
			this.world.etat = 1;
			sadMusic.playAsMusic(1f, .3f, true);
//			System.exit(0);
		}
	}

	public int getHp()
	{
		return PV;
	}

	public int getId()
	{
		return ID;
	}

	public int getAge()
	{
		return age;
	}

	public void addGold(int toAdd) {
		gold += toAdd;
	}

	public boolean removeGold(int toRemove) // enleve toRemove a l or du joueur, retourne true si le joueur peut payer
	{
		if((gold-toRemove)<0 )return false;

		else
		{
			gold-=toRemove;
			return true;
		}
	}

	public void augmenteXp(int inc)
	{
		xp+=inc;
	}

	private boolean removeXp(int toRemove) {
		if((xp-toRemove)<0 )return false;

		else
		{
			xp-=toRemove;
			return true;
		}
	}

	public void achatMinion(int type) {

		int price = World.priceMinion * type * age;

		if (ID==1 && this.world.board.getCase(0)==0 ) { //Teste si case de spawn vide
			if (this.world.p1.removeGold(price)) { // teste si le joueur peut payer et l'encaisse si oui
				if (Math.random() > 0.95) {
					type += 3;
				}
				Minion m = new Minion(this.world, 1, age, type);
				this.world.board.setMinionToCase(1,0);
				this.world.t1.removeRail(2); // Le joueur recrute, il perd des rails
			}
		} else if (ID==2 && this.world.board.getCase(boardLength-1) == 0) {
			if (this.world.p2.removeGold(price)) {
				if (Math.random() > 0.95) {
					type += 3;
				}
				Minion m = new Minion(this.world, 2, age, type);
				this.world.board.setMinionToCase(2, boardLength -1);
				this.world.t2.removeRail(2); // Le joueur recrute, il perd des rails
			}
		}
	}

	public void castSpell() {

		if (ID==1) { //Teste si case de spawn vide
			if (this.world.p1.removeXp(250*age)) { // teste si le joueur peut payer et l'encaisse si oui
				this.world.spells.add(new Sort(this.world, ID, age));
			}
		} else {
			if (this.world.p2.removeXp(250*age)) {
				this.world.spells.add(new Sort(this.world, ID, age));
			}
		}
	}


	public void update(GameContainer container,StateBasedGame game, int delta) {
		// update de age, xpMax, pv, pvmax ;
		if(xp>=xpMax && age < 3)
		{
			age++;
			xp = 0; // On réinitialise l'xp à 0
			xpMax=(int)(xpMax*2.5);
			PVMax=(int)(PVMax*1.25);
			PV=(int)(PV*1.25);
			base1=AppLoader.loadPicture("/images/ageOfWar/base_1_a"+age+".png");
			base2=AppLoader.loadPicture("/images/ageOfWar/base_2_a"+age+".png");

			// Gestion musique du fond sonore : vérifie qu'il est le premier à lancer cette musique, sinon ne fait rien
			if (needMusic()) {
				musicAge =AppLoader.loadAudio("/musics/ageOfWar/age" + age + ".ogg");
				musicAge.playAsMusic(1f, .3f, true);
			}
		}

		temps ++;
		if (temps >= 90) {
			gold += 5;
			temps = 0;
		}
	}

	public void render(GameContainer container,StateBasedGame game, Graphics g) {
		if(ID==1)
		{
			g.drawImage(base1, 6, 250);

			g.setColor(new Color(153,0,0));
			g.fillRect(6, 229, 120, 18);

			g.setColor(new Color(255,0,0));
			g.fillRect(6, 229, (int)(120*(double)(PV)/PVMax), 18);

			g.setColor(Color.white);
			g.drawString(""+(int)(((double)(PV)/PVMax)*100)+" %", 8, 229);

			g.setColor(Color.yellow);
			g.drawString((gold) + " gold", 8, 255);

			g.setColor(Color.magenta);
			g.drawString("Age : " + (age), 8, 280);

			if (age<3) {
				g.setColor(new Color(0,153,0));
				g.fillRect(6, 200, 120, 18);

				g.setColor(new Color(0,255,0));
				g.fillRect(6, 200, (int)(120*(double)(xp)/xpMax), 18);

				g.setColor(Color.white);
				g.drawString(""+(int)(((double)(xp)/xpMax)*100)+" %", 8, 200);
			} else {
				g.setColor(Color.white);
				g.drawString(""+xp+" xp", 8, 200);
			}

			// Affichage des coûts des unités :

			g.setColor(Color.white);
			g.drawString("[S] SPECIAL : " + (250*age) +" XP", 8, 135);

			g.setColor(Color.white);
			g.drawString("[A] Soldat : " + (World.priceMinion * 1 * age) +" G", 8, 150);

			g.setColor(Color.white);
			g.drawString("[Z] Distance : " + (World.priceMinion * 2 * age) +" G", 8, 165);

			g.setColor(Color.white);
			g.drawString("[E] Elite : " + (World.priceMinion * 3 * age) +" G", 8, 180);

		}
		else // joueur 2
		{
			g.drawImage(base2, 1151, 250);

			g.setColor(new Color(153,0,0));
			g.fillRect(1151, 229, 120, 18); //

			g.setColor(new Color(255,0,0));
			g.fillRect(1151, 229, (int)(120*(double)(PV)/PVMax), 18);

			g.setColor(Color.white);
			g.drawString(""+(int)(((double)(PV)/PVMax)*100)+" %", 1153, 229);

			g.setColor(Color.yellow);
			g.drawString((gold) + " gold", 1153, 255);

			g.setColor(Color.magenta);
			g.drawString("Age : " + (age), 1153, 280);

			if (age<3) {
				g.setColor(new Color(0,153,0));
				g.fillRect(1151, 200, 120, 18);

				g.setColor(new Color(0,255,0));
				g.fillRect(1151, 200, (int)(120*(double)(xp)/xpMax), 18);

				g.setColor(Color.white);
				g.drawString(""+(int)(((double)(xp)/xpMax)*100)+" %", 1153, 200);
			} else {
				g.setColor(Color.white);
				g.drawString(""+xp+" xp", 1153, 200);
			}

			// Affichage des coûts des unités :

			g.setColor(Color.white);
			g.drawString("[L] SPECIAL : " + (250*age) +" XP", 1100, 134);

			g.setColor(Color.white);
			g.drawString("[I] Soldat : " + (World.priceMinion * 1 * age) +" G", 1100, 150);

			g.setColor(Color.white);
			g.drawString("[O] Distance : " + (World.priceMinion * 2 * age) +" G", 1100, 165);

			g.setColor(Color.white);
			g.drawString("[P] Elite : " + (World.priceMinion * 3 * age) +" G", 1100, 180);
		}
	}

	private boolean needMusic() {
		if (ID ==1) {
			if (age > this.world.p2.getAge() ) {
				return true;
			}
		} else {
			if (age > this.world.p1.getAge() ) {
				return true;
			}
		}
		return false;
	}

}

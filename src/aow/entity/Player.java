package aow.entity;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import aow.World1;
import aow.entity.minions.Minion;

public class Player {
	
	
	private int PV; //pts de vie de la base
	private int PVMax;
	private int gold;
	private int ID; //num joueur
	private int xp;
	private int xpMax = 1000;
	private int age = 1;
	private int boardLength = World1.tailleBoard;
	private int temps = 0;
	
	//constructeur
	public Player(int num, int gold, int HP ) // init num joueur, or et pv 
	{
		ID=num;
		this.gold=gold;
		PV=HP;
		PVMax=HP;
	}
	
	public void takeDamage(int degat) {
		PV=PV-degat;
		if (PV<=0) {
			System.out.println("Défaite du joueur "+ID);
			System.exit(0);
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
	
	public void achatMinion(int type) {
		if (ID==1) {
			switch(type) {
			case 1 : if (World1.board.getCase(0)==0 && World1.p1.removeGold(15*age)) {
						System.out.println("Ca marche !");
						Minion m = new Minion(1, age, type);
					};
			case 2 : if (World1.board.getCase(0)==0 && World1.p1.removeGold(40*age)) {
						// Ajout d'un Minion2 en case 0
						Minion m = new Minion(1, age, type);
					};
			case 3 : if (World1.board.getCase(0)==0 && World1.p1.removeGold(100*age)) {
						// Ajout d'un Minion3 en case 0
						Minion m = new Minion(1, age, type);
					};
			}
		} else if (ID==2) {
			switch(type) {
			case 1 : if (World1.board.getCase(boardLength-1)==0 && World1.p2.removeGold(15*age)) {
						// Ajout d'un Minion1 en case n-1
						Minion m = new Minion(2, age, type);
					};
			case 2 : if (World1.board.getCase(boardLength-1)==0 && World1.p2.removeGold(40*age)) {
						// Ajout d'un Minion2 en case n-1
						Minion m = new Minion(2, age, type);
					};
			case 3 : if (World1.board.getCase(boardLength-1)==0 && World1.p2.removeGold(100*age)) {
						// Ajout d'un Minion3 en case n-1
						Minion m = new Minion(2, age, type);
					};
			}
		}
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException
	{
		// update de age, xpMax, pv, pvmax ;
		if(xp>=xpMax && age < 3)
		{
			age++;
			xpMax=(int)(xpMax*1.75);
			PVMax=(int)(PVMax*1.25);
			PV=(int)(PV*1.25);
		}
		
		temps ++;
		if (temps >= 90) {
			gold += 10;
			temps = 0;
		}
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		if(ID==1)
		{
			g.setColor(new Color(153,0,0));
			g.fillRect(6, 229, 120, 18);
		
			g.setColor(new Color(255,0,0));
			g.fillRect(6, 229, (int)(120*(double)(PV)/PVMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+(int)(((double)(PV)/PVMax)*100)+" %", 8, 229);
			
			g.setColor(Color.yellow);
			g.drawString((double)(gold) + " gold", 8, 255);
			
			g.setColor(Color.magenta);
			g.drawString("Age : " + (int)(age), 8, 280);
			
			g.setColor(new Color(0,153,0));
			g.fillRect(6, 200, 120, 18);
		
			g.setColor(new Color(0,255,0));
			g.fillRect(6, 200, (int)(120*(double)(xp)/xpMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+(int)(((double)(xp)/xpMax)*100)+" %", 8, 200);
			
		}		
		else // joueur 2
		{
			g.setColor(new Color(153,0,0));
			g.fillRect(1151, 229, 120, 18); //
			
			g.setColor(new Color(255,0,0));
			g.fillRect(1151, 229, (int)(120*(double)(PV)/PVMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+(int)(((double)(PV)/PVMax)*100)+" %", 1153, 229);
			
			g.setColor(Color.yellow);
			g.drawString((double)(gold) + " gold", 1153, 255);
			
			g.setColor(Color.magenta);
			g.drawString("Age : " + (int)(age), 1153, 280);
			
			g.setColor(new Color(0,153,0));
			g.fillRect(1151, 200, 120, 18);
		
			g.setColor(new Color(0,255,0));
			g.fillRect(1151, 200, (int)(120*(double)(xp)/xpMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+(int)(((double)(xp)/xpMax)*100)+" %", 1153, 200);
		}
	}
	
	private boolean aPress,zPress,ePress,iPress,oPress,pPress = false;
	
	public void keyPressed(int key, char c) {
		switch (key){
		case Input.KEY_A:
			aPress = true;
			World1.p1.achatMinion(1);
			break;
		case Input.KEY_Z:
			zPress = true;
			World1.p1.achatMinion(2);
			break;
		case Input.KEY_E:
			ePress = true;
			World1.p1.achatMinion(3);
			break;
		case Input.KEY_I:
			iPress = true;
			World1.p2.achatMinion(1);
			break;
		case Input.KEY_O:
			oPress = true;
			World1.p2.achatMinion(2);
			break;
		case Input.KEY_P:
			pPress = true;
			World1.p2.achatMinion(3);
			break;
		}
	}
	
	public void keyReleased(int key, char c) {
		switch (key){
		case Input.KEY_A:
			aPress = false;
			break;
		case Input.KEY_Z:
			zPress = false;
			break;
		case Input.KEY_E:
			ePress = false;
			break;
		case Input.KEY_I:
			iPress = false;
			break;
		case Input.KEY_O:
			oPress = false;
			break;
		case Input.KEY_P:
			pPress = false;
			break;
		}
	}
	
}

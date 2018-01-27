package aow.entity;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import aow.World1;

public class Player {
	
	
	private int PV; //pts de vie de la base
	private int PVMax;
	private int gold;
	private int ID; //num joueur
	private int xp;
	private int xpMax = 1000;
	private int age;
	private int boardLength = World1.tailleBoard;
	
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
						// Ajout d'un Minion1 en case 0
					};
			case 2 : if (World1.board.getCase(0)==0 && World1.p1.removeGold(40*age)) {
						// Ajout d'un Minion2 en case 0
					};
			case 3 : if (World1.board.getCase(0)==0 && World1.p1.removeGold(100*age)) {
						// Ajout d'un Minion3 en case 0
					};
			}
		} else if (ID==2) {
			switch(type) {
			case 1 : if (World1.board.getCase(boardLength-1)==0 && World1.p2.removeGold(50*age)) {
						// Ajout d'un Minion1 en case n-1
					};
			case 2 : if (World1.board.getCase(boardLength-1)==0 && World1.p2.removeGold(100*age)) {
						// Ajout d'un Minion2 en case n-1
					};
			case 3 : if (World1.board.getCase(boardLength-1)==0 && World1.p2.removeGold(150*age)) {
						// Ajout d'un Minion3 en case n-1
					};
			}
		}
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException
	{
		// update de age, xpMax, pv, pvmax ;
		if(xp>=xpMax)
		{
			age++;
			xpMax=(int)(xpMax*1.75);
			PVMax=(int)(PVMax*1.25);
			PV=(int)(PV*1.25);
		}
		
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		if(ID==1)
		{
			g.setColor(new Color(153,0,0));
			g.fillRect(6, 229, 120, 18);
		
			g.setColor(new Color(255,0,0));
			g.fillRect(6, 229, 120*(int)(PV/PVMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+((double)(PV)/PVMax)*100+" %", 8, 229);
			
			g.setColor(Color.yellow);
			g.drawString((double)(gold) + " gold", 8, 255);
			
			
			g.setColor(new Color(0,153,0));
			g.fillRect(6, 200, 120, 18);
		
			g.setColor(new Color(0,255,0));
			g.fillRect(6, 200, 120*(int)(xp/xpMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+((double)(xp)/xpMax)*100+" %", 8, 200);
			
		}		
		else // joueur 2
		{
			g.setColor(new Color(153,0,0));
			g.fillRect(1151, 229, 120, 18); //
			
			g.setColor(new Color(255,0,0));
			g.fillRect(1151, 229, 120*(int)(PV/PVMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+((double)(PV)/PVMax)*100+" %", 1153, 229);
			
			g.setColor(Color.yellow);
			g.drawString((double)(gold) + " gold", 1153, 255);
			
			g.setColor(new Color(0,153,0));
			g.fillRect(1151, 200, 120, 18);
		
			g.setColor(new Color(0,255,0));
			g.fillRect(1151, 200, 120*(int)(xp/xpMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+((double)(xp)/xpMax)*100+" %", 1153, 200);
		}
	}
	
	
}

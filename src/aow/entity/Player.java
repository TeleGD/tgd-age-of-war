package aow.entity;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	
	
	private int PV; //pts de vie de la base
	private int PVMax;
	private int gold;
	private int ID; //num joueur
	private int xp;
	private int xpMax;
	private int age;
	
	//constructeur
	public Player(int num, int gold, int HP ) // init num joueur, or et pv 
	{
		ID=num;
		this.gold=gold;
		PV=HP;
		PVMax=HP;
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
			
		}		
		else //deux joueur
		{
			g.setColor(new Color(153,0,0));
			g.fillRect(1151, 229, 120, 18); //
			
			g.setColor(new Color(255,0,0));
			g.fillRect(1151, 229, 120*(int)(PV/PVMax), 18);
			
			g.setColor(Color.white);
			g.drawString(""+((double)(PV)/PVMax)*100+" %", 1153, 229);
		}
	}
	
	
}

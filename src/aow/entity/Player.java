package aow.entity;


public class Player {
	
	
	
	private int PV; //pts de vie de la base
	private int gold;
	private int ID; //num joueur
	private int xp;
	
	//constructeur
	public Player(int num, int gold, int HP ) // init num joueur, or et pv 
	{
		ID=num;
		this.gold=gold;
		PV=HP;
	}
	
	
	
	public int getHp()
	{
		return PV;
	}
	
	public int getId()
	{
		return ID;
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
	
	
	
	
}

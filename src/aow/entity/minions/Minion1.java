package aow.entity.minions;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import aow.Board;
import aow.entity.Player;

public class Minion1 {
	
	private int x;
	private int y;
	private int nextPosX;
	private int currentPosX;
	private int life;
	private int attack;
	private int direction; // haut=1 -> sens horaire +1
	private double speed;
	private Image sprite;
	private Player player; //Joueur ennemi de cette unité
	private Board board;
	
	public Minion1(int posX, int y, Player player, Board board) {
		this.board = board;
		this.player = player;
		this.x = board.getCorner(posX);
		this.y= y;
		this.currentPosX = posX;
		this.nextPosX = posX;
		this.direction = 0;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.green);
    	g.fillRect(x, y, 50, 50);
	}
	
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }
	
}

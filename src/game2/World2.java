package game2;

import general.World;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.utils.FontUtils;
import menus.MainMenu;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.*;

public class World2 extends BasicGameState {
	
	public static int ID=77;
	public static String name = "La Sainte Ventouse";
	private Player player;
	private Dieu dieu;
	
	private static Shape shape1;
	private static Shape shape2;
	private static Shape shape3;
	
	private Button jouer,rejouer,retourMenu,quitter;
	
	private Tetris next;
	
	private static ArrayList<Tetris> tetrisList;
	private ArrayList<Cloud> cloudList;
	private ArrayList<Shark> sharkList;
	private ArrayList<Bulle> bulleList;
	
	private int time;
	
	private boolean gameOn,gameOver;
	
	private Image fond;
	private String urlFond = "images/TetrisPolyBridge/background.png";
	
	private Cloud cloud;
	
	private static Music mainMusic;
	

    @Override
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
    	
    	gameOn = false;
		gameOver = false;
    	
    	mainMusic=new Music("musics/game2/tetris.ogg");
    	
    	fond = new Image(urlFond);
    	
    	
    	
    }
    
    @Override
    public void enter(final GameContainer container, final StateBasedGame game) throws SlickException {
    	
    	jouer = new Button("Jouer",container,350,250,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		jouer.setTextSize(32);
		jouer.setBackgroundColor(new Color(255,255,255));
		jouer.setSize(420,140);
		jouer.setTextColor(Color.black);
		jouer.setPadding(70,100,70,100);
		jouer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	startGame();
                
            }});
		
		
		rejouer = new Button("Rejouer",container,1104,200,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		rejouer.setTextSize(20);
		rejouer.setBackgroundColor(new Color(255,255,255));
		rejouer.setSize(150,50);
		rejouer.setTextColor(Color.black);
		rejouer.setPadding(70,100,70,100);
		rejouer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	try {
					startAgain();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }});
		
		retourMenu = new Button("Retour Menu",container,1104,280,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		retourMenu.setTextSize(20);
		retourMenu.setBackgroundColor(new Color(255,255,255));
		retourMenu.setSize(150,50);
		retourMenu.setTextColor(Color.black);
		retourMenu.setPadding(70,100,70,100);
		retourMenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	retour();
            	game.enterState(MainMenu.ID, new FadeOutTransition(),
						new FadeInTransition());
                
            }});
		
		quitter = new Button("Quitter",container,1104,360,TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
		quitter.setTextSize(20);
		quitter.setBackgroundColor(new Color(255,255,255));
		quitter.setSize(150,50);
		quitter.setTextColor(Color.black);
		quitter.setPadding(70,100,70,100);
		quitter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(TGDComponent componenent) {
            	System.exit(0);
                
            }});
		
    }
    
    public void retour(){
    	gameOn = false;
    	mainMusic.stop();
    }
    
    public void startGame(){
		gameOn = true;
		try {
	    	mainMusic.play();
			player = new Player(45,400 - 16, new Rectangle(6, 0, 20, 32));
	    	dieu = new Dieu();
	    	
	    	tetrisList = new ArrayList<Tetris>();
	    	cloudList = new ArrayList<Cloud>();
	    	sharkList = new ArrayList<Shark>();
	    	sharkList.add(new Shark(110, 1));
	    	sharkList.add(new Shark(400, 1));
	    	sharkList.add(new Shark(700, 1));
	    	sharkList.add(new Shark(300, 0));
	    	sharkList.add(new Shark(500, 0));
	    	sharkList.add(new Shark(948, 0));
	    	bulleList = new ArrayList<Bulle>();
	    	
	    	time = 0;
			shape1 = new Rectangle(0,400, 100, 320);
	    	shape2 = new Rectangle(980, 400, 100, 320);
	    	shape3 = new Rectangle(100, 572, 880, 148);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void startAgain() throws SlickException{
		gameOn = true;
		gameOver = false;
		player = new Player(45,400 - 16, new Rectangle(6, 0, 20, 32));
    	dieu = new Dieu();
    	tetrisList = new ArrayList<Tetris>();
		time = 0;
		mainMusic.play();
	}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	
    	//fond avec image
    	g.drawImage(fond, 0, 0);
    	
    	g.setColor(Color.black);
    	g.fillRect(1080, 0, 1280, 720);
    	
    	if (!gameOn && !gameOver){
			jouer.render(container, game, g);
		} 
    	
    	
    	
    	if (gameOn) {
    	
    		
    		
	    	//les trucs
	    	for(Cloud u:cloudList){
	    		u.render(container, game, g);
	    	}
	    	
	    	g.setColor(Color.black);
	    	g.fillRect(1080, 0, 1280, 720);
	    	
	    	rejouer.render(container, game, g);
    		retourMenu.render(container, game, g);
    		quitter.render(container, game, g);
	    	
	    	for(Shark u:sharkList){
	    		u.render(container, game, g);
	    	}
	    	
	    	for(Bulle u:bulleList){
	    		u.render(container, game, g);
	    	}
	    	
	    	dieu.render(container, game, g);
	    	player.render(container, game, g);
	    	
	    	for(Tetris u:tetrisList){
	    		u.render(container, game, g);
	    	}
	    	
	    	g.draw(shape1);
	    	g.draw(shape2);
	    	g.draw(shape3);
	
	    	
	    	g.setColor(Color.white);
	    	g.drawString("Time : ", 1160, 65);
	    	g.drawString(((time/1000)/60)/60 + " h " + (time/1000)/60 + " min " + ((time/1000)%60)%60  + " s", 1130, 90);
	    	/*
	    	next = dieu.getNextBlock();
	    	next.setXcentre(1100);
	    	next.setYcentre(500);
	    	next.rotate(0);
	    	next.render(container, game, g);
	    	*/
    	}
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	if (gameOn){
	    	for(Cloud u:cloudList){
	    		if(u.getPosX()>1080){
	    			cloudList.remove(u);
	    			break;
	    		}
	    		
	    	}
	    	
	    	if(cloudList.size()>0){
	    	 	for(Cloud u:cloudList){
		    		u.update(container, game, delta);
		    	}
	    	}
	    	
	    	if(Math.random() * 1.001 > 0.997 ){
				cloudList.add(new Cloud());
			}
	    	
	    	for(int i = 0; i < bulleList.size(); i++){
	    		for(Bulle u: bulleList){
	        		if(u.getPosy()<652){
	        			bulleList.remove(u);
	        			break;
	        		}
	        		
	        	}
	    	}
	    	
	    	
	    	for(Shark u:sharkList){
	    		u.update(container, game, delta);
	    	}
	    	
	    	if(bulleList.size()>0){
		    	for(Bulle u:bulleList){
		    		u.update(container, game, delta);
		    	}
	    	}
	    	
	    	if(Math.random() * 1.001 > 0.9 ){
				bulleList.add(new Bulle((int) (Math.random() * (960 - 120) + 120), 0));
			}
	    	
	    	dieu.update(container, game, delta);
	    	player.update(container, game, delta);
	    	time += delta;
    	}
    }
    
    public static void reset() {

    }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public void keyPressed(int key, char c){
		dieu.keyPressed(key, c);
		player.keyPressed(key, c);
	}
	
	public void keyReleased(int key, char c){
		dieu.keyReleased(key, c);
		player.keyReleased(key, c);
	}
	
	public static ArrayList<Tetris> getTetrisList(){
		return tetrisList;
	}
	
	public static void addTetrisList(Tetris tet){
		tetrisList.add(tet);
	}

	public static void setTetrisList(ArrayList<Tetris> tetList){
		tetrisList = tetList;
	}
	
	public static Shape getShape1(){
		return shape1;
	}
	
	public static Shape getShape2(){
		return shape2;
	}
	
	public static Shape getShape3(){
		return shape3;
	}

}

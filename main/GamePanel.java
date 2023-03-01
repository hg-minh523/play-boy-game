package main;

import java.awt.*;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tile.TileManagerment;
public class GamePanel extends JPanel implements Runnable {
    public final int originalTitleSize = 16;
    public final int scale = 3;
    public final int titleSize = originalTitleSize * scale;
    public final int row = 12;
    public final int col = 16;

    public final int screenMaxCol = col * titleSize;
    public final int screenMaxRow = row * titleSize;
    public final int maxWorldX = 50;
    public final int maxWorldY = 50;
    Thread gameThread;
    long startTime = System.nanoTime();
    
    KeyHandler keyHandle = new KeyHandler(this);
    public Player player = new Player(this,keyHandle);
    public TileManagerment tileManagerment = new TileManagerment(this);
    public ColissionChecker cChecker = new ColissionChecker(this);
    public SuperObject obj_Arr[] = new SuperObject[10];
    public UI ui = new UI(this);
    AssetsSetter aSetter = new AssetsSetter(this);
    public Entity entityList[] = new Entity[10];
    public EventHandler eventH = new EventHandler(this);
    public int gameState;
    public int titleState = 0;
    public int playState = 1;
    public int pauseState = 2;
    public int dialogState = 3 ;
    public GamePanel() {
    	gameState = titleState;
        this.setPreferredSize(new Dimension(screenMaxCol, screenMaxRow));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandle);
        this.setFocusable(true);
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
    	if (gameState == playState) {
//        	Player
        	player.update();
//     		NPC
        	for ( int i = 0 ; i < entityList.length ; i++) {
        		if(entityList[i] != null) {
        			entityList[i].update();
        		}
        	}
    	}
    	
    	if ( gameState == pauseState) {
//    		System.out.println(1);
    	}
    	
//    	if (gameState == dialogState) {
//    		
//    	}

    }
    public void setAssets() {
    	aSetter.setterObject();
    	aSetter.setterNPC();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            // long nanoTime = System.nanoTime();
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
            // startTime = System.nanoTime();
        }
    }

    public void paintComponent(Graphics g) {

		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (gameState == titleState) {
        	if (keyHandle.titleStateNumber == 0) {
    			ui.drawTitleScreen(g2);
        	}else if (keyHandle.titleStateNumber == 1) {
        		ui.drawTitleScreenV2(g2);
        	}
		}else {
//            Tile
            tileManagerment.draw(g2);
//            Player
            player.draw(g2);

//            Object
            for ( int i = 0 ; i < obj_Arr.length ; i++) {
            	if (obj_Arr[i] != null) {
            		obj_Arr[i].draw(g2, this);
            	}
            }
//            UI
            ui.draw(g2);
//     		  NPC
            for ( int i = 0 ; i < entityList.length ; i++) {
            	if (entityList[i] != null) {
            		entityList[i].draw(g2, this);
            	}
            }
    	}
    	g2.dispose();
        
       
    }
}

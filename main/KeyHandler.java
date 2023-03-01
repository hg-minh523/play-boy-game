package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener{
	GamePanel gp;
    public boolean isUp,isDown,isLeft,isRight;
    int pauseCouter = 0;
    int dialogCouter = 0;
    public int numMenu = 0;
    public int titleStateNumber = 0;
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    public void pauseStateScreen(int pauseOn) {
    	if (pauseCouter == 1 ) {
    		gp.gameState = gp.pauseState;
    	}else {
    		gp.gameState = gp.playState;    	}
    }
    
    public void dialogStateScreen(int dialogCouter) {
    	if (dialogCouter == 1 ) {
    		gp.gameState = gp.dialogState;
    	}else {
    		gp.gameState = gp.playState;    	}
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
           isUp = true;
        }    

        if(e.getKeyCode() == KeyEvent.VK_A){
            isLeft = true;
        } 

        if(e.getKeyCode() == KeyEvent.VK_S){
            isDown = true;
        } 

        if(e.getKeyCode() == KeyEvent.VK_D){
            isRight = true;
        } 
    }
    @Override

    public void keyReleased(KeyEvent e){
    	if (gp.gameState == gp.titleState) {
    		 if(e.getKeyCode() == KeyEvent.VK_W){
    			 numMenu--;
    	     } 
    		 if(e.getKeyCode() == KeyEvent.VK_S){
 	            numMenu++;
 	         } 
    		 if (numMenu > 2) {
    			 numMenu = 0;
    		 }else if (numMenu < 0){
    			 numMenu = 2;
    		 }
    		 if(e.getKeyCode() == KeyEvent.VK_ENTER){
    			 if (numMenu == 0) {
    				 if(titleStateNumber == 1) {
        				 isUp=isDown=isLeft=isRight=false;
        	  	         gp.gameState = gp.playState;
    				 }
    				 titleStateNumber = 1;
    			 }
    			 if (numMenu == 1) {
// 	  	            gp.gameState = gp.playState;
    			 }
    			 if (numMenu == 2) {
    				 System.exit(0);
 			 }
  	         } 
    	}else if (gp.gameState == gp.playState){
    		  if(e.getKeyCode() == KeyEvent.VK_W){
    	            isUp = false;
    	         }    
    	 
    	         if(e.getKeyCode() == KeyEvent.VK_A){
    	             isLeft = false;
    	         } 
    	 
    	         if(e.getKeyCode() == KeyEvent.VK_S){
    	             isDown = false;
    	         } 
    	 
    	         if(e.getKeyCode() == KeyEvent.VK_D){
    	             isRight = false;
    	         } 
    	         
    	         if(e.getKeyCode() == KeyEvent.VK_P){
    	        	 if (pauseCouter == 0) {
    	        		 pauseCouter =1;
    	        	 }else {
    	        		 pauseCouter = 0;
    	        	 }
    	        	 pauseStateScreen(pauseCouter);
    	         } 
    	         
    	         if(e.getKeyCode() == KeyEvent.VK_ENTER){
    	        	 if (dialogCouter == 0) {
    	        		 dialogCouter =1;
    	        	 }else {
    	        		 dialogCouter = 0;
    	        	 }
    	        	 dialogStateScreen(dialogCouter);
    	         } 
    	}
      
    }
}

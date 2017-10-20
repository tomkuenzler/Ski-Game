import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;


public class Ski implements KeyListener{
    
    Image imgSkier, imgTree, imgGoomba, imgBoo, imgBullet, imgSnow;
    boolean crashed, leftArrow, rightArrow, upArrow, downArrow;

    int skierX, skierY, treeX, treeY, goombaX, goombaY, booX, booY, bulletX, bulletY;
    int skierWidth, skierHeight, treeWidth, treeHeight, goombaWidth, goombaHeight, booWidth, booHeight, bulletWidth, bulletHeight;
    int score;
    
    JFrame frame;
    GamePanel gPanel;
    
    public Ski()    {
        score = 0;
        treeX =(int)(Math.random() * ((440) + 1));
        treeY = 500;        
        goombaX =(int)(Math.random() * ((475) + 1));
        goombaY = 700;
        booX = 50 +(int)(Math.random() * ((425-50) + 1));
        booY = 700;
        bulletX = 25 +(int)(Math.random() * ((450-25) + 1));
        bulletY = 700;
        skierX = 100;
        skierY = 10;
        crashed = false;
        leftArrow = false;
        rightArrow = false;
        upArrow = false;
        downArrow = false;
        frame = new JFrame();
        gPanel = new GamePanel();
        gPanel.setFocusable(true);
        gPanel.addKeyListener(this);
        getImages();
        frame.add(gPanel);
        frame.setSize(500,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        animate();
    }

    public void getImages(){
        
        imgSkier = new ImageIcon("skier.png").getImage();
        imgTree = new ImageIcon("snowy_tree2.png").getImage();        
        imgGoomba = new ImageIcon("Goomba.png").getImage();
        imgBoo = new ImageIcon("boo.png").getImage();
        imgBullet = new ImageIcon("bullet.png").getImage();
        imgSnow = new ImageIcon("snow.gif").getImage();
        skierWidth = imgSkier.getWidth(null);
        skierHeight = imgSkier.getHeight(null);
        treeWidth = imgTree.getWidth(null);
        treeHeight = imgTree.getHeight(null);    
        goombaWidth = imgGoomba.getWidth(null);
        goombaHeight = imgGoomba.getHeight(null);
        booWidth = imgBoo.getWidth(null);
        booHeight = imgBoo.getHeight(null);
        bulletWidth = imgBoo.getWidth(null);
        bulletHeight = imgBoo.getHeight(null);
        
    }
        
    
    class GamePanel extends JPanel{//inner class to customize a JPanel for drawing
        
        public void paintComponent(Graphics g){
            
            super.paintComponent(g);
            g.drawImage(imgSnow, 0, 0, this);
        	
//        	g.setColor(Color.white);//Avoid trails
//            g.fillRect(0,0,this.getWidth(), this.getHeight());//Avoid trails

            g.drawImage(imgSkier, skierX, skierY, 60, 100, null);
            g.drawImage(imgTree, treeX, treeY, 60, 100, null);            
            g.drawImage(imgGoomba, goombaX, goombaY, 60, 100, null);
            g.drawImage(imgBoo, booX, booY, 100, 140, null);
            g.drawImage(imgBullet, bulletX, bulletY, 80, 120, null);
        
            g.setColor(Color.black);
            g.fillRect(400,0,84,30);           
            
            g.setColor(Color.yellow);
            g.fillRect(404,4,76,22);
            
            g.setColor(Color.blue);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString("Score: " + score, 415, 20);
            
            if(score == 3  || score == 10 || score == 25 || score == 50 || score == 100){
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Level Up", 200, 350);
            }            
            
            if(crashed){
                
                g.setColor(Color.black);
                g.fillRoundRect(60,100,350,200,20,20);
                g.setColor(Color.green);
                g.fillRoundRect(65,105,340,190,20,20);
                g.setColor(Color.blue);
                g.fillRoundRect(70,110,330,180,20,20);
                
                g.setColor(Color.black);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 80, 180);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Your Final Score: " + score, 140, 230);                                
            }
                
                
        }        
    }  
    
    
    
    public void animate(){
      while(!crashed){
        
        treeY = treeY - 2;
        if(score > 2){
            treeY = treeY - 1;
        }
        if(score > 9){
            goombaY = goombaY - 2;
        }  
        if(score > 24){
            goombaY = goombaY - 1;
            booY = booY - 1;
        }
        if(score > 49){
            treeY = treeY - 1;
            booY = booY - 1;
            bulletY = bulletY - 1;
        }
        if(score > 99){
            treeY = treeY - 1;
            goombaY = goombaY - 1;
            booY = booY - 1;
            bulletY = bulletY - 1;
        }
        if(score > 199){
            treeY = treeY - 1;
            goombaY = goombaY - 1;
            booY = booY - 1;
            bulletY = bulletY - 1;
        }
        if(skierX > 430){
        	if(rightArrow) skierX -= 5;
        }
        if(skierX < 0){
        	if(leftArrow) skierX += 5;
        }
        if(skierY < 5){
        	if(upArrow) skierY += 5;
        }
        if(skierY > 560){
        	if(downArrow) skierY -= 5;
        }
        if(leftArrow) skierX -= 5;
        if(rightArrow) skierX += 5;
        if(upArrow) skierY -= 5;
        if(downArrow) skierY += 5;
        
        
        try{
            
            Thread.sleep(10);
            
        } catch(Exception e){}

        
        Rectangle skierBounds = new Rectangle(skierX, skierY, 55, 98);
        Rectangle treeBounds = new Rectangle(treeX, treeY, 60, 100);
        Rectangle goombaBounds = new Rectangle(goombaX, goombaY, 60, 100);
        Rectangle booBounds = new Rectangle(booX, booY, 100, 140);
        Rectangle bulletBounds = new Rectangle(bulletX, bulletY, 80, 120);
        
        if(skierBounds.intersects(treeBounds)        ){
            crashed = true;
        }       
        if(skierBounds.intersects(goombaBounds)        ){
            crashed = true;
        }
        if(skierBounds.intersects(booBounds)        ){
            crashed = true;
        }
        if(skierBounds.intersects(bulletBounds)        ){
            crashed = true;
        }
        
        if(treeY < 1){
            score = score + 1;
            treeX =(int)(Math.random() * ((475) + 1));
            treeY = 600;
        }
        
        if(goombaY < 1){
            score = score + 1;
            goombaX =(int)(Math.random() * ((475) + 1));
            goombaY = 600;
        }
        
        if(booY < 1){
            score = score + 1;
            booX = 50 +(int)(Math.random() * ((425-50) + 1));
            booY = 600;
        }
        if(bulletY < 1){
            score = score + 1;
            bulletX = 25 +(int)(Math.random() * ((450-25) + 1));
            bulletY = 600;
        }
        
            
        
                        gPanel.repaint();
      }//close loop
    } //close animate  
    public void keyTyped(KeyEvent e){
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
        leftArrow = true;            
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        rightArrow = true;            
        }      
        if(e.getKeyCode() == KeyEvent.VK_UP){
        upArrow = true;            
        } 
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
        downArrow = true;            
        } 
    }
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
        leftArrow = true;            
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        rightArrow = true;            
        }          
        if(e.getKeyCode() == KeyEvent.VK_UP){
        upArrow = true;            
        }   
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
        downArrow = true;            
        }   
        
    }
    public void keyReleased(KeyEvent e){       
                
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
        leftArrow = false;            
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        rightArrow = false;            
        }   
        if(e.getKeyCode() == KeyEvent.VK_UP){
        upArrow = false;            
        }  
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
        downArrow = false;            
        }  
        
        
    }     
           
    public static void main(String[] y)
    {
        Ski s = new Ski();
        
    }
}

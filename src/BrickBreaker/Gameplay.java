package BrickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener , ActionListener {
	private boolean play = false;
	private int Score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int PlayerX= 310;
	
	private int ballposx = 120;
	private int ballposy = 350;
	private int balldirx = -1;
	private int balldiry = -2;
	
	private MapGenerator map;
	
	public Gameplay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	timer = new Timer(delay,this);
	timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.white);
		g.fillRect(1, 1,692,592);
		
		//map generator
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.red);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		//score
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+Score, 590, 30);
		
		//the paddle
		g.setColor(Color.blue);
		g.fillRect(PlayerX,550,100,8);
		
		//ball
		g.setColor(Color.red);
		g.fillOval(ballposx,ballposy,20,20);
		
		if(totalBricks<=0) {
			play = false;
			balldirx =0;
			balldiry = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,50));
			g.drawString("you won",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to restart",230,350);
		}
		
		if(ballposx>570) {
			play = false;
			balldirx =0;
			balldiry = 0;
			g.setColor(Color.magenta);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over,Score:",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to restart",230,350);
		}
		
		
		g.dispose();
		
	}
	

	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(PlayerX,550,100,8))) {
				balldiry = - balldiry;
			}

			A:	for(int i =0; i<map.map.length; i++) {
				  for(int j =0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickx= j*map.brickwidth + 80;
						int bricky = i*map.brickheight + 50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect = new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballrect = new Rectangle(ballposx,ballposy,20,20);
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect)) {
							map.setBrickValue(0,i,j);
							totalBricks--;
							Score+=5;
							
						if(ballposx +19 <= brickrect.x || ballposx + 1 >= brickrect.x + brickrect.width) {
						   balldirx = -balldirx;
						}else {
							balldiry = -balldiry;
						}
						break A;
					}
					}
				  }
			}
			ballposx+=balldirx;
			ballposy+=balldiry;
			if(ballposx<0) {
				balldirx = -balldirx;
			}
			if(ballposy <0) {
				balldiry = -balldiry;
			}
			if(ballposx>670) {
				balldirx = -balldirx;
			}
		}
		 
	repaint();
	
}

	public void keyTyped(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		if(PlayerX<=600) {
			PlayerX  =600;
		}else {
			moveRight();
		}
	}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(PlayerX<=10) {
				PlayerX  =10;
			}else {
				moveLeft();
			}
			}
		
	    if(e.getKeyCode()==KeyEvent.VK_ENTER) {
	    	if(!play) {
	    		play = true;
	    		 ballposx = 120;
	    	     ballposy = 350;
	    		 balldirx = -1;
	    		 balldiry = -2;
	    		 PlayerX = 310;
	    		 Score = 0;
	    		 totalBricks = 21;
	    		 map = new MapGenerator(3,7);
	    		 
	    		 repaint();
	    		 
	    }
	    }
	
	
	
	}
	public void moveRight() {
		play = true;
		PlayerX+=20;
	}
	public void moveLeft() {
		play = true;
		PlayerX-=20;
	

	
	}
}


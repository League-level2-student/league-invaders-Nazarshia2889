import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    
    Font titleFont;
    Font titleFontTwo;
    Font titleFontThree;
    
    Font endFont;
    Font endFontTwo;
    Font endFontThree;
    
    Timer frameDraw;
    
    Rocketship rocket = new Rocketship(250, 700, 50, 50);
    ObjectManager objects = new ObjectManager(rocket);
    
    GamePanel(){
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	titleFontTwo = new Font("Arial", Font.PLAIN, 24);
    	titleFontThree = new Font("Arial", Font.PLAIN, 28);
    	
    	endFont = new Font("Arial", Font.PLAIN, 48);
    	endFontTwo = new Font("Arial", Font.PLAIN, 24);
    	endFontThree = new Font("Arial", Font.PLAIN, 28);
    	
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.restart();
    }
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU) {
			drawMenuState(g);
		}
		else if(currentState == GAME) {
			drawGameState(g);
		}
		else if(currentState == END) {
			drawEndState(g);
		}

	}
	
	void updateMenuState() {
		
	}
	void updateGameState() {
		rocket.update();
		objects.update();
	}
	void updateEndState() {
		
	}
	
	
	void drawMenuState(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 100);
		
		g.setFont(titleFontTwo);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 120, 300);
		
		g.setFont(titleFontThree);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 65, 400);
	}
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		objects.draw(g);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(endFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 100, 100);
		
		g.setFont(endFontTwo);
		g.setColor(Color.YELLOW);
		g.drawString("You killed enemies", 120, 300);
		
		g.setFont(endFontThree);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 65, 450);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}
		else if(currentState == GAME){
		    updateGameState();
		}
		else if(currentState == END){
		    updateEndState();
		}
		
		System.out.println("action");
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}   
		if(currentState == GAME) {
			if(rocket.x < WIDTH || rocket.x > 0 || rocket.y < HEIGHT || rocket.y > 0) {
				if (e.getKeyCode()==KeyEvent.VK_UP) {
					rocket.up = true;
				}
				else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
					rocket.down = true;
				}
				else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
					rocket.right = true;
				}
				else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
					rocket.left = true;
				}
			}

			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			rocket.up = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			rocket.down = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocket.right = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			rocket.left = false;
		}
	}
}

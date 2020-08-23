import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
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
    Timer alienSpawn;
    
    Rocketship rocket = new Rocketship(250, 700, 50, 50);
    ObjectManager objects = new ObjectManager(rocket);
    
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    
    GamePanel(){
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	titleFontTwo = new Font("Arial", Font.PLAIN, 24);
    	titleFontThree = new Font("Arial", Font.PLAIN, 28);
    	
    	endFont = new Font("Arial", Font.PLAIN, 48);
    	endFontTwo = new Font("Arial", Font.PLAIN, 24);
    	endFontThree = new Font("Arial", Font.PLAIN, 28);
    	
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.restart();
    	
    	if (needImage) {
    	    loadImage ("space.png");
    	}
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
		if(rocket.isActive == false) {
			currentState = END;
			//System.out.println("Active");
		}
		else if(rocket.isActive == true) {
			rocket.update();
			objects.update();
		}
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
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
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
		g.drawString("You killed " + objects.getScore() + " enemies ", 120, 300);
		
		g.setFont(endFontThree);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 65, 450);
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, objects);
		alienSpawn.start();
		
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
		    } 
		    else {
		        currentState++;
		        if(currentState==GAME) {
		        	startGame();
		        }
		        if(currentState == END) {
		        	Rocketship rocket = new Rocketship(250, 700, 50, 50);
		        	ObjectManager objects = new ObjectManager(rocket);
		        }
		        if(currentState == MENU) {
		        	objects.score = 0;
		        }
		    }
		}   
		if(currentState == GAME) {
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
				
				if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					objects.addProjectile(rocket.getProjectile());
				}
			}
		if(currentState == END) {
			alienSpawn.stop();
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
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}

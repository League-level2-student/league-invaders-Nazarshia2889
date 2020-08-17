import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject{
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Rocketship(int x, int y, int width, int height){
		super(x,y,width,height);
		speed = 10;
		
		if (needImage) {
		    loadImage ("rocket.png");
		}

	}
	
	void draw(Graphics g) {
	     if (gotImage) {
	    		g.drawImage(image, x, y, width, height, null);
	    	} else {
	    		g.setColor(Color.BLUE);
	    		g.fillRect(x, y, width, height);
	    	}
	}
	void update() {
		super.update();
			if(up == true) {
				if(y > 0) {
					up();
				}
			}
			else if(down == true) {
				if(y < LeagueInvaders.HEIGHT - 75) {
					down();
				}
			}
			else if(left == true) {
				if(x > 0) {
					left();
				}
			}
			else if(right == true) {
				if(x < LeagueInvaders.WIDTH - 40) {
					right();
				}
			}
		}
	
	
	public void up() {
		y -= speed;
	}
	public void down() {
		y += speed;
	}
	public void left() {
		x -= speed;
	}
	public void right() {
		x += speed;
	}
	
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
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

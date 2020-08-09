import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject{
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	Rocketship(int x, int y, int width, int height){
		super(x,y,width,height);
		speed = 10;
	}
	
	void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	     g.fillRect(x, y, width, height);
	     
	}
	void update() {
		if(up == true) {
			up();
		}
		else if(down == true) {
			down();
		}
		else if(left == true) {
			left();
		}
		else if(right == true) {
			right();
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

}

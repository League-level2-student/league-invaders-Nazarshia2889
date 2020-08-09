import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	
	
	ObjectManager(Rocketship rocket){
		this.rocket = rocket;
	}
	
	void addProjectile(Projectile project) {
		projectiles.add(project);
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	void update(){
		for(int i = 0;i<aliens.size();i++){
			Alien s = aliens.get(i);
			s.update();
			if(s.y > LeagueInvaders.HEIGHT) {
				s.isActive = false;
			}
		}
		for(int i = 0;i<projectiles.size();i++){
			Projectile s = projectiles.get(i);
			s.update();
			if(s.y > LeagueInvaders.HEIGHT) {
				s.isActive = false;
			}
		}
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		
		for(int i = 0;i<aliens.size();i++){
			Alien s = aliens.get(i);
			s.draw(g);
		}
		for(int i = 0;i<projectiles.size();i++){
			Projectile s = projectiles.get(i);
			s.draw(g);
		}
	}
	
	void purgeObjects() {
		for(int i = 0;i<aliens.size();i++){
			Alien s = aliens.get(i);
			if(s.isActive == false) {
				aliens.remove(s);
			}
		}
		for(int i = 0;i<projectiles.size();i++){
			Projectile s = projectiles.get(i);
			if(s.isActive == false) {
				projectiles.remove(s);
			}
		}
	}


	
}


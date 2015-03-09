package f2.spw;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.Timer;

public class SpaceShip extends Sprite{
    
	int step = 10;
	//private Timer timer;

    public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 600 - width)
			x = 600 - width;
			System.out.println(x);
	}
	public void move_up_down(int direction){
		y += (step*direction);
		if(y < 60)
			y = 3 * height;
		if(y > 580 - height)
			y = 580 - height;
			System.out.println(y);
	}
    public void makeBullet(GamePanel gp,ArrayList<Bullet> bullet){
        Bullet b = new Bullet(this.x + this.width/2,this.y);
        gp.sprites.add(b);
        bullet.add(b);

    }
    public void shoot(GamePanel gp,ArrayList<Bullet> bullet){
        Iterator<Bullet> b_iter = bullet.iterator();
        while(b_iter.hasNext()){
            Bullet b = b_iter.next();
            b.proceed();
            if(!b.isAlive()){
                b_iter.remove();
                gp.sprites.remove(b);
            }
        }
    }
}

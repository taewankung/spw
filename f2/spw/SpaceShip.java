package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 10;
	
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
	
}

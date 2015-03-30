package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	protected int HP = 10;
	protected int step = 10;
	protected boolean alive = true;
	/*public int positionX(){
        return this.x;
    }
    public int positionY(){
        return this.y;
    }*/
	public Enemy(int x, int y) {
		super(x, y,20 , 30);	
	}
	public int getHP(){
		return this.HP;
	}
	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
		(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
    public void die(){
        alive = false;
    }

    public boolean isAlive(){
		return alive;
	}
}

package f2.spw;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;

public class Missile extends Bullet{
	public Missile(int x,int y)
	{
		super(x,y,30,20);
	}
	@Override
	public void draw(Graphics2D g){
			g.setColor(Color.YELLOW);
            g.fillRect(x,y,width,height);
	}
}

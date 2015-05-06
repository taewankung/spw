package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	protected long HP = 10;
	protected int step = 10;
	protected boolean alive = true;
	private BufferedImage img;

	public Enemy(int x, int y) {
		super(x, y,50 , 50);	
	}
	public void reduceHP(int damage){
		this.HP=this.HP-damage;
	}
	public long getHP(){
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
        try{
            img = ImageIO.read(new File("f2/resource/enermy.gif"));
        }catch(IOException e){
        }
	//	g.fillRect(x, y, width, height);
        g.drawImage(img,x,y,width,height,null);
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

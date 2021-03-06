package f2.spw;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class SpaceShip extends Sprite{
   	
	private int Hp = 100; 
	private int Exp = 0;
    private int maxHp;
	private boolean die = false;
    private int maxExp =1000;
    private BufferedImage img;
    private int level=1;
    int step = 10;
    	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
        try{
            img = ImageIO.read(new File("f2/resource/SpaceShip.png"));
        }
        catch(IOException e)
        {
            System.out.println("Can't loaded Image");
        }
	    g.drawImage(img,x,y,width,height,null);	
	}
	public int getHp(){
		return this.Hp;
	}
	public void reduceHP(int damage){
		this.Hp-= damage;
	}
    public void setMaxExp(int exp){
        this.maxExp = exp;
    }
    public int getMaxExp(){
        return this.maxExp;
    }
	public void incleaseExp(){
        this.Exp += 100;
        if(this.Exp > this.maxExp)
        {
            this.level++;
            this.Exp = 0;
            this.maxExp += this.maxExp;
        }
	}
    public int getLevel(){
        return this.level;
    }
	public int getExp(){
		return this.Exp;
	}
	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 600 - width)
			x = 600 - width;
	}
	public void move_up_down(int direction){
		y += (step*direction);
		if(y < 60)
			y = height;
		if(y > 580 - height)
			y = 580 - height;
	}
    public void makeBullet(GamePanel gp,ArrayList<Bullet> bullet){
        Bullet b = new Bullet(this.x + this.width/2,this.y);
        gp.sprites.add(b);
        bullet.add(b);
    }
    public void makeMissile(GamePanel gp,ArrayList<Missile> missile){
        Missile ms = new Missile(this.x + this.width/2,this.y);
        gp.sprites.add(ms);
        missile.add(ms);
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
    public void shootM(GamePanel gp,ArrayList<Missile> missile){
        Iterator<Missile> ms_iter = missile.iterator();
        while(ms_iter.hasNext()){
            Missile m = ms_iter.next();
            m.proceed();
            if(!m.isAlive()){
                ms_iter.remove();
                gp.sprites.remove(m);
            }
        }
    }
    public void die(){
        this.die = true;
    }
    public boolean getDie(){
        return this.die;
    }
}

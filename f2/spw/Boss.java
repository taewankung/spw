package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Boss extends Enemy{
	private boolean moveR = true;
	private boolean moveL = false;
	public Boss(int x,int y,int HP,int step){
		super(x,y);
		this.HP = HP;
		this.step = step;
	}
	public Boss(int x,int y,int HP,int step,int width,int height)
	{
		super(x,y);
		this.HP = HP;
		this.step = step;
		this.width = width;
		this.height = height;
	}
	public static void main(String args[]){
		Boss b = new Boss(20,30,100,12);
		b.proceed();
		System.out.println("this HP " + b.getHP());
	}
	@Override
	public void die(){
		if(this.HP <= 0){
			this.alive = false;
		}
	}
	@Override
	public void draw(Graphics2D g){
	g.setColor(Color.ORANGE);
	g.fillRect(x,y,this.width,this.height);
	}
	@Override
	public void proceed(){
		System.out.println("TRUE");
		if(this.x <= 650 && moveR == true )
		{
			this.x += this.step;
			if(this.x >640){
				moveR = false;
				moveL = true;
			}
		}
		if(this.x >= 0 && moveL == true)
		{
			this.x -= this.step;
			if(this.x < 0)
			{
				moveL = false;
				moveR = true;
			}
		}
	}
	
}

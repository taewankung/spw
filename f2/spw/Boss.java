package f2.spw;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
public class Boss extends Enemy{
	protected boolean moveR = true;
	protected boolean moveL = false;
    protected boolean moveT = false;
    protected boolean moveD = true;
	protected boolean blink = false;
/*    protected Runnable rRL = new RunRL(this,this.mode);
    protected Runnable rTD = new RunTD(this,this.mode);
    protected Thread tMoveRL = new Thread();
    protected Thread tMoveTD = new Thread();*/
    private BufferedImage img;
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
	@Override
	public void die(){
		if(this.HP <= 0){
			this.alive = false;
		}
	}
	@Override
	public void draw(Graphics2D g){
	    try{
            img = ImageIO.read(new File("f2/resource/Boss.png"));
        }
        catch(IOException e)
        {
            System.out.println("Can't loaded Image");
        }
        g.drawImage(img,x,y,this.width,this.height,null);
	}
	@Override
	public void proceed(){
     /*   tMoveRL.start();
        tMoveTD.start();*/
        moveRL();
        moveTD();
	}
    public void moveRL(){
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
    public void moveTD(){
        //System.out.print(this.y);
        if(this.y <= 500 && moveD == true )
		    {
			    this.y += this.step;
			    if(this.y > 500){
				    moveD = false;
				    moveT = true;
			    }
		    }
        if(this.y >= 70 && moveT == true)
	   	    {
			    this.y -= this.step;
			    if(this.y < 70)
			    {
				    moveT = false;
				    moveD = true;
			    }
		    }
    }    
}

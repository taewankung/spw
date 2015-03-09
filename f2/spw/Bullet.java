package f2.spw;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;

public class Bullet extends Sprite{ 
	  public static final int Y_TO_FADE = 100;
          public static final int Y_TO_DIE = 60;
          
/*          private int x;
          private int y;*/ 
          private int step = 10;
          private boolean alive = true;
          
         /* public int positionX(){
              return this.x;
          }
          public int positionY(){
              return this.y;
          }*/
          public Bullet(int x, int y) {
           /*       this.x = x;
                  this.y = y;*/
                  super(x, y, 5, 10);
          }
  
          @Override
          public void draw(Graphics2D g) {
                  if(y > Y_TO_FADE)
                          g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                  else{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
                  }
                  g.setColor(Color.BLUE);
                  g.fillRect(x, y, width, height);
          }
  
          public void proceed(){
                  y -= step;
                  if(y < Y_TO_DIE){
                          alive = false;
                  }
          }
  
          public boolean isAlive(){
                  return alive;
          }
  }

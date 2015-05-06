package f2.spw;
import java.awt.Color;
import java.awt.Graphics2D;
public class HPbar extends Sprite{
    public int hp; 
    public HPbar(int x,int y, int hp){
        super(x,y,hp,2);
        this.hp = hp;
    }
    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillRect(x,y,this.hp,20);
    }
    public void procreed(int damage){
        this.hp -= damage;
    }
}
